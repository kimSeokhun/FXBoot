package com.flexink.sample.web;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flexink.common.utils.RequestParams;
import com.flexink.domain.code.CommonCode;
import com.flexink.sample.service.CommonCodeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/api/v1/commonCodes")
public class CommonCodeController {

    @Inject
    private CommonCodeService basicCodeService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
	public Page<CommonCode> list(
			@PageableDefault(sort = {"groupCd", "sort"}, direction = Direction.DESC, size = 15) Pageable pageable,
			RequestParams<CommonCode> requestParams) {
    	
    	// 페이지 객체 수신시 파라미터
    	//address?pageNumber=0&pageSize=10&sort=gorupCd,desc
    	
    	// 페이지 객체 직접 생성시
    	//PageRequest pq = new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "groupCd"));
    	
    	log.debug("수신된 Pageable PageNumber {}", pageable.getPageNumber());
    	log.debug("수신된 Pageable PageSize {}", pageable.getPageSize());
    	System.out.println(pageable.getSort());
    	
    	/*System.out.println(requestParams.getString("filter"));
    	System.out.println(requestParams.getString("pageNumber"));
    	System.out.println(requestParams.getString("pageSize"));*/
        //List<CommonCode> basicCodes = basicCodeService.get(requestParams);
    	Page<CommonCode> basicCodes = basicCodeService.get(requestParams);

        return basicCodes;
    }

    @RequestMapping(method = {RequestMethod.PUT}, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public boolean save(@Valid @NotNull @RequestBody List<CommonCode> basicCodes) {
    	System.out.println(basicCodes);
        basicCodeService.saveCommonCode(basicCodes);
        return true;
    }

    /*@RequestMapping(value = "/getAllByMap", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public Map<String, List<CommonCode>> getAllByMap() {
        return CommonCodeUtils.getAllByMap();
    }*/
}
