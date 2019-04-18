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

	// 增加时间段
	@Override
	public void addTimes(long planID, String openid, Index_times times, user_study_time sTime) {
		// 获得session
		Session session = HibernateUtils.openSession();
		// 打开事务
		Transaction tx = session.beginTransaction();

		/*-------------------------计划表plan更新---------------------------*/
		// 根据传来的id查询关联的计划
		Index_plan plan = session.get(Index_plan.class, planID);
		// 表达一对多
		plan.getTimes().add(times);

		plan.setPlan_useTime(plan.getPlan_useTime() + times.getTimes_use()); // 累加每次所用的时间
		if ((plan.getPlan_useTime() + times.getTimes_use()) >= plan.getPlan_setTime()) { // 实际完成时间>=设置完成时间,则完成计划
			plan.setPlan_done(true);
		}
		/*---------------学习总时长表user_study_time更新---------------------------*/
		// 查询得到需要更新的表
		org.hibernate.Query query = session.createQuery("from user_study_time where openid = ? ");
		// 2、填写上一步中占位符的内容
		query.setParameter(0, openid);
		// 3、使用Query对象的list方法得到数据
		List<user_study_time> sTList = query.list();
		System.out.println(sTList.isEmpty()+""+sTList.size());
		if (sTList.size() < 1) { // 表不存在该项，则直接增加
			session.save(sTime);
		} else {
			for (user_study_time studyTime : sTList) {
				studyTime.setDayly_time(sTime.getDayly_time().add(studyTime.getDayly_time())); // 累加时间
				studyTime.setWeekly_time(sTime.getWeekly_time().add(studyTime.getWeekly_time()));
				studyTime.setMonthly_time(sTime.getMonthly_time().add(studyTime.getMonthly_time()));
				studyTime.setUpdated_at(TimeUtils.getTimeByInt()); // 更新时间
				session.update(studyTime);
			}
		}

		/*--------------------------学习时间表times更新------------------------------*/
		// 表达多对一
		times.setPlan(plan);

		// 保存为持久化对象
		session.update(plan);
		
		session.save(times);

		// 提交保存
		tx.commit();
		// 关闭资源
		session.close();

	}

	@Override
	public List<Index_times> listByPlanid(long planId) {
		// 1.获得session
		Session session = HibernateUtils.openSession();
		// 2.打开事务
		Transaction tx = session.beginTransaction();
		// 3、得到Query对象，并写入hql语句
		org.hibernate.Query query = session.createQuery("from Index_times where plan_times_id = ? ");
		// 4、填写上一步中占位符的内容
		query.setParameter(0, planId);
		// 5、使用Query对象的list方法得到数据集合
		List<Index_times> list = query.list();
		// 6.提交保存
		tx.commit();
		session.close();
		return list;
	}

	@Override
	public List<Index_times> listByOpenid(String openid) {
		// 1.获得session
				Session session = HibernateUtils.openSession();
				// 2.打开事务
				Transaction tx = session.beginTransaction();

				// 1、得到Query对象，并写入hql语句
				org.hibernate.Query query = session.createQuery("from Index_times where openid = ? ");
				// 2、填写上一步中占位符的内容
				query.setParameter(0, openid);
				// 3、使用Query对象的list方法得到数据集合
				List<Index_times> list = query.list();
				// 4.提交保存
				tx.commit();
				session.close();

				return list;
	}

}
