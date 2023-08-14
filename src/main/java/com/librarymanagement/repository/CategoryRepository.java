package com.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librarymanagement.entity.*;

/**
 * Repository interface for managing Category entities in the library management
 * system.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
