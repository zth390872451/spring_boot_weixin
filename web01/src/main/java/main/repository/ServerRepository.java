package main.repository;

import main.domain.Server;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2016/7/28.
 */
public interface ServerRepository  extends JpaRepository<Server,Long>{

}
