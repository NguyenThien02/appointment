package com.do_an.appointment.controllers;

import com.do_an.appointment.dtos.PassWordDTO;
import com.do_an.appointment.dtos.UserDTO;
import com.do_an.appointment.dtos.UserLoginDTO;
import com.do_an.appointment.models.User;
import com.do_an.appointment.responses.*;
import com.do_an.appointment.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<?> createUser(
            @Valid @RequestBody UserDTO userDTO,
            BindingResult result
            ){
        try {
            if (result.hasErrors()) {
                List<String> errorMessager = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessager);
            }
            if (!userDTO.getPassword().equals(userDTO.getRetypePassword())) {
                return ResponseEntity.badRequest().body("Password does not match");
            }
            User user = userService.crateUser(userDTO);
            return ResponseEntity.ok(user);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        try {
            String token = userService.login(
                    userLoginDTO.getPhoneNumber(),
                    userLoginDTO.getPassword(),
                    userLoginDTO.getRoleId() == null ? 1 : userLoginDTO.getRoleId()
            );
            User user = userService.getUserByPhoneNumber(userLoginDTO.getPhoneNumber());

            return ResponseEntity.ok(LoginResponse.builder()
                            .userId(user.getId())
                            .token(token)
                            .roleId(user.getRole().getId())
                            .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/details")
    public ResponseEntity<UserResponse> getUserDetails(@RequestHeader("Authorization") String token) {
        try {
            String extractedToken = token.substring(7); // Loại bỏ "Bearer " từ chuỗi token
            User user = userService.getUserDetailsFromToken(extractedToken);
            return ResponseEntity.ok(UserResponse.fromUser(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable("id") Long id,
                                            @RequestBody UserDTO userDTO) {
        try {
            User user = userService.updateUserById(id, userDTO);
            return ResponseEntity.ok(UserResponse.fromUser(user));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("updatePasword/{id}")
    public ResponseEntity<?> updatePasswordById(@PathVariable("id") long id,
                                                @RequestBody PassWordDTO passwordDTO
    ){
        try {
            if(!passwordDTO.getNewPassword().equals(passwordDTO.getRetypeNewPassword())){
                return ResponseEntity.badRequest().body("Password does not match");
            }
            User user = userService.updatePasswordById(id, passwordDTO);
            return ResponseEntity.ok(UserResponse.fromUser(user));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("")
    public ResponseEntity<?> getAllUser(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int limit
    ){
        PageRequest pageRequest = PageRequest.of(
                page,
                limit,
                Sort.by("id").ascending()
        );
        Page<User> userPage = userService.getAllUser(pageRequest);
        Page<UserResponse> userResponsePage = userPage.map(UserResponse::fromUser);
        List<UserResponse> userResponses = userResponsePage.getContent();
        int totalPages = userResponsePage.getTotalPages();
        return ResponseEntity.ok(UserListResponse.builder()
                .listUsers(userResponses)
                .totalPages(totalPages)
                .build()) ;
    }

    @GetMapping("/user-doctor")
    public ResponseEntity<?> getUserDoctors() {
        List<User> userDoctor = userService.getUserDoctor();
        List<UserResponse> userResponseDoctor = userDoctor.stream()
                .map(UserResponse::fromUser)
                .toList();
        return ResponseEntity.ok(userResponseDoctor);
    }
}

