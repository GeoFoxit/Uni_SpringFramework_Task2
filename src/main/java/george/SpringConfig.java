package george;

import george.dao.CarDao;
import george.dao.ClientDao;
import george.dao.OrderDao;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:postgresql://127.0.0.1:5432/smtrpz");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("george");
        return dataSourceBuilder.build();
    }

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

    @Bean
    public CarDao getCarDao() {
        return new CarDao();
    }

    @Bean
    public ClientDao getClientDao() {
        return new ClientDao();
    }

    @Bean
    public OrderDao getOrderDao() {
        return new OrderDao();
    }

}
