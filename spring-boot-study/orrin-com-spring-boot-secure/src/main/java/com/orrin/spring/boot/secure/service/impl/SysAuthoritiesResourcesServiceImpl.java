package com.orrin.spring.boot.secure.service.impl;

import com.orrin.spring.boot.secure.dao.SysAuthoritiesResourcesRepository;
import com.orrin.spring.boot.secure.domain.SysAuthoritiesResources;
import com.orrin.spring.boot.secure.service.SysAuthoritiesResourcesService;
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
@Service("sysAuthoritiesResourcesService")
public class SysAuthoritiesResourcesServiceImpl implements SysAuthoritiesResourcesService {

	@Autowired
	private SysAuthoritiesResourcesRepository sysAuthoritiesResourcesRepository;

	@Override
	public Page<SysAuthoritiesResources> findNoCriteria(Integer page, Integer size) {
		Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
		return sysAuthoritiesResourcesRepository.findAll(pageable);
	}

	@Override
	public Page<SysAuthoritiesResources> findCriteria(Integer page, Integer size, SysAuthoritiesResources sysAuthoritiesResources) {

		Pageable pageable = new PageRequest(page, size);
		Page<SysAuthoritiesResources> resultPage = sysAuthoritiesResourcesRepository.findAll(new Specification<SysAuthoritiesResources>() {
			@Override
			public Predicate toPredicate(Root<SysAuthoritiesResources> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (!StringUtils.isEmpty(sysAuthoritiesResources.getId())) {
					list.add(criteriaBuilder.equal(root.get("id").as(String.class), sysAuthoritiesResources.getId()));
				}

				if (!StringUtils.isEmpty(sysAuthoritiesResources.getResourceId())) {
					list.add(criteriaBuilder.equal(root.get("resource_id").as(String.class), sysAuthoritiesResources.getResourceId()));
				}
				if (!StringUtils.isEmpty(sysAuthoritiesResources.getAuthorityId())) {
					list.add(criteriaBuilder.equal(root.get("authority_id").as(String.class), sysAuthoritiesResources.getAuthorityId()));
				}


				Predicate[] p = new Predicate[list.size()];
				return criteriaBuilder.and(list.toArray(p));
			}
		}, pageable);

		return resultPage;
	}
}
