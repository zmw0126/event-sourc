package demo.api.v1;

import java.security.Principal;
import java.util.Optional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.user.User;

@RestController
@RequestMapping(path = "/v1")
public class UserControllerV1 {

    private UserServiceV1 userService;

    @Autowired
    public UserControllerV1(UserServiceV1 userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/me")
    @HystrixCommand(groupKey = "helloGroup", fallbackMethod = "fallBackCall",
            commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500"),
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "1"),
                //@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "5000")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "1"),
                    @HystrixProperty(name = "maxQueueSize", value = "1"),
                    @HystrixProperty(name = "allowMaximumSizeToDivergeFromCoreSize", value = "true"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "1"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "10"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "5000")
    })
    public ResponseEntity me(Principal principal) {
        User user = null;
        LogFactory.getLog(getClass()).info("before principal");
        if(principal != null) {
            LogFactory.getLog(getClass()).info("in principal: " + principal.getName());
            user = userService.getUserByUsername(principal.getName());
        }

        return Optional.ofNullable(user)
                .map(a -> new ResponseEntity<User>(a, HttpStatus.OK))
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    public String fallBackCall() {
        return "FAILED UserControllerV1.me CALL! - FALLING BACK";
    }
}
