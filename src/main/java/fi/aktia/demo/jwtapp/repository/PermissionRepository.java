package fi.aktia.demo.jwtapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fi.aktia.demo.jwtapp.bean.PermissionBean;

/**
 * @Author Thinh Dinh
 * @CreatedDate 24.03.2020
 * @Title Full Stack Developer
 */

@Repository
public interface PermissionRepository extends JpaRepository<PermissionBean, Integer> {

}
