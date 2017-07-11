package com.orrin.spring.boot.secure.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "SYS_AUTHORITIES")
public class SysAuthorities implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  @Column(name = "AUTHORITY_ID")
  private String authorityId;

  @Column(name = "AUTHORITY_MARK")
  private String authorityMark;

  @Column(name = "AUTHORITY_NAME")
  private String authority_name;

  @Column(name = "AUTHORITY_DESC")
  private String authorityDesc;

  @Column(name = "MESSAGE")
  private String message;

  @Column(name = "ENABLE")
  private Long enable;

  @Column(name = "ISSYS")
  private Long issys;

  @Column(name = "MODULE_ID")
  private String moduleId;

  public String getAuthorityId() {
    return authorityId;
  }

  public void setAuthorityId(String authorityId) {
    this.authorityId = authorityId;
  }

  public String getAuthorityMark() {
    return authorityMark;
  }

  public void setAuthorityMark(String authorityMark) {
    this.authorityMark = authorityMark;
  }

  public String getAuthority_name() {
    return authority_name;
  }

  public void setAuthority_name(String authority_name) {
    this.authority_name = authority_name;
  }

  public String getAuthorityDesc() {
    return authorityDesc;
  }

  public void setAuthorityDesc(String authorityDesc) {
    this.authorityDesc = authorityDesc;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Long getEnable() {
    return enable;
  }

  public void setEnable(Long enable) {
    this.enable = enable;
  }

  public Long getIssys() {
    return issys;
  }

  public void setIssys(Long issys) {
    this.issys = issys;
  }

  public String getModuleId() {
    return moduleId;
  }

  public void setModuleId(String moduleId) {
    this.moduleId = moduleId;
  }
}
