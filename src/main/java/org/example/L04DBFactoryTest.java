package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class L04DBFactoryTest {
    public static void main(String[] args) {
        String sql="SELECT * FROM DEPT";
        try(Connection conn=L03DBFactory.getConn(); //getConn 을 conn 으로 바꾸면 첫번째는 실행
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql)){
            while(rs.next()){
                System.out.println(rs.getString("DNAME"));
            }
        } catch (SQLException e) {e.printStackTrace();}
        System.out.println("///////////////close 이후 한번 더 호출///////////////");
         sql="SELECT * FROM DEPT";
        try(Connection conn=L03DBFactory.getConn(); //getConn 을 conn 으로 바꾸면 두번째는 실행되지 않음
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql)){
            while(rs.next()){
                System.out.println(rs.getString("DNAME"));
            }
        } catch (SQLException e) {e.printStackTrace();}
    }
}
