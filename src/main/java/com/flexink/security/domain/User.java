package com.flexink.security.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "USER", uniqueConstraints = @UniqueConstraint(columnNames = "USERNAME"))
@Data
@ToString(exclude = "userRoles")
public class User {

	public User() {
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialsNonExpired = true;
		this.enabled = true;
	}
	
	@Size(min=2, max=20)
	@NotNull
	@Id
	@Column(name="USERNAME", unique=true, length=50)
	private String username;

	@Size(min=4)
	@NotNull
	@Column(name="PASSWORD", length=200)
	private String password;
	
	@Transient
	private String retypePassword;
	
	@Column(name="EMAIL", length=100)
	private String email;
	
	@Column(name="PHONE", length=20)
	private String phone;

	@Column(name="ACCOUNTNONEXPIRED")
	private boolean accountNonExpired; 			// 계정 만료 여부 (true:만료되지 않음, false: 만료)

	@Column(name="ACCOUNTNONLOCKED")
	private boolean accountNonLocked; 			// 계정 잠김 여부 (true:잠기지 않음, false: 잠김)

	@Column(name="CREDENTIALSNONEXPIRED")
	private boolean credentialsNonExpired;		// 패스워드 만료 여부 (true:만료되지 않음, false:만료)

	@Column(name="ENABLED")
	private boolean enabled; 					// 계정 사용 여부 (true:사용가능, false:사용불가능)

	@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
	private List<Authorities> userRoles = new ArrayList<>();


}