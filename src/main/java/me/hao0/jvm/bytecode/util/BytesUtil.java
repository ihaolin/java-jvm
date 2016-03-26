package me.hao0.jvm.bytecode.util;

public class BytesUtil {

	/**
	 * convert bytes to int
	 * 
	 * @param b
	 *            byte array
	 * @param start
	 *            start index
	 * @param len
	 *            length
	 * @return int value replaced bytes
	 */
	public static int bytes2Int(byte[] b, int start, int len) {
		int sum = 0;
		int end = start + len;
		for (int i = start; i < end; i++) {
			int n = ((int) b[i]) & 0xff;
			n <<= (--len) * 8;
			sum = n + sum;
		}
		return sum;
	}

	/**
	 * convert int value to byte array
	 * 
	 * @param value
	 *            int value
	 * @param len
	 *            byte array length
	 * @return the byte array
	 */
	public static byte[] int2Bytes(int value, int len) {
		byte[] b = new byte[len];
		for (int i = 0; i < len; i++) {
			b[len - i - 1] = (byte) ((value >> 8 * i) & 0xff);
		}
		return b;
	}

	/**
	 * convert byte array to string
	 * 
	 * @param b
	 *            byte array
	 * @param start
	 *            array start index
	 * @param len
	 *            length
	 * @return the string converted by array byte
	 */
	public static String bytes2String(byte[] b, int start, int len) {
		return new String(b, start, len);
	}

	/**
	 * convert string to byte array
	 * 
	 * @param str
	 *            string
	 * @return byte array
	 */
	public static byte[] string2Bytes(String str) {
		return str.getBytes();
	}

	/**
	 * offset : o
	 * 
	 * o ---------------------------------------- | | len |
	 * ---------------------------------------- | | / \ | | ----------------- |
	 * new bytes | -----------------
	 * 
	 * @param orginalBytes
	 * @param offset
	 * @param len
	 * @param replaceBytes
	 * @return
	 */
	public static byte[] bytesReplace(byte[] orginalBytes, int offset, int len,
			byte[] replaceBytes) {
		// the new bytes
		byte[] newBytes = new byte[orginalBytes.length
				+ (replaceBytes.length - len)];
		// save 1st part bytes
		System.arraycopy(orginalBytes, 0, newBytes, 0, offset);
		// insert the new part bytes
		System.arraycopy(replaceBytes, 0, newBytes, offset, replaceBytes.length);
		// contact the remain bytes
		System.arraycopy(orginalBytes, offset + len, newBytes, offset
				+ replaceBytes.length, orginalBytes.length - offset - len);
		return newBytes;
	}
}
