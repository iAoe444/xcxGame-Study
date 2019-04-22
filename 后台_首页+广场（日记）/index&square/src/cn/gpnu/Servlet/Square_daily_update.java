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
import cn.gpnu.utils.TimeUtils;

/**
 * ����_id�������ڵ�ѧϰ�ռ�
 */
@WebServlet("/Square_daily_update")
public class Square_daily_update extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Square_dailyService dailyService = new Square_dailyServiceImpl();

	public Square_daily_update() {
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
		/* ������Ӧͷ����ajax������� */
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* �Ǻű�ʾ���е��������󶼿��Խ��ܣ� */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");

		// ----------------��ȡ΢��С���򴫹����Ĳ���ֵ-----------------------------------------------------------
		Long daily_id = Long.parseLong(request.getParameter("daily_id"));
		
		Square_daily daily = new Square_daily();
		daily.setDaily_id(daily_id);
		daily.setDaily_title(request.getParameter("daily_title"));
		daily.setDaily_info(request.getParameter("daily_info"));
		daily.setDaily_classify(request.getParameter("daily_classify"));
		daily.setDaily_date(TimeUtils.getTime());                      //����ʱ��
		daily.setOpenid(request.getParameter("openid"));


		dailyService.updateDaily(daily);

		// ---------------------------ת��json����--------------------------------------------
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("isDailyUpdated", true);
		result.put("msg", "ѧϰ�ռǸ��³ɹ�");
		// ʹ��Gson����Ҫ����gson-2.8.0.jar
		String json = new Gson().toJson(result);

		// ����ֵ��΢��С����
		Writer out = response.getWriter();
		out.write(json);
		out.flush();
	}

}
