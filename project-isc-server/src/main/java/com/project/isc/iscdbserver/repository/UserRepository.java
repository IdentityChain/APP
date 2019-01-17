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

	/**
	 * 邀请统计
	 * @param pinvitaCode
	 * @return
	 */
	int countByPinvitationCode(String pinvitaCode);

	/**
	 * 二级邀请
	 * SELECT count(1) FROM tb_user where pinvitation_code in (SELECT tu.invitation_code FROM tb_user tu where tu.pinvitation_code='K3XNTH')
	 * @param pinvitaCode
	 * @return
	 */
	@Query("SELECT count(t.userId) FROM User t where pinvitationCode in (SELECT tu.invitationCode FROM User tu where tu.pinvitationCode=?1)")
	int countByPinvitationCodeSecond(String pinvitaCode);

	/**
	 * 邀请统计列表
	 * @param pinvitaCode
	 * @param pageable
	 * @return
	 */
	List<User> findUsersByPinvitationCode(String pinvitaCode, Pageable pageable);
	//多条件分页查询
	public Page<User> findAll(Specification<User> spec, Pageable pageable);
	
//	@Query(value = "SELECT r FROM User r ORDER BY calculateValue DESC ",cxountQuery = "100",nativeQuery = true)
//	@Query("SELECT r FROM User r")
//	public List<User> findAllOrderByCalculateValueDesc(Pageable pageable);
	public List<User> findAllByOrderByCalculateValueDesc(Pageable pageable);
//	public List<User> getTop100User(Pageable pageable);

}
