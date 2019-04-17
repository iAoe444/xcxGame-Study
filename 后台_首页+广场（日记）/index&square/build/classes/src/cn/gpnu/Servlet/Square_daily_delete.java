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

import cn.gpnu.Service.Square_dailyService;
import cn.gpnu.Service.impl.Square_dailyServiceImpl;
import cn.gpnu.domain.Square_daily;

/**
 * 用户根据id删除所在的日记
 */
@WebServlet("/Square_daily_delete")
public class Square_daily_delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Square_dailyService dailyService = new Square_dailyServiceImpl();

	public Square_daily_delete() {
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

		Long daily_id = Long.parseLong(request.getParameter("daily_id"));

		// 根据id查询所在的日志
		Square_daily daily = dailyService.getByID(daily_id);
		dailyService.deleteDaily(daily);


		// ---------------------------转成json数据--------------------------------------------
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("isDailyDelete", true);
		result.put("msg", "日记删除成功");
		// 使用Gson类需要导入gson-2.8.0.jar
		String json = new Gson().toJson(result);

		// 返回值给微信小程序
		Writer out = response.getWriter();
		out.write(json);
		out.flush();
	}

}
