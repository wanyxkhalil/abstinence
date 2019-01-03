package io.wanyxkhalil.abstinence.wub.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;


/**
 * 统一配置
 */
public class WubConsulEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    @Override
    public int getOrder() {
        return ConfigFileApplicationListener.DEFAULT_ORDER + 1;
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        MutablePropertySources propertySources = environment.getPropertySources();
        PropertySource<?> bootstrap = propertySources.get("applicationConfig: [classpath:/bootstrap.yml] (document #0)");

        // 仅用于boostrap阶段，无需第二次
        if (Objects.isNull(bootstrap)) {
            return;
        }

        ClassPathResource path = new ClassPathResource("custom-consul.yml");
        PropertySource<?> custom = loadYaml(path);

        // 获取其他参数
        Object appName = bootstrap.getProperty("spring.application.name");
        String profileName = System.getProperty("spring.profiles.active");
        if (Objects.isNull(profileName)) {
            profileName = (String) bootstrap.getProperty("spring.profiles.active");
        }

        // 添加其他配置
        Map<String, Object> customMap = retrieveSourceMap(custom);
        customMap.put("spring.cloud.consul.config.defaultContext", appName);
        customMap.put("spring.cloud.consul.discovery.instanceId", appName + ":" + profileName);

        Object group = retrievePropertyOrDefault(bootstrap, "wub.consul.group", "khalil");
        Object urlPrefix = retrievePropertyOrDefault(bootstrap, "wub.consul.prefix", appName);
        customMap.put("spring.cloud.consul.discovery.tags", String.format("group=%s, urlprefix-/%s", group, urlPrefix));

        environment.getPropertySources().addLast(custom);
    }

    /**
     * 获取属性值
     */
    private Object retrievePropertyOrDefault(PropertySource<?> propertySource, String name, Object defaultValue) {
        Object property = propertySource.getProperty(name);
        if (Objects.nonNull(property)) {
            return property;
        }
        return defaultValue;
    }

    /**
     * 获取Source并转为Map类型
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> retrieveSourceMap(PropertySource<?> propertySource) {
        return (Map<String, Object>) propertySource.getSource();
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
