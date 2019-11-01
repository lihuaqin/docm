package com.docm.config.security;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.docm.dao.UserDao;
import com.docm.vo.LoginUser;
import com.docm.vo.UserVo;


@Component
public class MyUserService implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private HttpSession session;
	
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    	try {
            //  省略从数据库中获取用户信息的过程...
            //  通过用户名s去数据库里查找用户以及用户权限
            //  然后返回User对象，注意，这里的User对象是org.springframework.security.core.userdetails.User
        	System.out.println("loadUserByUsername"  + s);
        	UserVo userTable = userDao.getByUserTelphone(s);
        	session.setAttribute("currentUser", userTable);
            LoginUser loginUser = new LoginUser(s,userTable.getPassword(),getAuthorities(userTable.getRoles()));
            loginUser.setNickName(userTable.getUserName());
            return loginUser;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new UsernameNotFoundException("无该用户");
		}

    }
    
    
    public Collection<? extends GrantedAuthority> getAuthorities(List<String> roles ) {
        if(roles == null || roles.size() <1){
            return AuthorityUtils.commaSeparatedStringToAuthorityList("");
        }
        StringBuilder commaBuilder = new StringBuilder();
        for(String role : roles){
            commaBuilder.append(role).append(",");
        }
        String authorities = commaBuilder.substring(0,commaBuilder.length()-1);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
    }
    
}
