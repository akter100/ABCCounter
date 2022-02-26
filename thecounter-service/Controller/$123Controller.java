package edu.citytech.cst.thecounter.Controller;


import edu.citytech.cst.thecounter.service.$123CounterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/numbers")
public class $123Controller {

        @GetMapping("/123")
        public int[] countABC(@RequestParam (defaultValue = "100")int limit){
            return $123CounterService.get123(limit); }

    }



