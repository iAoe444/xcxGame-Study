package cn.gpnu.Service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.gpnu.domain.Square_daily;

public interface Square_dailyService {
	//增加
	void addDaily(Square_daily daily);
	//删除
	void deleteDaily(Square_daily daily);
	//更新
	void updateDaily(Square_daily daily);
	//查询全部
	List<Square_daily> getAll();
	//根据id查询
	Square_daily getByID(Long id);
	//条件查询
	List<Square_daily> getAll(DetachedCriteria dc);
	//根据用户open_id查询所有学习日记
	List<Square_daily> listByOpen_id(String openid);

}
