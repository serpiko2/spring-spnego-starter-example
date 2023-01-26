package it.ko2.spring.example.spnego.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
