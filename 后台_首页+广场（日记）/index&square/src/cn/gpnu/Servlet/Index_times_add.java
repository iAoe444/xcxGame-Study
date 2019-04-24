package cn.gpnu.Servlet;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.gpnu.Service.Index_timesService;
import cn.gpnu.Service.impl.Index_timesServiceImpl;
import cn.gpnu.domain.Index_times;
import cn.gpnu.domain.user_study_time;
import cn.gpnu.utils.TimeUtils;

/**
 * ���ӵ���ѧϰ��¼����ʼ�ͽ���ʱ�䣩
 */
@WebServlet("/Index_timesServlet")
public class Index_times_add extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Index_timesService timesService = new Index_timesServiceImpl();

	public Index_times_add() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ---------------------------------------------------------------------------
		response.setContentType("text/html;charset=utf-8");
		/* ������Ӧͷ����ajax������� */
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* �Ǻű�ʾ���е��������󶼿��Խ��ܣ� */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");

		// ----------------��ȡ΢��С���򴫹����Ĳ���ֵ-----------------------------------------------------------
		String startTime = request.getParameter("startTime");// ��ʽ"2019-04-02 20:00:00"
		String endTime = request.getParameter("endTime");
		String openid = request.getParameter("openid");
		System.out.println("����id��"+openid);
		Long planId = Long.parseLong(request.getParameter("planId")); // ��¼ʱ�����ڵļƻ�id
		System.out.println("planId:"+planId);
		Long Time = TimeUtils.betweenTime(startTime, endTime); // ѧϰ��ʱ��Σ�Ҳ���ۼӵ�ѧϰ�ա��ܡ���ʱ�䣩
		BigDecimal bd = TimeUtils.fomatTime(Time);
		System.out.println("���ʱ�䣺"+bd);

		// ��װIndex_times
		Index_times times = new Index_times();
		times.setOpenid(openid);
		times.setTimes_start(startTime);
		times.setTimes_end(endTime);
		times.setTimes_use(Time);
		times.setPlan_id(planId);

		// ��װuser_study_time
		user_study_time studyTime = new user_study_time();
		studyTime.setOpenid(openid); // �û�openid
		studyTime.setDayly_time(bd); // ������
		studyTime.setWeekly_time(bd);
		studyTime.setMonthly_time(bd);
		studyTime.setUpdated_at(0); // ��ʼֵΪ����
		studyTime.setCreated_at(TimeUtils.getTimeByInt());

		// ����Service��������ʱ���
		timesService.addTimes(planId,openid, times, studyTime);
		

		// ---------------------------ת��json����--------------------------------------------
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("isTimesAdd", true);
		result.put("msg", "ʱ����ӳɹ�");
		// ʹ��Gson����Ҫ����gson-2.8.0.jar
		String json = new Gson().toJson(result);

		// ����ֵ��΢��С����
		Writer out = response.getWriter();
		out.write(json);
		out.flush();
	}

}
