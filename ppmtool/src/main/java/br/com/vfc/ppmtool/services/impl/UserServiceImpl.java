package br.com.vfc.ppmtool.services.impl;

import br.com.vfc.ppmtool.domain.User;
import br.com.vfc.ppmtool.exceptions.ResourceNotFoundException;
import br.com.vfc.ppmtool.exceptions.UserConflictException;
import br.com.vfc.ppmtool.repositories.UserRepository;
import br.com.vfc.ppmtool.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repository = repository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<User> findAll() {

        Iterable<User> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public User findById(Long id) {

        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public User save(User entity) {
        User savedUser;
        try {

            entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
            entity.setConfirmPassword(entity.getPassword());
            savedUser = repository.save(entity);
        } catch (Exception e) {
            throw new UserConflictException(entity.getUsername());
        }
        return savedUser;
    }

    @Override
    public void deleteById(Long id) {

        repository.deleteById(id);
    }
}
