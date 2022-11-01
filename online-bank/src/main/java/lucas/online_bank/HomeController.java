package lucas.online_bank;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {
    @GetMapping()
    public String linkToSwagger(){
    return "http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs";
    }
}
