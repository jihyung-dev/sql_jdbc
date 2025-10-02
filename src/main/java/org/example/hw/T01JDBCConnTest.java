package org.example.hw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class T01JDBCConnTest {
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

                System.out.println(empno+" / "+ename+" / "+sal+" / "+hiredate);
            }
            System.out.println(conn);

        } catch (Exception e) {
            //throw new RuntimeException(e); // 오류를 넘길 필요는 없음
            e.printStackTrace(); // 대신 어떤 오류가 발생했는지 콘솔에 띄워주기
        }

    }
}
