package cn.gpnu.domain;

public class Square_daily {
	private Long daily_id; // 学习日记时间
	private String daily_title;// 学习日记标题
	private String daily_info;// 学习日记信息
	private String daily_date;// 学习日记设置的时间
	private String daily_classify; // 学习日志分类
	private String openid; // 用户的open_id

	
	public Long getDaily_id() {
		return daily_id;
	}

	public void setDaily_id(Long daily_id) {
		this.daily_id = daily_id;
	}

	public String getDaily_title() {
		return daily_title;
	}

	public void setDaily_title(String daily_title) {
		this.daily_title = daily_title;
	}

	public String getDaily_info() {
		return daily_info;
	}

	public void setDaily_info(String daily_info) {
		this.daily_info = daily_info;
	}

	public String getDaily_date() {
		return daily_date;
	}

	public void setDaily_date(String daily_date) {
		this.daily_date = daily_date;
	}

	public String getDaily_classify() {
		return daily_classify;
	}

	public void setDaily_classify(String daily_classify) {
		this.daily_classify = daily_classify;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	


}
