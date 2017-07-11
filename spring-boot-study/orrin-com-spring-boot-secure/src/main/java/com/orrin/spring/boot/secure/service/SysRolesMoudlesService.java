package com.orrin.spring.boot.secure.service;

import com.orrin.spring.boot.secure.domain.SysRolesMoudles;
import org.springframework.data.domain.Page;

/**
 * @author Orrin on 2017/7/8.
 */
public interface SysRolesMoudlesService {
	Page<SysRolesMoudles> findNoCriteria(Integer page, Integer size);
	Page<SysRolesMoudles> findCriteria(Integer page,Integer size,SysRolesMoudles sysRolesMoudles);
}
