package com.flexink.sample.service;

import java.util.List;

import javax.inject.Inject;
import javax.swing.SortOrder;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flexink.common.code.FxBootType;
import com.flexink.common.domain.BaseService;
import com.flexink.common.file.domain.QCommonFile;
import com.flexink.common.file.domain.RequestParams;
import com.flexink.sample.domain.CommonCode;
import com.flexink.sample.domain.CommonCodeId;
import com.flexink.sample.domain.CommonCodeRepository;
import com.flexink.sample.domain.QCommonCode;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;

import net.sf.ehcache.search.Direction;

@Service
public class CommonCodeService extends BaseService<CommonCode, CommonCodeId> {

    private CommonCodeRepository basicCodeRepository;
    
    private QCommonCode qCommonCode = QCommonCode.commonCode;

    @Inject
    public CommonCodeService(CommonCodeRepository basicCodeRepository) {
        super(CommonCode.class, basicCodeRepository);
        this.basicCodeRepository = basicCodeRepository;
    }

    public List<CommonCode> get(RequestParams requestParams) {
        String groupCd = requestParams.getString("groupCd", "");
        String useYn = requestParams.getString("useYn", "");

        String filter = requestParams.getString("filter");

        BooleanBuilder builder = new BooleanBuilder();

        if (StringUtils.isNotEmpty(groupCd)) {
            builder.and(qCommonCode.groupCd.eq(groupCd));
        }

        if (StringUtils.isNotEmpty(useYn)) {
            FxBootType.Used used = FxBootType.Used.valueOf(useYn);
            builder.and(qCommonCode.useYn.eq(used));
        }
        
        if (StringUtils.isNotEmpty(filter)) {
            builder.and(qCommonCode.groupCd.like("%"+filter+"%")
            		.or(qCommonCode.groupNm.like("%"+filter+"%"))
            		.or(qCommonCode.code.like("%"+filter+"%"))
            		.or(qCommonCode.name.like("%"+filter+"%")));
        }

        //Projections
        
        
        requestParams.addSort("groupCd", Sort.Direction.ASC);
        requestParams.addSort("sort", Sort.Direction.ASC);
        Page<CommonCode> list = readPage(query().from(qCommonCode).where(builder), requestParams.getPageable());
        
        return list.getContent();

    }

    @Transactional
    public void saveCommonCode(List<CommonCode> basicCodes) {
        save(basicCodes);
    }
}
