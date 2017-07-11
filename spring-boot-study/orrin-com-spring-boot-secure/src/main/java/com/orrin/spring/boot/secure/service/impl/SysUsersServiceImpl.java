package com.orrin.spring.boot.secure.service.impl;

import com.orrin.spring.boot.secure.dao.SysUsersRepository;
import com.orrin.spring.boot.secure.domain.SysUsers;
import com.orrin.spring.boot.secure.service.SysUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
@Service("sysUsersService")
public class SysUsersServiceImpl implements SysUsersService {

	@Autowired
	private SysUsersRepository sysUsersRepository;

	@Override
	public Page<SysUsers> findSysUsersNoCriteria(Integer page, Integer size) {
		Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "userId");
		Page<SysUsers> sysUsersPage = sysUsersRepository.findAll(pageable);
		return sysUsersPage;
	}

	@Override
	public Page<SysUsers> findSysUsersCriteria(Integer page, Integer size, SysUsers sysUsers) {

		Pageable pageable = new PageRequest(page, size);
		Page<SysUsers> resultPage = sysUsersRepository.findAll(new Specification<SysUsers>() {
			@Override
			public Predicate toPredicate(Root<SysUsers> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (!StringUtils.isEmpty(sysUsers.getUserId())) {
					list.add(criteriaBuilder.equal(root.get("user_id").as(String.class), sysUsers.getUserId()));
				}
				if (!StringUtils.isEmpty(sysUsers.getUsername())) {
					list.add(criteriaBuilder.equal(root.get("username").as(String.class), sysUsers.getUsername()));
				}
				if (!StringUtils.isEmpty(sysUsers.getName())) {
					list.add(criteriaBuilder.equal(root.get("name").as(String.class), sysUsers.getName()));
				}
				if (!StringUtils.isEmpty(sysUsers.getEnabled())) {
					list.add(criteriaBuilder.equal(root.get("enabled").as(Long.class), sysUsers.getEnabled()));
				}
				if (!StringUtils.isEmpty(sysUsers.getAccountNonExpired())) {
					list.add(criteriaBuilder.equal(root.get("account_non_expired").as(Long.class), sysUsers.getAccountNonExpired()));
				}
				if (!StringUtils.isEmpty(sysUsers.getAccountNonLocked())) {
					list.add(criteriaBuilder.equal(root.get("account_non_locked").as(Long.class), sysUsers.getAccountNonLocked()));
				}
				if (!StringUtils.isEmpty(sysUsers.getCredentialsNonExpired())) {
					list.add(criteriaBuilder.equal(root.get("credentials_non_expired").as(Long.class), sysUsers.getCredentialsNonExpired()));
				}


				Predicate[] p = new Predicate[list.size()];
				return criteriaBuilder.and(list.toArray(p));
			}
		}, pageable);

		return resultPage;
	}

	@Override
	public SysUsers findByUsername(String username) {
		return sysUsersRepository.findByUsername(username);
	}
}
