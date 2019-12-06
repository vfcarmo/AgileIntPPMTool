package br.com.vfc.ppmtool.web;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public abstract class BaseController {

    protected URI createURI(String relativePath, Object id, UriComponentsBuilder builder) {

        return builder.replacePath(relativePath).buildAndExpand(id).toUri();
    }

}
