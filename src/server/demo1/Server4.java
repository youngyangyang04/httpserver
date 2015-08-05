package server.demo1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server4 {
	private ServerSocket serverSocket;
	public static final String CRLF="\r\n";
	public static final String BLANK=" ";
	
	public static void main(String[] args) {
		Server4 server4 = new Server4();
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
			byte[] data = new byte[20480];
			int len = clientSocket.getInputStream().read(data);
			
			String requestInfoString = new String(data,0,len).trim();
			System.out.println(requestInfoString);
			
			Response rep = new Response(clientSocket.getOutputStream());
			rep.println("<html><head><title>HTTPÏìÓ¦Ê¾Àý</title>");
			rep.println("</head><body>Hello server!</body></html>");
			rep.pushToClient(200);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
