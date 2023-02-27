package top.flobby.oa.service.exception;

/**
 * @author : Flobby
 * @program : my-oa
 * @description :
 * @create : 2023-02-27 14:53
 **/

public class LoginException extends RuntimeException{
    public LoginException(String message) {
        super(message);
    }
}
