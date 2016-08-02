package main.com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/7/28 0028.
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleError(HttpServletRequest req, Exception exception) {
            return new ResponseEntity("异常:"+exception.getMessage(), HttpStatus.FORBIDDEN);
    }
}
