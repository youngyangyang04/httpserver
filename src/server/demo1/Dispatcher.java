package server.demo1;

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
		Servlet servlet = new Servlet();
		servlet.service(request, response);
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
		CloseUtil.closeSocket(client);
	}
}
