package org.example;

import java.sql.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {
        exemple1();
        exemple2();
        exemple3();
    }

    private static void exemple3() throws SQLException {
        Connection conn =  connectToDB();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select count(*) from author");
        rs.next();
        int k = rs.getInt(1);
        System.out.println("в таблице оказывается "+k+"строк");

        st.execute("INSERT INTO author(fio) VALUES('Пелевин')");

        rs = st.executeQuery("select count(*) from author");
        rs.next();
        k = rs.getInt(1);
        System.out.println("в таблице оказывается "+k+"строк");

        rs = st.executeQuery("SELECT * FROM author WHERE fio ilike 'п%' ORDER BY fio");
        while (rs.next()) {
            System.out.println(rs.getString("fio")+" : "+rs.getInt("id"));
        }
        rs.close();
        st.close();
    }

    private static void exemple2() throws SQLException {
        Connection conn =  connectToDB();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select count(*) from reader");
        rs.next();
        int k = rs.getInt(1);
        System.out.println("в таблице оказывается "+k+"строк");
        rs.close();
        st.close();
    }

    private static void exemple1() throws SQLException {
        Connection conn =  connectToDB();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT fio FROM reader limit 25");
        while (rs.next()) {
           System.out.println(rs.getString(1));
        }
        rs.close();
        st.close();

    }

    private static Connection connectToDB() {
        String url = "jdbc:postgresql://10.10.104.166:5432/Lib?user=postgres&password=123";//&ssl=true
        try {
            Connection conn = DriverManager.getConnection(url);
            System.out.println("подключено");
            return conn;
        } catch (Exception e) {
            System.out.println("не удалось подключиться к базе. "+e.getMessage());
            return null;
        }
    }
}
