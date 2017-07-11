package com.orrin.spring.boot.secure.service.impl;

import com.orrin.spring.boot.secure.domain.SysRolesAuthorities;
import com.orrin.spring.boot.secure.service.SysRolesAuthoritiesService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author Orrin on 2017/7/8.
 */
@Service("sysRolesAuthoritiesService")
public class SysRolesAuthoritiesServiceImpl implements SysRolesAuthoritiesService {
	@Override
	public Page<SysRolesAuthorities> findNoCriteria(Integer page, Integer size) {
		return null;
	}

	@Override
	public Page<SysRolesAuthorities> findCriteria(Integer page, Integer size, SysRolesAuthorities sysRolesAuthorities) {
		return null;
	}
}
