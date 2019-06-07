package com.ivvysoft.cm;

public class IdUserSetter {

	private static int userIdLogined;

	public static void setUserIdLogined(final int id) {
		userIdLogined = id;
	}

	public static int getUserIdLogined() {
		return userIdLogined;
	}

}
