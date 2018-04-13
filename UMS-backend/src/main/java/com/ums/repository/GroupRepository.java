package com.ums.repository;

import com.ums.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<UserGroup,Long> {
   UserGroup getOne(Long id);
}
