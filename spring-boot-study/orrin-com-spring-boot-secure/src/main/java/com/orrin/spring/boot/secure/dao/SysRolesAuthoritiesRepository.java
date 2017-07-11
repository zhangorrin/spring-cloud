package com.orrin.spring.boot.secure.dao;

import com.orrin.spring.boot.secure.domain.SysRolesAuthorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author Orrin on 2017/7/8.
 */
@Repository("sysRolesAuthoritiesRepository")
public interface SysRolesAuthoritiesRepository extends JpaRepository<SysRolesAuthorities, String>, JpaSpecificationExecutor<SysRolesAuthorities> {

}
