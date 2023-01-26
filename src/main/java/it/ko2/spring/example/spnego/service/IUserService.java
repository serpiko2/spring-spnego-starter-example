package it.ko2.spring.example.spnego.service;


import it.ko2.spring.example.spnego.exeption.ApplicationException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@FunctionalInterface
@Scope("prototype")
@Component
public interface IUserService<B, A> {
  B returnInfo(A input) throws ApplicationException;

}
