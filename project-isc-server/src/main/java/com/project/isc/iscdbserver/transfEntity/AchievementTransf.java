package com.project.isc.iscdbserver.transfEntity;

import com.project.isc.iscdbserver.entity.achieve.Achievement;
import com.project.isc.iscdbserver.entity.achieve.AchievementUser;
import com.project.isc.iscdbserver.viewentity.achieve.AchievementVO;

/**
 * 转义类，将数据库对象转换为页面对象
 */
public class AchievementTransf {
    public static AchievementVO transfToVO(Achievement aa, AchievementUser au) {
        AchievementVO avo = new AchievementVO();
        //Achievement 转换
        avo.setAaaddiscValue(aa.getAddiscValue());
        avo.setAacalculateValue(aa.getCalculateValue());
        avo.setAacontent(aa.getContent());
        avo.setAacreateUserId(aa.getCreateUserId());
        avo.setAacreateUserName(aa.getCreateUserName());
        avo.setAagrayImgPath(aa.getGrayImgPath());
        avo.setAaimg_path(aa.getImg_path());
        avo.setAaid(aa.getId());
        avo.setAasteps(aa.getSteps());
        avo.setAatitle(aa.getTitle());
        avo.setAatype(aa.getType());
        avo.setAaupdateTime(aa.getUpdateTime());
        avo.setAavailable(aa.isAvailable());

        //AchievementUser 转换
        avo.setAuid(au.getId());
        avo.setAuachId(au.getAchId());
        avo.setAuaucreateTime(au.getCreateTime());
        avo.setAuavailable(au.isAvailable());
        avo.setAucompleteRate(au.getCompleteRate());
        avo.setAufinishTime(au.getFinishTime());
        avo.setAuis_create(au.isIs_create());
        avo.setAuuserId(au.getUserId());
        avo.setAuuserSteps(au.getUserSteps());

        return avo;
    }

}
