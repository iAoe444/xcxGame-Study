package cn.gpnu.Service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.gpnu.Service.user_studyTimeService;
import cn.gpnu.domain.user_study_time;
import cn.gpnu.utils.HibernateUtils;

public class user_studyTimeServiceImpl implements user_studyTimeService {

	@Override
	public void addStudyTime(user_study_time study_time) {
		// 1.获得session
		Session session = HibernateUtils.openSession();
		// 2.打开事务
		Transaction tx = session.beginTransaction();
		// 3.执行增加
		session.save(study_time);
		// 4.提交保存
		tx.commit();
		// 5.关闭资源
		session.close();

	}

	@Override
	public void updateStudyTime(user_study_time study_time) {
		// 1.获得session
		Session session = HibernateUtils.openSession();
		// 2.打开事务
		Transaction tx = session.beginTransaction();
		// 3.执行增加
		session.update(study_time);
		// 4.提交保存
		tx.commit();
		// 5.关闭资源
		session.close();

	}

	@Override
	public List<user_study_time> listByOpenid(char openid) {
		// 1.获得session
		Session session = HibernateUtils.openSession();
		// 2.打开事务
		Transaction tx = session.beginTransaction();
		// 3、得到Query对象，并写入hql语句
		org.hibernate.Query query = session.createQuery("from user_study_time where openid = ? ");
		// 4、填写上一步中占位符的内容
		query.setParameter(0, openid);
		// 5、使用Query对象的list方法得到数据集合
		List<user_study_time> list = query.list();
		//6.提交保存
		tx.commit();
		session.close();
		return list;
	}

}
