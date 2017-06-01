package com.flexink.sample.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.jpa.QueryHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flexink.common.domain.BaseService;
import com.flexink.domain.menu.Menu;
import com.flexink.domain.menu.QMenu;
import com.flexink.domain.menu.repository.MenuRepository;
import com.flexink.vo.ParamsVo;
import com.querydsl.core.BooleanBuilder;

@Service
public class MenuCodeService extends BaseService<Menu, Long>{

	@Autowired
	public MenuCodeService(MenuRepository repository) {
		super(Menu.class, repository);
	}
	
	QMenu qMenu = QMenu.menu;
	
	/********************************************************************
	 * @메소드명	: getMenus
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 메뉴코드 리스트 조회
	 ********************************************************************/
	public List<Menu> getMenus(ParamsVo params) {
		BooleanBuilder builder = new BooleanBuilder();
		if(StringUtils.isNotBlank(params.getString("menuGrpCd"))) {
			builder.and(qMenu.menuGrpCd.eq(params.getString("menuGrpCd")));
		}
		if(StringUtils.isNotBlank(params.getString("useYn"))) {
			builder.and(qMenu.useYn.eq(Menu.UseYn.valueOf(params.getString("useYn"))));
		}
		String filter = params.getString("filter");
		if(StringUtils.isNotBlank(filter)) {
			builder.and(qMenu.menuNm.likeIgnoreCase(filter));
			builder.and(qMenu.progUrl.likeIgnoreCase(filter));
		}
		return query().from(qMenu).where(builder).orderBy(qMenu.level.asc(), qMenu.sort.asc()).setHint(QueryHints.HINT_CACHEABLE, true).fetch();
		
	}
	
	public List<Menu> getMenus(Menu menu) {
		ParamsVo params = new ParamsVo();
		params.put("menuGrpCd", menu.getMenuGrpCd());
		params.put("useYn", menu.getUseYn().toString());
		
		return getMenus(params);
	}
	
	
	/********************************************************************
	 * @메소드명	: getTagLibMenus
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: taglib용 메뉴코드 리스트 조회
	 ********************************************************************/
	@Cacheable(cacheNames="menuCode", key="#menu.menuGrpCd")
	public List<Menu> getTagLibMenus(Menu menu) {
		QMenu sMenu = new QMenu("sMenu");
		List<Menu> list = query().distinct().from(qMenu)
				.leftJoin(qMenu.children, sMenu)
				.fetchJoin()
				.where(qMenu.menuGrpCd.eq(menu.getMenuGrpCd())
				.and(qMenu.useYn.eq(Menu.UseYn.Y)
				.and(qMenu.level.eq(0))))
				.orderBy(qMenu.level.asc(), qMenu.sort.asc(), sMenu.level.asc(), sMenu.sort.asc())
				.fetch();
		return list;
	}
	
	
	
	/********************************************************************
	 * @메소드명	: saveMenus
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 그리드용 메뉴코드 CUD
	 ********************************************************************/
	@Transactional
	@CacheEvict(cacheNames="menuCode", allEntries=true)
    public void saveMenus(List<Menu> menus) {
        save(menus);
    }

}
