package com.flexink.domain.file;

import org.springframework.stereotype.Repository;

import com.flexink.common.domain.JpaQueryDslRepository;

@Repository
public interface CommonFileRepository extends JpaQueryDslRepository<CommonFile, Long> {
	
	CommonFile findByTargetTypeAndTargetId(String targetType, String targetId);
	
}
