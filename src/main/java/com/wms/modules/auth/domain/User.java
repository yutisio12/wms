package com.wms.modules.auth.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

  @Id
  private UUID id;

  private String username;
  private String password;

  private String twoFaSecret; // nullable
}