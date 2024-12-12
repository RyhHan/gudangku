/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gudangkuu;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author andi_
 */
public class Koneksi {

    private final MysqlDataSource dataSource = new MysqlDataSource();
    private final String DB_URL = "jdbc:mysql://localhost:3306/"
            + "gudangku?serverTimezone=Asia/Jakarta";
    private final String DB_USERNAME = "root";
    private final String DB_PASSWORD = "";

    public Connection getConnection() throws SQLException {
        dataSource.setUrl(DB_URL);
        dataSource.setUser(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        Connection conn = dataSource.getConnection();
        return conn;
    }
}
