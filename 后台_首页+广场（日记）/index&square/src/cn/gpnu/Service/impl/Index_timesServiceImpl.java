package cn.gpnu.Service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.gpnu.Service.Index_timesService;
import cn.gpnu.domain.Index_plan;
import cn.gpnu.domain.Index_times;
import cn.gpnu.domain.user_study_time;
import cn.gpnu.utils.HibernateUtils;
import cn.gpnu.utils.TimeUtils;

public class Index_timesServiceImpl implements Index_timesService {

	// ����ʱ���
	@Override
	public void addTimes(long planID, String openid, Index_times times, user_study_time sTime) {
		// ���session
		Session session = HibernateUtils.openSession();
		// ������
		Transaction tx = session.beginTransaction();

		/*-------------------------�ƻ���plan����---------------------------*/
		// ���ݴ�����id��ѯ�����ļƻ�
		Index_plan plan = session.get(Index_plan.class, planID);
		// ���һ�Զ�
		plan.getTimes().add(times);

		plan.setPlan_useTime(plan.getPlan_useTime() + times.getTimes_use()); // �ۼ�ÿ�����õ�ʱ��
		if ((plan.getPlan_useTime() + times.getTimes_use()) >= plan.getPlan_setTime()) { // ʵ�����ʱ��>=�������ʱ��,����ɼƻ�
			plan.setPlan_done(true);
		}
		/*---------------ѧϰ��ʱ����user_study_time����---------------------------*/
		// ��ѯ�õ���Ҫ���µı�
		org.hibernate.Query query = session.createQuery("from user_study_time where openid = ? ");
		// 2����д��һ����ռλ��������
		query.setParameter(0, openid);
		// 3��ʹ��Query�����list�����õ�����
		List<user_study_time> sTList = query.list();
		System.out.println(sTList.isEmpty()+""+sTList.size());
		if (sTList.size() < 1) { // �����ڸ����ֱ������
			session.save(sTime);
		} else {
			for (user_study_time studyTime : sTList) {
				studyTime.setDayly_time(sTime.getDayly_time().add(studyTime.getDayly_time())); // �ۼ�ʱ��
				studyTime.setWeekly_time(sTime.getWeekly_time().add(studyTime.getWeekly_time()));
				studyTime.setMonthly_time(sTime.getMonthly_time().add(studyTime.getMonthly_time()));
				studyTime.setUpdated_at(TimeUtils.getTimeByInt()); // ����ʱ��
				session.update(studyTime);
			}
		}

		/*--------------------------ѧϰʱ���times����------------------------------*/
		// �����һ
		times.setPlan(plan);

		// ����Ϊ�־û�����
		session.update(plan);
		
		session.save(times);

		// �ύ����
		tx.commit();
		// �ر���Դ
		session.close();

	}

	@Override
	public List<Index_times> listByPlanid(long planId) {
		// 1.���session
		Session session = HibernateUtils.openSession();
		// 2.������
		Transaction tx = session.beginTransaction();
		// 3���õ�Query���󣬲�д��hql���
		org.hibernate.Query query = session.createQuery("from Index_times where plan_times_id = ? ");
		// 4����д��һ����ռλ��������
		query.setParameter(0, planId);
		// 5��ʹ��Query�����list�����õ����ݼ���
		List<Index_times> list = query.list();
		// 6.�ύ����
		tx.commit();
		session.close();
		return list;
	}

	@Override
	public List<Index_times> listByOpenid(String openid) {
		// 1.���session
				Session session = HibernateUtils.openSession();
				// 2.������
				Transaction tx = session.beginTransaction();

				// 1���õ�Query���󣬲�д��hql���
				org.hibernate.Query query = session.createQuery("from Index_times where openid = ? ");
				// 2����д��һ����ռλ��������
				query.setParameter(0, openid);
				// 3��ʹ��Query�����list�����õ����ݼ���
				List<Index_times> list = query.list();
				// 4.�ύ����
				tx.commit();
				session.close();

				return list;
	}

}
