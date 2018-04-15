package com.ums.repository;

import com.ums.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface GroupRepository extends JpaRepository<UserGroup,Long> {
   UserGroup getOne(Long id);
   UserGroup findByName(String name);

   @Modifying
   @Transactional
   UserGroup save(UserGroup userGroup);

   @Modifying
   @Transactional
   void removeById(Long id);

}
