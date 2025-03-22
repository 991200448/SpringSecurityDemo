package com.daisypig.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.daisypig.entity.User;
import com.daisypig.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public boolean save(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return userService.removeById(id);
    }

    @PutMapping
    public boolean update(@RequestBody User user) {
        return userService.updateById(user);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("/page")
    public IPage<User> page(@RequestParam(defaultValue = "1") int current,
                            @RequestParam(defaultValue = "10") int size) {
        Page<User> page = new Page<>(current, size);
        return userService.page(page, new QueryWrapper<>());
    }
    @PreAuthorize("hasAuthority('USER_ADD')")
    @PostMapping("/add")
    public void add(@RequestBody User user){
        userService.addUserDetail(user);
    }
    @PostMapping("/list")
    public List<User> list(){
        return userService.list();
    }
    @PreAuthorize("hasRole('ADMIN123') and authentication.name == 'admin'")
    @PostMapping("/add2")
    public void add2(@RequestBody User user){
        userService.addUserDetail(user);
    }
    @PostMapping("/list2")
    public List<User> list2(){
        return userService.list();
    }
}