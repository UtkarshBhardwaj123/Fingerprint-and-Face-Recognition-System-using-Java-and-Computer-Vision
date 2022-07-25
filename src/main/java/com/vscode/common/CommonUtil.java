package com.vscode.common;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * @author Utkarsh Bhardwaj
 */

public class CommonUtil {

	public static boolean checkNull(String value) {
		if (value == null)
			return true;
		if (value.isEmpty())
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
		if (list == null)
			return true;
		if (list.isEmpty())
			return true;
		return false;
	}

	public static String getCleanData(Object data) {
		if (data instanceof String)
			return (String) data;

		else
			return data.toString();

	}
}
