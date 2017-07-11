package com.orrin.spring.boot.secure.service.impl;

import com.orrin.spring.boot.secure.dao.PersistentLoginsRepository;
import com.orrin.spring.boot.secure.domain.PersistentLogins;
import com.orrin.spring.boot.secure.service.PersistentLoginsService;
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
@Service("persistentLoginsService")
public class PersistentLoginsServiceImpl implements PersistentLoginsService {

	@Autowired
	private PersistentLoginsRepository persistentLoginsRepository;

	@Override
	public Page<PersistentLogins> findNoCriteria(Integer page, Integer size) {
		Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "last_used");
		return persistentLoginsRepository.findAll(pageable);
	}

	@Override
	public Page<PersistentLogins> findCriteria(Integer page, Integer size, PersistentLogins persistentLogins) {


		Pageable pageable = new PageRequest(page, size);
		Page<PersistentLogins> persistentLoginsPage = persistentLoginsRepository.findAll(new Specification<PersistentLogins>() {
			@Override
			public Predicate toPredicate(Root<PersistentLogins> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (!StringUtils.isEmpty(persistentLogins.getUsername())) {
					list.add(criteriaBuilder.equal(root.get("username").as(String.class), persistentLogins.getUsername()));
				}
				if (!StringUtils.isEmpty(persistentLogins.getSeries())) {
					list.add(criteriaBuilder.equal(root.get("series").as(String.class), persistentLogins.getSeries()));
				}
				if (!StringUtils.isEmpty(persistentLogins.getToken())) {
					list.add(criteriaBuilder.equal(root.get("token").as(String.class), persistentLogins.getToken()));
				}
				Predicate[] p = new Predicate[list.size()];
				return criteriaBuilder.and(list.toArray(p));
			}
		}, pageable);

		return persistentLoginsPage;

	}
}
