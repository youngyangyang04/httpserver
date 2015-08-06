package server.demo3;

import java.io.IOException;
import java.net.Socket;

public class Dispatcher implements Runnable{
	private Socket client;
	private Request request;
	private Response response;
	private int code = 200;
	public Dispatcher(Socket client) {
		// TODO Auto-generated constructor stub
		this.client = client;
		try {
			request = new Request(client.getInputStream());
			response = new Response(client.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = 500;
			return;
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Servlet servlet = WebApp.getServlet(request.getUrl());
			if(null == servlet)
				this.code = 404;
			else {
			
				servlet.service(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			response.pushToClient(code);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			response.pushToClient(500);
		} catch (IOException e) {
			e.printStackTrace();
		}
		request.close();
		response.close();
		CloseUtil.closeSocket(client);
	}
}
