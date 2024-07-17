package com.example.aiet_server.domain.user.controller;


import com.example.aiet_server.domain.user.payload.request.UserEditRequest;
import com.example.aiet_server.domain.user.payload.request.UserLoginRequest;
import com.example.aiet_server.domain.user.payload.request.UserRegisterRequest;
import com.example.aiet_server.domain.user.service.UserService;
import com.example.aiet_server.global.annotation.NeedAccess;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserRegisterRequest userRegisterRequest){
        return userService.register(userRegisterRequest);
    }

    @PostMapping("/edit")
    @NeedAccess
    public ResponseEntity<String> editUser(@RequestAttribute("user") String id, @RequestBody UserEditRequest userEditRequest){
        return userService.edit(id, userEditRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestBody UserLoginRequest userLoginRequest){
        return userService.login(userLoginRequest);
    }
}
