package com.flexink.common.domain;

import java.io.Serializable;
import java.time.Clock;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flexink.config.web.security.user.LoginUserDetails;
import com.flexink.config.web.security.user.UserDetailsHelper;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
@DynamicInsert
@DynamicUpdate
public abstract class BaseJpaModel<PK extends Serializable> implements Persistable<PK>, Serializable {
	
	@Override
    @JsonIgnore
    public boolean isNew() {
        return null == getId();
    }

    @Column(name = "CREATED_AT", updatable = false)
    protected Instant createdAt;

    @Column(name = "CREATED_BY", updatable = false)
    protected String createdBy;

    @Column(name = "UPDATED_AT")
    protected Instant updatedAt;

    @Column(name = "UPDATED_BY")
    protected String updatedBy;

    /*@Transient
    protected User createdUser;

    @Transient
    protected User modifiedUser;*/

    @PrePersist
    protected void onPersist() {
        this.createdBy = this.updatedBy = getCurrentLoginUserCd();
        this.createdAt = this.updatedAt = Instant.now(Clock.systemUTC());
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedBy = getCurrentLoginUserCd();
        this.updatedAt = Instant.now(Clock.systemUTC());
    }

    @PostLoad
    protected void onPostLoad() {
    }
    
    @Transient
    private String getCurrentLoginUserCd() {
    	LoginUserDetails user = null;
    	Object obj = UserDetailsHelper.getAuthenticatedUser();
    	if(obj != null && !obj.equals("anonymousUser")) {
    		user = (LoginUserDetails) obj;
    	}
    	return user == null ? "system" : user.getUsername();
    }
}
