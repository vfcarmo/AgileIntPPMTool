package br.com.vfc.ppmtool.validator;

import br.com.vfc.ppmtool.domain.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserValidator implements ConstraintValidator<UserValidation, User> {

    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {

        return (user.getPassword().equals(user.getConfirmPassword()));
    }
}
