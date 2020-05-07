package com.example.springbootdemo.handler;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

@ControllerAdvice
@Component
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseBody
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handle(Exception exception, HttpServletRequest request) {
//        if(exception instanceof ConstraintViolationException){
//            ConstraintViolationException exs = (ConstraintViolationException) exception;
//
//            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
//            for (ConstraintViolation<?> item : violations) {
//                System.out.println(item.getMessage());
//                return item.getMessage();
//            }
//        }
        String message = exception.getMessage();
        if (message.indexOf(": ") > 0) {
            String[] split = message.split(": ");
            return split[1];
        }
        return message;
        /**
         * return "forward:/error";(可以增加异常属性交给spring处理，返回错误页面或json)
         * 需要配合继承DefaultErrorAttributes,重写getErrorAttributes方法
         */

    }
}

