package it.ko2.spring.example.spnego.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
  @Secured({"ADMIN", "USER"})
  Optional<UserInfo> findByUsername(String username);
}