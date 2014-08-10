package com.mvmlabs.springboot.web.form;

import java.util.Calendar;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.mvmlabs.springboot.domain.User;
import com.mvmlabs.springboot.domain.UserAuthority;

public class UserForm {

    @Digits(integer=8, fraction=0)
    private Long id;

    @Digits(integer=8, fraction=0)
    private Long version;
    
    @NotNull
    @Size(min = 3, max = 80)
    private String name;

    @NotNull
    @Size(min = 6, max = 20)
    private String username;
    
    @Size(min = 8, max = 20)
    private String password;

    @Size(min = 8, max = 20)
    private String confirm;

    private boolean enabled;
    
    private boolean administrator;

    public UserForm() {
        
    }

    /** Populate fields from supplied user. */
    public UserForm(final User user) {
        setId(user.getId());
        setVersion(user.getVersion());
        setName(user.getName());
        setUsername(user.getUsername());
        setPassword(null);
        setConfirm(null);
        setEnabled(user.isEnabled());
        setAdministrator(user.isAdministrator());
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = emptyAsNull(name);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = emptyAsNull(username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = emptyAsNull(password);
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = emptyAsNull(confirm);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    private String emptyAsNull(final String value) {
        return (value == null || value.trim().length() == 0) ? null : value;
    }
    
    /**
     * Update the user with values from this form.
     * 
     * @param user
     */
    public void update(User user) {
        user.setId(getId());
        user.setVersion(getVersion());
        user.setName(getName());
        user.setUsername(getUsername());
        if (password != null && confirm != null && password.equals(confirm)) {
            user.setPassword(getPassword());
        }
        user.setEnabled(isEnabled());
        user.getAuthorities().clear();
        user.getAuthorities().add(new UserAuthority(1L, "ROLE_USER"));
        if (isAdministrator()) {
            user.getAuthorities().add(new UserAuthority(2L, "ROLE_ADMIN"));
        }
        user.setLastUpdatedDate(Calendar.getInstance());
    }

}
