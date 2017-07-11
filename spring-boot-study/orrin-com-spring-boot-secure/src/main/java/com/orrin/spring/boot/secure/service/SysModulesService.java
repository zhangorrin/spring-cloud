package com.orrin.spring.boot.secure.service;

import com.orrin.spring.boot.secure.domain.SysModules;
import org.springframework.data.domain.Page;

/**
 * @author Orrin on 2017/7/8.
 */
public interface SysModulesService {

	Page<SysModules> findNoCriteria(Integer page, Integer size);
	Page<SysModules> findCriteria(Integer page,Integer size,SysModules sysModules);
}
