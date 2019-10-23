package com.soul.controller;

import com.soul.pojo.Data;
import com.soul.pojo.Message;
import com.soul.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2019/3/27.
 */
@Controller
public class FileUploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private DataService dataService;

    @RequestMapping("/upload")
    public String upload() {
        return "upload";
    }

    @PostMapping("/uploadFile")
    @ResponseBody
    public Object upload(@RequestParam("fileUpload") MultipartFile file) {
        if (file.isEmpty()) {
            return new Message(-1,"please choice file");
        }
        String fileName = file.getOriginalFilename();
        String filePath = "C:\\Users\\Administrator\\Desktop\\003";
        File dest = new File(filePath +"/"+ fileName);
        try {
            long size = file.getSize();
            long timeMillis = System.currentTimeMillis();
            file.transferTo(dest);
            long currentTimeMillis = System.currentTimeMillis();
            long uploadTime = currentTimeMillis - timeMillis;
            Data data = new Data();
            data.setFileName(fileName);
            data.setFileSize(size);
            data.setUploadTime(uploadTime);
            dataService.save(data);
            System.out.println(data);
            LOGGER.info("上传成功");
            return new Message(0,"success to upload");
        }
            catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
            return new Message(-1,"fail to upload");
    }


}
