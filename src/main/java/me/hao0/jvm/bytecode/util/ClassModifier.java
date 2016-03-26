package me.hao0.jvm.bytecode.util;

import java.util.HashMap;
import java.util.Map;

/**
 * class modifier
 * 
 * @company symantec sep
 */
public class ClassModifier {

	private static final int CONSTANT_POOL_COUNT_INDEX = 8;

	private static final int CONSTANT_Utf8_info = 1; // constant tag

	private static final int[] CONSTANT_ITEM_LENGTH = { -1, -1, -1, 5, 5, 9, 9,
			3, 3, 5, 5, 5, 5 };
	
	private static Map<Integer, String> constantsTagMap = new HashMap<Integer, String>();
	
	static {
		constantsTagMap.put(1, "CONSTANT_Utf8_info");
		constantsTagMap.put(3, "CONSTANT_Integer_info");
		constantsTagMap.put(4, "CONSTANT_Float_info");
		constantsTagMap.put(5, "CONSTANT_Long_info");
		constantsTagMap.put(6, "CONSTANT_Double_info");
		constantsTagMap.put(7, "CONSTANT_Class_info");
		constantsTagMap.put(8, "CONSTANT_String_info");
		constantsTagMap.put(9, "CONSTANT_Fieldref_info");
		constantsTagMap.put(10, "CONSTANT_Methodref_info");
		constantsTagMap.put(11, "CONSTANT_InterfaceMethodref_info");
		constantsTagMap.put(12, "CONSTANT_NameAndType_info");
	}
	
	private static final int u1 = 1; // one byte
	private static final int u2 = 2; // two byte

	private byte[] classByte;

	public ClassModifier(byte[] classByte) {
		this.classByte = classByte;
	}

	public byte[] modifyUTF8Constant(String oldStr, String newStr) {
		int cpc = getConstantPoolCount();
		int offset = CONSTANT_POOL_COUNT_INDEX + u2;
		for (int i = 0; i < cpc; i++) {
			int tag = BytesUtil
					.bytes2Int(classByte, offset, CONSTANT_Utf8_info);
			if (tag == CONSTANT_Utf8_info) { // the utf8_constant_info type
				// get the constant info's bytes length
				int len = BytesUtil.bytes2Int(classByte, offset + u1, u2);
				// the constant info's offset start index
				offset += (u1 + u2);
				// the constant info(string)
				String str = BytesUtil.bytes2String(classByte, offset, len);
				if (str.equalsIgnoreCase(oldStr)) { // if find the str need to
													// be replaced
					// the new string's bytes array
					byte[] strBytes = BytesUtil.string2Bytes(newStr);
					// the new string's binary bytes array length
					byte[] strLen = BytesUtil.int2Bytes(newStr.length(), u2);
					// replace the old length
					classByte = BytesUtil.bytesReplace(classByte, offset - u2,
							u2, strLen);
					// replace the old string
					classByte = BytesUtil.bytesReplace(classByte, offset, len,
							strBytes);
					return classByte;
				} else {
					offset += len; // locate next constant
				}
			} else { // other constant type
				offset += CONSTANT_ITEM_LENGTH[tag];
			}
		}
		return classByte;
	}
	
	public void iterateConstantPool(){
		int cpc = getConstantPoolCount();
		System.out.println("constant pool count: " + cpc);
		
		int offset = CONSTANT_POOL_COUNT_INDEX + u2;
		for (int i = 1; i <= cpc; i++) {
			// constant flag
			int tag = BytesUtil.bytes2Int(classByte, offset, CONSTANT_Utf8_info);
			System.out.print(i+": ");
			if (tag == CONSTANT_Utf8_info) { // the utf8_constant_info type
				// get the constant info's bytes length
				int len = BytesUtil.bytes2Int(classByte, offset + u1, u2);
				// the constant info's offset start index
				offset += (u1 + u2);
				// the constant info(string)
				String str = BytesUtil.bytes2String(classByte, offset, len);
				offset += len; // locate next constant
				System.out.println("["+tag+"]"+constantsTagMap.get(tag)+"="+str);
			} else { // other constant type
				offset += CONSTANT_ITEM_LENGTH[tag];
				System.out.println("["+tag+"]"+constantsTagMap.get(tag));
			}
		}
	}

	public int getConstantPoolCount() {
		return BytesUtil.bytes2Int(classByte, CONSTANT_POOL_COUNT_INDEX, u2);
	}
}