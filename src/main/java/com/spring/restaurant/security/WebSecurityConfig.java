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

                .antMatchers("/**/allOrders").permitAll()
                .antMatchers("/**/category").permitAll()
                .antMatchers("/**/orderkey/*").permitAll()
                .antMatchers("/**/order").permitAll()
                .antMatchers("/**/orderSize").permitAll()
                .antMatchers("/**/ctegoryidsize").permitAll()
                .antMatchers("/**/keysize/*").permitAll()
                .antMatchers("/**/products/searchAllArticleOrderByIdDesc").permitAll()
                .antMatchers("/**/products/createWithFileInFolder").permitAll()
                .antMatchers("/**/products/findById/{prodId}").permitAll()

                .antMatchers("/**/products/photoProductInContext/{idArticle}").permitAll()
                .antMatchers("/**/products/uploadProductPhotoInFolder/{id}").permitAll()
                .antMatchers("/**/products/update/{idArticle}").permitAll()
                .antMatchers("/**/products/delete/{idArticle}").permitAll()

                .antMatchers("/**/categories/allCategories").permitAll()
                .antMatchers("/**/categories/searchAllCategoriesOrderByIdDesc").permitAll()
                .antMatchers("/**/categories/create").permitAll()
                .antMatchers("/**/categories/update/{catId}").permitAll()
                .antMatchers("/**/categories/findById/{catId}").permitAll()
                .antMatchers("/**/categories/delete/{catId}").permitAll()

                .antMatchers("/**/purchase").permitAll()

                .antMatchers("/**/ligneVentes/all").permitAll()
                .antMatchers("/**/ligneVentes/allLigneVenteOrderDesc").permitAll()
                .antMatchers("/**/ligneVentes/findById/{id}").permitAll()
                .antMatchers("/**/ligneVentes/findByNumero/{id}").permitAll()
                .antMatchers("/**/ligneVentes/searchListLigneVentesByVenteId/{venteId}").permitAll()
                .antMatchers("/**/ligneVentes/create").permitAll()
                .antMatchers("/**/ligneVentes/delete/{id}").permitAll()

                .antMatchers("/**/ventes/create").permitAll()
                .antMatchers("/**/ventes/findById/{id}").permitAll()
                .antMatchers("/**/ventes/sumVenteInDay").permitAll()
                .antMatchers("/**/ventes/sumVenteInMonth").permitAll()
                .antMatchers("/**/ventes/sumVenteInYear").permitAll()

                .antMatchers("/**/ventes/all").permitAll()
                .antMatchers("/**/ventes/searchAllVentesOrderByIdDesc").permitAll()
                .antMatchers("/**/ventes/sumTotalOfVentesPeerMonth").permitAll()
                .antMatchers("/**/ventes/sumTotalOfVentesPeerYear").permitAll()
                .antMatchers("/**/delete/{id}").permitAll()

                .antMatchers("/**/items/create").permitAll()
                .antMatchers("/**/items/findById/{id}").permitAll()
                .antMatchers("/**/items/all").permitAll()
                .antMatchers("/**/items/searchAllItemsOrderByIdDesc").permitAll()
                .antMatchers("/**/items/searchAllItemsOrderByQuantityDesc").permitAll()
                .antMatchers("/**/items/searchAllItemsByRequestOrderId/{comId}").permitAll()
                .antMatchers("/**/items/delete/{id}").permitAll()


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
