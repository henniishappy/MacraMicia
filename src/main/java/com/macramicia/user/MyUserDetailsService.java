package com.macramicia.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final User defaultUser = new User();

    private final UserService userService;

    @Autowired
    public MyUserDetailsService(UserService userService) {
        this.userService = userService;
        defaultUser.setUsername("user");
        defaultUser.setPassword("$2y$04$e6tigQZn2VrtVlKrjVxQ1eOjyXpy7qxpDuFz1Ep2Ired.jIpmj4.q");
        defaultUser.setEmail("lukas_ludwig@gmx.net");
        defaultUser.setFirstName("fname");
        defaultUser.setLastName("lname");

        Role role = new Role();
        role.setName("ADMIN");
        defaultUser.setRole(role);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        if(defaultUser.getUsername().equals(username)) {
            user = defaultUser;
        }
        else {
            user = userService.findUserByUsername(username);

            if(user == null)
                throw new UsernameNotFoundException("User not found!");
        }
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getName()));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

}
