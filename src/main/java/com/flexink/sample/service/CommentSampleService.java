package com.flexink.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flexink.common.domain.BaseService;
import com.flexink.config.web.security.user.UserDetailsHelper;
import com.flexink.domain.sample.Board;
import com.flexink.domain.sample.Comment;
import com.flexink.domain.sample.CommentSampleRepository;
import com.flexink.domain.sample.QBoard;
import com.flexink.domain.sample.QComment;
import com.flexink.vo.ParamsVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentSampleService extends BaseService<Comment, Long>{
	
	@Autowired
	BoardSampleService boardSampleService;
	
	@Autowired
	public CommentSampleService(CommentSampleRepository repository) {
		super(Comment.class, repository);
	}
	
	QBoard qBoard = QBoard.board;
	QComment qComment = QComment.comment;
	

	public List<Comment> getComments(Board board) {
		List<Comment> comments = query().from(qComment).where(qComment.board.eq(board).and(qComment.delYn.eq(Comment.DelYn.N))).fetch();
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
	public void updateComment(Comment comment) {
		update(qComment).set(qComment.content, comment.getContent()).where(qComment.id.eq(comment.getId())).execute();
	}
	
	/********************************************************************
	 * @메소드명	: deleteComment
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 댓글 삭제
	 ********************************************************************/
	@Transactional
	public void deleteComment(Long commentId) {
		Comment comment = query().from(qComment).where(qComment.id.eq(commentId)).fetchOne();
		if(comment.getCreatedBy().equals(UserDetailsHelper.getLoginUserDetails().getUsername()) || UserDetailsHelper.getAuthorities().contains("ROLE_ADMIN")) {
			delete(qComment).where(qComment.id.eq(commentId)).execute();
		}
	}

	
}
