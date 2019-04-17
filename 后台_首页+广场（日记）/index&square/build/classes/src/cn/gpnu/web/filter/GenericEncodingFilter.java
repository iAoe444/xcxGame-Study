package cn.gpnu.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


public class GenericEncodingFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 杞瀷涓轰笌鍗忚鐩稿叧瀵硅�?
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		// 瀵箁equest鍖呰澧炲己
		HttpServletRequest myrequest = new MyRequest(httpServletRequest);
		chain.doFilter(myrequest, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

}


class MyRequest extends HttpServletRequestWrapper {

	private HttpServletRequest request;

	private boolean hasEncode;

	public MyRequest(HttpServletRequest request) {
		super(request);// super蹇呴』鍐�?
		this.request = request;
	}


	public Map getParameterMap() {
		// 鍏堣幏寰楄姹傛柟寮�?
		String method = request.getMethod();
		if (method.equalsIgnoreCase("post")) {
			// post璇锋�?
			try {
				// 澶勭悊post涔辩�?
				request.setCharacterEncoding("utf-8");
				return request.getParameterMap();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else if (method.equalsIgnoreCase("get")) {
			Map<String, String[]> parameterMap = request.getParameterMap();
			if (!hasEncode) { // 纭繚get鎵嬪姩缂栫爜閫昏緫鍙繍琛屼竴娆�?
				for (String parameterName : parameterMap.keySet()) {
					String[] values = parameterMap.get(parameterName);
					if (values != null) {
						for (int i = 0; i < values.length; i++) {
							try {
								// 澶勭悊get涔辩�?
								values[i] = new String(values[i].getBytes("ISO-8859-1"), "utf-8");
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
						}
					}
				}
				hasEncode = true;
			}
			return parameterMap;
		}

		return super.getParameterMap();
	}

	@Override
	public String getParameter(String name) {
		Map<String, String[]> parameterMap = getParameterMap();
		String[] values = parameterMap.get(name);
		if (values == null) {
			return null;
		}
		return values[0]; // 鍙栧洖鍙傛暟鐨勭涓�涓��?
	}

	@Override
	public String[] getParameterValues(String name) {
		Map<String, String[]> parameterMap = getParameterMap();
		String[] values = parameterMap.get(name);
		return values;
	}

}