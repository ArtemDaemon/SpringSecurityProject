package org.example.services;

import org.example.models.User;
import org.example.models.UserRoles;
import org.example.repositories.UserRolesRepository;
import org.example.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserRolesRepository userRolesRepository;

    public List<User> getUsers() {
        return usersRepository.findAll();
    }

    public User getUserById(Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        User savedUser = usersRepository.save(user);
        UserRoles userRole = new UserRoles();
        userRole.setAccessLevel(0);
        userRole.setUser(savedUser);
        userRolesRepository.save(userRole);
        return savedUser;
    }

    public User findUserByUsernameAndPassword(String username, String password) {
        return usersRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByUsername(username);
    }
}
