package jstl_test.el;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	//el표현식으로 호출할것이기때문에, 이 method는 static이여야 함.
	public static String format(Date date) {
		return formatter.format(date);
	}

}
