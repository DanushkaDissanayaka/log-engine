package com.logger.base.service;

import com.logger.base.model.common.Abstract.PageResult;
import com.logger.base.model.common.Concrete.ResponseSuccessDto;
import com.logger.base.model.user.UserDto;
import com.logger.base.model.user.UserViewDto;

import java.util.List;


public interface UserService {
    ResponseSuccessDto save(UserDto model);
    ResponseSuccessDto update(UserDto model);
    ResponseSuccessDto delete(long id);
    UserViewDto get(long id);
    PageResult<UserViewDto> get(int page, int size, String searchText);
}
