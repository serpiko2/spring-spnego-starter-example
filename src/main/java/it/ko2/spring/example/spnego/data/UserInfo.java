package it.ko2.spring.example.spnego.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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