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

import cn.gpnu.Service.Index_planService;
import cn.gpnu.Service.impl.Index_planServiceImpl;
import cn.gpnu.domain.Index_plan;

/**
 * �����û���open_id��ѯ�����Ñ���Ӌ��
 */
@WebServlet("/Index_plan_listByOpen_id")
public class Index_plan_listByOpen_id extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Index_planService planService = new Index_planServiceImpl();

	public Index_plan_listByOpen_id() {
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

		String openid = request.getParameter("openid");

		List<Index_plan> list = planService.listByOpen_id(openid);
		// ��Ϊlist�к��������json�������л�ʱ������plan����timesѭ���ݹ飬�����ڴ����
		List<Index_plan> list1 = new ArrayList<Index_plan>();
		for (Index_plan a : list) {
			Index_plan b = new Index_plan();
			b.setPlan_id(a.getPlan_id());
			b.setPlan_info(a.getPlan_info());
			b.setPlan_setTime(a.getPlan_setTime());
			b.setPlan_useTime(a.getPlan_useTime());
			b.setPlan_done(a.isPlan_done());
			b.setPlan_date(a.getPlan_date());
			list1.add(b);

		}

		// ---------------------------ת��json����--------------------------------------------
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list_planByOpenid", list1);
		result.put("msg", "��ѯ�����ռǳɹ�");
		// ʹ��Gson����Ҫ����gson-2.8.0.jar
		String json = new Gson().toJson(result);

		// ����ֵ��΢��С����
		Writer out = response.getWriter();
		out.write(json);
		out.flush();

	}

}
