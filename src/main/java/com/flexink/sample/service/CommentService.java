package com.flexink.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flexink.common.code.FxBootType;
import com.flexink.common.domain.BaseService;
import com.flexink.config.web.security.user.UserDetailsHelper;
import com.flexink.domain.board.Board;
import com.flexink.domain.board.Comment;
import com.flexink.domain.board.QBoard;
import com.flexink.domain.board.QComment;
import com.flexink.domain.board.repository.CommentSampleRepository;
import com.flexink.vo.ParamsVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentService extends BaseService<Comment, Long>{
	
	@Autowired
	BoardService boardSampleService;
	
	@Autowired
	public CommentService(CommentSampleRepository repository) {
		super(Comment.class, repository);
	}
	
	QBoard qBoard = QBoard.board;
	QComment qComment = QComment.comment;
	

	public List<Comment> getComments(Board board) {
		List<Comment> comments = query().from(qComment).where(qComment.board.eq(board).and(qComment.delYn.eq(FxBootType.Deleted.N))).fetch();
		return comments;
	}
	
	/********************************************************************
	 * @메소드명	: saveComment
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 댓글 등록
	 ********************************************************************/
	@Transactional
	public Comment saveComment(ParamsVo params, Comment comment) {
		
		Board board = boardSampleService.getArticle(new Board(params.getLong("boardId")));
		comment.setBoard(board);
		insert(comment);
		
		return comment;
	}
	
	/********************************************************************
	 * @메소드명	: updateComment
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 댓글 수정
	 ********************************************************************/
	@Transactional
	public void updateComment(Comment commentVo) {
		Comment comment = query().from(qComment).where(qComment.id.eq(commentVo.getId())).fetchOne();
		if(comment.getCreatedBy().equals(UserDetailsHelper.getLoginUserDetails().getUsername()) || UserDetailsHelper.containsAuthority("ROLE_ADMIN", "ROLE_SYSTEM")) {
			update(qComment).set(qComment.content, commentVo.getContent()).where(qComment.id.eq(comment.getId())).execute();
		}
	}
	
	/********************************************************************
	 * @메소드명	: deleteComment
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 댓글 삭제
	 ********************************************************************/
	@Transactional
	public void deleteComment(Long commentId) {
		Comment comment = query().from(qComment).where(qComment.id.eq(commentId)).fetchOne();
		if(comment.getCreatedBy().equals(UserDetailsHelper.getLoginUserDetails().getUsername()) || UserDetailsHelper.containsAuthority("ROLE_ADMIN", "ROLE_SYSTEM")) {
			delete(qComment).where(qComment.id.eq(commentId)).execute();
		}
	}

	
}
