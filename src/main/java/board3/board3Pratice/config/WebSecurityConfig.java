package board3.board3Pratice.config;

import board3.board3Pratice.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {
    // 이걸 통해서 user객체를 불러옴!! 그리고 검증할때 사용!
    private final UserDetailService userDetailService;


    // 두가지를 해주는데
    // 일단 무조건 통과되는 애들 적어주기 -> static/** css,js 같은 애들
    // 그리고 무조건 로그인을 해야하는 url들에 대한 로그인 관련 설정

    // 일단 무조건 통과되는 애들 모음집
    // Bean으로 spring에 등록을 해놔야 스프링이 관리해서 처리해줌!
    //
    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers("/static/**");

    }

    // 이제 securityfilterchain 방식으로 로그인 부분들 설정해주기!
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return  http
                .authorizeRequests()
                .requestMatchers("/login", "/signup", "/user").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/articles")
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .and().csrf().disable().build();
    }

    // 그리고 유저 정보를 가져 왔을때 들어온 유저의 정보로 만든 토큰과 비교하기
   @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity,
                                                       BCryptPasswordEncoder bCryptPasswordEncoder,
                                                       UserDetailService userDetailService
    ) throws  Exception {

        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and().build();
   }

   @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return  new BCryptPasswordEncoder();
   }


}
