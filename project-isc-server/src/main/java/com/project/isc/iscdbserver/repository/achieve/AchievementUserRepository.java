package com.project.isc.iscdbserver.repository.achieve;

import com.project.isc.iscdbserver.entity.achieve.AchievementUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementUserRepository extends JpaSpecificationExecutor<AchievementUser>,JpaRepository<AchievementUser, String>{

}
