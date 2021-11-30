package com.lxb.aftask.mapper;


import com.lxb.aftask.domain.entity.QdNewsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QdNewsMapper {
    public List<QdNewsEntity> getQdNewsListBatchNull();

}
