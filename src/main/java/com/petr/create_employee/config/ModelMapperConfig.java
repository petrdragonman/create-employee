package com.petr.create_employee.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.typeMap(String.class, String.class).setConverter(new StringTrimConverter());
        return mapper;
    }

    private class StringTrimConverter implements Converter<String, String> {
        @Override
        public String convert(MappingContext<String, String> context) {
            String source = context.getSource();
            if(context.getSource() == null) {
                return null;
            }
            String trimmedString = source.trim();
            String capitalisedString = trimmedString.substring(0, 1).toUpperCase() + trimmedString.substring(1).toLowerCase();
            return capitalisedString;
        }   
    }  
}
