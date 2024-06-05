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

/**
 * Service class for managing {@link User} entities.
 * Implements {@link UserDetailsService} for Spring Security integration.
 */
@Service
public class UsersService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserRolesRepository userRolesRepository;

    /**
     * Retrieves all users.
     *
     * @return a list of all users
     */
    public List<User> getUsers() {
        return usersRepository.findAll();
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user
     * @return the user with the specified ID, or {@code null} if not found
     */
    public User getUserById(Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    /**
     * Creates a new user with a default role.
     *
     * @param user the user to create
     * @return the created user
     */
    public User createUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        User savedUser = usersRepository.save(user);
        UserRoles userRole = new UserRoles();
        userRole.setAccessLevel(0);
        userRole.setUser(savedUser);
        userRolesRepository.save(userRole);
        return savedUser;
    }

    /**
     * Finds a user by their username and password.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return the user with the specified username and password, or {@code null} if not found
     */
    public User findUserByUsernameAndPassword(String username, String password) {
        return usersRepository.findByUsernameAndPassword(username, password);
    }

    /**
     * Loads a user by their username for Spring Security.
     *
     * @param username the username of the user
     * @return the user details of the user
     * @throws UsernameNotFoundException if the user with the specified username is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByUsername(username);
    }
}
