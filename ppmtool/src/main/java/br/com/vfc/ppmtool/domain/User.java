package br.com.vfc.ppmtool.domain;

import br.com.vfc.ppmtool.exceptions.ErrorCode;
import br.com.vfc.ppmtool.validator.UserValidation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "uk_username", columnNames = {"username"})
})
@UserValidation(message = ErrorCode.PASSWORD_CONFIRM)
public class User extends BaseEntity implements UserDetails {

    @Email(message = ErrorCode.EMAIL)
    @NotBlank(message = ErrorCode.NOT_BLANK)
    private String username;

    @NotBlank(message = ErrorCode.NOT_BLANK)
    private String fullName;

    @NotBlank(message = ErrorCode.NOT_BLANK)
    @Length(min = 6, max = 60, message = ErrorCode.LENGTH)
    private String password;

    @Transient
    private String confirmPassword;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH, mappedBy = "user", orphanRemoval = true)
    private List<Project> projects = new ArrayList<>();

    public User() {
        super();
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
