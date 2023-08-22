package reactspringdev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import reactspringdev.domain.User;

import java.util.List;
import java.util.Optional;

public interface SpringDataJpaUserRepository extends JpaRepository<User,String> {
    @Query("select u from User u where u.userId = :userId")
    User findByUsers(@Param("userId") String userId);

    @Modifying
    @Transactional
    @Query("update User u set u.userId = :userId, u.userPasswd = :userPasswd where u.userId = :searchId")
    void updateUser(@Param("userId") String userId, @Param("userPasswd") String userPasswd, @Param("searchId") String searchId);

    @Modifying
    @Transactional
    @Query("delete from User u where u.userId = :userId")
    void delete(@Param("userId") String userId);
}
