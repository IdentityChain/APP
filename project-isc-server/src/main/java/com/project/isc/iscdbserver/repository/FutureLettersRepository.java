package com.project.isc.iscdbserver.repository;

import com.project.isc.iscdbserver.entity.Activty;
import com.project.isc.iscdbserver.entity.FutureLetters;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FutureLettersRepository extends JpaSpecificationExecutor<FutureLetters>,JpaRepository<FutureLetters, String>{

    @Query("SELECT f FROM FutureLetters f where flIsPublic=?1")
    public List<FutureLetters> findByFlIsPublicOrderByCreateTimeTop10(String flIsPubliec,Pageable pageable);
}
