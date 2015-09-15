package utility;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class PasswordUtility {

	public static String hash(String password) {
		BigInteger bigInt = new BigInteger(password);
		String hasher = bigInt.toString(16);
		return getHash(hasher, password);
	}

	private static String getHash(String md5Hash, String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(input.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
		}
		return null;
	}
}
