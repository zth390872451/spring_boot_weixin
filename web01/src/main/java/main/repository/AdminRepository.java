package main.repository;

import main.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepository extends JpaRepository<Admin, Long>{
    Admin findOneByUserName(String username);
}
