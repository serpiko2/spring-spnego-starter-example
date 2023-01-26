package it.ko2.spring.example.spnego.controller;

import it.ko2.spring.example.spnego.model.PageModel;
import it.ko2.spring.example.spnego.model.UserModel;
import it.ko2.spring.example.spnego.service.UserServicePassphraseReturn;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operation")
@RequiredArgsConstructor
public class OperationController {

  final UserServicePassphraseReturn userServicePassphraseReturn;

  @GetMapping("/")
  public PageModel<UserModel> getUsers(int page, int size, Sort of){
    return userServicePassphraseReturn.returnInfo(PageRequest.of(page, size, of));
  }

  @GetMapping("/{id}")
  @Secured({"ADMIN", "USER"})
  public String getPassphrase(@PathVariable String id) {
    return id;
  }

}
