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

/**
 * 根据用户的plan_id删除所在的学习日记
 */
@WebServlet("/Index_deleteServlet")
public class Index_plan_delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Index_planService planService = new Index_planServiceImpl();

	public Index_plan_delete() {
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
		long id = Long.parseLong(request.getParameter("planId"));

		// 1.根据id查询所在的Index_plan对象
		Index_plan plan = planService.getByID(id);

		// 2.调用Service删除该计划
		planService.delete(plan);

		// ---------------------------转成json数据--------------------------------------------
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("isPlanDelete", true);
		result.put("msg", "计划删除成功");
		// 使用Gson类需要导入gson-2.8.0.jar
		String json = new Gson().toJson(result);

		// 返回值给微信小程序
		Writer out = response.getWriter();
		out.write(json);
		out.flush();

	}

}
