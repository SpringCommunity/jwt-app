package fi.aktia.demo.jwtapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fi.aktia.demo.jwtapp.bean.UserBean;

@Repository
public interface UserRepository extends JpaRepository<UserBean, Integer>{

}
