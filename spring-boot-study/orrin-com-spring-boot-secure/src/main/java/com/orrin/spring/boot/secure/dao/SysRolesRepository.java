package com.orrin.spring.boot.secure.dao;

import com.orrin.spring.boot.secure.domain.SysRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author Orrin on 2017/7/8.
 */
@Repository("sysRolesRepository")
public interface SysRolesRepository extends JpaRepository<SysRoles, String>, JpaSpecificationExecutor<SysRoles> {

}
