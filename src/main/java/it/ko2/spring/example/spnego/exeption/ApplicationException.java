package it.ko2.spring.example.spnego.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Generic Error - This is bad!")
public class ApplicationException extends Exception {
}
