package server.demo1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server7 {
	private ServerSocket serverSocket;
	public static final String CRLF="\r\n";
	public static final String BLANK=" ";
	
	private boolean isShutDown = false;
	
	public static void main(String[] args) {
		Server7 server = new Server7();
		server.start();
	}
	public void start(){
		start(8888);
		
	}
	
	public void start(int port){
		try {
			serverSocket = new ServerSocket(port);
			this.receive();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			stop();
		}
	}
	private void receive(){
		try {
			while(!isShutDown){
				new Thread(new Dispatcher(serverSocket.accept())).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			stop();
		}
	}
	
	public void stop(){
		isShutDown = true;
		CloseUtil.closeSocket(serverSocket);
	}
}
