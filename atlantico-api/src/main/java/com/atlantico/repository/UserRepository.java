package com.atlantico.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.atlantico.data.model.User;
import com.atlantico.data.vo.UserResultVO;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    
    List<User> findAll();
    
    @Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:username)")
    Optional<User> findByUsername(@Param("username") String username);
    
    Page<User> findByEmailContains(String email, Pageable pageable);
    Page<User> findAllByEmail(String email, Pageable pageable);
    Page<User> findAllByEmailContainsAndEmail(String email, String auth, Pageable pageable);
    
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
    @Query("SELECT u.email FROM User u join u.authorities r where upper(r.name) like '%ADMIN%'")
	List<String> findEmailAllAdmin();
    
    void deleteByUsername(@Param("username") String username);
}
