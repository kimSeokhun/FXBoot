package com.flexink.sample.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flexink.common.code.FxBootType;
import com.flexink.common.domain.BaseService;
import com.flexink.common.utils.EgovMap;
import com.flexink.common.utils.RequestParams;
import com.flexink.domain.code.CommonCode;
import com.flexink.domain.code.CommonCodeId;
import com.flexink.domain.code.CommonCodeRepository;
import com.flexink.domain.code.QCommonCode;
import com.flexink.sample.mapper.CommonCodeMapper;
import com.querydsl.core.BooleanBuilder;

@Service
public class CommonCodeService extends BaseService<CommonCode, CommonCodeId> {

    private CommonCodeRepository basicCodeRepository;
    
    private QCommonCode qCommonCode = QCommonCode.commonCode;
    
    @Autowired
    private CommonCodeMapper commonCodeMapper;

    @Autowired
    public CommonCodeService(CommonCodeRepository basicCodeRepository) {
        super(CommonCode.class, basicCodeRepository);
        this.basicCodeRepository = basicCodeRepository;
    }

    
    public Page<CommonCode> get(RequestParams<CommonCode> requestParams) {
        String groupCd = requestParams.getString("groupCd", "");
        String useYn = requestParams.getString("useYn", "");

        String filter = requestParams.getString("filter");

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qCommonCode.useYn.eq(FxBootType.Used.valueOf("Y")));
        
        if (StringUtils.isNotEmpty(groupCd)) {
            builder.and(qCommonCode.groupCd.eq(groupCd));
        }
        
        if (StringUtils.isNotEmpty(filter)) {
            builder.and(qCommonCode.groupCd.like("%"+filter+"%")
            		.or(qCommonCode.groupNm.like("%"+filter+"%"))
            		.or(qCommonCode.code.like("%"+filter+"%"))
            		.or(qCommonCode.name.like("%"+filter+"%")));
        }
        //Projections
        //requestParams.addSort("groupCd", Sort.Direction.ASC);
        //requestParams.addSort("sort", Sort.Direction.ASC);
        
        Page<CommonCode> list = readPage(query().from(qCommonCode).where(builder), requestParams.getPageable());
        
        // Mybatis 테스트
        List<EgovMap> list2 = commonCodeMapper.readPage(requestParams);
        System.out.println(list2);
        
        return list;
    }

    @Transactional
    public void saveCommonCode(List<CommonCode> basicCodes) {
        save(basicCodes);
    }
}
