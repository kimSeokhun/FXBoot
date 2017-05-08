package com.flexink.sample.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flexink.common.domain.BaseService;
import com.flexink.domain.sample.Board;
import com.flexink.domain.sample.BoardSampleRepository;
import com.flexink.domain.sample.QBoard;
import com.flexink.vo.ParamsVo;
import com.querydsl.core.BooleanBuilder;

@Service
public class BoardSampleService extends BaseService<Board, Long>{

	QBoard qBoard = QBoard.board;
	
	@Autowired
	public BoardSampleService(BoardSampleRepository repository) {
		super(Board.class, repository);
	}
	
	/********************************************************************
	 * @메소드명	: getList
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 글목록 조회
	 ********************************************************************/
	public Page<Board> getList(ParamsVo params) {
		BooleanBuilder builder = new BooleanBuilder();
		if(StringUtils.isNotEmpty(params.getString("type"))) {
			builder.and(qBoard.type.eq(params.getString("type")));
		}
		
		/********************************************************************************************
		 *  ### 페이징 처리 ###
		 *	@ SpringData JPA
		 *	Page<Board> list = repository.findAll(builder, params.getPageable());	
		 ********************************************************************************************/
		// QueryDsl
		Page<Board> list = readPage(queryDsl().from(qBoard).where(builder).orderBy(qBoard.id.desc()), params.getPageable());
		
		return list;
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
		Board article = queryDsl().from(qBoard).where(qBoard.id.eq(board.getId())).fetchOne();
		article.setViewCount(article.getViewCount() + 1);
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
			 * 	@ QueryDsl
			 * 	Insert 메소드 없음
			 ********************************************************************************************/
			// SpringData JPA
			repository.save(board);
		} else {
			/********************************************************************************************
			 * 	#### Entity 수정 ###
			 * 	@ QueryDsl
			 *	new JPAUpdateClause(getEntityManager(), qBoard).where(qBoard.id.eq(board.getId()))
					.set(qBoard.title, board.getTitle())
					.set(qBoard.content, board.getContent())
					.execute(); 
			 * 
			 *	@ SpringData JPA
			 *	repository.save(board);
			 *
			 *  @ EntityManager 직접 이용
			 *	getEntityManager().merge(board)  
			 ********************************************************************************************/
			// QueryDsl
			Board selectBoard = queryDsl().from(qBoard).where(qBoard.id.eq(board.getId())).fetchOne();
			selectBoard.setTitle(board.getTitle());
			selectBoard.setContent(board.getContent());
			selectBoard.setSecret(board.getSecret());
			
		}
	}
	
	/********************************************************************
	 * @메소드명	: deleteArticle
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 글 삭제
	 ********************************************************************/
	@Transactional
	public void deleteArticle(Board board) {
		if(board.getId() != null) {
			/********************************************************************************************
			 * 	### Entity 삭제 ###
			 * 	@ EntityManager 직접 이용
			 *	getEntityManager().remove(board);
			 *
			 *	@ QueryDsl
			 *	new JPADeleteClause(getEntityManager(), qBoard).where(qBoard.id.eq(board.getId())).execute();
			 ********************************************************************************************/
			// SpringData JPA
			repository.delete(board.getId());
			
		}
	}

	
}
