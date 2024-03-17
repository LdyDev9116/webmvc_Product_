package com.ssg.springex3.controller.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

// @ControllerAdvice 어노테이션은 이 클래스가 전역적인 예외 처리자임을 나타냅니다.
// 즉, 애플리케이션의 모든 컨트롤러에서 발생하는 예외를 이 클래스에서 처리할 수 있습니다.
@ControllerAdvice
@Log4j2  // Lombok 라이브러리를 사용하여 로그를 쉽게 기록할 수 있게 해주는 어노테이션입니다.
public class CommonExceptionAdvice {

    // @ResponseBody 어노테이션은 메서드에서 반환하는 값이 응답 본문에 직접 쓰여야 함을 나타냅니다.
    // 즉, 해당 메서드가 HTTP 응답 본문으로 직접 데이터를 반환해야 함을 의미합니다.
    @ResponseBody

    // @ExceptionHandler 어노테이션은 특정 예외가 발생했을 때 이 메서드가 그 예외를 처리해야 함을 나타냅니다.
    // 여기서는 NumberFormatException 예외를 처리합니다.
    @ExceptionHandler(Exception.class)
    public String exceptionCommon(Exception exception){
        log.error("-----------------------------------");  // 에러 로그 시작 표시
        log.error(exception.getMessage());  // 발생한 NumberFormatException의 메시지를 로그로 기록합니다.
        StringBuffer buffer = new StringBuffer("<ul>");
        buffer.append("<li>" + exception.getMessage()+ "<li>");
        Arrays.stream(exception.getStackTrace()).forEach(stackTraceElement -> {
            buffer.append("<li>" + stackTraceElement + "<li>");
        });
        buffer.append("/<ul>");

        return buffer.toString();  // 클라이언트에게 "NUMBER FORMAT EXCEPTION" 문자열을 반환합니다.
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(){
        return "custom404";
    }

}