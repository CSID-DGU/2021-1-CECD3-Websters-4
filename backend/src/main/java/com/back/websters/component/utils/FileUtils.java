package com.back.websters.component.utils;

import com.back.websters.component.property.FilePathProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class FileUtils {

    private final FilePathProperty filePathProperty;

    public String createRandomName() {
        return UUID.randomUUID().toString();
    }

    public String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("잘못된 형식의 파일입니다.");
        }
    }

    public String saveToLocal(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String localPath = filePathProperty.getLocation();
        try {
            // 디렉토리 있는지 확인
            if (!new File(localPath).exists()) {
                new File(localPath).mkdir();
            }

            // 북마크 기능 사용을 위해 로컬에 파일 저장
            String filePath = localPath + '/' + createRandomName() + '.' + getFileExtension(fileName);
            File localFile = new File(filePath);
            file.transferTo(localFile);
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("파일 저장 중 오류가 발생했습니다. " + file.getOriginalFilename());
        }
    }

     public void deleteFromLocal(String filePath) {
        File localFile = new File(filePath);
        System.out.println("delete filePath = " + filePath);
        boolean deleteSuccess = localFile.delete();
        System.out.println("deleteSuccess = " + deleteSuccess);

        if(!deleteSuccess) {
            throw new IllegalArgumentException("파일 삭제 중 오류가 발생했습니다. " + filePath);
        }
    }

}
