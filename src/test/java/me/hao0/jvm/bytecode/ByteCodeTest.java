package me.hao0.jvm.bytecode;

import me.hao0.jvm.bytecode.util.ClassModifier;
import me.hao0.jvm.bytecode.util.FileUtil;
import org.junit.Test;
import java.io.IOException;

public class ByteCodeTest {
	
	@Test
	public void iterateConstants() throws IOException{
		///Users/haolin/Learning/java/codes/core/bytecode/bin/Person.class
		byte[] classBytes = FileUtil.file2Bytes("/Users/haolin/Learning/java/codes/core/bytecode/bin/Person.class");
		ClassModifier cm = new ClassModifier(classBytes);
		cm.iterateConstantPool();
	}
	
	@Test
	public void testNegative(){
		int i = -113;
		System.out.println(Integer.toBinaryString((byte)i));
	}
}
