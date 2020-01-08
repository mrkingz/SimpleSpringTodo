package com.simplespringtodo.config;

import com.simplespringtodo.repositories.TodoItemRepository;
import com.simplespringtodo.repositories.TodoRepository;
import com.simplespringtodo.services.TodoItemService;
import com.simplespringtodo.services.TodoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

/**
 * The type Web config.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.simplespringtodo")
public class WebConfig implements WebMvcConfigurer {

    /**
     * Data source data source.
     *
     * @return the data source
     */
    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/todo");
        dataSource.setUsername("postgres");
        dataSource.setPassword("123456789");

        return dataSource;
    }

    /**
     * Jdbc template jdbc template.
     *
     * @return the jdbc template
     */
    @Bean
    public JdbcTemplate getJdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(this.getDataSource());

        return jdbcTemplate;
    }

    /**
     * Todo repository todo repository.
     *
     * @return the todo repository
     */
    @Bean
    public TodoRepository getTodoRepository() {
        return new TodoRepository(this.getJdbcTemplate());
    }

    /**
     * Todo service todo service.
     *
     * @return the todo service
     */
    @Bean
    public TodoService getTodoService() {
        return new TodoService(this.getTodoRepository());
    }

    /**
     * Todo item repository todo item repository.
     *
     * @return the todo item repository
     */
    @Bean
    public TodoItemRepository getTodoItemRepository() {
        return new TodoItemRepository(this.getJdbcTemplate());
    }

    /**
     * Todo item service todo item service.
     *
     * @return the todo item service
     */
    @Bean
    public TodoItemService getTodoItemService() {
        return new TodoItemService(this.getTodoItemRepository(), this.getTodoService());
    }
}
