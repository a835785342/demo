package 文件处理;

import java.io.File;

public class ScanFile {

    public static void main(String[] args) {
        //获取某个目录下的所有子目录与文件
        File file = new File("/Users/zhangyuanhui/IdeaProjects/demo/src/main/java");

        scanAllFile(file);
    }

    private static void scanAllFile(File file) {

        for (File childFile : file.listFiles()) {
            if(childFile.isDirectory()){
                System.out.println("文件夹名称："+childFile.getName());
                scanAllFile(childFile);
            }else{
                System.out.println("文件名称："+childFile.getName());
            }

        }
    }
}
