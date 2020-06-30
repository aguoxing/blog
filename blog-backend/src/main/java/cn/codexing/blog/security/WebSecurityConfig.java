package cn.codexing.blog.security;

import cn.codexing.blog.common.filter.JwtAuthenticationFilter;
import cn.codexing.blog.common.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private LoginFailureHandler loginFailureHandler;
    @Autowired
    private CustomLogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private CustomAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private AccessDeineHandler accessDeineHandler;

    @Autowired
    protected CustomUserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/*.html", "favicon.ico", "/**/*.html", "/**/*.html", "/**/*.css", "/**/*.js").permitAll()
                .antMatchers("/link/save","/user/update/**").permitAll()
                .antMatchers("/**/update/**","/**/delete/**","/**/save").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .logout().logoutUrl("/user/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .and().formLogin().loginProcessingUrl("/user/login")
                // 自定义登录成功处理，返回token值
                .successHandler(loginSuccessHandler)
                // 登录失败处理
                .failureHandler(loginFailureHandler)
                .and().exceptionHandling().accessDeniedHandler(accessDeineHandler)
                // 匿名用户访问无权限资源时的异常
                .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                // 基于token，所以不需要session
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 由于使用的是jwt，这里不需要csrf防护并且禁用缓存
                .and().csrf().disable().headers().cacheControl();
        // token过滤
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * token过滤器
     *
     * @return
     */
    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() {
        return new JwtAuthenticationFilter();
    }
}