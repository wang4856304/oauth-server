package com.wj.dao.master.repo;

import com.wj.dao.master.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author jun.wang
 * @title: UserRepository
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/3/2814:34
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    User findUserByUserName(String userName);
    User findUserByUserNameAndPassword(String userName, String password);
}
