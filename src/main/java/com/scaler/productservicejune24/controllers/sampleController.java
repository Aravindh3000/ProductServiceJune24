package com.scaler.productservicejune24.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Allows to write http methods in the class - Annotation
@RestController
// To get here
@RequestMapping("/say")
public class sampleController {

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World";
    }

    @GetMapping("/hello/{name}/{times}")
    public String sayHello(@PathVariable("name") String name,
                           @PathVariable("times") int times){
        String result = "";
        for(int i=0; i<times; i++){
            result += "Hello ";
        }
        result += name;
        return result;
    }

    @GetMapping("/bye")
    public String bye(){
        return "Bye World";
    }
}
