package com.mvmlabs.springboot.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.mvmlabs.springboot.domain.User;

/**
 * Spring Data repository for interacting with user details data store.
 * 
 * @author Mark Meany
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    /**
     * Find a users record given their name.
     * 
     * @param name the name to identify
     * @return
     */
    User findByName(String name);

    /**
     * Find a users record given their name.
     * 
     * @param username the name to identify
     * @return
     */
    User findByUsername(String username);
    
    @Modifying
    @Query("update User u set u.numberOfVisits = u.numberOfVisits + 1 where id = ?1")
    void updateNumberOfVisits(Long id);
    
}
