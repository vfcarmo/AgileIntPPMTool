package br.com.vfc.ppmtool.web;

import br.com.vfc.ppmtool.domain.User;
import br.com.vfc.ppmtool.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User request, UriComponentsBuilder builder, BindingResult result) {

        User savedUser = userService.save(request);

        URI uri = createURI("/api/users/{id}",
                savedUser.getId(), builder);

        return ResponseEntity.created(uri).body(savedUser);
    }
}
