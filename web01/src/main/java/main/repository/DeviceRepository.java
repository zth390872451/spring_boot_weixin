package main.repository;

import main.domain.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/7/23 0023.
 */
public interface DeviceRepository extends JpaRepository<Device,Long> ,JpaSpecificationExecutor<Device>{

    Device findOneByImei(String imei);
}
