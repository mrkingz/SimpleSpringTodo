package com.simplespringtodo.repositories;

import com.simplespringtodo.exceptions.CustomException;
import com.simplespringtodo.interfaces.ICRUD;
import com.simplespringtodo.models.TodoItem;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.List;

/**
 * The type Todo item repository.
 */
public class TodoItemRepository implements ICRUD<TodoItem> {

    private TodoItemRepository todoItemRepository;

    private JdbcTemplate jdbcTemplate;

    /**
     * Instantiates a new Todo item repository.
     *
     * @param jdbcTemplate       the jdbc template
     */
    public TodoItemRepository(JdbcTemplate jdbcTemplate) {
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


    /**
     * Gets todo repository.
     *
     * @return the todo repository
     */
    public TodoItemRepository getTodoItemRepository() {
        return todoItemRepository;
    }


    @Override
    public TodoItem create(TodoItem todoItem) {
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String sql = "INSERT "
                        + "INTO \"todoItems\" (content, \"todoId\", \"isCompleted\", \"createdAt\", \"updatedAt\") "
                        + "VALUES(?, ?, ?, ?, ?)";

                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                ps.setString(1, todoItem.getContent());
                ps.setLong(2, todoItem.getTodoId());
                ps.setBoolean(3, false);
                ps.setString(4, todoItem.getCreatedAt());
                ps.setString(5, todoItem.getUpdatedAt());

                return ps;
            }
        };

        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.getJdbcTemplate().update(psc, keyHolder);

        todoItem.setId(Long.parseLong(keyHolder.getKeys().get("id").toString()));

        return todoItem;
    }

    @Override
    public List<TodoItem> list() {
        String sql = "SELECT * FROM \"todoItems\"";

        return this.getJdbcTemplate().query(sql, new RowMapper<TodoItem>() {
            @Override
            public TodoItem mapRow(ResultSet resultSet, int i) throws SQLException {
                TodoItem todoItem = new TodoItem();
                todoItem.setId(resultSet.getLong("id"));
                todoItem.setTodoId(resultSet.getLong("todoId"));
                todoItem.setContent(resultSet.getString("content"));
                todoItem.setCompleted(resultSet.getBoolean("isCompleted"));
                todoItem.setCreatedAt(resultSet.getString("createdAt"));
                todoItem.setUpdatedAt(resultSet.getString("updatedAt"));

                return todoItem;
            }
        });
    }

    @Override
    public TodoItem findOne(long id) {

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String sql = "SELECT * FROM \"todoItems\" WHERE id = ?";

                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setLong(1, id);

                return ps;
            }
        };
        return this.getJdbcTemplate().query(psc, new ResultSetExtractor<TodoItem>() {
            @Override
            public TodoItem extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                TodoItem todoItem = new TodoItem();

                if (resultSet.next()) {
                    todoItem.setId(resultSet.getLong("id"));
                    todoItem.setTodoId(resultSet.getLong("todoId"));
                    todoItem.setContent(resultSet.getString("content"));
                    todoItem.setCompleted(resultSet.getBoolean("isCompleted"));
                    todoItem.setCreatedAt(resultSet.getString("createdAt"));
                    todoItem.setUpdatedAt(resultSet.getString("updatedAt"));
                }

                return todoItem;
            }
        });
    }

    @Override
    public TodoItem update(TodoItem todoItem, long id) {

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String sql = "UPDATE \"todoItems\" SET conten = ?, \"isCompleted\" = ?, \"createdAt\" = ? WHERE id = ?";

                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, todoItem.getContent());
                ps.setBoolean(2, todoItem.isCompleted());
                ps.setString(3, todoItem.getUpdatedAt());
                ps.setLong(4, todoItem.getId());

                return ps;
            }
        };

        this.getJdbcTemplate().update(psc);

        return todoItem;
    }

    @Override
    public void delete(long id) {
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String sql = "DELETE FROM \"todoItems\" WHERE id = ?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setLong(1, id);

                return ps;
            }
        };

        this.getJdbcTemplate().update(psc);
    }
}
