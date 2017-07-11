package com.orrin.spring.boot.secure.service;

import com.orrin.spring.boot.secure.domain.SysResources;
import org.springframework.data.domain.Page;

/**
 * @author Orrin on 2017/7/8.
 */
public interface SysResourcesService {
	Page<SysResources> findNoCriteria(Integer page, Integer size);
	Page<SysResources> findCriteria(Integer page,Integer size,SysResources sysResources);

}
