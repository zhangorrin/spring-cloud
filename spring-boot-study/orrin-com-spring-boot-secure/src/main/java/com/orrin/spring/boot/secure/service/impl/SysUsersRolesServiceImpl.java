package com.orrin.spring.boot.secure.service.impl;

import com.orrin.spring.boot.secure.dao.SysUsersRolesRepository;
import com.orrin.spring.boot.secure.domain.SysUsersRoles;
import com.orrin.spring.boot.secure.service.SysUsersRolesService;
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
@Service("sysUsersRolesService")
public class SysUsersRolesServiceImpl implements SysUsersRolesService {

	@Autowired
	private SysUsersRolesRepository sysUsersRolesRepository;

	@Override
	public Page<SysUsersRoles> findNoCriteria(Integer page, Integer size) {
		Pageable pageable = new PageRequest(page, size);
		return sysUsersRolesRepository.findAll(pageable);
	}

	@Override
	public Page<SysUsersRoles> findCriteria(Integer page, Integer size, SysUsersRoles sysUsersRoles) {
		Pageable pageable = new PageRequest(page, size);
		Page<SysUsersRoles> resultPage = sysUsersRolesRepository.findAll(new Specification<SysUsersRoles>() {
			@Override
			public Predicate toPredicate(Root<SysUsersRoles> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (!StringUtils.isEmpty(sysUsersRoles.getUserId())) {
					list.add(criteriaBuilder.equal(root.get("userId").as(String.class), sysUsersRoles.getUserId()));
				}
				if (!StringUtils.isEmpty(sysUsersRoles.getId())) {
					list.add(criteriaBuilder.equal(root.get("id").as(String.class), sysUsersRoles.getId()));
				}
				if (!StringUtils.isEmpty(sysUsersRoles.getRoleId())) {
					list.add(criteriaBuilder.equal(root.get("roleId").as(String.class), sysUsersRoles.getRoleId()));
				}

				Predicate[] p = new Predicate[list.size()];
				return criteriaBuilder.and(list.toArray(p));
			}
		}, pageable);

		return resultPage;
	}

	@Override
	public List<SysUsersRoles> findDistinctByUserId(String userId) {
		return sysUsersRolesRepository.findDistinctByUserId(userId);
	}
}
