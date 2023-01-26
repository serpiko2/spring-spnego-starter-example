package it.ko2.spring.example.spnego.service;


import it.ko2.spring.example.spnego.exeption.ApplicationException;

@FunctionalInterface
public interface IUserService<B, A> {
  B returnInfo(A input) throws ApplicationException;

}
