package me.hao0.jvm.bytecode.util;

import java.io.*;

/**
 * File Operation Util
 * @company symantec sep
 */
public class FileUtil {
	
	/**
	 * convert file to bytes
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] file2Bytes(String filePath) throws IOException{
		File f = new File(filePath);
		if (!f.exists()) throw new FileNotFoundException("The file ["+filePath+"] not found!");
		BufferedInputStream bis = null;
		byte[] bs = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(filePath));
			bs = new byte[bis.available()];
			bis.read(bs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if (bis != null) bis.close();
		}
		return bs;
	}
	
	/**
	 * write string to a file
	 * @param str string
	 * @param destFile the dest file path
	 */
	public static void string2File(String str, String destFilePath){
		if (str == null) str = "";
		File destFile = new File(destFilePath);
		BufferedOutputStream bos = null;
		//System.out.println("generate file ["+destFilePath+"]");
		try {
			if (!destFile.exists()) destFile.createNewFile();
			bos = new BufferedOutputStream(new FileOutputStream(destFile));
			bos.write(str.getBytes("UTF-8"));
			bos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	/**
	 * extract the file name from file path
	 * @param filepath
	 * @return
	 */
	public static String extractFileName(String filepath){
		return filepath.substring(filepath.lastIndexOf(File.separatorChar) + 1);
	}
}
