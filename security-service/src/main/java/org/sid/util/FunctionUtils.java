package org.sid.util;

public class FunctionUtils {

	public static void simulateSlowCall() {
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
