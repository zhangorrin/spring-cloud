package com.orrin.spring.boot.secure.dao;

import com.orrin.spring.boot.secure.domain.SysUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author Orrin on 2017/7/8.
 */
@Repository("sysUsersRepository")
public interface SysUsersRepository extends JpaRepository<SysUsers, String>, JpaSpecificationExecutor<SysUsers> {
	SysUsers findByUsername(String username);
}
