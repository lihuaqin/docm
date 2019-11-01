package com.docm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.docm.config.security.MyAuthenticationFailHandler;
import com.docm.config.security.MyAuthenticationSuccessHandler;
import com.docm.config.security.MyUserService;
import com.docm.utils.MD5Util;


//@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	 @Autowired
	 private MyUserService myUserService;
	 
	@Autowired
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;
	
    @Autowired
    private MyAuthenticationFailHandler authenticationFailHandler;
    
//    @Autowired
//    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
            .authorizeRequests()
            	.antMatchers("/admin/**").access("hasRole('ADMIN')")
                .antMatchers("/**",
                		"/datatable/**",
                		"/sip/**",
                		"/meet/**",
                		"/authentication/*",
                		"/druid/*",
                		"/video/authority",
                		"/login",
                		"/doLogin",
                		"/user/list",
                		"/fail" ) // 不需要登录就可以访问
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/login") // 设置登录页面;
                .loginProcessingUrl("/authentication/form")// 自定义登录路径
                .failureHandler(authenticationFailHandler).permitAll()
                .successHandler(authenticationSuccessHandler);
        http.csrf().disable();
//        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
//        http.addFilter(openIdAuthenticationFilter());
//        http.addFilterBefore(new BeforeLoginFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/assets/**","/datatable/*" );
    }
    
    /**
     * 自定义登陆验证接口
     */
   /* public OpenIdAuthenticationFilter openIdAuthenticationFilter() throws Exception {
      OpenIdAuthenticationFilter openIdAuthenticationFilter = new OpenIdAuthenticationFilter();
      openIdAuthenticationFilter.setAuthenticationManager(authenticationManager());
      //只有post请求才拦截
      openIdAuthenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/authentication/form", "POST"));
//      openIdAuthenticationFilter.setAuthenticationSuccessHandler(securityAuthenticationSuccessHandler);
//      openIdAuthenticationFilter.setAuthenticationFailureHandler(securityAuthenticationFailureHandler);
      return openIdAuthenticationFilter;
    }*/


    
   
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("lzc").password(new BCryptPasswordEncoder().encode("123456")).roles("ADMIN","USER");
//        auth.inMemoryAuthentication().withUser("zhangsan").password(new BCryptPasswordEncoder().encode("123456")).roles("USER");
    	auth.userDetailsService(myUserService).passwordEncoder(new PasswordEncoder(){

            @Override
            public String encode(CharSequence rawPassword) {
//                return MD5Util.MD5Encode((String)rawPassword ,"UTF-8");
                return (String)rawPassword;
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(MD5Util.MD5Encode((String)rawPassword ,"UTF-8"));
//            	return encodedPassword.equals((String)rawPassword );
            }}); //user Details Service验证

//        auth.userDetailsService(myUserService); // 注入MyUserService，这样SpringSecurity会调用里面的loadUserByUsername(String s)
    }   
}