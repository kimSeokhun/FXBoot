package com.flexink.sample.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flexink.common.domain.BaseService;
import com.flexink.common.file.service.CommonFileService;
import com.flexink.common.utils.CookieUtils;
import com.flexink.common.utils.HttpUtils;
import com.flexink.domain.file.CommonFile;
import com.flexink.domain.file.QCommonFile;
import com.flexink.domain.sample.Board;
import com.flexink.domain.sample.BoardSampleRepository;
import com.flexink.domain.sample.Comment;
import com.flexink.domain.sample.QBoard;
import com.flexink.domain.sample.QComment;
import com.flexink.vo.ParamsVo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardSampleService extends BaseService<Board, Long>{
	
	@Autowired
	CommonFileService commonFileService;
	
	@Autowired
	public BoardSampleService(BoardSampleRepository repository) {
		super(Board.class, repository);
	}
	
	QBoard qBoard = QBoard.board;
	QComment qComment = QComment.comment;
	
	/********************************************************************
	 * @메소드명	: getList
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 글목록 조회
	 ********************************************************************/
	public Page<Map<String, Object>> getList(ParamsVo params) {
		BooleanBuilder builder = new BooleanBuilder();
		if(StringUtils.isNotEmpty(params.getString("type"))) {
			builder.and(qBoard.type.eq(params.getString("type")));
		}
		
		/********************************************************************************************
		 *  ### 페이징 처리 ###
		 *	@ SpringData JPA
		 *	Page<Board> list = repository.findAll(builder, params.getPageable());	
		 ********************************************************************************************/
		// QueryDsl	(Page<Entity>)
		//Page<Board> list = (Page<Board>) readPage(query().from(qBoard).where(builder).orderBy(qBoard.id.desc()), params.getPageable());

		// Page<Map>
		Page<Map<String, Object>> mapList = readPageToMap(
				query().select(Projections.map(qBoard, qComment.id.count().as("commentCount")))
				.from(qBoard).leftJoin(qBoard.comments, qComment)
				.groupBy(qBoard.id)
				.where(builder)
				.orderBy(qBoard.id.desc())
				, params.getPageable());
		log.debug("Page<Map<String, Object>> : " + mapList.getContent());
		
		// Page<vo>
		//Page<BoardVo> voList = (Page<BoardVo>) readPage(query().select(Projections.bean(BoardVo.class, qBoard.title, qBoard.content, qBoard.comments.size().as("commentCount"))).from(qBoard).where(builder).orderBy(qBoard.id.desc()),  params.getPageable());
		//log.debug("Page<BoardVo> : " + voList.getContent());
		
		// List
		//List<Tuple> tuple = query().select(qBoard, qBoard.comments.size().as("size")).from(qBoard).where(builder).orderBy(qBoard.id.desc()).fetch();
		//List<Map<Expression<?>, ?>> expList = query().select(Projections.map(qBoard.title, qBoard.content, qBoard.comments.size().as("count"), qBoard.viewCount)).from(qBoard).where(builder).orderBy(qBoard.id.desc()).fetch();
		//List<BoardVo> l = query().select(Projections.bean(BoardVo.class, qBoard.title, qBoard.content, qBoard.comments.size().as("count"))).from(qBoard).where(builder).orderBy(qBoard.id.desc()).fetch();
		
		return mapList;
	}
	

	/********************************************************************
	 * @메소드명	: getArticle
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 글 상세 조회
	 ********************************************************************/
	@Transactional
	public Board getArticle(Board board) {
		/********************************************************************************************
		 * 	### 단건 조회 ###
		 *	@ SpringData JPA
		 *	Board article = repository.findOne(board.getId());
		 ********************************************************************************************/
		// QueryDsl
		Board article = query().from(qBoard).where(qBoard.id.eq(board.getId())).fetchOne();
		List<CommonFile> files = commonFileService.getList(article.getType(), String.valueOf(article.getId()));
		article.setFiles(files);
		
		// view count update
		String cookieView = CookieUtils.getCookieValue("view_count");
		String newCookieView = " | " + article.getType() + ":" + article.getId();
		if(StringUtils.indexOfIgnoreCase(cookieView, newCookieView) < 0) {
			article.setViewCount(article.getViewCount() + 1);
			CookieUtils.addCookie("view_count", cookieView + newCookieView);
		}
		
		
		return article;
	}
	
	/********************************************************************
	 * @메소드명	: saveArticle
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 글 저장 및 수정
	 ********************************************************************/
	@Transactional
	public void saveArticle(Board board) {
		if(board.getId() == null) {
			/********************************************************************************************
			 * 	### Entity 저장 ###
			 * 	@ EntityManager 직접 이용
			 *	getEntityManager().persist(board);
			 *
			 *	@ SpringData JPA
			 *	repository.save(board);
			 *
			 * 	@ QueryDsl
			 * 	Insert 메소드 없음
			 ********************************************************************************************/
			// EntityManager
			insert(board);
		} else {
			/********************************************************************************************
			 * 	#### Entity 수정 ###
			 *	@ SpringData JPA
			 *	repository.save(board);
			 *
			 *  @ EntityManager 직접 이용
			 *	getEntityManager().merge(board)  
			 ********************************************************************************************/
			// QueryDsl
			update(qBoard).where(qBoard.id.eq(board.getId()))
				.set(qBoard.title, board.getTitle())
				.set(qBoard.content, board.getContent())
				.set(qBoard.secret, board.getSecret()).execute();
		}
	}
	
	/********************************************************************
	 * @메소드명	: deleteArticle
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 글 삭제
	 ********************************************************************/
	@Transactional
	public void deleteArticle(Long boardId) {
		if(boardId != null) {
			/********************************************************************************************
			 * 	### Entity 삭제 ###
			 * 	@ EntityManager 직접 이용
			 *	getEntityManager().remove(board);
			 *
			 *	@ SpringData JPA
			 *	repository.delete(board.getId());
			 ********************************************************************************************/
			// QueryDsl
			delete(qBoard).where(qBoard.id.eq(boardId)).execute();
		}
	}
	
	
	/********************************************************************
	 * @메소드명	: saveComment
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 댓글 등록
	 ********************************************************************/
	@Transactional
	public Comment saveComment(ParamsVo params, Comment comment) {
		Board board = query().from(qBoard).where(qBoard.id.eq(params.getLong("boardId"))).fetchOne();
		comment.setBoard(board);
		insert(comment);
		
		return comment;
	}
	
	@Transactional
	public void deleteComment(Long commentId) {
		
		delete(qComment).where(qComment.id.eq(commentId)).execute();
	}

	
}
