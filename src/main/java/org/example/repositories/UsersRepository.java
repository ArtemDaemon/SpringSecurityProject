package org.example.repositories;

import org.example.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * Repository interface for {@link User} entities.
 * Extends {@link JpaRepository} to provide basic CRUD operations.
 */
public interface UsersRepository extends JpaRepository<User, Long> {
    /**
     * Finds a user by username and password.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return the user with the specified username and password
     */
    @Query("select u from User u join fetch u.roles where u.username = :username and u.password = :password")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * Finds a user by username.
     *
     * @param username the username of the user
     * @return the user with the specified username
     */
    @Query("select u from User u join fetch u.roles where u.username = :username")
    User findByUsername(@Param("username") String username);
}
