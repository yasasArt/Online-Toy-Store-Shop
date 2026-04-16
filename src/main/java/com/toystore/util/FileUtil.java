package com.toystore.util;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    public static String getFilePath(String fileName) {
        String projectPath = System.getProperty("user.dir");
        String folderPath = projectPath + File.separator + "src" + File.separator + "main" + File.separator + "resources";

        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File file = new File(folder, fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file.getAbsolutePath();
    }
}