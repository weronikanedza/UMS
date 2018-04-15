package com.ums.repository;

import com.ums.entity.User;
import com.ums.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
    User getOne(Long id);

    @Modifying
    @Transactional
    @Query("DELETE from User u where u.id=?1")
    void removeUser(Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.group=?1")
    void removeUsersByGroup(UserGroup userGroup);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.userName= ?1," +
            "u.firstName= ?2,u.date=?3,u.lastName= ?4," +
            "u.password= ?5,u.group= ?6 WHERE u.id=?7")
    void updateUser(String userName, String firstName, String date, String lastName, String password, UserGroup userGroup,Long id);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.group= ?1 WHERE u.group=?2")
    void updateUsersGroups(UserGroup userGroupFrom,UserGroup userGroupTo);
}
