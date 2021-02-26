package simbirSoftPractice.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simbirSoftPractice.demo.model.Item;

@RestController
@RequestMapping("/api")
public class ItemController {

    @GetMapping("/sayHello")
    public Item getHelloWorld(){
        return new Item("MyName");
    }
}
