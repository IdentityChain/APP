package com.project.isc.iscdbserver.server;

import com.project.isc.iscdbserver.entity.CalculateStatistics;
import com.project.isc.iscdbserver.entity.ISCLog;
import com.project.isc.iscdbserver.entity.User;
import com.project.isc.iscdbserver.entity.achieve.Achievement;
import com.project.isc.iscdbserver.entity.achieve.AchievementUser;
import com.project.isc.iscdbserver.service.ActivtyService;
import com.project.isc.iscdbserver.service.CalculateService;
import com.project.isc.iscdbserver.service.UserService;
import com.project.isc.iscdbserver.statusType.ISCConstant;
import com.project.isc.iscdbserver.util.TimeUtil;
import com.project.isc.iscdbserver.util.UserLoginSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class IscServerSchedul {
    @Autowired
    private UserService userService;
    @Autowired
    private CalculateService calculateService;
    @Autowired
    private ActivtyService activtyService;
    @Autowired
    private UserLoginSetting userLoginSetting;

    /**
     * 统计top100
     */
    @Transactional
    public void mainTop100() {
        try {
            List<User> users = userService.findAllOrderByCalculateValueTop100();
            List<CalculateStatistics> ccss = new ArrayList<CalculateStatistics>();
            if (users != null && users.size() > 0) {
                int i = 1;
                for (User user : users) {
                    CalculateStatistics ccs = new CalculateStatistics();
                    ccs.setCalculateValue(user.getCalculateValue());
                    ccs.setCreateTime(new Date());
                    ccs.setName(user.getNickName());
                    ccs.setRanking(i);
                    i++;
                    ccs.setUserId(user.getUserId());
                    ccss.add(ccs);
                }
//			calculateService
                calculateService.deleteAllCalculateStatistics();
                calculateService.saveAllCalculateStatistics(ccss);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * blockSetting:
     * # 每日发送总量-根据分配规则分配ISC币，最高不能超过这个数目
     * totalblock: 10000
     * # 每次最大数目-最高发送的数目，根据规则部分用户可能超过这个数目
     * maxblock: 3.1415
     * # 最多存在数目
     * maxblocknumber: 30
     * # 分配规则 如：100得一份，200得2份 1000得4份 10000得8份
     * weight: 100,200,1000,10000
     * # 分配间隔 小时为单位 4小时一次
     * interval: 4
     */
    //最多可剩余资源数目
    @Value("${app.blockSetting.totalblock}")
    private double totalblock;
    //最多可剩余资源数目
    @Value("${app.blockSetting.maxblocknumber}")
    private int maxblocknumber;
    //每个资源的大小
    @Value("${app.blockSetting.maxblock}")
    private double maxblock;
    //权重
    @Value("${app.blockSetting.weight}")
    private String weight;

    /**
     * 生成矿数据
     */
    @Transactional
    public void mainISCcoin() {
        try {
            List<User> users = userService.findAll();
            List<ISCLog> isclogs = new ArrayList<ISCLog>();
            if (users != null && users.size() > 0) {
                double usedblock = 0.0;
                //判断是不是资源数目超量
                if (totalblock > maxblock * users.size()) {

                } else {
                    //如果超限降低可获得数目
                    maxblock = totalblock / users.size();
                }
                for (User user : users) {
                    List<ISCLog> isculogs = calculateService.getCalculateLogByUserIdAndStatus(user.getUserId());
                    //最多出现30个
                    if (isculogs != null && isculogs.size() < maxblocknumber) {
                        ISCLog isclog = new ISCLog();
                        isclog.setCreateTime(new Date());
                        isclog.setStatus(ISCConstant.ISC_LOG_NEW);
                        isclog.setOriginalISC(user.getIscCoin());
                        isclog.setAddISC(maxblock);
                        isclog.setFinallyISC(user.getIscCoin() + maxblock);
                        isclog.setUserId(user.getUserId());
                        isclogs.add(isclog);
                        usedblock = usedblock + maxblock;
                    } else {
                        //等于空或者大于30个不做处理
                    }
                }
                calculateService.savaAllCalculateLog(isclogs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改的矿数据
     */
    @Transactional
    public void mainDeleteISCcoinlog() {
        try {
            Date nowDate = TimeUtil.addDay(new Date(), -2);
            List<ISCLog> isclogs = calculateService.getAllCalculateLogByCreateTime(nowDate, ISCConstant.ISC_LOG_NEW);
            if (isclogs != null && isclogs.size() > 0) {
                for (ISCLog iscLog : isclogs) {
                    if (ISCConstant.ISC_LOG_NEW.equals(iscLog.getStatus())) {
                        iscLog.setStatus(ISCConstant.ISC_LOG_OVER);
                        iscLog.setConfirmTime(new Date());
                    }
                }
                calculateService.savaAllCalculateLog(isclogs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createAchievementUserList() {
        List<User> users = userService.findAll();
        List<Achievement> acmlist = calculateService.findAllAchievement();
        if (users != null && users.size() > 0 && acmlist != null && acmlist.size() > 0) {
            for (User u : users
                    ) {
                for (Achievement ach : acmlist
                        ) {
                    AchievementUser au = calculateService.findAchievementUserByUserIdAndAchId(u.getUserId(), ach.getId());
                    if (au == null) {
                        AchievementUser achievementUser = creatAchievmentUser(ach, u.getUserId());
                        calculateService.insertAchievementUser(achievementUser);
                    }
                }
            }

        }
    }

    /**
     * 如果数据为空，表示没有给这个用户创建所属于他的任务，所以需要创建
     *
     * @param ach
     * @param userid
     * @return
     */
    private AchievementUser creatAchievmentUser(Achievement ach, String userid) {
        AchievementUser acu = new AchievementUser();
        acu.setAchId(ach.getId());
        acu.setAvailable(true);
        acu.isAvailable();
        acu.setCreateTime(new Date());
        acu.setType(ach.getType());
        acu.setIs_create(false);
        acu.setUserId(userid);
        acu.setCompleteRate(0); //百分之0
        acu.setUserSteps(0);

        return acu;
    }
}
