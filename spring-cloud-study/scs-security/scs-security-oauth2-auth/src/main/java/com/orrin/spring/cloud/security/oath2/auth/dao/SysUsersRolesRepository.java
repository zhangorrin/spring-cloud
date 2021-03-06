package com.orrin.spring.cloud.security.oath2.auth.dao;

import com.orrin.spring.cloud.security.oath2.auth.domain.SysUsersRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Orrin on 2017/7/8.
 */
@Repository("sysUsersRolesRepository")
public interface SysUsersRolesRepository extends JpaRepository<SysUsersRoles, String>, JpaSpecificationExecutor<SysUsersRoles> {
	List<SysUsersRoles> findDistinctByUserId(String userId);
}
