package com.ricardocreates.translator.util;

import java.io.File;
import java.net.URL;

/**
 * File utils class
 * @author ricardo
 *
 */
public class FileUtil {
    private FileUtil() {}
    /**
     * return a File given a resource path if not found return null
     * @param resourcePath - path from resource of jar app
     * @return a file or a null
     */
    public static File getFileFromResourcePath(String resourcePath) {
        File file = null;
        URL urlResource = FileUtil.class.getClassLoader().getResource(resourcePath);
        if (urlResource != null) {
            file = new File(urlResource.getFile());
        }
        return file;
    }
}
