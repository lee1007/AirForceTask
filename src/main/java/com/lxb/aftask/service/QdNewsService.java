package com.lxb.aftask.service;





import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONPObject;
import com.lxb.aftask.dao.QdNewsDao;
import com.lxb.aftask.domain.Dto.QdNewsDto;
import com.lxb.aftask.domain.entity.QdNewsEntity;
import org.apache.http.client.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import sun.net.www.http.HttpClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class QdNewsService {
    @Autowired
    QdNewsDao qdNewsDao;
    public List<QdNewsDto> getQdNewsListBatchNull(){
        List<QdNewsEntity> qdNewsList = qdNewsDao.getQdNewsListBatchNull();
        List<QdNewsDto> qdNewsDtosList = new ArrayList<QdNewsDto>();
        StringBuffer title_zh = new StringBuffer();
        StringBuffer textContent_zh = new StringBuffer();
        for (QdNewsEntity qdNews:qdNewsList) {
            title_zh = stringBufferReplace(title_zh,translate(qdNews.getTitle(),qdNews.getLanguage()));
            textContent_zh = stringBufferReplace(textContent_zh,translate(qdNews.getTextContent(),qdNews.getLanguage()));
            QdNewsDto qdNewsDto = new QdNewsDto(
                    qdNews.getNewsId(),
                    qdNews.getNewsSection(),
                    qdNews.getColumnUrl(),
                    qdNews.getTitle(),
                    qdNews.getAuthor(),
                    qdNews.getReleaseTime(),
                    qdNews.getTextContent(),
                    qdNews.getSummary(),
                    qdNews.getAcquisitionTime(),
                    qdNews.getFirstClassify(),
                    qdNews.getSecondClassify(),
                    qdNews.getThreeClassify(),
                    qdNews.getDomain(),
                    qdNews.getSiteType(),
                    qdNews.getMediaType(),
                    qdNews.getKeywords(),
                    qdNews.getNationZh(),
                    qdNews.getNationEn(),
                    qdNews.getLanguage(),
                    qdNews.getGrabLocation(),
                    qdNews.getNewsUrl(),
                    qdNews.getAttachmentUrl(),
                    qdNews.getAttachmentDownload(),
                    qdNews.getAttachmentId(),
                    qdNews.getAttachmentName(),
                    qdNews.getBatch(),
                    qdNews.getFlagDel(),
                    title_zh.toString(),
                    textContent_zh.toString());
            System.out.println(qdNewsDto.toString());
            qdNewsDtosList.add(qdNewsDto);
        }

        return qdNewsDtosList;
    }

    public StringBuffer stringBufferReplace(StringBuffer stringBuffer,String str){
        stringBuffer.delete(0,stringBuffer.length());
        stringBuffer.append(str);
        return stringBuffer;
    }
    public String translate(String content,String language){
        HashMap<String,Object> jsonMap = new HashMap<>();
        if(content.isEmpty()||language.isEmpty()){
            return content;
        }else{
            jsonMap.put("text",content);
            jsonMap.put("tgtl","nzh");
            if("??????".equals(language)){
                jsonMap.put("srcl","nen");
            }else if("??????".equals(language)){
                jsonMap.put("srcl","nru");
            }else if("??????".equals(language)){
                jsonMap.put("srcl","nko");
            }
            String jsonpObject = JSON.toJSONString(jsonMap);
            String translateAPI = "http://192.168.52.3/new/translate";

            RestTemplate restTemplate= new RestTemplate();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> httpEntity = new HttpEntity<>(jsonpObject,httpHeaders);
            ResponseEntity<String> result = restTemplate.postForEntity(translateAPI, httpEntity, String.class);
            return result.getBody();
        }

    }
}
