package it.ko2.spring.example.spnego.model.converters;

import it.ko2.spring.example.spnego.data.UserPassphraseRepository;
import it.ko2.spring.example.spnego.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserInfoToUserModelConverter implements InfoToModelConverter<UserModel,
    UserPassphraseRepository.UserPassphraseProjection> {

  @Override
  public UserModel convert(UserPassphraseRepository.UserPassphraseProjection input) {
    return new UserModel(
        input.getUserId().toString(),
        input.getUsername(),
        new UserModel.UserPassphraseModel(input.getPassphraseId().toString(),
            input.getPassphrase()));
  }
}
