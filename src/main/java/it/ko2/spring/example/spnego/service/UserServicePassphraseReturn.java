package it.ko2.spring.example.spnego.service;

import it.ko2.spring.example.spnego.data.UserPassphraseRepository;
import it.ko2.spring.example.spnego.model.PageModel;
import it.ko2.spring.example.spnego.model.UserModel;
import it.ko2.spring.example.spnego.model.converters.UserInfoToUserModelConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service("UserPassphraseReturnService")
@Scope("request")
public class UserServicePassphraseReturn implements IUserService<PageModel<UserModel>, Pageable> {

  final UserPassphraseRepository userPassphraseRepository;
  final UserInfoToUserModelConverter userInfoToUserModelConverter;

  @Override
  public PageModel<UserModel> returnInfo(Pageable pageable) {
    Page<UserPassphraseRepository.UserPassphraseProjection> userInfoPage = userPassphraseRepository.findUserPassphrases(pageable);
    return new PageModel<>(
        userInfoPage.getPageable().getPageNumber(),
        userInfoPage.getTotalPages(),
        userInfoPage.getTotalElements(),
        userInfoPage.get().map(userInfoToUserModelConverter::convert)
            .collect(Collectors.toList())
    );
  }

}
