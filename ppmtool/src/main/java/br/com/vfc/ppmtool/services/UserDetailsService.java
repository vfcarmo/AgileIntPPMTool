package br.com.vfc.ppmtool.services;

import br.com.vfc.ppmtool.domain.User;

public interface UserDetailsService extends org.springframework.security.core.userdetails.UserDetailsService {

    /**
     * Return an User by the given User id.
     * @param id User id.
     * @return User.
     */
    public User loadUserById(Long id);
}
