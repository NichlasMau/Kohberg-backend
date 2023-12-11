package com.example.kohbergbackend.security.controller;

import com.example.kohbergbackend.security.JwtTokenManager;
import com.example.kohbergbackend.security.model.JwtRequestModel;
import com.example.kohbergbackend.security.model.JwtResponseModel;
import com.example.kohbergbackend.security.model.User;
import com.example.kohbergbackend.security.service.IUserService;
import com.example.kohbergbackend.security.service.JwtUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class JwtController {
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenManager jwtTokenManager;
    @Autowired
    private IUserService userService;

    @PostMapping("/signup")
    public ResponseEntity<JwtResponseModel> signup(@RequestBody JwtRequestModel request){
        System.out.println("signup: username:" + request.getUsername() + " password: " + request.getPassword() );
        User user = new User(request.getUsername(),request.getPassword());
        if(userService.findByName(user.getUsername()).size()==0) {
            if (userService.save(user) != null) {
                return ResponseEntity.ok(new JwtResponseModel("created user: " + user.getUsername() + " pw: " + user.getPassword()));
            } else {
                return ResponseEntity.ok(new JwtResponseModel("error creating user: " + user.getUsername()));
            }
        }else {
                return ResponseEntity.ok(new JwtResponseModel("error: user exists: " + user.getUsername()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseModel> createToken(@RequestBody JwtRequestModel request) throws Exception {
        // HttpServletRequest servletRequest is available from Spring, if needed.
        System.out.println(" JwtController createToken Call: 4" + request.getUsername());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(),
                            request.getPassword())
            );
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            return ResponseEntity.ok(new JwtResponseModel("bad credentials"));
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwtToken = jwtTokenManager.generateJwtToken(userDetails);
        return ResponseEntity.ok(new JwtResponseModel(jwtToken));
    }


    @PostMapping("/getSecret")
    public ResponseEntity<Map> getSecret() {
        System.out.println("getSecret is called");
        Map<String,String > map = new HashMap<>();
        map.put("message","this is secret from server");
        return ResponseEntity.ok(map);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<Map> deleteUser(@RequestBody User user) { // hvis man kommer hertil, er token OK
        System.out.println("deleteUser is called with user: " + user.getUsername());
        // evt. findById, som finder hele objektet fra MySQL, inkl. id.
        List<User> users =  userService.findByName(user.getUsername());
        User userToDelete = users.get(0);
        userService.delete(userToDelete);
        Map<String,String > map = new HashMap<>();
        map.put("message","user deleted, if found " + user.getUsername());
        return ResponseEntity.ok(map);
    }

}