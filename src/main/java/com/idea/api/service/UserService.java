package com.idea.api.service;

import com.idea.api.dto.UserDto;
import com.idea.api.dto.auth.UserRegistrationRequest;
import com.idea.api.mapper.UserMapper;
import com.idea.api.model.Role;
import com.idea.api.model.RoleName;
import com.idea.api.model.UserEntity;
import com.idea.api.repository.RoleRepository;
import com.idea.api.repository.UserRepository;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDto registerNewUser(UserRegistrationRequest userRegistrationRequest) {
        // Role may depend on how do we register a User.
        Role role = roleRepository.findByNameThrows(RoleName.USER);

        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(userRegistrationRequest.getLogin());
        userEntity.setPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));
        userEntity.setName(userRegistrationRequest.getUsername());
        userEntity.setRoles(Set.of(role));
        userEntity = userRepository.save(userEntity);
        return userMapper.toUserDto(userEntity);
    }
}
