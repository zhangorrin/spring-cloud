package com.orrin.spring.boot.secure.service.impl;

import com.orrin.spring.boot.secure.domain.SysModules;
import com.orrin.spring.boot.secure.service.SysModulesService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author Orrin on 2017/7/8.
 */
@Service("sysModulesService")
public class SysModulesServiceImpl implements SysModulesService {
	@Override
	public Page<SysModules> findNoCriteria(Integer page, Integer size) {
		return null;
	}

	@Override
	public Page<SysModules> findCriteria(Integer page, Integer size, SysModules sysModules) {
		return null;
	}
}
