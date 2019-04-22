package cn.gpnu.Service.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;

import cn.gpnu.Service.Square_dailyService;
import cn.gpnu.domain.Index_plan;
import cn.gpnu.domain.Square_daily;
import cn.gpnu.utils.HibernateUtils;

public class Square_dailyServiceImpl implements Square_dailyService {

	@Override
	public void addDaily(Square_daily daily) {
		// 1.���session
		Session session = HibernateUtils.openSession();
		// 2.������
		Transaction tx = session.beginTransaction();
		// 3.ִ������
		session.save(daily);
		// 4.�ύ����
		tx.commit();
		// 5.�ر���Դ
		session.close();

	}

	@Override
	public void deleteDaily(Square_daily daily) {
		// 1.���session
		Session session = HibernateUtils.openSession();
		// 2.������
		Transaction tx = session.beginTransaction();
		// 3.ִ������
		session.delete(daily);
		// 4.�ύ����
		tx.commit();
		// 5.�ر���Դ
		session.close();

	}

	@Override
	public void updateDaily(Square_daily daily) {
		// 1.���session
		Session session = HibernateUtils.openSession();
		// 2.������
		Transaction tx = session.beginTransaction();
		// 3.ִ������
		session.update(daily);
		// 4.�ύ����
		tx.commit();
		// 5.�ر���Դ
		session.close();

	}

	@Override
	public List<Square_daily> getAll() {
		// 1.���session
		Session session = HibernateUtils.openSession();
		// 2.������
		Transaction tx = session.beginTransaction();
		// 3.����Criteria����
		Criteria c = session.createCriteria(Square_daily.class);
		List<Square_daily> list = c.list();
		tx.commit();
		return list;
	}

	@Override
	public Square_daily getByID(Long id) {
		// 1.���session
		Session session = HibernateUtils.openSession();
		Square_daily daily = session.get(Square_daily.class, id);
		return daily;
	}

	@Override
	public List<Square_daily> getAll(DetachedCriteria dc) {
		// 1.���session
		Session session = HibernateUtils.openSession();
		// 2.������
		Transaction tx = session.beginTransaction();
		// 3.�����߶��������session
		Criteria c = dc.getExecutableCriteria(session);

		// 4.ִ�б���
		List<Square_daily> list = c.list();
		// 5.�ύ����
		tx.commit();
		return list;
	}

	@Override
	public List<Square_daily> listByOpen_id(String openid) {
		// 1.���session
		Session session = HibernateUtils.openSession();
		// 2.������
		Transaction tx = session.beginTransaction();

		// 1���õ�Query���󣬲�д��hql���
		org.hibernate.Query query = session.createQuery("from Square_daily where openid = ? ");
		// 2����д��һ����ռλ��������
		query.setParameter(0, openid);
		// 3��ʹ��Query�����list�����õ����ݼ���
		List<Square_daily> list = query.list();

		tx.commit();
		session.close();

		return list;
	}

}
