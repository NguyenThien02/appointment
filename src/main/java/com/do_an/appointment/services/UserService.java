package com.do_an.appointment.services;

import com.do_an.appointment.components.JwtTokenUtils;
import com.do_an.appointment.dtos.UserDTO;
import com.do_an.appointment.exceptions.DataNotFoundException;
import com.do_an.appointment.exceptions.PermissionDenyException;
import com.do_an.appointment.models.Role;
import com.do_an.appointment.models.User;
import com.do_an.appointment.repositories.RoleRepository;
import com.do_an.appointment.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtil;

    @Override
    @Transactional
    public User crateUser(UserDTO userDTO) throws Exception {
        String phoneNumber = userDTO.getPhoneNumber();
        if(userRepository.existsByPhoneNumber(phoneNumber)){
            throw new DataIntegrityViolationException("Phone number already exists");
        }
        Role role = roleRepository.findById(userDTO.getRoleId())
                .orElseThrow(() -> new DataNotFoundException("Role not found"));
        if(role.getName().equals(Role.ADMIN)){
            throw new PermissionDenyException("You cannot register an admin account");
        }
        User newUser = User.builder()
                .fullName(userDTO.getFullName())
                .phoneNumber((userDTO.getPhoneNumber()))
                .password(userDTO.getPassword())
                .birthday(userDTO.getBirthday())
                .address(userDTO.getAddress())
                .build();
        newUser.setRole(role);
        String passwrod = userDTO.getPassword();
        String encodedPassword = passwordEncoder.encode(passwrod);
        newUser.setPassword(encodedPassword);
        return userRepository.save(newUser);
    }

    @Override
    public String login(String phoneNumber, String password, Long roleId) throws Exception {
        Optional<User> optionalUser = userRepository.findByPhoneNumber(phoneNumber);
        if (optionalUser.isEmpty()) {
            throw new DataNotFoundException("Invalid phone number / password");
        }
        User existingUser = optionalUser.get();
        if(!passwordEncoder.matches(password,existingUser.getPassword())){
            throw new BadCredentialsException("Wrong phone number or password");
        }
        Optional<Role> optionalRole = roleRepository.findById(roleId);
        if(optionalRole.isEmpty() || !roleId.equals(existingUser.getRole().getId())) {
            throw new DataNotFoundException("You do not have this right");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                phoneNumber,password,existingUser.getAuthorities());
        authenticationManager.authenticate(authenticationToken);
        return jwtTokenUtil.generateToken(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
