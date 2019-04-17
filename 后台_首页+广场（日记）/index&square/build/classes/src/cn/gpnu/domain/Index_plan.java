package cn.gpnu.domain;

import java.util.HashSet;
import java.util.Set;

public class Index_plan {
	private Long plan_id; // 计划编号
	private String plan_info; // 计划详细信息
	private String plan_date; // 计划创建时间（时间点）
	private Long plan_setTime; // 计划设置用时（时间段）
	private Long plan_useTime; // 计划实际完成用时（时间段）
	private boolean plan_done; // 是否完成（设置用时>实际用时->true）
	private String openid; // 用户的id(类似外键)

	// 使用set集合，表达一对多关系
	private Set<Index_times> times = new HashSet<Index_times>();

	public Set<Index_times> getTimes() {
		return times;
	}

	public void setTimes(Set<Index_times> times) {
		this.times = times;
	}

	public Long getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(Long plan_id) {
		this.plan_id = plan_id;
	}

	public String getPlan_info() {
		return plan_info;
	}

	public void setPlan_info(String plan_info) {
		this.plan_info = plan_info;
	}

	public String getPlan_date() {
		return plan_date;
	}

	public void setPlan_date(String plan_date) {
		this.plan_date = plan_date;
	}

	public Long getPlan_useTime() {
		return plan_useTime;
	}

	public void setPlan_useTime(Long plan_useTime) {
		this.plan_useTime = plan_useTime;
	}

	public Long getPlan_setTime() {
		return plan_setTime;
	}

	public void setPlan_setTime(Long plan_setTime) {
		this.plan_setTime = plan_setTime;
	}

	public boolean isPlan_done() {
		return plan_done;
	}

	public void setPlan_done(boolean plan_done) {
		this.plan_done = plan_done;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Override
	public String toString() {
		return "plan [plan_id=" + plan_id + "]";
	}

}
