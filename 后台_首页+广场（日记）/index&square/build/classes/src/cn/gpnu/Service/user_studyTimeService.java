package cn.gpnu.Service;

import java.util.List;

import cn.gpnu.domain.user_study_time;

public interface user_studyTimeService {
	//增加用户学习总时间
	void addStudyTime(user_study_time study_time);
	
	//更新用户学习总时间
	void updateStudyTime(user_study_time study_time);
	
	//根据用户openid查询学习时间
	List<user_study_time> listByOpenid(char openid);
	

}
