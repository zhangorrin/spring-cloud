package com.orrin.spring.boot.secure.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "SYS_ROLES_AUTHORITIES")
public class SysRolesAuthorities implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue
  @Column(name = "ID")
  private String id;

  @Column(name = "AUTHORITY_ID")
  private String authorityId;

  @Column(name = "ROLE_ID")
  private String roleId;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getAuthorityId() {
    return authorityId;
  }

  public void setAuthorityId(String authorityId) {
    this.authorityId = authorityId;
  }

  public String getRoleId() {
    return roleId;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }
}
