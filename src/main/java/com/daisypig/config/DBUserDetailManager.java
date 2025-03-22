package com.daisypig.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.daisypig.entity.User;
import com.daisypig.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class DBUserDetailManager implements UserDetailsManager, UserDetailsPasswordService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    @Override
    public void createUser(UserDetails user) {
        User u = new User();
        u.setUsername(user.getUsername());
        u.setPassword(passwordEncoder.encode(user.getPassword()));
        u.setEnable(true);
        userMapper.insert(u);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        } else {
//            Collection<GrantedAuthority> authorities = new ArrayList<>();
//            //authorities.add(() -> "USER_LIST");
//            authorities.add(() -> "USER_ADD");
//            return new org.springframework.security.core.userdetails.User(
//                    user.getUsername(),
//                    user.getPassword(),
//                    user.getEnable(),
//                    true, true, true,
//                    authorities
//            );

//            return org.springframework.security.core.userdetails.User
//                    .withUsername(user.getUsername())
//                    .password(user.getPassword())
//                    .disabled(!user.getEnable())
//                    .credentialsExpired(false)
//                    .accountLocked(false)
//                    .roles("ADMIN123")
//                    .build();

            Collection<GrantedAuthority> authorities = new ArrayList<>();
            // 添加角色
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN123"));
            // 添加权限
            authorities.add(new SimpleGrantedAuthority("USER_LIST"));
            authorities.add(new SimpleGrantedAuthority("USER_ADD"));

            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .disabled(!user.getEnable())
                    .credentialsExpired(false)
                    .accountLocked(false)
                    .authorities(authorities)
                    .build();

        }
    }
}
