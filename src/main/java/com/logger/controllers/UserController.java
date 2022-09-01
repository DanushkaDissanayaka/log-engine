package com.logger.controllers;

import com.logger.base.model.common.Concrete.ResponseSuccessDto;
import com.logger.base.model.user.UserDto;
import com.logger.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController extends BaseController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseSuccessDto save(@RequestBody UserDto model) {
        return this.userService.save(model);
    }

    @GetMapping(path = "{id}")
    public UserDto get(@PathVariable("id") long id){
        return this.userService.get(id);
    }

    @GetMapping
    public String hello(){
        return "Hello";
    }
}
