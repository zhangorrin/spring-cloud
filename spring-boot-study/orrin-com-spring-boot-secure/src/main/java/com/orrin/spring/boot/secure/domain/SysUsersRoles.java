package com.orrin.spring.boot.secure.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "SYS_USERS_ROLES")
public class SysUsersRoles implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue
  @Column(name = "ID")
  private String id;

  @Column(name = "ROLE_ID")
  private String roleId;

  @Column(name = "USER_ID")
  private String userId;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getRoleId() {
    return roleId;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
