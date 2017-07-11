package com.orrin.spring.boot.secure.service;

import com.orrin.spring.boot.secure.domain.SysAuthorities;
import org.springframework.data.domain.Page;

/**
 * @author Orrin on 2017/7/8.
 */
public interface SysAuthoritiesService {

	Page<SysAuthorities> findNoCriteria(Integer page, Integer size);
	Page<SysAuthorities> findCriteria(Integer page,Integer size,SysAuthorities sysAuthorities);
}
