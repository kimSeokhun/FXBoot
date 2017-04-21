package com.flexink.sample.web;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flexink.common.file.domain.RequestParams;
import com.flexink.sample.domain.CommonCode;
import com.flexink.sample.service.CommonCodeService;

@Controller
@RequestMapping(value = "/api/v1/commonCodes")
public class CommonCodeController {

    @Inject
    private CommonCodeService basicCodeService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CommonCode> list(Pageable pageable, RequestParams<CommonCode> requestParams) {
    	System.out.println(pageable.getPageNumber());
    	System.out.println(pageable.getPageSize());
    	/*System.out.println(requestParams.getString("filter"));
    	System.out.println(requestParams.getString("pageNumber"));
    	System.out.println(requestParams.getString("pageSize"));*/
        List<CommonCode> basicCodes = basicCodeService.get(requestParams);
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
