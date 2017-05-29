package com.flexink.sample.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flexink.common.domain.BaseService;
import com.flexink.domain.menu.Menu;
import com.flexink.domain.menu.QMenu;
import com.flexink.domain.menu.repository.MenuRepository;
import com.flexink.vo.ParamsVo;
import com.querydsl.core.BooleanBuilder;

@Service
public class MenuService extends BaseService<Menu, Long>{

	@Autowired
	public MenuService(MenuRepository repository) {
		super(Menu.class, repository);
	}
	
	QMenu qMenu = QMenu.menu;
	
	/*public List<Menu> getMenus(ParamsVo params) {
		Menu menu = new Menu();
		if(StringUtils.isNotBlank(params.getString("menuGrpCd"))) {
			menu.setMenuGrpCd(params.getString("menuGrpCd"));
		}
		return getMenus(menu);
	}*/
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
		
		return query().from(qMenu).where(builder).orderBy(qMenu.level.asc(), qMenu.sort.asc()).fetch();
		
	}
	
	public List<Menu> getMenus(Menu menu) {
		ParamsVo params = new ParamsVo();
		params.put("menuGrpCd", menu.getMenuGrpCd());
		params.put("useYn", menu.getUseYn().toString());
		
		return getMenus(params);
	}
	
	
	
	
	@Transactional
    public void saveMenus(List<Menu> menus) {
        save(menus);
    }

}
