package com.orrin.spring.boot.secure.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "PERSISTENT_LOGINS")
public class PersistentLogins implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "USERNAME")
  private String username;

  @Id
  @GeneratedValue
  @Column(name = "SERIES")
  private String series;

  @Column(name = "TOKEN")
  private String token;

  @Column(name = "LAST_USED")
  private java.sql.Timestamp lastUsed;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getSeries() {
    return series;
  }

  public void setSeries(String series) {
    this.series = series;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Timestamp getLastUsed() {
    return lastUsed;
  }

  public void setLastUsed(Timestamp lastUsed) {
    this.lastUsed = lastUsed;
  }
}
