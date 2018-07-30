package Util;

import java.nio.ByteBuffer;

/**
 * Created by ChenLD on 2018/3/22.
 *
 * @author ChenLD
 * @version 1.0
 */
public class SerializeHelper {

    private static final int BYTE_BUFFER_SIZE = 4096;

    //函数流程示例
    public byte[] serialize( ){
        //create a HeapByteBuffer, HeapByteBuffer使用的java堆内存
        ByteBuffer buf = ByteBuffer.allocate(BYTE_BUFFER_SIZE);

        Object[] elements = new Object[]{};
        int numOfElements = 10;

        buf.put((byte) numOfElements);
        for(Object element:elements){
//            buf.put((byte) element.length);
//            buf.put((byte) element.value);
        }

        byte[] result = new byte[buf.position()];
        int offset = 0;
        int length = 10;
        buf.get(result,offset,length);
        return result;

    }

    //




}
