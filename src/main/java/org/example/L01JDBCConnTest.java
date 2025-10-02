package org.example;
import oracle.jdbc.OracleDriver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

public class L01JDBCConnTest {
    public static void main(String[] args) {
        String url="jdbc:oracle:thin:@//localhost:3000/XEPDB1";  // 연결할 SQL 주소(url)을 작성
        String user="scott";                                     // 해당 SQL의 유저 이름 작성
        String pw="tiger";                                       // 해당 유저의 비밀번호 작성
        String sql="SELECT * FROM EMP";                          // 내가 궁금한 query를 작성

        try {                                                   // connection(통신) 이므로, 오류가 발생할 수 있어서 try - catch 구문 작성
            //Class.forName("oracle.jdbc.OracleDriver");                    // 왜 주석처리??  => 버전이 올라가면서 안해도되는데 , 종종해야할 경우 있음
            Connection conn = DriverManager.getConnection(url,user,pw);     // 드라이버매니저의 연결로부터 url,user,pw를 받아온것을 conn에 참조
            Statement stmt = conn.createStatement();                        // conn의 진술을 stmt에 참조
            ResultSet rs=stmt.executeQuery(sql);                            // stmt의 (sql)쿼리 실행을 rs에 참조
            while (rs.next()){                                              // 반복문 ( rs.next() 다음게 있는지 물어보는 함수) //while : 반복횟수를 모르는 반복문
                int empno=rs.getInt("empno");                     // DriverManager로부터 empno열의 Int를 받아서 empno에 참조
                double sal=rs.getDouble("sal");                   //                    sal열의 Double을 받아서 sal에 참조
                String hiredate=rs.getString("hiredate");         //                    hiredate열의 String 받아서, hiredate에 참조
                String ename=rs.getString("ename");               //                    ename열의 String을 받아서 ename에 참조

                System.out.println(empno+" / " +ename+" / "+sal+" / " +hiredate);  //       while 문에서 저장한 것을 출력
            }
            System.out.println("구분");
            System.out.println(conn);       // conn 이 저장된 주소를 출력
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
