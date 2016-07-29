package main.controller;

import main.domain.Device;
import main.dto.ExcelBuilderDto;
import main.repository.DeviceCodeRepository;
import main.repository.DeviceRepository;
import main.service.DeviceCodeService;
import main.service.common.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 */
@RestController
@RequestMapping("deviceCode")
public class DeviceCodeController {
    @Autowired
    private DeviceCodeService deviceCodeService;
    /**
     *
     * 根据imei号 生成绑定码
     * 1、读取Excel文件内容
     * 2、批量save(DeviceCode对象)
     * 3、save(DeviceCodeLog对象)
     */
    @RequestMapping("/createBindCode")
    public ResponseEntity imports(MultipartFile multipartFile,String description){
        //DeviceService ：1、DeviceCode 保存绑定码信息 2、DeviceCodeLog保存 日志信息
        List mapList = new ArrayList();
        //ExcelService 读取导入的文件
        mapList = ExcelService.readExcel(multipartFile);
        //DeviceService ：1、DeviceCode 保存绑定码信息 2、DeviceCodeLog保存 日志信息
        mapList = deviceCodeService.batchAutoGenerationBindCode(mapList,description);
        /*List<Device> deviceList = new ArrayList<Device>();
        deviceList = ExcelBuilderDto.build(mapList);*/
        return new ResponseEntity(mapList,HttpStatus.OK);
    }


}
