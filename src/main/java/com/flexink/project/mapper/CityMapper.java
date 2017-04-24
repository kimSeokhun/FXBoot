package com.flexink.project.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CityMapper {

	HashMap<String, Object> findByReader();
}
