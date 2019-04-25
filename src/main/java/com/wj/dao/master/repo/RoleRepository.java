package com.wj.dao.master.repo;

import com.wj.dao.master.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author jun.wang
 * @title: RoleRepository
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/4/23 15:57
 */
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

    List<Role> findByParentId(Long parentId);
    Role findById(Long roleId);
}
