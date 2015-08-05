package server.demo1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server3 {
	private ServerSocket serverSocket;
	public static final String CRLF="\r\n";
	public static final String BLANK=" ";
	
	public static void main(String[] args) {
		Server3 server3 = new Server3();
		server3.start();
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
			
			StringBuilder responseContext = new StringBuilder();
			responseContext.append("<html><head><title>HTTP响应示例</title>" +
					"</head><body>Hello bjsxt!</body></html>");
			
			StringBuilder response = new StringBuilder();	//1)  HTTP协议版本、状态代码、描述
			response.append("HTTP/1.1").append(BLANK).append("200").append(BLANK).append("OK").append(CRLF);
			//2)  响应头(Response Head)
			response.append("Server:bjsxt Server/0.0.1").append(CRLF);
			response.append("Date:").append(new Date()).append(CRLF);
			response.append("Content-type:text/html;charset=GBK").append(CRLF);
			//正文长度 ：字节长度
			response.append("Content-Length:").append(responseContext.toString().getBytes().length).append(CRLF);
			//3)正文之前
			response.append(CRLF);
			//4)正文
			response.append(responseContext);
			
			System.out.println(responseContext);
			
			BufferedWriter bwBufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			bwBufferedWriter.append(response.toString());
			bwBufferedWriter.flush();
			bwBufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
