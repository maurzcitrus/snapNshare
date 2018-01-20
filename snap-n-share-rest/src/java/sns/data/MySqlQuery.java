package sns.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides low level JDBC implementation details for DAO classes to access
 * MySQL database.
 *
 * It assumes that database host name is "localhost", port number is "3306",
 * schema is "phoenix", user is "phoenix", and finally password is "password".
 *
 * If there is a change in database environment, please edit the openConnection
 * method in this class.
 *
 * @author phyo
 */
public class MySqlQuery {

  private ParameterSetter parameters;
  private final ResultSetMapper mapper;
  private final String sql;

  /**
   * Constructs a MySqlQuery for SELECT scenario that involve setting parameter
   * values in WHERE clause.
   *
   * @param sql
   * @param parameters ParameterSetter or equivalent Lambda Expression that set
   * parameter values of PreparedStatement.
   * @param mapper ResultSetMapper or equivalent Lambda Expression that maps one
   * database row values from ResultSet into one domain class instance.
   */
  public MySqlQuery(String sql, ParameterSetter parameters, ResultSetMapper mapper) {
    this.sql = sql;
    this.parameters = parameters;
    this.mapper = mapper;
  }

  /**
   * Constructs a MySqlQuery for SELECT scenario that does not involve setting
   * parameter values in WHERE clause.
   *
   * @param sql
   * @param mapper
   */
  public MySqlQuery(String sql, ResultSetMapper mapper) {
    this.sql = sql;
    this.parameters = null;
    this.mapper = mapper;
  }

  /**
   * Constructs a MySqlQuery for INSERT/UPDATE/DELETE scenario that involve
   * setting parameter values.
   *
   * @param sql
   * @param parameters
   */
  public MySqlQuery(String sql, ParameterSetter parameters) {
    this.sql = sql;
    this.parameters = parameters;
    this.mapper = null;
  }

  /**
   * Constructs a MySqlQuery for INSERT/UPDATE/DELETE scenario that does not
   * involve setting parameter values.
   *
   * @param sql
   */
  public MySqlQuery(String sql) {
    this.sql = sql;
    this.parameters = null;
    this.mapper = null;
  }

  private Connection openConnection() {
    Connection conn = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      System.out.print(e.getMessage());
    }

    try {
      conn = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/phoenix", "phoenix", "password");
    } catch (SQLException e) {
      System.out.print(e.getMessage());
    }
    return conn;
  }

  /**
   * Generic method to execute SELECT statement that returns one record.
   *
   * @param <T>
   * @return
   */
  public <T> T findOne() {

    PreparedStatement stmt = null;
    Connection conn = null;

    T result = null;

    try {
      conn = openConnection();
      stmt = conn.prepareStatement(sql);

      if (parameters != null) {
        parameters.SetParameters(stmt);
      }

      ResultSet resultSet = stmt.executeQuery();

      if (resultSet.next()) {
        result = (T) mapper.map(resultSet);
      }

    } catch (SQLException e) {
      // log error
      return result;
    } finally {
      try {
        if (conn != null) {
          conn.close();
        }
        if (stmt != null) {
          stmt.close();
        }
      } catch (SQLException ex) {
        // log error
      }
    }

    return result;
  }

  /**
   * Generic method to execute SELECT statement that returns zero or more
   * records.
   *
   * @param <T>
   * @return List of type T containing zero or more objects.
   */
  public <T> List<T> findAll() {

    PreparedStatement stmt = null;
    Connection conn = null;

    List<T> results = new ArrayList<T>();

    try {
      conn = openConnection();
      stmt = conn.prepareStatement(sql);

      if (parameters != null) {
        parameters.SetParameters(stmt);
      }

      ResultSet resultSet = stmt.executeQuery();

      while (resultSet.next()) {
        T item = (T) mapper.map(resultSet);
        results.add(item);
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return results;
    } finally {
      try {
        if (conn != null) {
          conn.close();
        }
        if (stmt != null) {
          stmt.close();
        }
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }

    return results;
  }

  /**
   * Generic method to execute INSERT/UPDATE/DELETE SQL statement
   *
   * @return number of rows inserted or updated or deleted
   */
  public int update() {
    PreparedStatement stmt = null;
    Connection conn = null;

    try {
      conn = openConnection();
      stmt = conn.prepareStatement(sql);

      if (parameters != null) {
        parameters.SetParameters(stmt);
      }

      int rowcount = stmt.executeUpdate();
      return rowcount;

    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    } finally {
      try {
        if (conn != null) {
          conn.close();
        }
        if (stmt != null) {
          stmt.close();
        }
      } catch (SQLException ex) {
        // log error
      }
    }
  }

  /**
   * Generic method to execute INSERT/UPDATE/DELETE SQL statement.
   *
   * The parameter newRecord is meant to be used for creating audit log records
   * but this functionality is not implemented yet.
   *
   * @return the same object instance
   */
  public <T> T update(T newRecord) {

    PreparedStatement stmt = null;
    Connection conn = null;

    T result = null;

    try {
      conn = openConnection();
      stmt = conn.prepareStatement(sql);

      if (parameters != null) {
        parameters.SetParameters(stmt);
      }

      int rowcount = stmt.executeUpdate();
      result = newRecord;

    } catch (SQLException e) {
      // log error
      e.printStackTrace();
      return result;
    } finally {
      try {
        if (conn != null) {
          conn.close();
        }
        if (stmt != null) {
          stmt.close();
        }
      } catch (SQLException ex) {
        // log error
      }
    }

    return result;
  }
}
