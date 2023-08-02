package com.lamanna.auth.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.lamanna.auth.dtos.UserInfoDTO;
import com.lamanna.auth.models.MarkdownUserModel;

@Configuration
public class BeanConfig {

   @Bean
   public ModelMapper modelMapper() {

       ModelMapper modelMapper = new ModelMapper();

      // final TypeMap<MarkdownUserModel, UserInfoDTO> markdownUserModelUserInfoDTOTypeMap = modelMapper.typeMap(MarkdownUserModel.class, UserInfoDTO.class);
      // markdownUserModelUserInfoDTOTypeMap.addMappings(mapping -> mapping.skip(UserInfoDTO::setPassword));

       modelMapper.getConfiguration().setSkipNullEnabled(true);
       return modelMapper;
   }

   @Bean
   public BCryptPasswordEncoder bCryptPasswordEncoder() {
       return new BCryptPasswordEncoder();
   }


   @Bean
   public CorsFilter corsFilter() {

       UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();

       CorsConfiguration config = new CorsConfiguration();

       // GET , POST, HEAD
       config.applyPermitDefaultValues();

       config.addAllowedMethod(HttpMethod.PUT);
       config.addAllowedMethod(HttpMethod.DELETE);
       config.addAllowedMethod(HttpMethod.OPTIONS);

       configSource.registerCorsConfiguration("/**", config);
       return new CorsFilter(configSource);
   }
}
