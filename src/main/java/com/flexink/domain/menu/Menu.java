package com.flexink.domain.menu;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flexink.common.domain.BaseJpaModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "MENU")
public class Menu extends BaseJpaModel<Long> {

    @Id
    @Column(name = "MENU_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "MENU_GRP_CD", length = 100)
    private String menuGrpCd;

    @Column(name = "MENU_NM", length = 100)
    private String menuNm;

    @Column(name = "PARENT_ID", precision = 19)
    private Long parentId;

    @Column(name = "LEVEL", precision = 10)
    @JsonProperty("__depth__")
    private Integer level;

    @Column(name = "SORT", precision = 10)
    private Integer sort;

    @Column(name = "PROG_URL")
    private String progUrl;
    
    @Column(name="USE_YN")
    @Enumerated(EnumType.STRING)
    private UseYn useYn = UseYn.Y;

    @Transient
    private List<Menu> children = new ArrayList<>();
    
    @Transient
    private String pId;
    
    @JsonProperty("pId")
    public Long getPid() {
        return parentId;
    }
    
    @Transient
    private boolean hidden;
    /*@ManyToOne
    @JoinColumn(name = "PROG_CD", referencedColumnName = "PROG_CD", insertable = false, updatable = false)
    private Program program;*/


    public void addChildren(Menu menu) {
        children.add(menu);
    }

    public enum UseYn {
    	Y, N
    }
}
