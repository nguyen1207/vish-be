package com.nguyen1207.vishbe.repositories;

import com.nguyen1207.vishbe.entities.user.User;
import com.nguyen1207.vishbe.projections.user.PersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserId(String userId);

    Optional<User> findByEmailOrPhone(String email, String phone);

    @Query("SELECT u.fullName as fullName, u.email as email, u.phone as phone, u.avatarUrl as avatarUrl FROM User u WHERE u.userId = :userId")
    Optional<PersonalInfo> findUserPersonalInfo(@Param("userId") String userId);
}
