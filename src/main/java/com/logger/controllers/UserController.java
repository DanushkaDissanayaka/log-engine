package com.logger.controllers;

import com.logger.base.model.common.Abstract.PageResult;
import com.logger.base.model.common.Concrete.ResponseSuccessDto;
import com.logger.base.model.user.UserDto;
import com.logger.base.model.user.UserViewDto;
import com.logger.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public UserViewDto get(@PathVariable("id") long id){
        return this.userService.get(id);
    }

    @GetMapping
    public ResponseEntity<PageResult<UserViewDto>> getUsers(
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size,
            @RequestParam(name = "searchText", required = false) String searchText
    ){
        var response = userService.get(page, size, searchText);
        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseSuccessDto update(@RequestBody UserDto model) {
        return this.userService.update(model);
    }
    @DeleteMapping(path = "{id}")
    public ResponseSuccessDto delete(@PathVariable("id") long id) {
       if(id == getUserId()) {
           return new ResponseSuccessDto(false, "you cannot delete current logged in user");
       }
        return this.userService.delete(id);
    }
}
