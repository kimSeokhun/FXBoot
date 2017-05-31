package com.flexink.sample.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.flexink.common.domain.BaseService;
import com.flexink.domain.board.BoardType;
import com.flexink.domain.board.QBoardType;
import com.flexink.domain.board.repository.BoardTypeRepository;
import com.flexink.vo.ParamsVo;
import com.querydsl.core.BooleanBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardTypeService extends BaseService<BoardType, String>{
	
	@Autowired
	public BoardTypeService(BoardTypeRepository repository) {
		super(BoardType.class, repository);
	}
	
	QBoardType qBoardType = QBoardType.boardType;
	
	
	public BoardType getBoardType(String type) {
		return getBoardType(new BoardType(type)); 
	}
	
	public BoardType getBoardType(BoardType boardType) {
		return query().from(qBoardType).where(qBoardType.type.eq(boardType.getType())).fetchOne();
				
	}
	
	public Page<BoardType> getBoardTypes(ParamsVo params) {
		BooleanBuilder builder = new BooleanBuilder();
		String filter = params.getString("filter");
		if(StringUtils.isNotBlank(filter)) {
			builder.and(qBoardType.type.eq(filter));
		}
		return (Page<BoardType>) readPage(query().from(qBoardType).where(builder), params.getPageable());
	}
	
	public void saveBoardType(List<BoardType> boardTypes) {
		save(boardTypes);
	}
	
	
	
	public void saveBt(BoardType boardType) {
		getEntityManager().persist(boardType);
		//insert(boardType);
	}
	
	public BoardType getBt(String id) {
		return repository.findOne(id);
				
	}

	
}
