package main.controller;

import main.domain.Admin;
import main.repository.AdminDao;
import main.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/7/27.
 */
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Resource
    private AdminDao adminDao;
    /**
     * 保存修改管理员权限
     * @param admin
     * @param roles
     * @return
     */
    @RequestMapping("/update")
    public ResponseEntity update(Admin admin, List<Long> roles){
        adminService.updateAdmin(admin,roles);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping("/list")
    public ResponseEntity list(){
        return new ResponseEntity(adminService.getAll(),HttpStatus.OK);
    }
    @RequestMapping("/edit")
    public ResponseEntity edit(long id){
        return new ResponseEntity(adminDao.getById(id),HttpStatus.OK);
    }
}
