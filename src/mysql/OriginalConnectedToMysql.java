package mysql;

import java.sql.Driver;

/**
 * @author masuo
 * @data 5/5/2022 下午5:38
 * @Description 传统连接MySQL的方式
 * 需要准备MySQL-connect-java.jar
 */

public class OriginalConnectedToMysql {

    public void connectedToMysql() {

        try {
            Class<?> driver = Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
