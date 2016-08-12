package main.repository;

import main.domain.DataSourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2016/8/4 0004.
 */
public interface DataSourceEntityRepository extends JpaRepository<DataSourceEntity,Long>{

}
