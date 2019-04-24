package cn.gpnu.Service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.gpnu.domain.Square_daily;

public interface Square_dailyService {
	//����
	void addDaily(Square_daily daily);
	//ɾ��
	void deleteDaily(Square_daily daily);
	//����
	void updateDaily(Square_daily daily);
	//��ѯȫ��
	List<Square_daily> getAll();
	//����id��ѯ
	Square_daily getByID(Long id);
	//������ѯ
	List<Square_daily> getAll(DetachedCriteria dc);
	//�����û�open_id��ѯ����ѧϰ�ռ�
	List<Square_daily> listByOpen_id(String openid);

}
