package Util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class DirectoryHelper {

    private void sample(){
        try {
            /**
             * 删除目下的所有文件不包括目录本身
             */
            FileUtils.cleanDirectory(new  File("D:\\GeoData"));
            /**
             * 删除一个目录本身或者文件
             */
            FileUtils.deleteQuietly(new  File("D:\\GeoData"));
            /**
             * 删除一个目录本身，如果对象是文件的话报错
             */
            FileUtils.deleteDirectory(new  File("D:\\GeoData"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *测试
     */
    public static void main(String[] args) {

    }
}