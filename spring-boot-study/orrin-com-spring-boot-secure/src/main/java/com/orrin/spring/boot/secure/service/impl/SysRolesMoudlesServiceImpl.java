package com.orrin.spring.boot.secure.service.impl;

import com.orrin.spring.boot.secure.domain.SysRolesMoudles;
import com.orrin.spring.boot.secure.service.SysRolesMoudlesService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author Orrin on 2017/7/8.
 */
@Service("sysRolesMoudlesService")
public class SysRolesMoudlesServiceImpl implements SysRolesMoudlesService {
	@Override
	public Page<SysRolesMoudles> findNoCriteria(Integer page, Integer size) {
		return null;
	}

	@Override
	public Page<SysRolesMoudles> findCriteria(Integer page, Integer size, SysRolesMoudles sysRolesMoudles) {
		return null;
	}
}
