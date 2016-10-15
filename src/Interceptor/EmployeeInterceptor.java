package Interceptor;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

//拦截器
public class EmployeeInterceptor extends AbstractInterceptor {

	public String intercept(ActionInvocation ai) throws Exception {
		List list=null;
		Map session=ai.getInvocationContext().getSession();
	    list=(List)session.get("employee");
		if(list==null){
			HttpServletResponse response=ServletActionContext.getResponse();
	    	response.setContentType("text/html;charset=UTF-8");
	    	PrintWriter out=response.getWriter();
	    	out.print("<script>alert('登陆超时，请重新登陆');location='http://localhost:8080/Jboa/Login.jsp';</script>");
	    	out.flush();
	    	out.close();
		}
		return ai.invoke();
	}
}
