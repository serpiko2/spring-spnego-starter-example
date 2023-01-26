package it.ko2.spring.example.spnego.controller;

import it.ko2.spring.example.spnego.data.UserPassphraseRepository;
import it.ko2.spring.example.spnego.model.PageModel;
import it.ko2.spring.example.spnego.model.UserModel;
import it.ko2.spring.example.spnego.model.converters.UserInfoToUserModelConverter;
import it.ko2.spring.example.spnego.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/passphrase")
@RequiredArgsConstructor
public class PassphraseController {

  @GetMapping("/")
  public PageModel<UserModel> getAllPassphrases(int page, int size, Sort of){
    return new IUserService<PageModel<UserModel>, Pageable>() {
      @Autowired
      UserPassphraseRepository userPassphraseRepository;

      @Autowired
      UserInfoToUserModelConverter userInfoToUserModelConverter;

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
    .returnInfo(PageRequest.of(page, size, of));
  }

  @GetMapping("/{id}")
  public String getPassphrase(@PathVariable String id) {
    return id;
  }

}
