package com.fitness.userserviceApplication.Repository;


import com.fitness.userserviceApplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "SELECT COUNT(*) FROM users WHERE email= :email",nativeQuery = true)
    Long exitsEmailId(@Param("email") String email);

    @Query(value="SELECT id FROM users WHERE email= :email",nativeQuery = true)
    Long findIdByEmail(@Param("email") String email);

}
