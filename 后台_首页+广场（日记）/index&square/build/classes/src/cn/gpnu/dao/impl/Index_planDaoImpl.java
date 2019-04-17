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
		//1.获得session
		Session session=HibernateUtils.openSession();
		//2.打开事务
		Transaction tx = session.beginTransaction();
		//3.执行增加
		session.save(plan);
		
		//4.提交保存
		tx.commit();
		//5.关闭资源
		session.close(); 
	}

	@Override
	public void delete(Index_plan plan) {
		// 1.获得session
		Session session = HibernateUtils.openSession();
		// 2.打开事务
		Transaction tx = session.beginTransaction();
		// 3.执行保存
		session.delete(plan);
		// 4.提交删除
		tx.commit();
		// 5.关闭资源
		session.close();
	}

	@Override
	public void update(Index_plan plan) {
		//1.获得session
		Session session=HibernateUtils.openSession();
		//2.打开事务
		Transaction tx = session.beginTransaction();
		//3.执行更新
		session.update(plan);
		//4.提交保存
		tx.commit();
		//5.关闭资源
		session.close(); 
	}

	@Override
	public List<Index_plan> getAll() {
		//1.获得session
		Session session=HibernateUtils.openSession();
		//2.创建Criteria对象
		Criteria c=session.createCriteria(Index_plan.class);
		return c.list();
	}

	@Override
	public List<Index_plan> getAll(DetachedCriteria dc) {
		//1.获得session
		Session session=HibernateUtils.openSession();
		//2.将离线对象关联到session
		Criteria c=dc.getExecutableCriteria(session);
		return c.list();
	}

	@Override
	public Index_plan getByID(long id) {
		//1.获得session
		Session session=HibernateUtils.openSession();
		Index_plan plan=session.get(Index_plan.class, id);
		session.close();
		return plan;
	}

}
