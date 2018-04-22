package com.kaishengit.tms.fileStore;


import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QiniuStore {


    @Value("${qiniu.ak}")
    private String accesskey;


    @Value("${qiniu.sk}")
    private String secretkey;


    //七牛的空间名
    @Value("${qiniu.bucket}")
    private String bucket;


    public String getUploadToken(){
        Auth auth = Auth.create(accesskey,secretkey);
        return auth.uploadToken(bucket);
    }


}
