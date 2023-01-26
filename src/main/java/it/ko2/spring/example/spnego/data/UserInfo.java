package it.ko2.spring.example.spnego.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "user_info")
@Getter
@Setter
public class UserInfo {

  @Id
  Long id;
  String username;

  @OneToMany
  List<UserPassphrase> userPassphraseList;
}