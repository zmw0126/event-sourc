package demo.login;

import java.security.Principal;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {

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
    @RequestMapping(value="/user")
    public Principal user(Principal user) {
        return user;
    }

    public String fallBackCall() {
        return "FAILED UserController CALL! - FALLING BACK";
    }
}
