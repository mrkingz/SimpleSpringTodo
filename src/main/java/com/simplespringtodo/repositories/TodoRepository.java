package com.simplespringtodo.repositories;

import com.simplespringtodo.exceptions.CustomException;
import com.simplespringtodo.interfaces.ICRUD;
import com.simplespringtodo.models.Todo;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

/**
 * The type Todo repository.
 */
@Repository
public class TodoRepository implements ICRUD<Todo> {

    private JdbcTemplate jdbcTemplate;


    /**
     * Instantiates a new Todo repository.
     *
     * @param jdbcTemplate the jdbc template
     */
    public TodoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Gets jdbc template.
     *
     * @return the jdbc template
     */
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public Todo create(Todo todo) {

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String sql = "INSERT INTO todos (title, \"createdAt\", \"updatedAt\") VALUES (?, ?, ?)";

                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, todo.getTitle());
                ps.setString(2, todo.getCreatedAt());
                ps.setString(3, todo.getUpdatedAt());

                return ps;
            }
        };

        KeyHolder keyHolder = new GeneratedKeyHolder();

        this.getJdbcTemplate().update(psc, keyHolder);
        todo.setId(Long.parseLong(keyHolder.getKeys().get("id").toString()));

        return todo;
    }

    @Override
    public List<Todo> list() {
        String sql = "SELECT * FROM todos";

        return this.getJdbcTemplate().query(sql, new RowMapper<Todo>() {

            @Override
            public Todo mapRow(ResultSet resultSet, int i) throws SQLException {
                Todo todo = new Todo();
                todo.setId(resultSet.getLong("id"));
                todo.setTitle(resultSet.getString("title"));
                todo.setCreatedAt(resultSet.getString("createdAt"));
                todo.setUpdatedAt(resultSet.getString("updatedAt"));
                return todo;
            }
        });
    }

    @Override
    public Todo findOne(long id) {
        String sql = "SELECT * FROM todos WHERE id = ?";

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setLong(1, id);
                return ps;
            }
        };

        return this.getJdbcTemplate().query(psc, new ResultSetExtractor<Todo> () {
            @Override
            public Todo extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                Todo todo = new Todo();

                if (resultSet.next()) {
                    todo.setId(resultSet.getLong("id"));
                    todo.setTitle(resultSet.getString("title"));
                    todo.setCreatedAt(resultSet.getString("createdAt"));
                    todo.setUpdatedAt(resultSet.getString("updatedAt"));
                }

                return todo;
            }
        });
    }

    @Override
    public Todo update(Todo todo, long id) {
        String sql = "UPDATE todos SET title = ?, \"updatedAt\" = ? WHERE id = ?";

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, todo.getTitle());
                ps.setString(2, todo.getUpdatedAt());
                ps.setLong(3, id);
                return ps;
            }
        };

        if (this.getJdbcTemplate().update(psc) > 0)
            return  todo;
        else throw new CustomException("Internal error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public void delete(long id) {
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String sql = "DELETE FROM todos WHERE id  = ?";

                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setLong(1, id);
                return ps;
            }
        };

        this.getJdbcTemplate().update(psc);
    }
}
