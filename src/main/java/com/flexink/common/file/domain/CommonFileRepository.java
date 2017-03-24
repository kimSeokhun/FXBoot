package com.flexink.common.file.domain;

import java.com.flexink.common.file.domain.QCommonFile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface CommonFileRepository extends JpaRepository<CommonFile, Long>, QueryDslPredicateExecutor<CommonFile> {
	
	QCommonFile qCommonFile = QCommonFile.commonFile;
	
	CommonFile findByTargetTypeAndTargetId(String targetType, String targetId);
	
	
	
}
