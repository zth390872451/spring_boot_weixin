package main.repository;

import main.domain.DeviceCode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2016/7/28.
 */
public interface DeviceCodeRepository extends JpaRepository<DeviceCode,Long>{

    DeviceCode findOneByImei(String imei);

}
