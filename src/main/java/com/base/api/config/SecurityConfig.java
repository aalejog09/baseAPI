package com.base.api.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * Clase principal de configuracion de acceso a la aplicacion
 * @author AAlejo
 * */
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
   
    BCryptPasswordEncoder bCryptPasswordEncoder;
   
    /**
     * Crea el encriptador de contraseñas	
     * El numero 4 representa que tan fuerte quieres la encriptacion. Se puede en un rango entre 4 y 31. 
     * Si no se coloca un numero el programa utilizara uno aleatoriamente cada vez que inicies la aplicacion
     * 
     * @return bCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        return bCryptPasswordEncoder;
    }
  
    protected void configure(HttpSecurity http) throws Exception {
    	
    	 /**
         * Step adicional para callear al cors del Metodo Main
         */
        http = http.cors().and().csrf().disable();

        http.authorizeRequests()
        .antMatchers("/swagger-ui/**").permitAll() //necesario para acceder a  swagger sin ninguna restriccion
        .antMatchers("swagger-ui.html").permitAll() //necesario para swagger
        .antMatchers("/swagger-resources/**").permitAll() //necesario para swagger
        .antMatchers("/v2/api-docs/**").permitAll()//necesario para swagger
        .antMatchers("/v2/api-docs").permitAll()//necesario para swagger
        .antMatchers(HttpMethod.OPTIONS).permitAll()//necesario para que accedan a todos los componentes de las carpetas internas del app
        .anyRequest().authenticated()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  
    }

}
