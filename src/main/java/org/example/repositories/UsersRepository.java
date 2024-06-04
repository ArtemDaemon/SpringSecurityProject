package org.example.repositories;

import org.example.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<User, Long> {
    @Query("select u from User u join fetch u.roles where u.username = :username and u.password = :password")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
    @Query("select u from User u join fetch u.roles where u.username = :username")
    User findByUsername(@Param("username") String username);
}
