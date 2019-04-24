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
 * ����plan_idɾ�����ڵ�ѧϰ�ռ�
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
		/* ������Ӧͷ����ajax������� */
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* �Ǻű�ʾ���е��������󶼿��Խ��ܣ� */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");

		// ----------------��ȡ΢��С���򴫹����Ĳ���ֵ-----------------------------------------------------------
		if(Long.parseLong(request.getParameter("plan_useTime"))>=Long.parseLong(request.getParameter("plan_setTime"))) {
				isdone=true;
		}
		
		// 1.��ò�������װ��Index_plan����
		Index_plan plan = new Index_plan();

		plan.setPlan_id(Long.parseLong(request.getParameter("planId")));
		plan.setOpenid(request.getParameter("openid"));
		plan.setPlan_info(request.getParameter("plan_info"));
		plan.setPlan_date(TimeUtils.getTime());  //�ƻ��޸�ʱ��
		plan.setPlan_useTime(Long.parseLong(request.getParameter("plan_useTime")));
		plan.setPlan_setTime(Long.parseLong(request.getParameter("plan_setTime")));
		plan.setPlan_done(isdone);

		// 2.����Service����ͻ�
		planService.update(plan);

		// ---------------------------ת��json����--------------------------------------------
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("isPlanUpdated", true);
		result.put("msg", "�ƻ����³ɹ�");
		// ʹ��Gson����Ҫ����gson-2.8.0.jar
		String json = new Gson().toJson(result);

		// ����ֵ��΢��С����
		Writer out = response.getWriter();
		out.write(json);
		out.flush();

	}

}
