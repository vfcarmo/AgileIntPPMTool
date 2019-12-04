package br.com.vfc.ppmtool.web;

import br.com.vfc.ppmtool.web.errors.MessageErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public abstract class BaseController {

    protected ResponseEntity<?> responseErrors(BindingResult result) {
        if (result.hasErrors()) {
            MessageErrors errors = new MessageErrors(result.getFieldErrors());

            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    protected URI createURI(String relativePath, Object id, UriComponentsBuilder builder) {

        return builder.replacePath(relativePath).buildAndExpand(id).toUri();
    }

}
