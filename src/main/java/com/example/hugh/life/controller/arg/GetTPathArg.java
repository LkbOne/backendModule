package com.example.hugh.life.controller.arg;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class GetTPathArg implements Serializable {
    /**
     * {@link com.example.hugh.life.commmon.enums.FileTypeEnum}
    * */
    Integer type;
    MultipartFile file;

    public boolean isWrongParams(){
        if(null == file){
            return true;
        }
        return false;
    }
}
