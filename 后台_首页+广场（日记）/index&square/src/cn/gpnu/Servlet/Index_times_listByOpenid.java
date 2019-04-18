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
 * 根据openid查询所有时间段
 */
@WebServlet("/Index_times_listByOpenid")
public class Index_times_listByOpenid extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Index_timesService timesService = new Index_timesServiceImpl();

	public Index_times_listByOpenid() {
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

		String openid = request.getParameter("openid");

		List<Index_times> list = timesService.listByOpenid(openid);
		List<Index_times> list1 = new ArrayList<Index_times>();
		for (Index_times a : list) {
			Index_times b = new Index_times();
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
		result.put("times_listByopenid", list1);
		result.put("msg", "openid查询所有时间段成功");
		// 使用Gson类需要导入gson-2.8.0.jar
		String json = new Gson().toJson(result);

		// 返回值给微信小程序
		Writer out = response.getWriter();
		out.write(json);
		out.flush();

	}

}
