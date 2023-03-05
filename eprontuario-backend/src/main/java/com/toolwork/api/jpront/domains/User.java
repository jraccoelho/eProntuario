package com.toolwork.api.jpront.domains;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "t_users")
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @Column(name = "id", length = 100, unique = true)
    private String userName;
    @Column(name = "pwd", length = 500)
    private String userPassword;
    @Column(name = "fullName", length = 100)
    private String fullName;
    @Column(name = "createDate", columnDefinition = "DateTime")
    private LocalDate createdDate;
    @Column(name = "createdBy", length = 100)
    private String createdBy;
    @Column(name = "lastUpdateDate", columnDefinition = "DateTime")
    private LocalDate lastUpdateDate;
    @Column(name = "lastUpdateBy", length = 100)
    private String lastUpdateBy;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean userActive;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO readjust this logic.
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO readjust this logic.
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO readjust this logic.
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userActive;
    }

}
