package com.project.isc.iscdbserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.project.isc.iscdbserver.entity.Invitation;

@Repository
public interface InvitationRepositoy extends JpaSpecificationExecutor<Invitation>,JpaRepository<Invitation, Long> {

	public List<Invitation> findBySourceUserId(Long userid);

}
