package com.orrin.spring.boot.secure.dao;

import com.orrin.spring.boot.secure.domain.SysAuthorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Orrin on 2017/7/8.
 */
@Repository("sysAuthoritiesRepository")
public interface SysAuthoritiesRepository extends JpaRepository<SysAuthorities, String>, JpaSpecificationExecutor<SysAuthorities> {
	@Query(value = "SELECT DISTINCT a.* " +
			" FROM sys_roles_authorities ra, sys_users_roles ur, sys_authorities a" +
			" WHERE ra.authority_id = a.authority_id" +
			" AND ur.role_id = ra.role_id" +
			" AND ur.user_id = :userId", nativeQuery = true)
	List<SysAuthorities> findAuthorityByUserId(@Param("userId")String userId);
}
