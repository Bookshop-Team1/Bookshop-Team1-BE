package com.tw.bootcamp.bookshop.user;

import com.tw.bootcamp.bookshop.user.dto.*;
import com.tw.bootcamp.bookshop.user.exception.InvalidEmailException;
import com.tw.bootcamp.bookshop.user.exception.UserNotFoundException;
import com.tw.bootcamp.bookshop.utils.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/create")
    @Operation(summary = "Create user", description = "Creates user with credentials", tags = {"User Service"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "User created",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponse.class))}),
                    @ApiResponse(responseCode = "400", content = @Content),
                    @ApiResponse(responseCode = "422", content = @Content)
            }
    )
    ResponseEntity<UserResponse> create(@RequestBody CreateUserRequest userRequest) throws InvalidEmailException {
        User user = userService.create(userRequest);
        return new ResponseEntity<>(new UserResponse(user), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    ResponseEntity<Object> update(@PathVariable Long id, @RequestBody UpdateUserRequest updateUserRequest) throws UserNotFoundException {
        userService.update(id, updateUserRequest);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody JwtRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new Exception("Invalid username or password", e);
        }

        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
            final String jwtToken = jwtUtil.generateToken(userDetails);
    return ResponseEntity.ok(new JwtResponse(jwtToken, "user successfully authenticated"));
    }
}
