package com.xxx.sample.spring.boot.muti.ds.dao.cluster;

import com.xxx.sample.spring.boot.muti.ds.domain.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Orrin on 2017/5/27.
 */
@Mapper
public interface CityDao {

	/**
	 * 根据城市名称，查询城市信息
	 *
	 * @param cityName 城市名
	 */
	City findByName(@Param("cityName") String cityName);

	int insertCity(City city);
}