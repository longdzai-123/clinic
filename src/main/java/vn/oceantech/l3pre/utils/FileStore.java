package vn.oceantech.l3pre.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileStore {
    public static final String UPLOAD_FOLDER = "D:/Project/clinic_management_be/src/main/resources/uploads";

    public static String getFileName(MultipartFile multipartFile, String prefix) {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            try {
                int index = multipartFile.getOriginalFilename().lastIndexOf(".");
                String ext = multipartFile.getOriginalFilename().substring(index);
                String fileName = prefix + UUID.randomUUID() + ext;
                File file = new File(UPLOAD_FOLDER + fileName);
                multipartFile.transferTo(file);
                return fileName;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
