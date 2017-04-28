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
	
	public Page<Board> getList(ParamsVo params) {
		BooleanBuilder builder = new BooleanBuilder();
		if(StringUtils.isNotEmpty(params.getString("type"))) {
			builder.and(qBoard.type.eq(params.getString("type")));
		}
		Page<Board> list = readPage(queryDsl().from(qBoard).where(builder), params.getPageable());
		return list;
	}
	
	@Transactional
	public Board getArticle(Board board) {
		Board article = queryDsl().from(qBoard).where(qBoard.id.eq(board.getId())).fetchOne();
		article.setViewCount(article.getViewCount() + 1);
		return article;
	}
	
	@Transactional
	public void saveArticle(Board board, ParamsVo vo) {
		repository.save(board);
		
	}

	
}
