package main.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/7/27.
 */
@Entity
@Table(name = "sys_role")
public class Role extends BaseEntity{

    private String name;
    private Boolean isSystem;//是否是最高系统管理员
    private String description;
    private List<String> authorities = new ArrayList<String>();//该角色拥有的权限列表
    private Set<Admin> admins = new HashSet<Admin>();//管理员与角色身份多对多的关系


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSystem() {
        return isSystem;
    }

    public void setSystem(Boolean system) {
        isSystem = system;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 创建sys_role_authority表，字段role引用role表的主键id作为外键。
     */
    @ElementCollection
    @CollectionTable(name="sys_role_authority",joinColumns={@JoinColumn(name="role",referencedColumnName="id")})
    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    @ManyToMany(mappedBy="roles", fetch= FetchType.LAZY)
    public Set<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(Set<Admin> admins) {
        this.admins = admins;
    }

}
