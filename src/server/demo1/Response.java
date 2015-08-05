package server.demo1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

public class Response {
	public static final String CRLF="\r\n";
	public static final String BLANK=" ";
	private BufferedWriter bw;
	private StringBuilder content;
	
	private StringBuilder headinfo;
	
	private int len=0;
	public Response(){
		headinfo = new StringBuilder();
		content = new StringBuilder();
		len=0;
	}
	public Response(Socket client){
		this();
		try {
			bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			headinfo=null;
		}
		
	}
	public Response(OutputStream os){
		this();
		bw = new BufferedWriter(new OutputStreamWriter(os));
	}
	public Response print(String info){
		content.append(info);
		len+=(info+CRLF).getBytes().length;
		return this;
	}
	public Response println(String info){
		content.append(info).append(CRLF);
		len+=(info+CRLF).getBytes().length;
		return this;
	}
	
	private void creatHeadInfo(int code){
		headinfo.append("HTTP/1.1").append(BLANK).append(code).append(BLANK);
		switch(code){
			case 200:
				headinfo.append("OK");
			case 404:
				headinfo.append("NOT FOUND");
				break;
			case 505:
				headinfo.append("SEVER ERROR");
				break;	
		}
		headinfo.append(CRLF);
		//2)  响应头(Response Head)
		headinfo.append("Server:bjsxt Server/0.0.1").append(CRLF);
		headinfo.append("Date:").append(new Date()).append(CRLF);
		headinfo.append("Content-type:text/html;charset=GBK").append(CRLF);
		//正文长度 ：字节长度
		headinfo.append("Content-Length:").append(len).append(CRLF);
		headinfo.append(CRLF); //分隔符
	}
	void pushToClient(int code) throws IOException{
		if(null == headinfo){
			code = 500;
		}
		creatHeadInfo(code);
		bw.append(headinfo.toString());
		
		bw.append(content.toString());
		bw.flush();
	}
	
	public void close(){
		CloseUtil.closeIO(bw);
	}
}














