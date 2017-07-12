package com.orrin.spring.boot.secure.core.secure;

import com.orrin.spring.boot.secure.dao.SysAuthoritiesRepository;
import com.orrin.spring.boot.secure.domain.SysAuthorities;
import com.orrin.spring.boot.secure.domain.SysUsers;
import com.orrin.spring.boot.secure.service.SysUsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * @author Orrin on 2017/7/10.
 */
public class CustomUserService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(CustomUserService.class);

	@Autowired
	private SysUsersService sysUsersService;

	@Autowired
	private SysAuthoritiesRepository sysAuthoritiesRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		SysUsers sysUser = sysUsersService.findByUsername(username);
		if (sysUser == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}

		logger.info("username:" + username);

		List<SysAuthorities> sysAuthorities = sysAuthoritiesRepository.findAuthorityByUserId(sysUser.getUserId());

		CurrentSessionUser currentSessionUser = CurrentSessionUser.createCurrentSessionUser(sysUser, sysAuthorities);

		if(!currentSessionUser.isAccountNonLocked()){
			throw new LockedException("账号被锁定");
		}

		if(!currentSessionUser.isEnabled()){
			throw new DisabledException("账号不可用");
		}

		if(!currentSessionUser.isCredentialsNonExpired()){
			throw new BadCredentialsException("证书过期");
		}

		if(!currentSessionUser.isAccountNonExpired()){
			throw new AccountExpiredException("账户过期");
		}

		return currentSessionUser;
	}
}
