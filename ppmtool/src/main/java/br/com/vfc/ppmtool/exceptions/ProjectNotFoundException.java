package br.com.vfc.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProjectNotFoundException extends RuntimeException {

    private List<Object> args = new ArrayList<>();

    public ProjectNotFoundException(String projectIdentifier) {
        super(ErrorCode.RESOURCE_NOT_FOUND);
        if (projectIdentifier != null) {
            args.add(projectIdentifier);
        }
    }

    public ProjectNotFoundException(Long id) {
        super(ErrorCode.RESOURCE_NOT_FOUND);
        if (id != null) {
            args.add(id);
        }
    }

    public Object[] getArgs() {
        return args.toArray();
    }
}
