package cn.gpnu.Servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.gpnu.Service.Index_planService;
import cn.gpnu.Service.impl.Index_planServiceImpl;
import cn.gpnu.domain.Index_plan;
import cn.gpnu.utils.TimeUtils;

/**
 * 根据用户的open_id增加学习日记
 */
@WebServlet("/Index_addServlet")
public class Index_plan_add extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Index_planService planService = new Index_planServiceImpl();

	public Index_plan_add() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

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
		String setTime=request.getParameter("setTime");    //设置预计完成的的时间，规定格式为yyyy-MM-dd HH:mm:ss
		Long setTimes=TimeUtils.betweenTime("0000-00-00 00:00:00",setTime);   //将设置的时间转化为毫秒

		// 1.获得参数并封装到Index_plan对象
		Index_plan plan = new Index_plan();
		plan.setPlan_info(request.getParameter("plan_info"));
		plan.setPlan_date(TimeUtils.getTime()); // 时间为当前增加时间
		plan.setPlan_setTime(setTimes); // 设置时间
		plan.setPlan_done(false); // 初始值为false
		plan.setPlan_useTime((long) 0); // 初始默认值为0
		plan.setOpenid(request.getParameter("openid")); // 设置传过来的用户id

		System.out.println("plan_title=" + request.getParameter("plan_title"));

		// 2.调用Service保存客户
		planService.add(plan);

		
		//---------------------------转成json数据--------------------------------------------
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("isPlanAdd", true);
		result.put("msg", "添加成功");
		// 使用Gson类需要导入gson-2.8.0.jar
		String json = new Gson().toJson(result);

		// 返回值给微信小程序
		Writer out = response.getWriter();
		out.write(json);
		out.flush();

	}

}
