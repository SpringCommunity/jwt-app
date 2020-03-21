package fi.aktia.demo.jwtapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fi.aktia.demo.jwtapp.bean.RoleBean;

@Repository
public interface RoleRepository extends JpaRepository<RoleBean, Integer> {

}
