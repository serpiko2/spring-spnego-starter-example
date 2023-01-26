package it.ko2.spring.example.spnego.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_psh")
@Getter
@Setter
public class UserPassphrase {
  @Id
  Long id;
  String passphrase;

  @ManyToOne
  UserInfo userInfo;

}
