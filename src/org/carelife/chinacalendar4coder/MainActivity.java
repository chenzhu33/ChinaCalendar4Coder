package org.carelife.chinacalendar4coder;

import java.util.Calendar;

import android.os.Bundle;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MainActivity extends Activity {

	TextView date;
	TextView orientation;
	TextView drink;
	TextView peachIndex;
	ListView goodThing;
	ListView badThing;

	DataUtil dataUtil;
	private RandomUtil randomUtil;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		if (VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB) {
			
		} else {
			
		}
		
		date = (TextView) findViewById(R.id.calendar_date);
		orientation = (TextView) findViewById(R.id.calendar_orientation_content);
		drink = (TextView) findViewById(R.id.calendar_drink_content);
		peachIndex = (TextView) findViewById(R.id.calendar_peach_content);
		goodThing = (ListView) findViewById(R.id.calendar_good_content);
		badThing = (ListView) findViewById(R.id.calendar_bad_content);

	}

	@Override
	public void onResume() {
		super.onResume();
		createData();

	}

	private void createData() {
		Calendar cal = Calendar.getInstance();
		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH);
		int d = cal.get(Calendar.DATE);
		int w = cal.get(Calendar.DAY_OF_WEEK);
		date.setText("今天是	" + y + "年" + m + "月" + d + "日 星期" + w);

		dataUtil = new DataUtil(MainActivity.this);
		randomUtil = new RandomUtil();
		
		orientation.setText("面向"+dataUtil.directions[randomUtil
				.randomInt(dataUtil.directions.length)]+"写程序，BUG 最少。");
		drink.setText(dataUtil.drinks[randomUtil.randomInt(
				dataUtil.drinks.length)]);
		peachIndex.setText(randomUtil.randomDouble() + "");

		List<HashMap<String, String>> datagood = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < 5; i++) {
			HashMap<String, String> tmp = new HashMap<String, String>();
			int index = randomUtil.randomInt(
					dataUtil.activities.size());
			String title = dataUtil.activities.get(index).get("name");
			tmp.put("name", parseTitle(title));
			tmp.put("content", dataUtil.activities.get(index).get("good"));
			dataUtil.activities.remove(index);
			datagood.add(tmp);
		}
		List<HashMap<String, String>> databad = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < 5; i++) {
			HashMap<String, String> tmp = new HashMap<String, String>();
			int index = randomUtil.randomInt(
					dataUtil.activities.size());
			String title = dataUtil.activities.get(index).get("name");
			tmp.put("name", parseTitle(title));
			tmp.put("content", dataUtil.activities.get(index).get("bad"));
			dataUtil.activities.remove(index);
			databad.add(tmp);

		}
		MyListAdapter goodList = new MyListAdapter(MainActivity.this, datagood);
		goodThing.setAdapter(goodList);
		MyListAdapter badList = new MyListAdapter(MainActivity.this, databad);
		badThing.setAdapter(badList);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private String parseTitle(String title) {
		if (title.contains("%a")) {
			String tool = dataUtil.tools[randomUtil.randomInt(
					dataUtil.tools.length)];
			title = title.replace("%a", tool);
		} else if (title.contains("%b")) {
			String var = dataUtil.varNames[randomUtil.randomInt(
					dataUtil.varNames.length)];
			title = title.replace("%b", var);
		} else if (title.contains("%c")) {
			int number = randomUtil.randomInt(10000);
			title = title.replace("%b", number + "");
		}
		return title;
	}

	private class MyListAdapter extends BaseAdapter {
		Context context;
		List<HashMap<String, String>> data;

		public MyListAdapter(Context context,
				List<HashMap<String, String>> result) {
			this.data = result;
			this.context = context;
		}

		public int getCount() {
			return data.size();
		}

		public Object getItem(int position) {
			return data.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(final int position, View convertView,
				ViewGroup parent) {
			final HolderView holder;
			if (convertView == null) {
				convertView = View
						.inflate(context, R.layout.list_adapter, null);
				holder = new HolderView();
				holder.title = (TextView) convertView.findViewById(R.id.title);
				holder.content = (TextView) convertView
						.findViewById(R.id.content);

				convertView.setTag(holder);
			} else {
				holder = (HolderView) convertView.getTag();
			}

			if (null != data.get(position).get("name")) {
				holder.title.setText(data.get(position).get("name"));
			}

			if (null != data.get(position).get("content")) {
				holder.content.setText(data.get(position).get("content"));
			}

			return convertView;
		}

		private class HolderView {
			TextView title;
			TextView content;
		}
	}

}
