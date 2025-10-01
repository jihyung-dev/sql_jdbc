package org.example;
import oracle.jdbc.OracleDriver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

public class L01JDBCConnTest {
    public static void main(String[] args) {
        String url="jdbc:oracle:thin:@//localhost:3000/XEPDB1";
        String user="scott";
        String pw="tiger";
        String sql="SELECT * FROM EMP";
        try {
            //Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = DriverManager.getConnection(url,user,pw);
            Statement stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while (rs.next()){
                int empno=rs.getInt("empno");
                double sal=rs.getDouble("sal");
                String hiredate=rs.getString("hiredate");
                String ename=rs.getString("ename");

                System.out.println(empno+" / " +ename+" / "+sal+" / " +hiredate);
            }

            System.out.println(conn);


        } catch (Exception e){
            e.printStackTrace();
        }


    }
}
