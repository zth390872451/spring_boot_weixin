
package zth.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2016/10/31.
 */

@RestController
@RequestMapping("/refreshScope")
@RefreshScope
public class RestScopeController {
    @Value("${refreshTest:Hello default}")
    private String refreshTest;
    @RequestMapping("/refresh")
    public String getMessage(){
        return refreshTest;
    }
}

