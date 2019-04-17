package cn.gpnu.domain;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class Index_times {
	private long times_id; // 完成次数所在的id
	private String times_start; // 开始时间
	private String times_end; // 结束时间
	private Long times_use;  //开始到结束的时间
	private String openid;
	
	//表达多对一关系
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "market_id")
	private Index_plan plan;
	//不与数据库中的列对应，只是为了接受表单参数
	private Long plan_id;

	@JsonBackReference
	public Long getPlan_id() {
		return plan_id;
	}
	@JsonBackReference
	public void setPlan_id(Long plan_id) {
		this.plan_id = plan_id;
	}

	//josn序列化时声明取消序列化该外键
	@com.fasterxml.jackson.annotation.JsonBackReference
	public Index_plan getPlan() {
		return plan;
	}
	@com.fasterxml.jackson.annotation.JsonBackReference
	public void setPlan(Index_plan plan) {
		this.plan = plan;
	}
	
	
	public long getTimes_id() {
		return times_id;
	}
	
	public void setTimes_id(long times_id) {
		this.times_id = times_id;
	}
	public String getTimes_start() {
		return times_start;
	}
	public void setTimes_start(String times_start) {
		this.times_start = times_start;
	}
	public String getTimes_end() {
		return times_end;
	}
	public void setTimes_end(String times_end) {
		this.times_end = times_end;
	}
	public Long getTimes_use() {
		return times_use;
	}
	public void setTimes_use(Long times_use) {
		this.times_use = times_use;
	}
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	

}
