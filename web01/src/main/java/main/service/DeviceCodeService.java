package main.service;

import main.domain.Device;
import main.domain.DeviceCode;
import main.domain.DeviceCodeLog;
import main.repository.DeviceCodeLogRepository;
import main.repository.DeviceCodeRepository;
import main.repository.DeviceRepository;
import main.util.Encryptor;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Administrator on 2016/7/28.
 */
//TODO 未作事务控制@Transaction
@Service
public class DeviceCodeService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DeviceCodeRepository deviceCodeRepository;
    @Autowired
    private DeviceCodeLogRepository deviceCodeLogRepository;
    @Autowired
    private DeviceRepository deviceRepository;
    /**
     * 1、批量根据IMEI号生成绑定码
     * 2、绑定码导入日志记录
     * @param mapList
     * @param description
     * @return
     */
    public List<DeviceCode> batchAutoGenerationBindCode(List<Map<String,String>> mapList,String description){
        List<DeviceCode> deviceCodeList = new ArrayList<DeviceCode>();
        Map<String,String> map = null;
        //批次号= "时间"+ "_" + "随机3个数字"
        String lot = DateFormatUtils.format(new Date(),"yyyyMMddHHmmss")+"_"+ RandomStringUtils.random(3,"123456");
        StringBuilder failReason = new StringBuilder();
        DeviceCodeLog deviceCodeLog = new DeviceCodeLog();

        for (int i = 0; i < mapList.size(); i++) {
            map = mapList.get(i);
            String imei = map.get(0);
            DeviceCode isExist = deviceCodeRepository.findOneByImei(imei);
            if (isExist==null){
                String bindCode = Encryptor.encrypt(imei+ UUID.randomUUID().toString());
                String qrCode = "qrCode:"+bindCode;
                isExist = new DeviceCode();
                isExist.setImei(imei);
                isExist.setLot(lot);
                isExist.setBindCode(bindCode);
                isExist.setQrCode(qrCode);
                deviceCodeRepository.save(isExist);
                deviceCodeList.add(isExist);
            }else {
                deviceCodeLog.setStatusType(DeviceCodeLog.StatusType.FAIL);
                failReason.append("该imei=["+imei+"]曾经导入过，生成过绑定码了");
                logger.info("该imei={}曾经导入过，生成过绑定码了",imei);
            }
            //同步设备的绑定码
            Device device = deviceRepository.findOneByImei(imei);
            if (!device.getBindCode().equals(isExist.getBindCode())){
                device.setBindCode(isExist.getBindCode());
                deviceRepository.save(device);
            }
        }

        deviceCodeLog.setLot(lot);
        deviceCodeLog.setDescription(description);
        deviceCodeLog.setFailReason(failReason.toString());
        if (deviceCodeLog.getStatusType()==null)
            deviceCodeLog.setStatusType(DeviceCodeLog.StatusType.SUCCESS);
        deviceCodeLogRepository.save(deviceCodeLog);
        return deviceCodeList;
    }
}
