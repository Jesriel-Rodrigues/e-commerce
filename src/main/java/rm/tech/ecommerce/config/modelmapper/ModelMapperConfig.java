package rm.tech.ecommerce.config.modelmapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NameTokenizers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    
    @Bean
    public ModelMapper modelMapper(){
        
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setUseOSGiClassLoaderBridging(true)
                .setPreferNestedProperties(false)
                .setSourceNameTokenizer(NameTokenizers.UNDERSCORE)
                .setDestinationNameTokenizer(NameTokenizers.UNDERSCORE);

        // Adiciona um conversor personalizado para lidar com a conversão de string para LocalDateTime
        Converter<String, LocalDateTime> stringToLocalDateTimeConverter = new AbstractConverter<String, LocalDateTime>() {
            protected LocalDateTime convert(String source) {
                try {
                    return LocalDateTime.parse(source);
                } catch (DateTimeParseException e) {
                    // Se ocorrer um erro ao fazer o parsing da data, retorna null
                    // O ModelMapper irá ignorar automaticamente o valor null
                    return null;
                }
            }
        };
        modelMapper.addConverter(stringToLocalDateTimeConverter);

        return modelMapper;
    }
}
