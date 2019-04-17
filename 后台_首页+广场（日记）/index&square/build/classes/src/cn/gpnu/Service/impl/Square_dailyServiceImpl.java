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
		// 1.获得session
		Session session = HibernateUtils.openSession();
		// 2.打开事务
		Transaction tx = session.beginTransaction();
		// 3.执行增加
		session.save(daily);
		// 4.提交保存
		tx.commit();
		// 5.关闭资源
		session.close();

	}

	@Override
	public void deleteDaily(Square_daily daily) {
		// 1.获得session
		Session session = HibernateUtils.openSession();
		// 2.打开事务
		Transaction tx = session.beginTransaction();
		// 3.执行增加
		session.delete(daily);
		// 4.提交保存
		tx.commit();
		// 5.关闭资源
		session.close();

	}

	@Override
	public void updateDaily(Square_daily daily) {
		// 1.获得session
		Session session = HibernateUtils.openSession();
		// 2.打开事务
		Transaction tx = session.beginTransaction();
		// 3.执行增加
		session.update(daily);
		// 4.提交保存
		tx.commit();
		// 5.关闭资源
		session.close();

	}

	@Override
	public List<Square_daily> getAll() {
		// 1.获得session
		Session session = HibernateUtils.openSession();
		// 2.打开事务
		Transaction tx = session.beginTransaction();
		// 3.创建Criteria对象
		Criteria c = session.createCriteria(Square_daily.class);
		List<Square_daily> list = c.list();
		tx.commit();
		return list;
	}

	@Override
	public Square_daily getByID(Long id) {
		// 1.获得session
		Session session = HibernateUtils.openSession();
		Square_daily daily = session.get(Square_daily.class, id);
		return daily;
	}

	@Override
	public List<Square_daily> getAll(DetachedCriteria dc) {
		// 1.获得session
		Session session = HibernateUtils.openSession();
		// 2.打开事务
		Transaction tx = session.beginTransaction();
		// 3.将离线对象关联到session
		Criteria c = dc.getExecutableCriteria(session);

		// 4.执行保存
		List<Square_daily> list = c.list();
		// 5.提交保存
		tx.commit();
		return list;
	}

	@Override
	public List<Square_daily> listByOpen_id(String openid) {
		// 1.获得session
		Session session = HibernateUtils.openSession();
		// 2.打开事务
		Transaction tx = session.beginTransaction();

		// 1、得到Query对象，并写入hql语句
		org.hibernate.Query query = session.createQuery("from Square_daily where openid = ? ");
		// 2、填写上一步中占位符的内容
		query.setParameter(0, openid);
		// 3、使用Query对象的list方法得到数据集合
		List<Square_daily> list = query.list();

		tx.commit();
		session.close();

		return list;
	}

}
