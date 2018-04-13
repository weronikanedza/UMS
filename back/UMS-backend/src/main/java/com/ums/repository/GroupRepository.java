package com.ums.repository;

import com.ums.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<UserGroup,Long> {

}
