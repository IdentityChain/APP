package com.project.isc.iscdbserver.repository;

import com.project.isc.iscdbserver.entity.Dict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DictRepository extends JpaSpecificationExecutor<Dict>,JpaRepository<Dict, String> {
    Dict findDictByDictTypeAndDictKey(String type,String key);
}
