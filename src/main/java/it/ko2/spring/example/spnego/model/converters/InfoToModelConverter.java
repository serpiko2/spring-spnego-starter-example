package it.ko2.spring.example.spnego.model.converters;

@FunctionalInterface
public interface InfoToModelConverter<B, A> {
  B convert(A input);

}
