package com.orrin.spring.boot.secure.core.secure;

import com.orrin.spring.boot.secure.domain.SysAuthorities;
import com.orrin.spring.boot.secure.domain.SysUsers;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Orrin on 2017/7/10.
 */
public class CurrentSessionUser implements UserDetails {


	private String userId;

	private String username;

	private String name;

	private String password;

	private String depId;

	private String depName;

	private Long enabled;

	private Long accountNonExpired;

	private Long accountNonLocked;

	private Long credentialsNonExpired;

	private List<SysAuthorities> sysAuthorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auths = new ArrayList<>();
		List<SysAuthorities> authoritiesTemp = this.getSysAuthorities();
		Set<String> nonRepetitionSet = new HashSet<>();
		for (SysAuthorities sas : authoritiesTemp) {
			if(!nonRepetitionSet.contains(sas.getAuthorityMark())){
				nonRepetitionSet.add(sas.getAuthorityMark());
				auths.add(new SimpleGrantedAuthority(sas.getAuthorityMark()));
			}
		}
		return auths;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired.equals(Long.valueOf(0)) ? false : true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked.equals(Long.valueOf(0)) ? false : true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired.equals(Long.valueOf(0)) ? false : true;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled.equals(Long.valueOf(0)) ? false : true;
	}
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepId() {
		return depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public Long getEnabled() {
		return enabled;
	}

	public void setEnabled(Long enabled) {
		this.enabled = enabled;
	}

	public Long getAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(Long accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public Long getAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(Long accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public Long getCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public List<SysAuthorities> getSysAuthorities() {
		return sysAuthorities;
	}

	public void setSysAuthorities(List<SysAuthorities> sysAuthorities) {
		this.sysAuthorities = sysAuthorities;
	}

	public void setCredentialsNonExpired(Long credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public static CurrentSessionUser createCurrentSessionUser(SysUsers sysUsers, List<SysAuthorities> authorities){
		CurrentSessionUser sessionUser = new CurrentSessionUser();
		BeanUtils.copyProperties(sysUsers,sessionUser);
		sessionUser.setSysAuthorities(authorities);
		return sessionUser;
	}
}
