package cn.gpnu.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import cn.gpnu.domain.Index_plan;
import cn.gpnu.utils.TimeUtils;
import cn.gpnu.utils.HibernateUtils;

//测试主键生成
public class demo {

	
	//增加信息
	@Test
	public void add() {
		//1.获得session
		Session session = HibernateUtils.openSession();
		//2.控制事物
		Transaction tx = session.beginTransaction();
		//3.执行操作
		Index_plan c=new Index_plan();
		
		c.setPlan_info("背10000个单词");
		c.setPlan_date(TimeUtils.getTime());
		
		session.save(c);
		//4.提交事务关闭资源
		tx.commit();
		session.close();
		
	}

}
