package cn.gpnu.Service;

import java.util.List;

import cn.gpnu.domain.Index_times;
import cn.gpnu.domain.user_study_time;


public interface Index_timesService {
	//����ʱ���
	void addTimes(long planID,String openid,Index_times times,user_study_time studyTime); 
	//����openid��ѯ
	List<Index_times> listByOpenid(String openid);
	//��ѯ����ʱ��
	List<Index_times> listByPlanid(long planId);

}
