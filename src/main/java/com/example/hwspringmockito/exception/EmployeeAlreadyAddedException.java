package com.example.hwspringmockito.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONTINUE)
public class EmployeeAlreadyAddedException extends RuntimeException {

}
