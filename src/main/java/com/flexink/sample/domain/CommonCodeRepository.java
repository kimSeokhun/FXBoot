package com.flexink.sample.domain;

import org.springframework.stereotype.Repository;

import com.flexink.common.domain.JpaQueryDslRepository;

@Repository
public interface CommonCodeRepository extends JpaQueryDslRepository<CommonCode, CommonCodeId> {

}
