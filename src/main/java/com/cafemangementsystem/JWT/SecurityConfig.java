//package com.cafemangementsystem.JWT;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.BeanIds;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig{
//
//    @Autowired
//    CustomerUserDetailsServices customerUserDetailsServices;
//
//    @Autowired
//     JwtFilter jwtFilter;
//
//
////    public DaoAuthenticationConfigurer<AuthenticationManagerBuilder, CustomerUserDetailsServices> configure(AuthenticationManagerBuilder auth) throws Exception
////    {
////       return auth.userDetailsService(customerUserDetailsServices);
////    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder()
//    {
//        return NoOpPasswordEncoder.getInstance();
//    }
//
////    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
////    protected AuthenticationManager authenticationManagerBean() throws Exception
////    {
////        return authenticationManagerBean();
////    }
//
//
//    @Bean
//    public SecurityFilterChain configureHttp(HttpSecurity http) throws Exception
//    {
//
////               http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//       // return http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
//
//                 http
//                         .csrf().disable()
//                         //  .authorizeRequests()
//                         .authorizeHttpRequests()
//                         .requestMatchers("/user/login", "/user/signup","user/forgotPassword")
//                         .permitAll()
//                         .anyRequest()
//                         .authenticated()
//                         .and().exceptionHandling()
//                         .and()
//                         .sessionManagement()
//                         .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                         ;
//
//
//              // .and().build();
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
//    {
//        return config.getAuthenticationManager();
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider()
//    {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService((customerUserDetailsServices));
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }
//
//}
