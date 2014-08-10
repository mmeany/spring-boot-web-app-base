package com.mvmlabs.springboot.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="user_details")
public class User implements UserDetails {

    private static final long serialVersionUID = -3648550085103824260L;
    
    public static final UserAuthority ROLE_USER = new UserAuthority("ROLE_USER");

    public static final UserAuthority ROLE_ADMIN = new UserAuthority("ROLE_ADMIN");

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;
    
    @Column(nullable = false, unique = true)
    private String username;
    
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer numberOfVisits;

    @Column(nullable = false)
    private boolean enabled;

    @Transient
    private boolean administrator;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createDate;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastUpdatedDate;

    @Column(nullable = true)
    private Calendar lastLoginDate;
    
    /** The authorities assigned to this user. */
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<UserAuthority>  authorities;

    /**
     * No parameter constructor required for JPA.
     */
    public User() {
    }

    /**
     * Convenience constructor used in application.
     * 
     * @param name name of the user
     */
    public User(final String name) {
        this.name = name;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfVisits() {
        return numberOfVisits == null ? 0 : numberOfVisits;
    }

    public void setNumberOfVisits(Integer numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAdministrator() {
        return getAuthorities().contains(ROLE_ADMIN);
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public Calendar getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Calendar lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Calendar getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Calendar lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    @Override
    public Collection<UserAuthority> getAuthorities() {
        if (authorities == null) {
            authorities = new ArrayList<UserAuthority>();
        }
        return authorities;
    }

    public void setAuthorities(Collection<UserAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
