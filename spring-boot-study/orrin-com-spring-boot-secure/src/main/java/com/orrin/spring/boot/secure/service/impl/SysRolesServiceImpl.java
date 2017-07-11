package com.orrin.spring.boot.secure.service.impl;

import com.orrin.spring.boot.secure.dao.SysRolesRepository;
import com.orrin.spring.boot.secure.domain.SysRoles;
import com.orrin.spring.boot.secure.service.SysRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Orrin on 2017/7/8.
 */
@Service("sysRolesService")
public class SysRolesServiceImpl implements SysRolesService {

	@Autowired
	private SysRolesRepository sysRolesRepository;

	@Override
	public Page<SysRoles> findNoCriteria(Integer page, Integer size) {
		Pageable pageable = new PageRequest(page, size);
		return sysRolesRepository.findAll(pageable);
	}

	@Override
	public Page<SysRoles> findCriteria(Integer page, Integer size, SysRoles sysRoles) {
		Pageable pageable = new PageRequest(page, size);
		Page<SysRoles> resultPage = sysRolesRepository.findAll(new Specification<SysRoles>() {
			@Override
			public Predicate toPredicate(Root<SysRoles> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (!StringUtils.isEmpty(sysRoles.getRoleId())) {
					list.add(criteriaBuilder.equal(root.get("roleId").as(String.class), sysRoles.getRoleId()));
				}
				if (!StringUtils.isEmpty(sysRoles.getRoleName())) {
					list.add(criteriaBuilder.equal(root.get("roleName").as(String.class), sysRoles.getRoleName()));
				}
				if (!StringUtils.isEmpty(sysRoles.getEnable())) {
					list.add(criteriaBuilder.equal(root.get("enable").as(String.class), sysRoles.getEnable()));
				}

				Predicate[] p = new Predicate[list.size()];
				return criteriaBuilder.and(list.toArray(p));
			}
		}, pageable);

		return resultPage;
	}
}
