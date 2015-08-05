package server.demo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket serverSocket;
	public static void main(String[] args) {
		Server server = new Server();
		server.start();
		
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
			Socket client = serverSocket.accept();
			StringBuilder sBuilder = new StringBuilder();
			String msg = null;
			
			BufferedReader brBufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			while((msg=brBufferedReader.readLine()).length()>0){
				sBuilder.append(msg);
				sBuilder.append("\r\n");
			}
			String requestInfoString = sBuilder.toString().trim();
			System.out.println(requestInfoString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void stop(){
		
	}
}
