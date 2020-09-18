package com.bupt.dql.common.util;

import com.bupt.dql.pojo.param.BaseInfoParam;
import com.bupt.dql.pojo.param.SearchParam;

import java.util.Calendar;

public class ParamCheckModifyUtil {

    public static void modify(SearchParam param){
        if(param.getFrom()!=null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(param.getFrom());
            int year = calendar.get(Calendar.YEAR);
            if (year < 1900) {
                param.setFrom(null);
            }
        }
        if(param.getState()<0){
            param.setState(null);
        }
        if(param.getLanguage()!=null) {
            if (!(param.getLanguage().equals("Java") || param.getLanguage().equals("C_C++"))) {
                param.setLanguage(null);
            }
        }
        if(param.getPlatForm()!=null){
            if(!(param.getPlatForm().equals("coding")||param.getPlatForm().equals("github")||param.getPlatForm().equals("bitbucket")||param.getPlatForm().equals("gitee")||param.getPlatForm().equals("aliyun"))){
                param.setPlatForm(null);
            }
        }
        if(param.getProjectName()!=null){
            if(param.getProjectName().equals("")){
                param.setProjectName(null);
            }
        }
        if(param.getAuthorName()!=null){
            if(param.getAuthorName().equals("")){
                param.setAuthorName(null);
            }
        }
    }
    public static void modify(BaseInfoParam param){
        if(param.getCveId()!=null){
            if(param.getCveId().trim().equals("")){
                param.setCveId(null);
            }else{
                param.setCveId(param.getCveId().trim());
            }
        }
        if(param.getLanguageType()!=null) {
            if (!(param.getLanguageType().equals("java") || param.getLanguageType().equals("c")|| param.getLanguageType().equals("cpp"))) {
                param.setLanguageType(null);
            }
        }
        if(param.getBugType()!=null){
            if(!(param.getBugType().equals("code")||param.getBugType().equals("lib")||param.getBugType().equals("frame"))){
                param.setBugType(null);
            }
        }
        if(param.getDangerLevel()!=null){
            if(!(param.getDangerLevel().trim().equals("low")||param.getDangerLevel().trim().equals("medium")||param.getDangerLevel().trim().equals("high"))){
                param.setDangerLevel(null);
            }
        }
        if(param.getStatus()!=null){
            if(!(param.getStatus().equals("enable")||param.getStatus().equals("disable"))){
                param.setStatus(null);
            }
        }
        if(param.getOrigin()!=null){
            if(!(param.getOrigin().equals("manual")||param.getOrigin().equals("snyk")||param.getOrigin().equals("nvd"))){
                param.setStatus(null);
            }
        }
    }
}
