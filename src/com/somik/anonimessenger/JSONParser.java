package com.somik.anonimessenger;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {
	public JSONParser()
	{
		
	}
	static InputStream is=null;
	static JSONObject jObj=null;
	static String json=null;
	static JSONArray jarr=null;
	List<NameValuePair> params=new ArrayList<NameValuePair>();
	public JSONObject makeHttpRequest(String url,String method,List<NameValuePair> params)
	{
		try
		{
			if (method.equals("POST"))
					{
				DefaultHttpClient httpclient=new DefaultHttpClient();
				HttpPost httppost=new HttpPost(url);
				httppost.setEntity(new UrlEncodedFormEntity(params));
				HttpResponse httpresponse=httpclient.execute(httppost);
				HttpEntity httpentity=httpresponse.getEntity();
				is= httpentity.getContent();
								}
			else if (method.equals("GET"))
			{
				DefaultHttpClient httpclient=new DefaultHttpClient();
				String paramstring=URLEncodedUtils.format(params, "utf-8");
				url += "?"+paramstring;
				HttpGet httpget=new HttpGet(url);
				HttpResponse httpresponse=httpclient.execute(httpget);
				HttpEntity httpentity=httpresponse.getEntity();
				is=httpentity.getContent();
				
				
				
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}catch(ClientProtocolException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		try {
			BufferedReader reader= new BufferedReader(new InputStreamReader(is, "iso-8859-1"),8);
			StringBuilder sb=new StringBuilder();
			String line=null;
			while(reader.readLine()!=null)
			{
				sb.append(line+"\n");
			
			is.close();
			json=sb.toString();
			
			}}catch(Exception e)
			{
			Log.e("Buffer Error","Error converting result"+e.toString());
			
			}
		
		try {
			jObj=new JSONObject(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jObj;	
		}
	}
	


