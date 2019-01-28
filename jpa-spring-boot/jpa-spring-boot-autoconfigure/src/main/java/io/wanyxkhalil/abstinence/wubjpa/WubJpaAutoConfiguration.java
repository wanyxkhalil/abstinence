package io.wanyxkhalil.abstinence.wubjpa;

import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(WubJpaProperties.class)
public class WubJpaAutoConfiguration {

    @Autowired
    private WubJpaProperties wubJpaProperties;

    @Bean
    public PhysicalNamingStrategy physicalNamingStrategy() {
        return new SpringPhysicalNamingStrategy();
    }

    @Bean
    public JpaProperties jpaProperties() {
        JpaProperties properties = new JpaProperties();
        properties.setShowSql(wubJpaProperties.isShowSql());
        properties.setOpenInView(false);

        return properties;
    }
}
