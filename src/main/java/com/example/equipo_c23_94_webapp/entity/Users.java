package com.example.equipo_c23_94_webapp.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Entity
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String last_name;
    private String username;

    @Column(unique = true)
    private String email;

    private String password;
    private String phone;
    private String address;

    // Relación con Loans (Uno a Muchos)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Loans> loans;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    //@Enumerated(EnumType.STRING)
    //private UserEnum userEnum;

    // Constructor privado para Builder
    private Users(Long id, String last_name, String username, String email, String password, String phone, String address, List<Loans> loans, Role role) {
        this.id = id;
        this.last_name = last_name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.address = address;
        this.loans = loans;
    }

    public Users() {
    }

    public void addLoan(Loans loan) {
        this.loans.add(loan);
    }

    // Método estático builder() para iniciar el Builder
    public static Builder builder() {
        return new Builder();
    }

    // Clase Builder para Users
    public static class Builder {
        private Long id;
        private String last_name;
        private String username;
        private String email;
        private String password;
        private String phone;
        private String address;
        private List<Loans> loans;
        private Role role;

        // Métodos setter para cada atributo
        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder last_name(String last_name) {
            this.last_name = last_name;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder loan(List<Loans> loan) {
            this.loans = loans;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        // Método build() que construye la instancia de Users
        public Users build() {
            return new Users(id, last_name, username, email, password, phone, address, loans,role);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Loans> getLoan() {
        return loans;
    }

    public void setLoan(List<Loans> loan) {
        this.loans = loan;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role){
        this.role = role;
    }

    // Métodos de UserDetails
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

     @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = role.getPermissions().stream().map(
                permissionEnum -> new SimpleGrantedAuthority(permissionEnum.name())).collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));

        return authorities;
    }
}
