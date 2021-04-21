package com.fruitbasket.orange.config.security;

import com.fruitbasket.orange.module.rbac.pojo.entity.RbacRole;
import com.fruitbasket.orange.module.rbac.pojo.entity.RbacUserAccount;
import com.fruitbasket.orange.module.rbac.service.PermissionService;
import com.fruitbasket.orange.module.rbac.service.RoleService;
import com.fruitbasket.orange.module.rbac.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.isNull;

/**
 * 自定义用户登陆信息处理
 *
 * @author LiuBing
 * @date 2021/4/17
 */
@Slf4j
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    private final RoleService roleService;

    /**
     * 这个方法是 spring security 调用的，跟一般的 controller 不一样
     * 不会因为配置 open-in-view: true 而将数据库 session 保持不断开
     * 直接获取懒加载 LAZY 的数据会报错： could not initialize proxy - no Session
     * 所以懒加载部分的数据，需要用代码根据关系一层层查询
     *
     * @param s 账号
     * @return 用户登录详细信息
     * @throws UsernameNotFoundException 无法根据账号找到用户
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        RbacUserAccount ua = userService.getUserAccountBy(s);
        if (isNull(ua))
            throw new UsernameNotFoundException("user not found.");
        List<RbacRole> roles = roleService.listRolesOf(ua.getUser().getId());
        return new CustomUserDetails()
                .setUserId(ua.getUser().getId())
                .setUsername(ua.getIdentifier())
                .setPassword(ua.getCredential())
                .loadAuthoritiesBy(roles);
    }

    public CustomUserDetailsService(
            UserService userService,
            RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
}
