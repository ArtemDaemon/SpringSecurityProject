package org.example.repositories;

import org.example.models.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link UserRoles} entities.
 * Extends {@link JpaRepository} to provide basic CRUD operations.
 */
public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {
}
