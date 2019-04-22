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
 * 增加单次学习记录（开始和介绍时间）
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
		/* 设置响应头允许ajax跨域访问 */
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");

		// ----------------获取微信小程序传过来的参数值-----------------------------------------------------------
		String startTime = request.getParameter("startTime");// 格式"2019-04-02 20:00:00"
		String endTime = request.getParameter("endTime");
		String openid = request.getParameter("openid");
		System.out.println("接收id："+openid);
		Long planId = Long.parseLong(request.getParameter("planId")); // 记录时间所在的计划id
		System.out.println("planId:"+planId);
		Long Time = TimeUtils.betweenTime(startTime, endTime); // 学习的时间段（也是累加的学习日、周、月时间）
		BigDecimal bd = TimeUtils.fomatTime(Time);
		System.out.println("间隔时间："+bd);

		// 封装Index_times
		Index_times times = new Index_times();
		times.setOpenid(openid);
		times.setTimes_start(startTime);
		times.setTimes_end(endTime);
		times.setTimes_use(Time);
		times.setPlan_id(planId);

		// 封装user_study_time
		user_study_time studyTime = new user_study_time();
		studyTime.setOpenid(openid); // 用户openid
		studyTime.setDayly_time(bd); // 日排行
		studyTime.setWeekly_time(bd);
		studyTime.setMonthly_time(bd);
		studyTime.setUpdated_at(0); // 初始值为更新
		studyTime.setCreated_at(TimeUtils.getTimeByInt());

		// 调用Service进行增加时间段
		timesService.addTimes(planId,openid, times, studyTime);
		

		// ---------------------------转成json数据--------------------------------------------
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("isTimesAdd", true);
		result.put("msg", "时间添加成功");
		// 使用Gson类需要导入gson-2.8.0.jar
		String json = new Gson().toJson(result);

		// 返回值给微信小程序
		Writer out = response.getWriter();
		out.write(json);
		out.flush();
	}

}
