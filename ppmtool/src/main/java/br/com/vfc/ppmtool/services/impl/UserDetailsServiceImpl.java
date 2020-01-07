package br.com.vfc.ppmtool.services.impl;

import br.com.vfc.ppmtool.domain.User;
import br.com.vfc.ppmtool.exceptions.ResourceNotFoundException;
import br.com.vfc.ppmtool.repositories.UserRepository;
import br.com.vfc.ppmtool.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Return an UserDetails by the given Username.
     * @param username Username.
     * @return UserDetails.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return repository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException(username));
    }

    @Override
    public User loadUserById(Long id) {

        return  repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
