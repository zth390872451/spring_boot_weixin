package main.controller;

import main.domain.Device;
import main.dto.ExcelBuilderDto;
import main.repository.DeviceRepository;
import main.service.DeviceService;
import main.service.common.ExcelService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/7/23 0023.
 */
@RestController
@RequestMapping("device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceRepository deviceRepository;

    @RequestMapping("/createDevice")
    public HttpEntity imports(MultipartFile multipartFile, Long serverId){
        List mapList = new ArrayList();
        //ExcelService 读取导入的文件
        mapList = ExcelService.readExcel(multipartFile);
        List<Device> deviceList = new ArrayList<Device>();
        deviceList = ExcelBuilderDto.build(mapList);
        List<String>  missList = deviceService.imports(deviceList,serverId);
        return new HttpEntity(missList);
    }

    @RequiresPermissions({"admin:list"})
    @RequestMapping("/list")
    public ResponseEntity listAll(@PageableDefault(size=10)Pageable pageable, ModelMap modelMap
    , Device device){
        Page<Device> page = deviceRepository.findAll(this.getSpecification(device),pageable);
        return new ResponseEntity(page, HttpStatus.OK);
    }


    public Specification<Device> getSpecification(final Device device){
        return new Specification<Device>() {
            @Override
            public Predicate toPredicate(Root<Device> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<Predicate>();
                if(!StringUtils.isEmpty(device.getImei())){
                    predicate.add(cb.like(root.get("imei").as(String.class),"%"+device.getImei()+"%"));
                }
                if(!StringUtils.isEmpty(device.getVersion())){
                    predicate.add(cb.equal(root.get("version").as(String.class),device.getVersion()));
                }
                if(!StringUtils.isEmpty(device.getSaleChannel())){
                    predicate.add(cb.equal(root.get("saleChannel").as(String.class),device.getSaleChannel()));
                }
                if(!StringUtils.isEmpty(device.getDeviceType())){
                    predicate.add(cb.equal(root.get("deviceType").as(String.class),device.getDeviceType()));
                }
                if(device.getActivationStatus()!=null){
                    predicate.add(cb.equal(root.get("activityStatus").as(Enum.class),device.getActivationStatus()));
                }
                Predicate[] pre = new Predicate[predicate.size()];
                cq.where(predicate.toArray(pre));
                cq.orderBy(cb.desc(root.get("id").as(Integer.class)));
                return cq.getRestriction();
            }
        };
    }


    @RequestMapping("findOne")
    public Object list(){
        Device device = deviceRepository.findOne(1l);
        return device;
    }

    @RequestMapping("/edit")
    public Object edit(){
        Device device = deviceRepository.findOne(1l);
        device.setCreateDate(new Date());
        deviceRepository.save(device);
        return device;
    }

    @RequestMapping("delete")
    public Object delete(Device device){

        deviceRepository.delete(1l);
        return "succes";
    }
}
