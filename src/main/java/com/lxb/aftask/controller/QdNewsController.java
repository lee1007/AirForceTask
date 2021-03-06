package com.lxb.aftask.controller;

import com.alibaba.fastjson.JSONArray;
import com.lxb.aftask.domain.Dto.QdNewsDto;
import com.lxb.aftask.domain.entity.QdNewsEntity;
import com.lxb.aftask.service.QdNewsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QdNewsController {
    @Autowired
    QdNewsService qdNewsService;
    @GetMapping("/getTransList")
    @ApiOperation("获取翻译列表")
    public String getNewsTransList(){
        JSONArray jsonArray = new JSONArray();
        List<QdNewsDto> list =  qdNewsService.getQdNewsListBatchNull();
        return list.toString();
    }
}
