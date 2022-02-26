package edu.citytech.cst.thecounter.Controller;

import edu.citytech.cst.thecounter.service.ABCService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/alphabets")

    public class ABCController {
    @GetMapping("/abc")
    public  Character[] counterABC(){

        return ABCService.getABC();
    }


    }


