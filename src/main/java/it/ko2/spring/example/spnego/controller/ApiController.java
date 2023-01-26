package it.ko2.spring.example.spnego.controller;

import it.ko2.spring.example.spnego.data.UserInfoRepository;
import it.ko2.spring.example.spnego.exeption.ApplicationException;
import it.ko2.spring.example.spnego.exeption.EntityNotFoundException;
import it.ko2.spring.example.spnego.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

  final IUserService<String, String> userService;

  public ApiController() {
    this.userService = new IUserService<>() {
      @Autowired
      UserInfoRepository userInfoRepository;
      @Override
      public String returnInfo(String currentPrincipalName) throws EntityNotFoundException {
        return userInfoRepository.findByUsername(currentPrincipalName)
            .orElseThrow(EntityNotFoundException::new)
            .getUsername();
      }

    };
  }

  @GetMapping("/admin")
  public String adminProtected() throws ApplicationException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentPrincipalName = authentication.getName();
    return userService.returnInfo(currentPrincipalName);
  }

  @GetMapping("/user")
  public String userProtected() {
    return "hello user";
  }

  @GetMapping("/unprotected")
  public String unprotected() {
    return "hello guest";
  }

}
