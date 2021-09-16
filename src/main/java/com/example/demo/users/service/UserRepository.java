package com.example.demo.users.service;

import com.example.demo.users.util.Status;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

  @Query("select case when count(e) > 0 then true else false end from UserEntity e where e.email = lower(:email) ")
  boolean existsByEmail(@Param("email") String email);

  @Query("select u from UserEntity u where u.currentStatus = :status")
  List<UserEntity> findAllByCurrentStatus(@Param("status") Status status);
}
