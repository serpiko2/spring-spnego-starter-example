package it.ko2.spring.example.spnego.service;

import it.ko2.spring.example.spnego.data.UserInfoRepository;
import it.ko2.spring.example.spnego.exeption.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceUsernameReturn implements IUserService<String, String>{

  final UserInfoRepository userInfoRepository;
  @Override
  public String returnInfo(String currentPrincipalName) throws EntityNotFoundException {
    return userInfoRepository.findByUsername(currentPrincipalName)
        .orElseThrow(EntityNotFoundException::new)
        .getUsername();
  }

}
