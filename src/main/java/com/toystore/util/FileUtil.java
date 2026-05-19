package com.toystore.util;

import java.io.File;
import java.io.IOException;

/**
 * Utility for resolving file paths for text-file-based storage
 * (orders, reviews, payments).
 *
 * Files are stored under a 'data/' folder in the working directory
 * (project root when running with Spring Boot).
 */
public class FileUtil {

    private static final String DATA_FOLDER = "data";

    /**
     * Returns the absolute path to a data file inside the 'data/' folder.
     * Creates both the folder and the file if they do not exist.
     */
    public static String getFilePath(String fileName) {
        File folder = new File(DATA_FOLDER);
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
