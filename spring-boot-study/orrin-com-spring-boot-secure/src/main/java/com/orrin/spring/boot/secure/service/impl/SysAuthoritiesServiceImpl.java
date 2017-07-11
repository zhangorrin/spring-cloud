package com.orrin.spring.boot.secure.service.impl;

import com.orrin.spring.boot.secure.domain.SysAuthorities;
import com.orrin.spring.boot.secure.service.SysAuthoritiesService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author Orrin on 2017/7/8.
 */
@Service("sysAuthoritiesService")
public class SysAuthoritiesServiceImpl implements SysAuthoritiesService {
	@Override
	public Page<SysAuthorities> findNoCriteria(Integer page, Integer size) {
		return null;
	}

	@Override
	public Page<SysAuthorities> findCriteria(Integer page, Integer size, SysAuthorities sysAuthorities) {
		return null;
	}
}
