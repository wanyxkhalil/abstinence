package io.wanyxkhalil.abstinence.wubmybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;


/**
 * 统一配置
 */
public class WubMybatisEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {


    @Override
    public int getOrder() {
        return ConfigFileApplicationListener.DEFAULT_ORDER + 1;
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        ClassPathResource path = new ClassPathResource("custom-mybatis.yml");
        PropertySource<?> custom = loadYaml(path);

        environment.getPropertySources().addLast(custom);
    }

    private final YamlPropertySourceLoader loader = new YamlPropertySourceLoader();

    private PropertySource<?> loadYaml(Resource path) {
        if (!path.exists()) {
            throw new IllegalArgumentException("Resource " + path + " does not exist");
        }
        try {
            return this.loader.load(path.getFilename(), path).get(0);
        } catch (IOException ex) {
            throw new IllegalStateException(
                    "Failed to load yaml configuration from " + path, ex);
        }
    }

}
