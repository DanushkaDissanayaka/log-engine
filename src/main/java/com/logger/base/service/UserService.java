package com.logger.base.service;

import com.logger.base.model.common.Concrete.ResponseSuccessDto;
import com.logger.base.model.user.UserDto;

import java.util.List;


public interface UserService {
    ResponseSuccessDto save(UserDto model);
    ResponseSuccessDto update(UserDto model);
    ResponseSuccessDto delete(long id);
    UserDto get(long id);
    List<UserDto> get(int skip, int take, int role, String searchText);
}
