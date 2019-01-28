package io.wanyxkhalil.abstinence.wubjpa;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wub.jpa")
public class WubJpaProperties {

    private boolean showSql;

    public boolean isShowSql() {
        return showSql;
    }

    public void setShowSql(boolean showSql) {
        this.showSql = showSql;
    }
}
