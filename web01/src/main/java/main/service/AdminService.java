package main.service;

import main.domain.Admin;
import main.domain.Role;
import main.repository.AdminRepository;
import main.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private RoleRepository roleRepository;

    public List<Admin> getAll(){
        return adminRepository.findAll();
    }

    public Admin getOne(long id){
        return adminRepository.findOne(id);
    }

    public boolean updateAdmin(Admin admin, List<Long> roles) {
        Admin adminEntity = adminRepository.findOne(admin.getId());
        adminEntity.setRoles(new HashSet<Role>(roleRepository.findAllRolesByIds(roles)));
        adminEntity.setUserName(admin.getUserName());
        adminEntity.setPassword(DigestUtils.md5DigestAsHex(admin.getPassword().getBytes()));
        adminEntity.setEmail(admin.getEmail());
        adminRepository.save(adminEntity);
        return true;
    }
}
