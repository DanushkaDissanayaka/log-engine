package com.logger.business.service;

import com.logger.base.model.common.Concrete.ResponseSuccessDto;
import com.logger.base.model.user.UserDto;
import com.logger.base.service.UserService;
import com.logger.repository.RoleRepository;
import com.logger.repository.UserRepository;
import com.logger.repository.model.Role;
import com.logger.repository.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            ModelMapper modelMapper,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found in the database");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));

        return new org.springframework.security.core.userdetails.User(
                user.getId().toString(),
                user.getPassword(),
                authorities
        );
    }

    @Override
    public ResponseSuccessDto save(UserDto model) {
        Optional<User> userByEmail = userRepository.findUserByEmail(model.getEmail());
        Role role = roleRepository.getReferenceById(model.getRoleId());

        if(userByEmail.isPresent()){
            return new ResponseSuccessDto(false, "User email exist");
        }

        User user = modelMapper.map(model, User.class);
        user.setPassword(passwordEncoder.encode(model.getPassword()));
        user.setRole(role);
        this.userRepository.save(user);
        return new ResponseSuccessDto(true, "User has save");
    }

    @Override
    public ResponseSuccessDto update(UserDto model) {
        var r = new ResponseSuccessDto(false, "User not fund");
        if (userRepository.existsById(model.getId())) {
            User user = userRepository.getReferenceById(model.getId());
            Role role = roleRepository.getReferenceById(model.getRoleId());

            user.setEmail(model.getEmail());
            user.setName(model.getName());
            user.setRole(role);
            userRepository.save(user);
        }
        return r;
    }

    @Override
    public ResponseSuccessDto delete(long id) {
        var response = new ResponseSuccessDto(false, "User not found");
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            response = new ResponseSuccessDto(true, "User has Deleted");
        }

        return response;
    }

    @Override
    public UserDto get(long id) {
        User user = this.userRepository.getReferenceById(id);
        return this.modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> get(int skip, int take, int role, String searchText) {
        return List.of(new UserDto());
    }
}
