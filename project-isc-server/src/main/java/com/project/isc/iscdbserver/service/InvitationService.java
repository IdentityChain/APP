package com.project.isc.iscdbserver.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.isc.iscdbserver.entity.Invitation;
import com.project.isc.iscdbserver.repository.InvitationRepository;

@Service
public class InvitationService {

	@Autowired
	private InvitationRepository invitationRepositoy;
	
	
	/**
	 * 获得本用户下的邀请数目
	 * @param userid
	 * @return
	 */
	public int getInvitationCountByUser(String userid) {
		List<Invitation> list = this.invitationRepositoy.findBySourceUserId(userid);
		if (list!=null && list.size()>0) {
			return list.size();
		}
		return 0;
	}
	
	// 新增 邀请信息
	@Transactional
	public Invitation save(Invitation invitation) {
		return this.invitationRepositoy.save(invitation);
	}

	// 批量更新或者新加用户
	@Transactional
	public List<Invitation> saveAll(List<Invitation> invitationupdate) {
		if (invitationupdate != null && invitationupdate.size() > 0) {
			for (Invitation invitation : invitationupdate) {
				this.invitationRepositoy.save(invitation);
			}
		}
		return invitationupdate;
	}

	// 删除用户
	@Transactional
	public void delete(Invitation invitation) {
		this.invitationRepositoy.delete(invitation);
	}

	// 删除所有用户数据
	public void deleteAllUser() {
		this.invitationRepositoy.deleteAll();
	}

}
