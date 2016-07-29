package main.service;

import main.domain.Device;
import main.domain.DeviceCode;
import main.domain.Server;
import main.repository.DeviceCodeRepository;
import main.repository.DeviceRepository;
import main.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/23 0023.
 */
@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private ServerRepository serverRepository;
    @Autowired
    private DeviceCodeRepository deviceCodeRepository;

    /**
     * 设备导入
     * 1、绑定码已经生成
     * 2、绑定号尚未生成
     *
     * @param deviceList
     * @param serverId
     * @return 缺失绑定码的imei的设备
     */
    public List<String> imports(List<Device> deviceList, Long serverId) {
        List<String> imeiList = new ArrayList<String>();
        Device isExist = null;
        Server server = serverRepository.findOne(serverId);
        if (server==null){
           return null;
        }
        for (Device device : deviceList) {

            isExist = deviceRepository.findOneByImei(device.getImei());
            if (isExist==null){
                device.setServer(server);
            }else {//已经存在对应imei号的该设备
                isExist = null;

            }
            DeviceCode deviceCode = deviceCodeRepository.findOneByImei(device.getImei());
            if (deviceCode!=null){//更新绑定码
                device.setBindCode(deviceCode.getBindCode());
            }else {//绑定码未生成，则提示 生成绑定码
                imeiList.add(device.getImei());
            }
            deviceRepository.save(device);
        }
        return imeiList;
    }
}
