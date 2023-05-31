package com.vscode.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * @author Utkarsh Bhardwaj
 */

public class CommonUtil {

	// private constructor as we don't need to create an object of this class
	// because all the methods are static.
	private CommonUtil() {
		super();
	}

	public static boolean checkNull(Object value) {
		if (value == null)
			return true;

		if (value instanceof String && ((String) value).trim().isEmpty())
			return true;

		return false;
	}

	public static boolean checkNull(List<String> list) {
		if (list == null)
			return true;

		if (list.isEmpty())
			return true;

		return false;
	}

	public static boolean checkNullCriteria(List<Criteria> list) {
//		List<Object> obj = null;
		if (list == null)
			return true;

		if (list.isEmpty())
			return true;

//		obj.stream(in -> {if(in instanceof Criteria)
//			                ((Criteria)in).getColumn(); });

		return false;
	}

	public static String getCleanData(Object data) {
		if (data == null)
			return "  ";

		else if (data instanceof String)
			return (String) data;

		else
			return data.toString();

	}

	public static Date getDateFromStr(String date) {
		DateFormat formatter;

		try {
			formatter = new SimpleDateFormat("yyyy-mm-dd");
			return formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date getCurrentDate() {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-mm-yyyy");
		LocalDateTime currentDate = LocalDateTime.now();
		System.out.println(dateFormat.format(currentDate));
		return getDateFromStr(currentDate.toString());
	}
}
