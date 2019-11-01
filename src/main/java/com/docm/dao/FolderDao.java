package com.docm.dao;

import com.docm.vo.Folder;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lhq
 * @since 2019-11-01
 */
public interface FolderDao extends BaseMapper<Folder> {
	
	Folder queryById(final String fid);
    
    List<Folder> queryByParentId(final String pid);
    
    Folder queryByParentIdAndFolderName(final Map<String, String> map);
    
    int insertNewFolder(final Folder f);
    
    int deleteById(final String folderId);
    
    int updateFolderNameById(final Map<String, String> map);
    
    int updateFolderConstraintById(final Map<String, Object> map);

	int moveById(Map<String, String> map);

}
