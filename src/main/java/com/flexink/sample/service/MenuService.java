package com.flexink.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flexink.common.domain.BaseService;
import com.flexink.domain.menu.Menu;
import com.flexink.domain.menu.QMenu;
import com.flexink.domain.menu.repository.MenuRepository;
import com.flexink.vo.ParamsVo;

@Service
public class MenuService extends BaseService<Menu, Long>{

	@Autowired
	public MenuService(MenuRepository repository) {
		super(Menu.class, repository);
	}
	
	QMenu qMenu = QMenu.menu;
	
	public List<Menu> getMenus(ParamsVo params) {
		
		return query().from(qMenu).fetch();
	}
	
	@Transactional
    public void saveMenus(List<Menu> menus) {
        save(menus);
    }

}
