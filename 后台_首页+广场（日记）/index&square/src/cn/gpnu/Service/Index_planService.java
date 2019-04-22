package cn.gpnu.Service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.gpnu.domain.Index_plan;

public interface Index_planService {
	
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
	//����������ѯ�ƻ�
	List<Index_plan> getAll(DetachedCriteria dc);
	//�����û���open_id��ѯ�ƻ�
	List<Index_plan> listByOpen_id(String open_id);

}
