package cn.gpnu.domain;

import java.util.HashSet;
import java.util.Set;

public class Index_plan {
	private Long plan_id; // �ƻ����
	private String plan_info; // �ƻ���ϸ��Ϣ
	private String plan_date; // �ƻ�����ʱ�䣨ʱ��㣩
	private Long plan_setTime; // �ƻ�������ʱ��ʱ��Σ�
	private Long plan_useTime; // �ƻ�ʵ�������ʱ��ʱ��Σ�
	private boolean plan_done; // �Ƿ���ɣ�������ʱ>ʵ����ʱ->true��
	private String openid; // �û���id(�������)

	// ʹ��set���ϣ����һ�Զ��ϵ
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
