package cn.gpnu.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import cn.gpnu.domain.Index_plan;
import cn.gpnu.utils.TimeUtils;
import cn.gpnu.utils.HibernateUtils;

//������������
public class demo {

	
	//������Ϣ
	@Test
	public void add() {
		//1.���session
		Session session = HibernateUtils.openSession();
		//2.��������
		Transaction tx = session.beginTransaction();
		//3.ִ�в���
		Index_plan c=new Index_plan();
		
		c.setPlan_info("��10000������");
		c.setPlan_date(TimeUtils.getTime());
		
		session.save(c);
		//4.�ύ����ر���Դ
		tx.commit();
		session.close();
		
	}

}
