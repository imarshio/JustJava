package mysql;

import com.mysql.cj.jdbc.Driver;
import org.junit.Test;

import java.sql.*;
import java.util.Properties;

/**
 * @author masuo
 * @data 5/5/2022 下午5:38
 * @Description 传统连接MySQL的方式
 * 需要准备 MySQL-connector-java-version.jar
 * JDBC：Java DataBase Connectivity，是Java连接数据库的API，不与特定的数据库关联
 * 如MySQL-connector-java-8.0.18.jar就是根据JDBC延伸出来的与MySQL相连的实现
 */

public class OriginalConnectedWay {

    // 直接获取与 MySQL 的连接
    public Connection getConnectionToMysqlWithDriver() {
        Connection connection = null;
        try {
            // 设置驱动
            Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            // url
            String url = "jdbc:mysql://localhost:3306/cloudalibaba?characterEncoding=UTF-8&serverTimezone=UTC";
            // 连接数据库信息
            Properties info = new Properties();
            info.setProperty("user", "root");
            info.setProperty("password", "20173602");

            // 打开链接
            connection = driver.connect(url, info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    // 使用DriverManager获取与 MySQL 的连接
    public Connection getConnectionToMysqlWithDriverManager() {
        Connection connection = null;
        try {
            // 连接数据库信息
            String url = "jdbc:mysql://localhost:3306/cloudalibaba?characterEncoding=UTF-8&serverTimezone=UTC";
            String user = "root";
            String password = "20173602";

            // 打开链接
            connection = DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    // 测试数据库
    @Test
    public void connectedToMysql() {

        // 打开连接
        Connection connect = getConnectionToMysqlWithDriverManager();
        // 准备
        PreparedStatement preparedStatement = null;
        try {
            // 准备sql
            String sql = "select * from payment where id between ? and ?";

            // 打开
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setInt(1, 10);
            preparedStatement.setInt(2, 12);

            // 执行查询
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String serial = resultSet.getString("serial");
                System.out.println("id:" + id + "+ serial:" + serial);
            }
            //
            System.out.println(connect);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            // 关闭流
            try {
                if(preparedStatement != null){
                    preparedStatement.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }
    }

    /**
     * 使用 Statement 执行查询
     */
    @Test
    public void executeWithStatement() {
        // 打开连接
        Connection connect = getConnectionToMysqlWithDriver();
        // 准备
        Statement statement = null;

        try {
            int index1 = 10;
            int index2 = 12;
            // 准备sql
            String sql = "select * from payment where id between " + index1 +  " and " + index2;

            statement = connect.createStatement();
            statement.executeQuery(sql);

            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String serial = resultSet.getString("serial");
                System.out.println("id:" + id + " + serial:" + serial);
            }

        }catch (Exception exception){
            exception.printStackTrace();
        }finally {
            // 关闭流
            try {
                if(statement != null){
                    statement.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * 使用 PreparedStatement 执行查询
     * PreparedStatement与Statement的区别是，
     *  - PreparedStatement是经过预编译的，可以直接执行的，所以相对较快，对于批处理可以大大提高效率，也叫JDBC存储过程，且具有安全性，防止SQL注入
     *  - Statement是需要经过Mysql编译才能执行的，当查询只涉及一次存取时，适合使用
     */
    @Test
    public void executeWithPreparedStatement() {
        // 打开连接
        Connection connect = getConnectionToMysqlWithDriver();
        // 准备
        PreparedStatement preparedStatement = null;
        try {
            // 准备sql
            String sql = "select * from payment where id between ? and ?";

            // 打开
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setInt(1, 10);
            preparedStatement.setInt(2, 13);

            // 执行查询
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String serial = resultSet.getString("serial");
                System.out.println(resultSet + "id:" + id + "+ serial:" + serial);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            // 关闭流
            try {
                if(preparedStatement != null){
                    preparedStatement.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }
    }
}
