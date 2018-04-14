package com.ums.repository;

import com.ums.entity.User;
import com.ums.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
    List<User> findAllByOrderByIdAsc();
    User getOne(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE User u set u.userName= ?1," +
            "u.firstName= ?2,u.date=?3,u.lastName= ?4," +
            "u.password= ?5,u.group= ?6 where u.id=?7")
    void updateUser(String userName, String firstName, String date, String lastName, String password, UserGroup userGroup,Long id);

    @Modifying
    @Transactional
    @Query("DELETE from User u where u.id=?1")
    void removeUser(Long id);
}
