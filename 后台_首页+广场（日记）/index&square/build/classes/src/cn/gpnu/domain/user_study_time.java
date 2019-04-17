package cn.gpnu.domain;

import java.math.BigDecimal;

public class user_study_time {
	private long study_time_id;
	private String openid; // 微信openid
    private BigDecimal dayly_time; // 日学习时长
	private BigDecimal weekly_time; // 周学习时长
	private BigDecimal monthly_time;// 月学习时长
	private int updated_at; // 更新时间
	private int created_at; // 创建时间

	public long getStudy_time_id() {
		return study_time_id;
	}

	public void setStudy_time_id(long study_time_id) {
		this.study_time_id = study_time_id;
	}
	
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public BigDecimal getDayly_time() {
		return dayly_time;
	}

	public void setDayly_time(BigDecimal dayly_time) {
		this.dayly_time = dayly_time;
	}

	public BigDecimal getWeekly_time() {
		return weekly_time;
	}

	public void setWeekly_time(BigDecimal weekly_time) {
		this.weekly_time = weekly_time;
	}

	public BigDecimal getMonthly_time() {
		return monthly_time;
	}

	public void setMonthly_time(BigDecimal monthly_time) {
		this.monthly_time = monthly_time;
	}

	public int getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(int updated_at) {
		this.updated_at = updated_at;
	}

	public int getCreated_at() {
		return created_at;
	}

	public void setCreated_at(int created_at) {
		this.created_at = created_at;
	}

}
