package cn.gpnu.Service;

import java.util.List;

import cn.gpnu.domain.user_study_time;

public interface user_studyTimeService {
	//�����û�ѧϰ��ʱ��
	void addStudyTime(user_study_time study_time);
	
	//�����û�ѧϰ��ʱ��
	void updateStudyTime(user_study_time study_time);
	
	//�����û�openid��ѯѧϰʱ��
	List<user_study_time> listByOpenid(char openid);
	

}
