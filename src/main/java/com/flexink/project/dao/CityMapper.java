package com.flexink.project.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CityMapper {

	HashMap<String, Object> findByReader();
}
