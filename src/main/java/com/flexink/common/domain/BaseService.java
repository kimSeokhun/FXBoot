package com.flexink.common.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.flexink.common.code.FxBootType;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;


public class BaseService<T, ID extends Serializable> extends QueryDslRepositorySupport{

	protected JpaQueryDslRepository<T, ID> repository;
	
	public BaseService(Class<T> clazz) {
		super(clazz);
	}

	public BaseService(Class<T> clazz, JpaQueryDslRepository<T, ID> repository) {
		super(clazz);
		this.repository = repository;
	}
	
	/********************************************************************
	 * @메소드명	: queryDsl
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: JPAQuery 객체 반환
	 * @return
	 * JPAQuery<T>
	 ********************************************************************/
	public JPAQuery<T> queryDsl() {
		JPAQuery<T> query = new JPAQuery<T>(getEntityManager());
		return query;
	}
	
	
	/********************************************************************
	 * @메소드명	: save
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 그리드용 CUD
	 * @param var
	 * @return
	 * S
	 ********************************************************************/
	@Transactional
    public <S extends T> S save(S var) {
        boolean deleted = false;

        if (var instanceof FxBootCrudModel) {
        	FxBootCrudModel crudModel = (FxBootCrudModel) var;

            if (crudModel.getDataStatus() == FxBootType.DataStatus.DELETED) {
                deleted = true;
            }
        }

        if (deleted) {
            repository.delete(var);
        } else {
            repository.save(var);
        }

        return var;
    }

    @Transactional
    public <S extends T> Collection<S> save(Collection<S> vars) {
    	for(S var : vars) {
    		this.save(var);
    	}
        return vars;
    }
    
	/********************************************************************
	 * @메소드명	: readPage
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: QueryDSL 페이징 처리용
	 * @param query
	 * @param pageable
	 * @return
	 * Page<T>
	 ********************************************************************/
	protected Page<T> readPage(JPAQuery<T> query, Pageable pageable) {
		if (pageable == null) {
			return readPage(query, new QPageRequest(0, Integer.MAX_VALUE));
		}
        long total = query.clone(super.getEntityManager()).fetchCount();
		JPQLQuery<T> pagedQuery = getQuerydsl().applyPagination(pageable, query);
		List<T> content = total > pageable.getOffset() ? pagedQuery.fetch() : Collections.<T>emptyList();
		return new PageImpl<>(content, pageable, total);
	}
		
}
