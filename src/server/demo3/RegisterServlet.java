package server.demo3;

public class RegisterServlet extends Servlet{

	@Override
	public void doGet(Request request, Response response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doPost(Request request, Response response) {
		// TODO Auto-generated method stub
		response.println("<html><head><title>����ע��</title>");
		response.println("</head><body>");
		response.println("����û���Ϊ:"+request.getParameter("uname"));
		response.println("</body></html>");
	}

}
