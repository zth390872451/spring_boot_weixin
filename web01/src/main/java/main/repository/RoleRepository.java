package main.repository;

import main.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/7/27.
 */
public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query(value = "select * from role where id in (?1)",nativeQuery = true)
    List<Role> findAllRolesByIds(List<Long> ids);

}
