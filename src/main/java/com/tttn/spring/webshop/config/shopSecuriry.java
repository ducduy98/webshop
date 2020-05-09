package com.tttn.spring.webshop.config;


import com.tttn.spring.webshop.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class shopSecuriry extends WebSecurityConfigurerAdapter {

    @Autowired
    TaiKhoanService taiKhoanService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(taiKhoanService).passwordEncoder(passwordEncoder());
    }
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Get AuthenticationManager bean
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/register").permitAll()
                .antMatchers("/trangchu").permitAll()
                .antMatchers("/quanlidonhang").hasAnyRole("ADMIN", "QUANLI", "NHANVIEN")
                .antMatchers("/homeAdmin").hasAnyRole("ADMIN", "QUANLI", "NHANVIEN")
                .antMatchers("/nhaphang").hasAnyRole("ADMIN", "QUANLI", "NHANVIEN")
                .antMatchers("/code").hasAnyRole("ADMIN", "QUANLI", "NHANVIEN")
                .antMatchers("/quanlitaikhoan").hasAnyRole("ADMIN", "QUANLI")
                .antMatchers("/quanlidanhmuc").hasAnyRole("ADMIN", "QUANLI")
                .antMatchers("/total").hasAnyRole("ADMIN", "QUANLI")
                .and()
                .formLogin()
                .loginPage("/loginShop")
                .loginProcessingUrl("/login_security")
                .usernameParameter("username")
                .passwordParameter("pass")
                .defaultSuccessUrl("/trangchu")
                .failureUrl("/loginShop?error=login false")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403");

    }
}
