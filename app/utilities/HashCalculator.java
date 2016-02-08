package utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashCalculator {
 
	public static String Sha512Digest(String str) {
		String ret;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-512");
			md.update(str.getBytes());

			byte[] mdbytes = md.digest();

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < mdbytes.length; i++) {
				sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
			ret = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			ret = "NULL";
		}
		return ret;
	}
}
