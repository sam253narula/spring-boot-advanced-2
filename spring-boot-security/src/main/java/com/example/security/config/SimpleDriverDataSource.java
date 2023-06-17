package com.example.security.config;

import java.sql.Driver;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.ConnectionProperties;
import org.springframework.jdbc.datasource.embedded.DataSourceFactory;
import org.springframework.stereotype.Component;

@Component
final class SimpleDriverDataSourceFactory implements DataSourceFactory {

    private final SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

    @Override
    public ConnectionProperties getConnectionProperties() {
      return new ConnectionProperties() {
        @Override
        public void setDriverClass(Class<? extends Driver> driverClass) {
          dataSource.setDriverClass(driverClass);
        }

        @Override
        public void setUrl(String url) {
          dataSource.setUrl(url);
        }

        @Override
        public void setUsername(String username) {
          // override the sa user
          dataSource.setUsername( "admin" );
        }

        @Override
        public void setPassword(String password) {
          // override the empty password
          dataSource.setPassword( "admin" );
        }
      };
    }

    @Override
    public DataSource getDataSource() {
      return this.dataSource;
    }
  }