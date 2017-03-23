
package com.flexink.config.web.security.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginUserGrantedAuthorityRepository extends JpaRepository<LoginUserGrantedAuthority, Long> {
    
}
