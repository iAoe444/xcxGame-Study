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

	// 新增计划
	@Override
	public void add(Index_plan plan) {
		// 增加计划
		plandao.add(plan);

	}

	// 删除计划
	@Override
	public void delete(Index_plan plan) {
		plandao.delete(plan);
	}

	// 更新计划
	@Override
	public void update(Index_plan plan) {
		plandao.update(plan);

	}

	// 查询全部计划
	@Override
	public List<Index_plan> getAll() {
		// 1.获得session
		Session session = HibernateUtils.openSession();
		// 2.打开事务
		Transaction tx = session.beginTransaction();
		// 3.执行保存
		List<Index_plan> list = plandao.getAll();
		// 4.提交保存
		tx.commit();
		return list;
	}

	// 根据条件查询（模糊搜索）
	@Override
	public List<Index_plan> getAll(DetachedCriteria dc) {
		// 1.获得session
		Session session = HibernateUtils.openSession();
		// 2.打开事务
		Transaction tx = session.beginTransaction();
		// 3.执行保存
		List<Index_plan> list = plandao.getAll(dc);
		// 4.提交保存
		tx.commit();
		return list;
	}

	@Override
	public Index_plan getByID(long id) {
		// 1.获得session
		Session session = HibernateUtils.openSession();
		// 2.打开事务
		Transaction tx = session.beginTransaction();
		// 3.执行保存
		Index_plan plan = plandao.getByID(id);
		// 4.提交保存
		tx.commit();
		session.close(); // 关闭session，避免出现两个session
		return plan;
	}

	@Override
	public List<Index_plan> listByOpen_id(String openid) {
		// 1.获得session
		Session session = HibernateUtils.openSession();
		// 2.打开事务
		Transaction tx = session.beginTransaction();

		// 1、得到Query对象，并写入hql语句
		org.hibernate.Query query = session.createQuery("from Index_plan where openid = ? ");
		// 2、填写上一步中占位符的内容
		query.setParameter(0, openid);
		// 3、使用Query对象的list方法得到数据集合
		List<Index_plan> list = query.list();

		tx.commit();
		session.close();

		return list;
	}

}
