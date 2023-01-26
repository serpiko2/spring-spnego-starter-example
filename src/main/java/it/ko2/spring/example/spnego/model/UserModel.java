package it.ko2.spring.example.spnego.model;

public record UserModel(String id,
                        String username,
                        UserPassphraseModel specialPassphrase) {

  public record UserPassphraseModel(String id, String passphrase) {

  }
}
