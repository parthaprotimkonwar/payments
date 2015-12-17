package play.utilities;

import java.util.UUID;

public class Crypto {

	/**
	 * Generates a random number
	 * @return
	 */
	public static String generateUniqueRandomString() {
		return System.currentTimeMillis() + UUID.randomUUID().toString().replace("-", "");
		
	}
	
	public static void main(String[] args) {
		String secomd = generateUniqueRandomString();
		System.out.println(secomd + " Length :" + secomd.length());
		
	}
}
