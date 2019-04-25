package com.wj.dao.master.repo;

import com.wj.dao.master.entity.RoleAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * @author jun.wang
 * @title: RoleAuthorityRepository
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/4/23 15:51
 */
@NoRepositoryBean
public interface RoleAuthorityRepository extends JpaRepository<RoleAuthority, Long>, JpaSpecificationExecutor<RoleAuthority> {

    List<RoleAuthority> findByRoleId(Long roleId);
    List<RoleAuthority> findByRoleIdIn(List<Long> roleIds);
}
