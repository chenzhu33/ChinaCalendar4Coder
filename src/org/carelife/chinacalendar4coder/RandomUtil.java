package org.carelife.chinacalendar4coder;

import java.util.Calendar;
import java.util.Random;

import android.util.Log;

public class RandomUtil {
	private Random random;

	public RandomUtil() {
		Calendar cal = Calendar.getInstance();
		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH);
		int d = cal.get(Calendar.DATE);
		random = new Random(y + m + d);
	}

	public int randomInt(int range) {
		return Math.abs(random.nextInt()) % range;
	}

	public double randomDouble() {
		double index =  random.nextDouble()*10;
		return index;
	}
}
