package server.demo2;

public class LoginServlet extends Servlet{

	@Override
	public void doGet(Request request, Response response) {
		// TODO Auto-generated method stub
		String name = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		if(login(name, pwd)){
			response.println("µÇÂ¼³É¹¦");
		}else{
			response.println("µÇÂ¼Ê§°Ü");
		}
	}

	public boolean login(String name, String pwd){
		return name.equals("yang") && pwd.equals("pwd");
	}
	@Override
	public void doPost(Request request, Response response) {
		// TODO Auto-generated method stub
		
	}

}
