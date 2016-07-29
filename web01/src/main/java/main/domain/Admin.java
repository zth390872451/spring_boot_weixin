package main.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/7/25 0025.
 */
@Entity
@Table(name = "sys_admin")
public class Admin extends BaseEntity{

    private String userName;
    private String password;
    private String email;
    private String name;
    private String department;
   /* private Boolean isEnabled;
    private Boolean isLocked;
    private Integer loginFailureCount;//登录失败次数
    private Date lockedDate;//锁定日期
    private Date loginDate;//登录日期
    private String loginIp;*/
    private Set<Role> roles = new HashSet<Role>();//admin拥有的角色


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * 创建sys_admin_role表，
     * admins字段来源于sys_admin表的id
     * roles字段来源于sys_role表的id
     * @return
     */
    @NotEmpty(message = "角色设置")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sys_admin_role",joinColumns = {@JoinColumn(name="admins")},
            inverseJoinColumns = {@JoinColumn(name="roles")})
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
