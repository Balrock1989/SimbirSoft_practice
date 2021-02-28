package simbirSoftPractice.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simbirSoftPractice.demo.model.Item;

@RestController
@RequestMapping("/api")
@Api(value = "Item resources", description = "crud operations")
public class ItemController {

    @GetMapping("/sayHello")
    @ApiOperation(value = "show Item", response = Item.class)
    public Item getHelloWorld(){
        return new Item("MyName");
    }
}
