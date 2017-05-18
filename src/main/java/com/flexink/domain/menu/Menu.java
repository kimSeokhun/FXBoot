package com.flexink.domain.menu;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.flexink.common.domain.BaseJpaModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@DynamicInsert
@DynamicUpdate
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "MENU_M")
public class Menu extends BaseJpaModel<Long> implements Cloneable {

    @Id
    @Column(name = "MENU_ID", precision = 20, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    @Column(name = "MENU_GRP_CD", length = 100)
    private String menuGrpCd;

    @Column(name = "MENU_NM", length = 100)
    private String menuNm;

    /*@Column(name = "MULTI_LANGUAGE", length = 100)
    @Convert(converter = JsonNodeConverter.class)
    private JsonNode multiLanguageJson;*/

    @Column(name = "PARENT_ID", precision = 19)
    private Long parentId;

    @Column(name = "LEVEL", precision = 10)
    private Integer level;

    @Column(name = "SORT", precision = 10)
    private Integer sort;

    @Column(name = "PROG_CD", length = 50)
    private String progCd;

    @Transient
    private boolean open = false;

    @Transient
    private List<Menu> children = new ArrayList<>();

    /*@ManyToOne
    @JoinColumn(name = "PROG_CD", referencedColumnName = "PROG_CD", insertable = false, updatable = false)
    private Program program;*/

    @Override
    public Long getId() {
        return menuId;
    }

    @JsonProperty("name")
    public String label() {
        return menuNm;
    }

    @JsonProperty("id")
    public Long id() {
        return menuId;
    }

    @JsonProperty("open")
    public boolean isOpen() {
        return open;
    }

    public void addChildren(Menu menu) {
        children.add(menu);
    }

    /*public Menu clone() {
        try {
            Menu menu = (Menu) super.clone();
            menu.setChildren(new ArrayList<>());
            return menu;
        } catch (Exception e) {
            // ignore
        }
        return null;
    }*/

    public static Menu of(Long id, String menuGrpCd, String menuNm, JsonNode languageJson, Long parentId, int level, int sort, String progCd) {
        Menu menu = new Menu();
        menu.setMenuId(id);
        menu.setMenuGrpCd(menuGrpCd);
        menu.setMenuNm(menuNm);
        //menu.setMultiLanguageJson(languageJson);
        menu.setParentId(parentId);
        menu.setLevel(level);
        menu.setSort(sort);
        menu.setProgCd(progCd);
        return menu;
    }

    /*@JsonIgnore
    public String getLocalizedMenuName(HttpServletRequest request) {
        Locale locale = RequestUtils.getLocale(request);

        if (getMultiLanguageJson() != null) {
            JsonNode jsonNode = getMultiLanguageJson().findPath(locale.getLanguage());

            if (jsonNode != null) {
                return jsonNode.asText();
            }
        }
        return menuNm;
    }*/
}
