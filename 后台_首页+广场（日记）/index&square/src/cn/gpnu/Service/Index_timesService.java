package cn.gpnu.Service;

import java.util.List;

import cn.gpnu.domain.Index_times;
import cn.gpnu.domain.user_study_time;


public interface Index_timesService {
	//增加时间段
	void addTimes(long planID,String openid,Index_times times,user_study_time studyTime); 
	//根据openid查询
	List<Index_times> listByOpenid(String openid);
	//查询所有时间
	List<Index_times> listByPlanid(long planId);

}
