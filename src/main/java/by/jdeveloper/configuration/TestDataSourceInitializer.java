package by.jdeveloper.configuration;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.MapPropertySource;

import java.util.Map;

public class TestDataSourceInitializer implements ApplicationContextInitializer<GenericApplicationContext> {
    @Override
    public void initialize(GenericApplicationContext context) {
        context.getEnvironment().getPropertySources().addFirst(new MapPropertySource(
                "test-props",
                Map.of(
                        "spring.datasource.url",      "jdbc:postgresql://localhost:5432/blog",
                        "spring.datasource.username", "postgres",
                        "spring.datasource.password", "postgres"
                )
        ));
    }
}
