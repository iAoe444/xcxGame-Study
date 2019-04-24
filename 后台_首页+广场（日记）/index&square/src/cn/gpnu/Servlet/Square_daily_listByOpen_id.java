package cn.gpnu.Servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
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
 * 根据用户的open_id查询所有学习日记
 */
@WebServlet("/Square_daily_listByOpen_id")
public class Square_daily_listByOpen_id extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Square_dailyService dailyService = new Square_dailyServiceImpl();

	public Square_daily_listByOpen_id() {
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

		String open_id =request.getParameter("openid");
		//根据openid查询并得到list数组
		List<Square_daily> list = dailyService.listByOpen_id(open_id);

		// ---------------------------转成json数据--------------------------------------------
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list_dailyByopenid", list);
		result.put("msg", "根据open_id查询学习日记成功");
		// 使用Gson类需要导入gson-2.8.0.jar
		String json = new Gson().toJson(result);

		// 返回值给微信小程序
		Writer out = response.getWriter();
		out.write(json);
		out.flush();

	}

}
