package server.demo3;

public class RegisterServlet extends Servlet{

	@Override
	public void doGet(Request request, Response response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doPost(Request request, Response response) {
		// TODO Auto-generated method stub
		response.println("<html><head><title>返回注册</title>");
		response.println("</head><body>");
		response.println("你的用户名为:"+request.getParameter("uname"));
		response.println("</body></html>");
	}

}
