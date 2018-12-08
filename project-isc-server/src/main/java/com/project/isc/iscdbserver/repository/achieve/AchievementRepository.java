package com.project.isc.iscdbserver.repository.achieve;

import com.project.isc.iscdbserver.entity.achieve.Achievement;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AchievementRepository extends JpaSpecificationExecutor<Achievement>,JpaRepository<Achievement, String>{
    List<Achievement> findAchievementsByAvailableAndType(boolean available,String type,Pageable pageable);

    List<Achievement> findAchievementsByIdIn(List<String> ids);

    //SELECT * FROM tb_achievement_user au LEFT JOIN tb_achievement a on au.ach_id=a.id where au.user_id='' and a.type=''
//    @Query("SELECT r FROM User r")
//    @Query("SELECT au,a FROM achievement au LEFT JOIN tb_achievement a on au.ach_id=a.id where au.user_id='' and a.type=''")
//    List<Object> findObjectWhithView(String userid,String type,Pageable pageable);
}
