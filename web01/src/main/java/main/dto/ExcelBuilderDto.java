package main.dto;

import main.domain.Device;
import main.domain.DeviceCode;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/28.
 */
public class ExcelBuilderDto implements Serializable{

    /**
     * 构建导入的设备信息
     *IMEI	终端类型	协议版本	出厂日期	服务器配置	销售国家	渠道号
     * @param mapList
     * @return
     */
    public static List<Device> build(List<Map<String,String>> mapList){
        List<Device> deviceList = new ArrayList<Device>();
        Map<String,String> map = null;
        Device device = null;
        for (int i = 1; i < mapList.size(); i++) {
            device = new Device();
            map = mapList.get(i);
            String imei = map.get(0);
            String deviceType = map.get(1);
            String version = map.get(2);
            String exFactoryDate = map.get(3);
            String server = map.get(4);
            String saleCountry = map.get(5);
            String saleChannel = map.get(6);
            String flag = map.get(7);
            if (!StringUtils.isEmpty(imei)){
                device.setImei(imei);
            }
            if (!StringUtils.isEmpty(deviceType)){
                device.setDeviceType(deviceType);
            }
            if (!StringUtils.isEmpty(version)){
                device.setVersion(version);
            }
            if (!StringUtils.isEmpty(exFactoryDate)){
                try {
                    device.setExFactoryDate(DateUtils.parseDate(exFactoryDate,new String[]{"yyyyMMddHH"}));
                } catch (ParseException e) {
                    System.out.println("e = " + e);
                    e.printStackTrace();
                    device.setExFactoryDate(new Date());
                }
            }else {
                device.setExFactoryDate(new Date());
            }


            if (!StringUtils.isEmpty(server)){
            //              不设置
            }
            if (!StringUtils.isEmpty(saleCountry)){
                device.setSaleChannel(saleCountry);
            }
            if (!StringUtils.isEmpty(saleChannel)){
                device.setSaleChannel(saleChannel);
            }
            if (!StringUtils.isEmpty(flag)){
                device.setFlag(Integer.valueOf(flag));
            }
            deviceList.add(device);
        }
        return deviceList;
    }




}
