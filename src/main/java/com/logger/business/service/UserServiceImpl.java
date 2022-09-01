package com.logger.business.service;

import com.logger.base.model.common.Concrete.ResponseSuccessDto;
import com.logger.base.model.user.UserDto;
import com.logger.base.service.UserService;
import com.logger.repository.UserRepository;
import com.logger.repository.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository , ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseSuccessDto save(UserDto model) {
        Optional<User> userByEmail = userRepository.findUserByEmail(model.getEmail());

        if(userByEmail.isPresent()){
            return new ResponseSuccessDto(false, "User email exist");
        }

        User user = modelMapper.map(model, User.class);
        this.userRepository.save(user);
        return new ResponseSuccessDto(true, "User has save");
    }

    @Override
    public UserDto get(long id) {
        return new UserDto();
    }

    @Override
    public List<UserDto> get(int skip, int take, int role, String searchText) {
        return List.of(new UserDto());
    }
}
