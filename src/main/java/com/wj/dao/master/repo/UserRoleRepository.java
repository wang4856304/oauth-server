package com.wj.dao.master.repo;

import com.wj.dao.master.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author jun.wang
 * @title: UserRoleRepository
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/4/23 15:35
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Long>, JpaSpecificationExecutor<UserRole> {
    UserRole findByUserId(Long userId);
}
