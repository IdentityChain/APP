package com.project.isc.iscdbserver.schedul;

import com.project.isc.iscdbserver.server.IscServerSchedul;
import com.project.isc.iscdbserver.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class BonusPaylistScheduling {
	
	@Autowired
	private IscServerSchedul iscServerSchedul;
	/**
	 * 	* 第一位，表示秒，取值0-59
		* 第二位，表示分，取值0-59
		* 第三位，表示小时，取值0-23
		* 第四位，日期天/日，取值1-31
		* 第五位，日期月份，取值1-12
		* 第六位，星期，取值1-7，星期一，星期二...，注：不是第1周，第二周的意思
		          另外：1表示星期天，2表示星期一。
		* 第7为，年份，可以留空，取值1970-2099

		(*)星号：可以理解为每的意思，每秒，每分，每天，每月，每年...
		(?)问号：问号只能出现在日期和星期这两个位置，表示这个位置的值不确定，每天3点执行，所以第六位星期的位置，我们是不需要关注的，就是不确定的值。同时：日期和星期是两个相互排斥的元素，通过问号来表明不指定值。比如，1月10日，比如是星期1，如果在星期的位置是另指定星期二，就前后冲突矛盾了。
		(-)减号：表达一个范围，如在小时字段中使用“10-12”，则表示从10到12点，即10,11,12
		(,)逗号：表达一个列表值，如在星期字段中使用“1,2,4”，则表示星期一，星期二，星期四
		(/)斜杠：如：x/y，x是开始值，y是步长，比如在第一位（秒） 0/15就是，从0秒开始，每15秒，最后就是0，15，30，45，60    另：
		
		0 0 3 * * ?     每天3点执行
		0 5 3 * * ?     每天3点5分执行
		0 5 3 ? * *     每天3点5分执行，与上面作用相同
		0 5/10 3 * * ?  每天3点的 5分，15分，25分，35分，45分，55分这几个时间点执行
		0 10 3 ? * 1    每周星期天，3点10分 执行，注：1表示星期天    
		0 10 3 ? * 1#3  每个月的第三个星期，星期天 执行，#号只能出现在星期的位置
	 */
	
	public final static long ONE_Minute =  60 * 1000;
    
    @Scheduled(fixedDelay=ONE_Minute)
    public void fixedDelayJob(){
//        System.out.println(TimeUtil.getDateFormat(new Date())+" >>fixedDelay执行....");
    }
    
    @Scheduled(fixedRate=ONE_Minute)
    public void fixedRateJob(){
//        System.out.println(TimeUtil.getDateFormat(new Date())+" >>fixedRate执行....");
    }

//    @Scheduled(cron="0 0 0 * * ?")
    @Scheduled(cron="0 0 0/1 * * ?")
    public void cronJob(){
        System.out.println(TimeUtil.getDateFormat(new Date())+" >>每天00:00:00定时任务执行....");
		//获得矿数据
		iscServerSchedul.mainISCcoin();
		//更新矿数据
		iscServerSchedul.mainDeleteISCcoinlog();
        //获得100统计数据
        iscServerSchedul.mainTop100();
    }
    
    @Scheduled(cron="* * * * * ?")
    public void cronJobDay(){
//        System.out.println(TimeUtil.getDateFormat(new Date())+" >>cron执行....");
//        earnServerSchedul.mainComperBonuspaylist();
    }
    
    @Scheduled(cron="0 0 0/1 * * ?")
    public void cronJobForFiveSecond(){
    	iscServerSchedul.createAchievementUserList();
//        System.out.println(TimeUtil.getDateFormat(new Date())+" >>5秒cron执行....");
//    		  earnServerSchedul.mainComperBonuspaylist();
//    		  earnServerSchedul.mainAssessInspect();
    }
}
