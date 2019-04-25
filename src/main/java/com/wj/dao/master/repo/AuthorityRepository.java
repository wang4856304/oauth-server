package com.wj.dao.master.repo;

import com.wj.dao.master.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * @author jun.wang
 * @title: AuthorityRepository
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/4/23 15:59
 */
@NoRepositoryBean
public interface AuthorityRepository extends JpaRepository<Authority, Long>, JpaSpecificationExecutor<Authority> {
    List<Authority> findByIdIn(List<Long> ids);
}
