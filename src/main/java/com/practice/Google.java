package com.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Google {
	public static final String GOOGLE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"
			+ "location=24.95375,121.22575&" + "radius=500&" + "types=food&" + "name=吃到飽&" + "language=zh-TW&"
			+ "key=AIzaSyB6Jr3Y7Y6Gj4bPFnONptlqJ1fsdBkt5hI";

	public static void main(String[] args) throws IOException {
		URL url = new URL(GOOGLE_URL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");

		int statusCode = con.getResponseCode();
		if (statusCode == 200) {
			InputStream is = con.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String data;
			StringBuilder str = new StringBuilder();
			while ((data = br.readLine()) != null) {
				str.append(data);
			}
			data = str.toString();
			int first = data.indexOf("[", 40);
			int last = data.lastIndexOf(",");

			try {
				JSONArray arr = new JSONArray(data.substring(first, last));
				System.out.println(arr);
				for (int i = 0; i < arr.length(); i++) {
					JSONObject object = arr.getJSONObject(i);
//					1. 取得餐廳名稱 (name)
					String name = object.getString("name");
//					2. 取得該餐廳的緯經度 (lat, lng)
					JSONObject geoLocAll = object.getJSONObject("geometry");
					JSONObject geoLoc = geoLocAll.getJSONObject("location");
					double lat = geoLoc.getDouble("lat");
					double lng = geoLoc.getDouble("lng");
//					3. 取得該餐廳的評分 (rating)
					double rating = object.getDouble("rating");
//					4. 取得該餐廳是否營業中 (open_now)
					String status = object.getString("business_status");
					if (status.equals("OPERATIONAL")) {
						status = "營業中";
					} else {
						status = "已倒閉";
					}
//					5. 取得該餐廳的地址 (vicinity)
					String addr = object.getString("vicinity");
					System.out.print("餐廳名稱：" + name +  "\r\n" + "餐廳評分：" + rating + "\r\n" + "餐廳地址：" + addr + "\r\n" + "餐廳經度：" + lat + "\r\n" + "餐廳緯度：" + lng + "\r\n" + "營業情況："
							+ status + "\r\n\r\n");
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//				str.toString()
//				JSONArray jsonArray = new JSONArray();

			br.close();
			isr.close();
			is.close();
			con.disconnect();
		}

	}
}