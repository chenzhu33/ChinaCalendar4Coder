package org.carelife.chinacalendar4coder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.content.Context;

public class DataUtil {
	public String[] directions = { "北方", "东北方", "东方", "东南方", "南方", "西南方", "西方",
			"西北方" };
	public String[] tools = { "Eclipse写程序", "MSOffice写文档", "记事本写程序",
			"Windows8", "Linux", "MacOS", "IE", "Android设备", "iOS设备" };
	public String[] varNames = { "jieguo", "huodong", "pay", "expire",
			"zhangdan", "every", "free", "i1", "a", "virtual", "ad", "spider",
			"mima", "pass", "ui" };
	public String[] drinks = { "水", "茶", "红茶", "绿茶", "咖啡", "奶茶", "可乐", "牛奶",
			"豆奶", "果汁", "果味汽水", "苏打水", "运动饮料", "酸奶", "酒" };

	public List<HashMap<String, String>> activities = new ArrayList<HashMap<String, String>>();

	public DataUtil(Context context) {
		readStream(context.getResources().openRawResource(R.raw.activities));
	}

	private void readStream(InputStream is) {

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"));
			String line;
			while ((line = reader.readLine()) != null) {
				HashMap<String, String> activity = new HashMap<String, String>();
				String[] content = line.split("#");
				activity.put("name", content[0]);
				activity.put("good", content[1]);
				activity.put("bad", content[2]);
				activities.add(activity);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// String[] specials = {
	// {date:20130207, type:'good', name:'关注王登朝', description:''},
	// {date:20130208, type:'good', name:'关注橘梨紗', description:'前AKB48哦'}
	// };

}
