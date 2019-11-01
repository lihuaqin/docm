package com.docm.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.docm.vo.UserVo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lhq
 * @since 2019-11-01
 */
public interface UserDao extends BaseMapper<UserVo> {
	
	//有无登录用户
		public UserVo getLoginUser(UserVo user) throws Exception;
		
		//注册用户
		public int addUser(UserVo user) throws Exception;
		
		//获取所有用户
		public List<UserVo> getUserList(Page<UserVo> page );
		
		//根据id获取用户
		public UserVo getUserById(int id);
		
		//删除用户
		public void delUser(UserVo user);
		
		//修改用户
		public UserVo updateUser(UserVo user);
		
		//通过电话号码获取用户
		public UserVo getByUserTelphone(String telphone) throws Exception;

}
