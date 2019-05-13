import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    private static final String IMAGE_EXTENSION = "png";

    @Bean
    public Base64ImageConverterService base64ImageConverterService(){
        return new Base64ImageConverterService();
    }

    @Bean
    public ImageFileScanner imageFileScanner(){
        return new ImageFileScanner(IMAGE_EXTENSION);
    }
}
