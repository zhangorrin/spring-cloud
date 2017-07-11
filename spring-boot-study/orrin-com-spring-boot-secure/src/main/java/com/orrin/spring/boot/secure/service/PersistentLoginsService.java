package com.orrin.spring.boot.secure.service;

import com.orrin.spring.boot.secure.domain.PersistentLogins;
import org.springframework.data.domain.Page;

/**
 * @author Orrin on 2017/7/8.
 */
public interface PersistentLoginsService {
	Page<PersistentLogins> findNoCriteria(Integer page, Integer size);
	Page<PersistentLogins> findCriteria(Integer page,Integer size,PersistentLogins persistentLogins);
}
