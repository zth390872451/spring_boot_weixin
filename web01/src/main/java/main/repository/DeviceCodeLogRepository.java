package main.repository;

import main.domain.DeviceCodeLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2016/7/28.
 */
public interface DeviceCodeLogRepository extends JpaRepository<DeviceCodeLog,Long>{
}
