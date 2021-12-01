package com.lxb.aftask.domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QdNewsDto implements Serializable {

    private int newsId;
//    新闻栏目
    private String newsSection;
    private String columnUrl;
    private String  title;
    private String  author;
    private String releaseTime;
    private String textContent;
//    摘要
    private String summary;
//    采集时间
    private String acquisitionTime;
//    行业一级分类
    private String firstClassify;
    private String  secondClassify;
    private String  threeClassify;
//    网站主域名
    private String  domain;
//    网站类型
    private String siteType;
//    媒体类型
    private String mediaType;
//    关键词
    private String keywords;
    private String  nationZh;
    private String  nationEn;
    private String  language;
//    抓取位置
    private String grabLocation;
    private String  newsUrl;
//    附件链接
    private String  attachmentUrl;
//    附件下载
    private String  attachmentDownload;
    private String  attachmentId;
    private String attachmentName;
    private String batch;
    private String flagDel;
//    title翻译
    private String  title_zh;
//    text_content翻译
    private String  textContent_zh;


}
