package com.orrin.spring.boot.secure.service.impl;

import com.orrin.spring.boot.secure.domain.SysResources;
import com.orrin.spring.boot.secure.service.SysResourcesService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author Orrin on 2017/7/8.
 */
@Service("sysResourcesService")
public class SysResourcesServiceImpl implements SysResourcesService {

	@Override
	public Page<SysResources> findNoCriteria(Integer page, Integer size) {
		return null;
	}

	@Override
	public Page<SysResources> findCriteria(Integer page, Integer size, SysResources sysResources) {
		return null;
	}
}
