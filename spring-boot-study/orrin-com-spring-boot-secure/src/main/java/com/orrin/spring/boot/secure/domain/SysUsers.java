package com.orrin.spring.boot.secure.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "SYS_USERS")
public class SysUsers implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  @Column(name = "USER_ID")
  private String userId;

  @Column(name = "USERNAME")
  private String username;

  @Column(name = "NAME")
  private String name;

  @Column(name = "PASSWORD")
  private String password;

  @Column(name = "DT_CREATE")
  private java.sql.Date dtCreate;

  @Column(name = "LAST_LOGIN")
  private java.sql.Date lastLogin;

  @Column(name = "DEADLINE")
  private java.sql.Date deadline;

  @Column(name = "LOGIN_IP")
  private String loginIp;

  @Column(name = "V_QZJGID")
  private String vqzjgid;

  @Column(name = "V_QZJGMC")
  private String vqzjgmc;

  @Column(name = "DEP_ID")
  private String depId;

  @Column(name = "DEP_NAME")
  private String depName;

  @Column(name = "ENABLED")
  private Long enabled;

  @Column(name = "ACCOUNT_NON_EXPIRED")
  private Long accountNonExpired;

  @Column(name = "ACCOUNT_NON_LOCKED")
  private Long accountNonLocked;

  @Column(name = "CREDENTIALS_NON_EXPIRED")
  private Long credentialsNonExpired;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Date getDtCreate() {
    return dtCreate;
  }

  public void setDtCreate(Date dtCreate) {
    this.dtCreate = dtCreate;
  }

  public Date getLastLogin() {
    return lastLogin;
  }

  public void setLastLogin(Date lastLogin) {
    this.lastLogin = lastLogin;
  }

  public Date getDeadline() {
    return deadline;
  }

  public void setDeadline(Date deadline) {
    this.deadline = deadline;
  }

  public String getLoginIp() {
    return loginIp;
  }

  public void setLoginIp(String loginIp) {
    this.loginIp = loginIp;
  }

  public String getVqzjgid() {
    return vqzjgid;
  }

  public void setVqzjgid(String vqzjgid) {
    this.vqzjgid = vqzjgid;
  }

  public String getVqzjgmc() {
    return vqzjgmc;
  }

  public void setVqzjgmc(String vqzjgmc) {
    this.vqzjgmc = vqzjgmc;
  }

  public String getDepId() {
    return depId;
  }

  public void setDepId(String depId) {
    this.depId = depId;
  }

  public String getDepName() {
    return depName;
  }

  public void setDepName(String depName) {
    this.depName = depName;
  }

  public Long getEnabled() {
    return enabled;
  }

  public void setEnabled(Long enabled) {
    this.enabled = enabled;
  }

  public Long getAccountNonExpired() {
    return accountNonExpired;
  }

  public void setAccountNonExpired(Long accountNonExpired) {
    this.accountNonExpired = accountNonExpired;
  }

  public Long getAccountNonLocked() {
    return accountNonLocked;
  }

  public void setAccountNonLocked(Long accountNonLocked) {
    this.accountNonLocked = accountNonLocked;
  }

  public Long getCredentialsNonExpired() {
    return credentialsNonExpired;
  }

  public void setCredentialsNonExpired(Long credentialsNonExpired) {
    this.credentialsNonExpired = credentialsNonExpired;
  }
}
