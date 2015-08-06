package server.demo3;

import java.util.Map;

public class WebApp {
	private static ServletContext context;
	static{
		context = new ServletContext();
		
		Map<String, String> mapping = context.getMapping();
		mapping.put("/login", "login");
		mapping.put("/log", "login");
		mapping.put("/reg", "register");
		
		Map<String, String> servlet  = context.getServlet();
		servlet.put("login", "server.demo3.LoginServlet");
		servlet.put("register", "server.demo3.RegisterServlet");
	}
	public static Servlet getServlet(String url) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		if((null == url)|| (url=url.trim()).equals("")){
			return null;
		}
		//return context.getServlet().get(context.getMapping().get(url));
		String name = context.getServlet().get(context.getMapping().get(url));
		return (Servlet)Class.forName(name).newInstance();
	}
}
