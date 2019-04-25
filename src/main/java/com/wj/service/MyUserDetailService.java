package com.wj.service;

import com.wj.dao.master.entity.*;
import com.wj.dao.master.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author jun.wang
 * @title: MyUserDetailService
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/4/23 14:57
 */

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    /*@Autowired
    private RoleAuthorityRepository roleAuthorityRepository;

    @Autowired
    private AuthorityRepository authorityRepository;*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserName(username);
        if (null == user) {
            throw new RuntimeException("user is not exists");
        }
        UserRole userRole = userRoleRepository.findByUserId(user.getId());
        if (userRole == null) {
            throw new RuntimeException("user role is not exists");
        }
        Role role = roleRepository.findById(userRole.getRoleId());
        if (role == null) {
            throw new RuntimeException("role is not exists");
        }
        /*List<Long> roleIds = new ArrayList<>();
        roleIds.add(role.getId());
        List<Role> roleList = roleRepository.findByParentId(userRole.getRoleId());
        if (roleList != null && roleList.size() > 0) {
            roleList.stream().map(role1 -> {roleIds.add(role1.getId()); return 0;}).collect(Collectors.toList());
        }

        List<RoleAuthority> roleAuthorityList = roleAuthorityRepository.findByRoleIdIn(roleIds);
        List<Long> authorityIds = new ArrayList<>();
        if (roleAuthorityList != null && roleAuthorityList.size() > 0) {
            roleAuthorityList.stream().map(roleAuthority -> {authorityIds.add(roleAuthority.getAuthorityId()); return 0;}).collect(Collectors.toList());
            List<Authority> authorityList = authorityRepository.findByIdIn(authorityIds);
        }*/

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
        Set<GrantedAuthority> set = new HashSet<>();
        set.add(grantedAuthority);
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), true, true, true, true, set);
    }
}
