package com.flexink.common.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.flexink.common.code.FxBootType;
import com.flexink.domain.menu.Menu;

import lombok.Data;

@Data
public abstract class FxBootCrudModel {

    @Transient
    public FxBootType.DataStatus getDataStatus() {
        if (__deleted__) {
            return FxBootType.DataStatus.DELETED;
        }

        if (__created__) {
            return FxBootType.DataStatus.CREATED;
        }

        if (__modified__) {
            return FxBootType.DataStatus.MODIFIED;
        }

        return FxBootType.DataStatus.ORIGIN;
    }

    @Transient
    @JsonProperty("__deleted__")
    protected boolean __deleted__;

    @Transient
    @JsonProperty("__created__")
    protected boolean __created__;

    @Transient
    @JsonProperty("__modified__")
    protected boolean __modified__;

    @Transient
    @JsonIgnore
    public boolean isDeleted() {
        return __deleted__;
    }

    @Transient
    @JsonIgnore
    public boolean isCreated() {
        return __created__;
    }

    @Transient
    @JsonIgnore
    public boolean isModified() {
        return __modified__;
    }
    
    /*@Transient
    @JsonProperty("__children__")
    protected List<Menu> __children__ = new ArrayList<>();
    
    @Transient
    @JsonProperty("__depth__")
    protected int __depth__;
    
    @Transient
    @JsonProperty("__hp__")
    protected String __hp__;
    
    @Transient
    @JsonProperty("__hs__")
    protected String __hs__;
    
    @Transient
    @JsonProperty("__index")
    protected int __index;
    
    @Transient
    @JsonProperty("__origin_index__")
    protected int __origin_index__;
    
    @Transient
    @JsonProperty("dataStatus")
    protected String dataStatus;
    
    @Transient
    @JsonProperty("hidden")
    protected boolean hidden;
    
    @Transient
    @JsonProperty("__selected__")
    protected boolean __selected__;*/
    
    
}
