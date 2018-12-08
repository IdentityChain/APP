package com.project.isc.iscdbserver.repository.achieve;

import com.project.isc.iscdbserver.entity.achieve.AchievementUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AchievementUserRepository extends JpaSpecificationExecutor<AchievementUser>,JpaRepository<AchievementUser, String>{

    List<AchievementUser> findAchievementUsersByUserIdAndType(String userid,String type,Pageable pageable);

    List<AchievementUser> findAchievementUsersByAchIdIn(Collection<String> achids);

    List<AchievementUser> findAchievementUsersByUserIdAndAchIdIn(String userid,Collection<String> achids);

    AchievementUser findAchievementUserByUserIdAndAchId(String userid,String achid);
}
