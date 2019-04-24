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
		// 1.���session
		Session session = HibernateUtils.openSession();
		// 2.������
		Transaction tx = session.beginTransaction();
		// 3.ִ������
		session.save(study_time);
		// 4.�ύ����
		tx.commit();
		// 5.�ر���Դ
		session.close();

	}

	@Override
	public void updateStudyTime(user_study_time study_time) {
		// 1.���session
		Session session = HibernateUtils.openSession();
		// 2.������
		Transaction tx = session.beginTransaction();
		// 3.ִ������
		session.update(study_time);
		// 4.�ύ����
		tx.commit();
		// 5.�ر���Դ
		session.close();

	}

	@Override
	public List<user_study_time> listByOpenid(char openid) {
		// 1.���session
		Session session = HibernateUtils.openSession();
		// 2.������
		Transaction tx = session.beginTransaction();
		// 3���õ�Query���󣬲�д��hql���
		org.hibernate.Query query = session.createQuery("from user_study_time where openid = ? ");
		// 4����д��һ����ռλ��������
		query.setParameter(0, openid);
		// 5��ʹ��Query�����list�����õ����ݼ���
		List<user_study_time> list = query.list();
		//6.�ύ����
		tx.commit();
		session.close();
		return list;
	}

}
