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
 * ����plan_id��ѯ����ѧϰ��¼������ѯ�����ƻ����ڵ����е�ѧϰ��¼��
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
		/* ������Ӧͷ����ajax������� */
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* �Ǻű�ʾ���е��������󶼿��Խ��ܣ� */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");

		// ----------------��ȡ΢��С���򴫹����Ĳ���ֵ-----------------------------------------------------------

		Long plan_id = Long.parseLong(request.getParameter("plan_id"));

		// ��ѯ�üƻ�id������ѧϰʱ��μ�¼
		List<Index_times> list = timesService.listByPlanid(plan_id);
		//��Ϊlist�к��������json�������л�ʱ������plan����timesѭ���ݹ飬�����ڴ����
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

		// ---------------------------ת��json����--------------------------------------------
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("times_listByplanid", list1);
		result.put("msg", "��ѯ�ƻ��ɹ�");
		// ʹ��Gson����Ҫ����gson-2.8.0.jar
		String json = new Gson().toJson(result);

		// ����ֵ��΢��С����
		Writer out = response.getWriter();
		out.write(json);
		out.flush();

	}

}
