package fi.aktia.demo.jwtapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fi.aktia.demo.jwtapp.bean.PermissionBean;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionBean, Integer> {

}
