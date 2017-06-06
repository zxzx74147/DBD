package com.zxzx74147.modules_dbd.utils;

import java.io.File;
import java.io.IOException;

/**
 * Created by zhengxin on 2016/12/20.
 */
public class FileUtils {

    public static File createFile(String path){
        File file= new File(path);
        if(file.exists()){
            return file;
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;

    }


}
