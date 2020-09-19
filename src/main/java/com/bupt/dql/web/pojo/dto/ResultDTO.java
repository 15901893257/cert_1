package com.bupt.dql.web.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: mai
 * @date: 2020/9/15
 */
@Data
@NoArgsConstructor
public class ResultDTO {
    private boolean ret;

    private String msg;

    private Object data;

    public ResultDTO(boolean ret){
        this.ret = ret;
    }

    public static ResultDTO success(Object obj, String msg){
        ResultDTO resultDTO = new ResultDTO(true);
        resultDTO.data = obj;
        resultDTO.msg = msg;
        return resultDTO;
    }

    public static ResultDTO success(Object obj){
        ResultDTO resultDTO = new ResultDTO(true);
        resultDTO.data = obj;
        return resultDTO;
    }

    public static ResultDTO success(){
        return new ResultDTO(true);
    }

    public static ResultDTO fail(String msg){
        ResultDTO resultDTO = new ResultDTO(false);
        resultDTO.msg = msg;
        return resultDTO;
    }

    public static ResultDTO fail(){
        return new ResultDTO(false);
    }
}