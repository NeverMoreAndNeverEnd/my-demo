package com.soul.pojo;


import org.springframework.data.elasticsearch.annotations.Document;


import javax.persistence.*;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Administrator on 2019/3/27.
 */
@Entity
@Table(name = "Data")
@Document(indexName = "suredar",type = "data")
public class Data {

    //private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.springframework.data.annotation.Id
    private Integer id;

    @Column
    private String fileName;

    @Column
    private Long fileSize;

    @Column
    private Long uploadTime;

    public Data() {
    }

    public Data(String fileName, Long fileSize, Long uploadTime) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.uploadTime = uploadTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Long uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Override
    public String toString() {
        return "Data{" +
                "fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", uploadTime=" + uploadTime +
                '}';
    }
}
