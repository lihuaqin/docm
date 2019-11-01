package com.docm.service.impl;

import com.docm.dao.UserDao;
import com.docm.service.UserService;
import com.docm.vo.UserVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhq
 * @since 2019-11-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserVo> implements UserService {

}
