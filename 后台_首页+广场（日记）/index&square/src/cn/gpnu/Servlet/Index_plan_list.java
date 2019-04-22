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
 * 查询表中所有学习日记
 */
@WebServlet("/Index_listServlet")
public class Index_plan_list extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Index_planService planService = new Index_planServiceImpl();

	public Index_plan_list() {
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

		// ---------------------------------------------------------------------------

		// 1.调用Service查询所有客户
		List<Index_plan> list = planService.getAll();
		
		//因为list中含有外键，json进行序列化时会进入表plan、表times循环递归，导致内存溢出
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
		// ---------------------------转成json数据--------------------------------------------
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", list1);
		
		Writer out = response.getWriter();
		String json = new Gson().toJson(result);
		out.write(json);
		out.flush();
	

	}

}
