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

    public class StringTrimConverter implements Converter<String, String> {
        @Override
        public String convert(MappingContext<String, String> context) {
            String source = context.getSource();
            if(source == null) return null;
            
            String trimmedString = source.trim();
            if(trimmedString.isEmpty()) return "";

            if (context.getMapping() != null) {
                String destinationPropertyName = context.getMapping().getLastDestinationProperty().getName();
                if ("emailAddress".equals(destinationPropertyName)) {
                    return trimmedString; // Skip capitalization for emailAddress
                }
            }
            return capitalizeFirstLetter(trimmedString);
        }
        
        private String capitalizeFirstLetter(String input) {
            return input.length() > 0 
                ? Character.toUpperCase(input.charAt(0)) + input.substring(1) 
                : input;
        }
    }
}