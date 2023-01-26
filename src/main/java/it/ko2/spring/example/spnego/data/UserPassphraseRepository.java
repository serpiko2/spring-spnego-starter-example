package it.ko2.spring.example.spnego.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPassphraseRepository extends JpaRepository<UserPassphrase, Long> {
  @Query("select ui.id as userId, ui.username, up.id as passphraseId, up.passphrase from UserInfo ui, " +
      "UserPassphrase up where ui.username = ?#{ principal?.username }")
  Page<UserPassphraseProjection> findUserPassphrases(Pageable pageable);

  interface UserPassphraseProjection {
    Long getUserId();
    Long getPassphraseId();
    String getUsername();
    String getPassphrase();
  }
}