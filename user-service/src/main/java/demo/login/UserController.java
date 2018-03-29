package demo.login;

import java.security.Principal;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {
    @RequestMapping(value="/user", method = RequestMethod.GET, 
    produces = "application/json; charset=utf-8")
    public Principal user(Principal user) {
        return user;
    }
}
