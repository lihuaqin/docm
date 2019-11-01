package com.docm.dao;

import com.docm.vo.Propertie;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lhq
 * @since 2019-11-01
 */
public interface PropertiesDao extends BaseMapper<Propertie> {
	
int insert(final Propertie p);
	
	int deleteByKey(final String propertieKey);
	
	Propertie selectByKey(final String propertieKey);
	
	int update(final Propertie p);

}
