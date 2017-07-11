package com.orrin.spring.boot.secure.service;

import com.orrin.spring.boot.secure.domain.SysAuthoritiesResources;
import org.springframework.data.domain.Page;

/**
 * @author Orrin on 2017/7/8.
 */
public interface SysAuthoritiesResourcesService {
	Page<SysAuthoritiesResources> findNoCriteria(Integer page, Integer size);
	Page<SysAuthoritiesResources> findCriteria(Integer page,Integer size,SysAuthoritiesResources sysAuthoritiesResources);

}
