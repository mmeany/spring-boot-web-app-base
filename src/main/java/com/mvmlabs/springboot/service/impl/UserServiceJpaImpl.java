package com.mvmlabs.springboot.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mvmlabs.springboot.dao.UserRepository;
import com.mvmlabs.springboot.domain.User;
import com.mvmlabs.springboot.service.UserService;

@Service
@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
public class UserServiceJpaImpl implements UserService {

    private final UserRepository userRepository;
    
    @Autowired
    public UserServiceJpaImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public User registerVisit(final User user) {
        if (user != null) {
            user.setNumberOfVisits(user.getNumberOfVisits() + 1);
            user.setLastLoginDate(Calendar.getInstance());
            return userRepository.save(user);
        }
        return user;
    }

    @Override
    @Transactional(readOnly=true)
    public Page<User> getAllRegisteredUsers(final Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Override
    public void save(final User user) {
        userRepository.save(user);
    }

    @Override
    public User loadUserById(final Long id) {
        return userRepository.findOne(id);
    }
    
}
