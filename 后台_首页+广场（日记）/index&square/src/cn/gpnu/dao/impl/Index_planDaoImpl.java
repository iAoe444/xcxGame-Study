package cn.gpnu.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import cn.gpnu.dao.Index_planDao;
import cn.gpnu.domain.Index_plan;
import cn.gpnu.utils.HibernateUtils;

public class Index_planDaoImpl implements Index_planDao{

	@Override
	public void add(Index_plan plan) {
		//1.���session
		Session session=HibernateUtils.openSession();
		//2.������
		Transaction tx = session.beginTransaction();
		//3.ִ������
		session.save(plan);
		
		//4.�ύ����
		tx.commit();
		//5.�ر���Դ
		session.close(); 
	}

	@Override
	public void delete(Index_plan plan) {
		// 1.���session
		Session session = HibernateUtils.openSession();
		// 2.������
		Transaction tx = session.beginTransaction();
		// 3.ִ�б���
		session.delete(plan);
		// 4.�ύɾ��
		tx.commit();
		// 5.�ر���Դ
		session.close();
	}

	@Override
	public void update(Index_plan plan) {
		//1.���session
		Session session=HibernateUtils.openSession();
		//2.������
		Transaction tx = session.beginTransaction();
		//3.ִ�и���
		session.update(plan);
		//4.�ύ����
		tx.commit();
		//5.�ر���Դ
		session.close(); 
	}

	@Override
	public List<Index_plan> getAll() {
		//1.���session
		Session session=HibernateUtils.openSession();
		//2.����Criteria����
		Criteria c=session.createCriteria(Index_plan.class);
		return c.list();
	}

	@Override
	public List<Index_plan> getAll(DetachedCriteria dc) {
		//1.���session
		Session session=HibernateUtils.openSession();
		//2.�����߶��������session
		Criteria c=dc.getExecutableCriteria(session);
		return c.list();
	}

	@Override
	public Index_plan getByID(long id) {
		//1.���session
		Session session=HibernateUtils.openSession();
		Index_plan plan=session.get(Index_plan.class, id);
		session.close();
		return plan;
	}

}
