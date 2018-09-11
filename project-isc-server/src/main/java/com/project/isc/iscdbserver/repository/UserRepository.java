package com.project.isc.iscdbserver.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.isc.iscdbserver.entity.User;

@Repository
public interface UserRepository extends JpaSpecificationExecutor<User>,JpaRepository<User, String> {
	public User findByAccount(String account);
	public User findByUserId(String userId);
	public User findByUserPhone(String phone);
	public List<User> findByNickName(String nickName);
	
	//基于createTime，查询用户数
	@Query("select r from User r where createTime between date_format(?1,'%Y-%m-%d %H:%i:%S') and date_format(?2,'%Y-%m-%d %H:%i:%S')")
	public List<User> findByCreateTimeBetween(Date startDate,Date endDate);
	
	//多条件分页查询
	public Page<User> findAll(Specification<User> spec, Pageable pageable);
	
//	@Query("SELECT r FROM User r ORDER BY calculate_value DESC LIMIT 100")
	@Query("SELECT r FROM User r")
	public List<User> findAllOrderByCalculateValueTop100(Pageable pageable);

}
