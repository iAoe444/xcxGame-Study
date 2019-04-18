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
 * 根据plan_id删除所在的学习日记
 */
@WebServlet("/Index_updateServlet")
public class Index_plan_update extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Index_planService planService = new Index_planServiceImpl();
	private static boolean isdone=false;

	public Index_plan_update() {
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
		if(Long.parseLong(request.getParameter("plan_useTime"))>=Long.parseLong(request.getParameter("plan_setTime"))) {
				isdone=true;
		}
		
		// 1.获得参数并封装到Index_plan对象
		Index_plan plan = new Index_plan();

		plan.setPlan_id(Long.parseLong(request.getParameter("planId")));
		plan.setOpenid(request.getParameter("openid"));
		plan.setPlan_info(request.getParameter("plan_info"));
		plan.setPlan_date(TimeUtils.getTime());  //计划修改时间
		plan.setPlan_useTime(Long.parseLong(request.getParameter("plan_useTime")));
		plan.setPlan_setTime(Long.parseLong(request.getParameter("plan_setTime")));
		plan.setPlan_done(isdone);

		// 2.调用Service保存客户
		planService.update(plan);

		// ---------------------------转成json数据--------------------------------------------
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("isPlanUpdated", true);
		result.put("msg", "计划更新成功");
		// 使用Gson类需要导入gson-2.8.0.jar
		String json = new Gson().toJson(result);

		// 返回值给微信小程序
		Writer out = response.getWriter();
		out.write(json);
		out.flush();

	}

}
