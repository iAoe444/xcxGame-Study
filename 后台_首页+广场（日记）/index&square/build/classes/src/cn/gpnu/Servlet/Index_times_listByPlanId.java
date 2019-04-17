package cn.gpnu.Servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

/**
 * 根据plan_id查询所有学习记录（即查询单个计划所在的所有的学习记录）
 */
@WebServlet("/Index_times_listByPlanId")
public class Index_times_listByPlanId extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Index_timesService timesService = new Index_timesServiceImpl();

	public Index_times_listByPlanId() {
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

		Long plan_id = Long.parseLong(request.getParameter("plan_id"));

		// 查询该计划id的所有学习时间段记录
		List<Index_times> list = timesService.listByPlanid(plan_id);
		//因为list中含有外键，json进行序列化时会进入表plan、表times循环递归，导致内存溢出
		List<Index_times>  list1 = new ArrayList<Index_times>();
		for (Index_times a : list) {
			Index_times b=new Index_times();
			b.setTimes_id(a.getTimes_id());
			b.setPlan_id(a.getPlan_id());
			b.setOpenid(a.getOpenid());
			b.setTimes_start(a.getTimes_start());
			b.setTimes_end(a.getTimes_end());
			b.setTimes_use(a.getTimes_use());
			list1.add(b);
			
		}

		// ---------------------------转成json数据--------------------------------------------
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("times_listByplanid", list1);
		result.put("msg", "查询计划成功");
		// 使用Gson类需要导入gson-2.8.0.jar
		String json = new Gson().toJson(result);

		// 返回值给微信小程序
		Writer out = response.getWriter();
		out.write(json);
		out.flush();

	}

}
