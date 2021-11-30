package com.lxb.aftask.dao;

import com.lxb.aftask.domain.entity.QdNewsEntity;
import com.lxb.aftask.mapper.QdNewsMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public class QdNewsDao {
    @Autowired
    QdNewsMapper qdNewsMapper;
    public List<QdNewsEntity> getQdNewsListBatchNull(){
        return qdNewsMapper.getQdNewsListBatchNull();
    }
}
