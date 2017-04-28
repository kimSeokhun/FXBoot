package com.flexink.common.utils;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class PageImplWraper<T> extends PageImpl<T> {

	private static final long serialVersionUID = -6150024773797588991L;

	public PageImplWraper(List<T> content, Pageable pageable, long total) {
		super(content, pageable, total);
	}
	
	public PageImplWraper(List<T> content) {
		super(content);
	}
	 
	public int getBegin() {
		System.out.println("Get Number : " + getNumber());
		return Math.max(1, getNumber()+1 -5);
	}
	
	public int getEnd() {
		System.out.println("Get Begin : " + getBegin());
		System.out.println("Get Total Page : " + getTotalPages() );
		return Math.min(getBegin() + 10, getTotalPages());
	}
	

}
