package com.lxb.aftask.service;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPObject;
import com.lxb.aftask.dao.QdNewsDao;
import com.lxb.aftask.domain.entity.QdNewsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class QdNewsService {
    @Autowired
    QdNewsDao qdNewsDao;
    public List<QdNewsEntity> getQdNewsListBatchNull(){
        List<QdNewsEntity> qdNewsList = qdNewsDao.getQdNewsListBatchNull();
        String title = "";
        String textContent = "";
        for (QdNewsEntity qdNews:qdNewsList) {
            title = qdNews.getTitle();
            textContent = qdNews.getTitle();
        }

        return qdNewsList;
    }

    public String translate(String content,String language){
        HashMap<String,Object> jsonMap = new HashMap<>();
        if("英语".equals(language)){
            jsonMap.put("srcl","nen");
        }else if("俄语".equals(language)){
            jsonMap.put("srcl","nru");
        }else if("韩语".equals(language)){
            jsonMap.put("srcl","nko");
        }
        jsonMap.put("tgtl","nzh");
        jsonMap.put("text",content);
        JSONPObject jsonpObject = new JSONPObject(JSON.toJSONString(jsonMap));

        String translateAPI = "http://43.240.136.178/nmt2/translate";
        return "1";
    }
}
