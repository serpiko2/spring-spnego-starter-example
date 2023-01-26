package it.ko2.spring.example.spnego;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

  @GetMapping("/admin")
  public String adminProtected(){
    return "hello admin";
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
