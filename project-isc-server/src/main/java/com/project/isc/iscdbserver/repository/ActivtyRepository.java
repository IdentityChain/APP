package com.project.isc.iscdbserver.repository;

import org.springframework.stereotype.Repository;

import com.project.isc.iscdbserver.entity.Activty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface ActivtyRepository extends JpaSpecificationExecutor<Activty>,JpaRepository<Activty, String>{

}
