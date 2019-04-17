package cn.gpnu.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.gpnu.domain.Index_plan;


public interface Index_planDao {
 	//增加计划
 	void add(Index_plan plan);
 	//删除计划
 	void delete(Index_plan plan);
 	//修改计划
 	void update(Index_plan plan);
	//查询所有计划
	List<Index_plan> getAll();
	//根据id查询
	Index_plan getByID(long id);
	//根据条件查询所有计划
	List<Index_plan> getAll(DetachedCriteria dc);

}
