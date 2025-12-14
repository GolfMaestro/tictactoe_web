package org.bygolf;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/main")
    public String mainPage() {
        return "Tic tac toe app";
    }



}
