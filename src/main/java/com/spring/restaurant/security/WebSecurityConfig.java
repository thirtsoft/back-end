package com.spring.restaurant.security;


import com.spring.restaurant.deo.UtilisateurRepository;
import com.spring.restaurant.security.jwt.JwtAuthEntryPoint;
import com.spring.restaurant.security.jwt.JwtAuthTokenFilter;
import com.spring.restaurant.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/**/auth/signIn").permitAll()
                .antMatchers("/**/auth/signUp").permitAll()
                .antMatchers("/**/utilisateurs/photoUser/{id}").permitAll()
                .antMatchers("/**/utilisateurs/avatar/{id}").permitAll()
                .antMatchers("/**/utilisateurs/activatedUser/*").permitAll()

                .antMatchers("/api/allOrders").permitAll()
                .antMatchers("/api/category").permitAll()
                .antMatchers("/api/orderkey/*").permitAll()
                .antMatchers("/api/order").permitAll()
                .antMatchers("/api/orderSize").permitAll()
                .antMatchers("/api/ctegoryidsize").permitAll()
                .antMatchers("/api/keysize/*").permitAll()
                .antMatchers("/api/products/searchAllArticleOrderByIdDesc").permitAll()
                .antMatchers("/api/products/createWithFileInFolder").permitAll()
                .antMatchers("/api/products/findById/{prodId}").permitAll()

                .antMatchers("/api/allCategoies").permitAll()
                .antMatchers("/api/categories/searchAllCategoriesOrderByIdDesc").permitAll()
                .antMatchers("/api/categories/create").permitAll()
                .antMatchers("/api/categories/update/{catId}").permitAll()
                .antMatchers("/api/categories/findById/{catId}").permitAll()
                .antMatchers("/api/categories/delete/{catId}").permitAll()

                .antMatchers("/api/buy/purchase").permitAll()

                .antMatchers("/**/ligneVentes/all").permitAll()
                .antMatchers("/**/ligneVentes/allLigneVenteOrderDesc").permitAll()
                .antMatchers("/**/ligneVentes/findById/{id}").permitAll()
                .antMatchers("/**/ligneVentes/findByNumero/{id}").permitAll()
                .antMatchers("/**/ligneVentes/searchListLigneVentesByVenteId/{venteId}").permitAll()
                .antMatchers("/**/ligneVentes/create").permitAll()
                .antMatchers("/**/ligneVentes/delete/{id}").permitAll()

                .antMatchers("/api/ventes/create").permitAll()
                .antMatchers("/api/ventes/findById/{id}").permitAll()
                .antMatchers("/api/ventes/sumVenteInDay").permitAll()
                .antMatchers("/api/ventes/sumVenteInMonth").permitAll()
                .antMatchers("/api/ventes/sumVenteInYear").permitAll()

                .antMatchers("/api/ventes/all").permitAll()
                .antMatchers("/api/ventes/searchAllVentesOrderByIdDesc").permitAll()
                .antMatchers("/api/ventes/sumTotalOfVentesPeerMonth").permitAll()
                .antMatchers("/api/ventes/sumTotalOfVentesPeerYear").permitAll()
                .antMatchers("/api/delete/{id}").permitAll()

                .antMatchers("/api/items/create").permitAll()
                .antMatchers("/api/findById/{id}").permitAll()
                .antMatchers("/api/items/all").permitAll()
                .antMatchers("/api/items/searchAllItemsOrderByIdDesc").permitAll()
                .antMatchers("/api/items/searchAllItemsByRequestOrderId/{comId}").permitAll()
                .antMatchers("/api/delete/{id}").permitAll()

                .antMatchers("/**/utilisateurs/all").permitAll()
                .antMatchers("/**/utilisateurs/allUtilisateurOrderDesc").permitAll()
                .antMatchers("/**/utilisateurs/findById/{id}").permitAll()
                .antMatchers("/**/utilisateurs/avatar/{id}").permitAll()
                .antMatchers("/**/utilisateurs/photo").permitAll()
                .antMatchers("/**/utilisateurs/photoUser/{id}").permitAll()
                .antMatchers("/**/utilisateurs/uploadUserPhoto/{id}").permitAll()
                .antMatchers("/**/utilisateurs/uploadUserPhoto/{id}/uploadUserPhoto").permitAll()
                .antMatchers("/**/utilisateurs/update/{idUser}").permitAll()
                .antMatchers("/**/utilisateurs/activatedUser/{id}").permitAll()
                .antMatchers("/**/utilisateurs/delete/{id}").permitAll()
                .antMatchers("/**/utilisateurs/updateUsername").permitAll()
                .antMatchers("/**/utilisateurs/updatePassword").permitAll()


                .anyRequest().authenticated();
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*").allowedOriginPatterns("http://localhost:4200");
            }
        };
    }

}
