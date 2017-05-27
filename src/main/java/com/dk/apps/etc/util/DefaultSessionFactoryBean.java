package com.dk.apps.etc.util;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

public class DefaultSessionFactoryBean extends LocalSessionFactoryBean {
    protected Configuration newConfiguration() throws HibernateException {
        Configuration config = new Configuration();
        config.setNamingStrategy(UnderscoreNamingStrategy.INSTANCE);
        return config;
    }

    public void setTablePrefix(String tablePrefix) {
        UnderscoreNamingStrategy.INSTANCE.setTablePrefix(tablePrefix);
    }
}