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
 * �����û���open_id����ѧϰ�ռ�
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
		/* ������Ӧͷ����ajax������� */
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* �Ǻű�ʾ���е��������󶼿��Խ��ܣ� */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");

		// ----------------��ȡ΢��С���򴫹����Ĳ���ֵ-----------------------------------------------------------
		String setTime=request.getParameter("setTime");    //����Ԥ����ɵĵ�ʱ�䣬�涨��ʽΪyyyy-MM-dd HH:mm:ss
		Long setTimes=TimeUtils.betweenTime("0000-00-00 00:00:00",setTime);   //�����õ�ʱ��ת��Ϊ����

		// 1.��ò�������װ��Index_plan����
		Index_plan plan = new Index_plan();
		plan.setPlan_info(request.getParameter("plan_info"));
		plan.setPlan_date(TimeUtils.getTime()); // ʱ��Ϊ��ǰ����ʱ��
		plan.setPlan_setTime(setTimes); // ����ʱ��
		plan.setPlan_done(false); // ��ʼֵΪfalse
		plan.setPlan_useTime((long) 0); // ��ʼĬ��ֵΪ0
		plan.setOpenid(request.getParameter("openid")); // ���ô��������û�id

		System.out.println("plan_title=" + request.getParameter("plan_title"));

		// 2.����Service����ͻ�
		planService.add(plan);

		
		//---------------------------ת��json����--------------------------------------------
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("isPlanAdd", true);
		result.put("msg", "��ӳɹ�");
		// ʹ��Gson����Ҫ����gson-2.8.0.jar
		String json = new Gson().toJson(result);

		// ����ֵ��΢��С����
		Writer out = response.getWriter();
		out.write(json);
		out.flush();

	}

}
