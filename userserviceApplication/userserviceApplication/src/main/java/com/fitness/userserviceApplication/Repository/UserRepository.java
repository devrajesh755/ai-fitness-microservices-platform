package com.fitness.userserviceApplication.Repository;


import com.fitness.userserviceApplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "SELECT CASE"
                           +" WHEN COUNT(*)>0 THEN TRUE"
                           +" ELSE FALSE"
                           +" END"
                           +" FROM users"
                           +" WHERE email= :email"
                             ,nativeQuery = true) //return 0 or 1
    Long exitsEmailId(@Param("email") String email);


    @Query(value = "SELECT  * FROM users WHERE email = 'user4@gmail.com' LIMIT 1",nativeQuery = true)
    User getUserByEmail(@Param("email") String email);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM users WHERE email= :email",nativeQuery = true)
    void deleteUserByEmail(@Param("email") String email);


}
