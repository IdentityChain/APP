package com.project.isc.iscdbserver.repository;

import com.project.isc.iscdbserver.entity.Activty;
import com.project.isc.iscdbserver.entity.FutureLetters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FutureLettersRepository extends JpaSpecificationExecutor<FutureLetters>,JpaRepository<FutureLetters, String>{

}
