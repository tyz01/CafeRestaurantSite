package com.tyzCorporation.caferestaurantsite.login;

import com.tyzCorporation.caferestaurantsite.user.AppUserService;
import com.tyzCorporation.caferestaurantsite.utility.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/login")
public class LoginController {
    private final AppUserService appUserService;
    private AuthenticationManager authenticationManager;
    @GetMapping("/page")
    public ApiResponse<String> login(@RequestParam String userName, @RequestParam String password) {
        try {
            UserDetails userDetails = appUserService.loadUserByUsername(userName);

            if(userName.isEmpty() || password.isEmpty()){
                return new ApiResponse<>("userName or password is empty", true);
            }

            if (!userDetails.isEnabled()) {
                return new ApiResponse<>("confirm you email", true);
            }

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userName,
                    password
            );

            Authentication authenticated = authenticationManager.authenticate(authentication);
            if (!authenticated.isAuthenticated()) {
                return new ApiResponse<>("user not authenticated", true);
            }

            SecurityContextHolder.getContext().setAuthentication(authenticated);
            return new ApiResponse<>("Success!", false);

        } catch (AuthenticationException e) {
            return new ApiResponse<>("check username or password", true);
        }
    }
}