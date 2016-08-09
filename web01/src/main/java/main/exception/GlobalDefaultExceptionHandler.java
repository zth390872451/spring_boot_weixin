package main.exception;

import main.annotation.PermissionAccessException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/7/28 0028.
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity handleError(HttpServletRequest req, UnauthorizedException exception) {
            return new ResponseEntity("未授权", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = PermissionAccessException.class)
    @ResponseBody
    public String handleError(HttpServletRequest req, PermissionAccessException exception) {
//        return new ResponseEntity("权限访问禁止", HttpStatus.FORBIDDEN);
        return "权限访问禁止";
    }
}
