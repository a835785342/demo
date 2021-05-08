package io流;

import java.io.*;

public class TryCatch {

    public static void main(String[] args) {
        copy();
    }

    private static void copy() {
        try (
                FileInputStream fis = new FileInputStream("/Users/zhangyuanhui/Desktop/test.txt");
                BufferedInputStream bis = new BufferedInputStream(fis);
                FileOutputStream fos = new FileOutputStream("/Users/zhangyuanhui/Desktop/copy.txt");
                BufferedOutputStream bos = new BufferedOutputStream(fos);
        ) {
            int size;
            byte[] data = new byte[1024];
            while((size=bis.read(data))!=-1){
                bos.write(data,0,size);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
