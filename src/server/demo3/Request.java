package server.demo3;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Request {
	private String methodString;
	private String urlString;
	private Map<String, List<String>> parameterMapValues;
	
	public static final String CRLF = "\r\n";
	private InputStream is;
	private String requestInfoString;
	
	public Request(){
		methodString = "";
		urlString = "";
		parameterMapValues = new HashMap<String, List<String>>();
		requestInfoString = "";
	}
	public Request(InputStream is){
		this();
		this.is = is;
		
		try {
			byte[] data = new byte[20480];
			int len = is.read(data);
			requestInfoString = new String(data,0,len);
			System.out.println(requestInfoString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		parseRequestInfo();
	}
	private void parseRequestInfo(){
		if(null == requestInfoString || (requestInfoString.trim()).equals("")){
			return;
		}
		String paramString = "";
		
		String firstLineString = requestInfoString.substring(0,requestInfoString.indexOf(CRLF));
		
		int idx = requestInfoString.indexOf("/");
		this.methodString = firstLineString.substring(0,idx).trim();
		String urlString = firstLineString.substring(idx, firstLineString.indexOf("HTTP/")).trim();
		if(this.methodString.equalsIgnoreCase("post")){
			this.urlString = urlString;
			paramString = requestInfoString.substring(requestInfoString.lastIndexOf(CRLF)).trim();
		}
		else if(this.methodString.equalsIgnoreCase("get")){
			if(urlString.contains("?")){
				String[] urlArray = urlString.split("\\?");
				this.urlString = urlArray[0];
				paramString = urlArray[1];
			}else{
				this.urlString = urlString;
			}
		}
		if(paramString.equals("")){
			return;
		}
		parseParams(paramString);
		
	}
	
	private void parseParams(String paramString){
		StringTokenizer tokenizer = new StringTokenizer(paramString,"&");
		while(tokenizer.hasMoreTokens()){
			String keyValueString = tokenizer.nextToken();
			String[] keyValues = keyValueString.split("=");
			if(keyValues.length==1){
				keyValues = Arrays.copyOf(keyValues, 2);
				keyValues[1]= null;
			}
			String keyString = keyValues[0].trim();
			String value = keyValues[1];
			
			if(!parameterMapValues.containsKey(keyString)){
				parameterMapValues.put(keyString, new ArrayList<String>());
			}
			List valuesList = parameterMapValues.get(keyString);
			valuesList.add(value);
		}
	}
	
	private String decode(String value,String code){
		try {
			return java.net.URLDecoder.decode(value, code);
		} catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
		}
		return null;
	}
	public String[] getParameterValues(String name){
		List values=null;
		if((values=parameterMapValues.get(name))==null){
			return null;
		}else{
			return (String[])((List) values).toArray(new String[0]);
		}
	}
	/**
	 * 根据页面的name 获取对应的单个值
	 * @param args
	 */
	public String getParameter(String name){
		String[] values =getParameterValues(name);
		if(null==values){
			return null;
		}
		return values[0];
	}
	public String getUrl() {
		return urlString;
	}
	public void close(){
		CloseUtil.closeIO(is);
	}
	
	public static void main(String[] args) {
		
	}
}
