package com.example.shop.service;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
@Log4j2
public class FileService {

    public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws  Exception{

        //uuid -> adfd-bdfdds-xfdsfd-xfsdfads(32자)
        UUID uuid = UUID.randomUUID();

        //dog.jpg -> jpg
        String extendsion = originalFileName.substring(originalFileName.lastIndexOf("."));

        String savedFileName = uuid.toString() + extendsion;

        String fileUploadFullUrl = uploadPath + "/" + savedFileName;

        log.info("fileUploadFullUrl: " + fileUploadFullUrl);

        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);

        fos.write(fileData);

        fos.close();

        return savedFileName;
    }

    public void deleteFile(String filePath) throws  Exception{
        File deleteFile = new File(filePath);

        if(deleteFile.exists()){
            deleteFile.delete();
            log.info("파일을 삭제하였습니다.");
        }else{
            log.info("파일이 존재하지 않습니다.");
        }
    }
}
