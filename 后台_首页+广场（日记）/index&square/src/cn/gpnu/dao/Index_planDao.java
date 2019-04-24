package cn.gpnu.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.gpnu.domain.Index_plan;


public interface Index_planDao {
 	//���Ӽƻ�
 	void add(Index_plan plan);
 	//ɾ���ƻ�
 	void delete(Index_plan plan);
 	//�޸ļƻ�
 	void update(Index_plan plan);
	//��ѯ���мƻ�
	List<Index_plan> getAll();
	//����id��ѯ
	Index_plan getByID(long id);
	//����������ѯ���мƻ�
	List<Index_plan> getAll(DetachedCriteria dc);

}
