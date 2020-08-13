package eu.davidemartorana.cloud.gcp.demo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class ControllerAPI {

    @GetMapping("/hello")
    public String getHelloMsg(@RequestParam final String name) {
        return "Hello World " + name;
    }

    @GetMapping("/fabio")
    public String getFabioMsg(@RequestParam final String name) {
        return "Fabio ama " + name;
    }

}
