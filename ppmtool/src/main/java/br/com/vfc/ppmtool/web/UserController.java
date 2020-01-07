package br.com.vfc.ppmtool.web;

import br.com.vfc.ppmtool.domain.User;
import br.com.vfc.ppmtool.security.JwtTokenProvider;
import br.com.vfc.ppmtool.security.SecurityConstants;
import br.com.vfc.ppmtool.services.UserService;
import br.com.vfc.ppmtool.web.reponses.LoginResponse;
import br.com.vfc.ppmtool.web.requests.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController {

    private UserService userService;

    private JwtTokenProvider jwtTokenProvider;

    private AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User request, UriComponentsBuilder builder) {

        User savedUser = userService.save(request);

        URI uri = createURI("/api/users/{id}",
                savedUser.getId(), builder);

        return ResponseEntity.created(uri).body(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new LoginResponse(true, jwt));
    }
}
