package server.demo1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server5 {
	private ServerSocket serverSocket;
	public static final String CRLF="\r\n";
	public static final String BLANK=" ";
	
	public static void main(String[] args) {
		Server5 server4 = new Server5();
		server4.start();
	}
	public void start(){
		try {
			serverSocket = new ServerSocket(8888);
			this.receive();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void receive(){
		try {
			Socket clientSocket = serverSocket.accept();
			Request request = new Request(clientSocket.getInputStream());
	//		System.out.println(requestInfoString);
			
			Response response = new Response(clientSocket.getOutputStream());
			
//			Servlet servlet = new Servlet();
//			servlet.service(request, response);
			response.println("<html><head><title>HTTP响应示例</title>");
			response.println("</head><body>");
			response.println("欢迎:").println(request.getParameter("uname")).println("回来");
			response.println("</body></html>");
			response.pushToClient(200);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
