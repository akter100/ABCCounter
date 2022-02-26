package edu.citytech.cst.thecounter.service;


import org.springframework.boot.SpringApplication;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ABCService {
    public static Character[] getABC() {
        var data = IntStream.iterate(65, e -> e + 1).limit(26)
        .mapToObj( e -> (char)e)
                .toArray(Character[]::new);

        return data;

    }

    //public static void main(String[] args) {
      //  Arrays.stream(getABC()).forEach(System.out::print);
     //   SpringApplication.run(edu.citytech.cst.thecounter.service.ABCService.class, args);



    }



