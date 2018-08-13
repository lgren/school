package com.lgren.school.student.dao;

import com.lgren.school.student.pojo.AdvertisingImg;

public interface AdvertisingImgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdvertisingImg record);

    int insertSelective(AdvertisingImg record);

    AdvertisingImg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdvertisingImg record);

    int updateByPrimaryKey(AdvertisingImg record);
}