package cn.gpnu.Service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;

import cn.gpnu.Service.Index_planService;
import cn.gpnu.dao.Index_planDao;
import cn.gpnu.dao.impl.Index_planDaoImpl;
import cn.gpnu.domain.Index_plan;
import cn.gpnu.utils.HibernateUtils;

public class Index_planServiceImpl implements Index_planService {

	private Index_planDao plandao = new Index_planDaoImpl();

	// �����ƻ�
	@Override
	public void add(Index_plan plan) {
		// ���Ӽƻ�
		plandao.add(plan);

	}

	// ɾ���ƻ�
	@Override
	public void delete(Index_plan plan) {
		plandao.delete(plan);
	}

	// ���¼ƻ�
	@Override
	public void update(Index_plan plan) {
		plandao.update(plan);

	}

	// ��ѯȫ���ƻ�
	@Override
	public List<Index_plan> getAll() {
		// 1.���session
		Session session = HibernateUtils.openSession();
		// 2.������
		Transaction tx = session.beginTransaction();
		// 3.ִ�б���
		List<Index_plan> list = plandao.getAll();
		// 4.�ύ����
		tx.commit();
		return list;
	}

	// ����������ѯ��ģ��������
	@Override
	public List<Index_plan> getAll(DetachedCriteria dc) {
		// 1.���session
		Session session = HibernateUtils.openSession();
		// 2.������
		Transaction tx = session.beginTransaction();
		// 3.ִ�б���
		List<Index_plan> list = plandao.getAll(dc);
		// 4.�ύ����
		tx.commit();
		return list;
	}

	@Override
	public Index_plan getByID(long id) {
		// 1.���session
		Session session = HibernateUtils.openSession();
		// 2.������
		Transaction tx = session.beginTransaction();
		// 3.ִ�б���
		Index_plan plan = plandao.getByID(id);
		// 4.�ύ����
		tx.commit();
		session.close(); // �ر�session�������������session
		return plan;
	}

	@Override
	public List<Index_plan> listByOpen_id(String openid) {
		// 1.���session
		Session session = HibernateUtils.openSession();
		// 2.������
		Transaction tx = session.beginTransaction();

		// 1���õ�Query���󣬲�д��hql���
		org.hibernate.Query query = session.createQuery("from Index_plan where openid = ? ");
		// 2����д��һ����ռλ��������
		query.setParameter(0, openid);
		// 3��ʹ��Query�����list�����õ����ݼ���
		List<Index_plan> list = query.list();

		tx.commit();
		session.close();

		return list;
	}

}
