package org.example.hw.dao;

import java.sql.*;

public class DBFactory {
    //[1. scott 계정 (스키마)에 접속한 Connection을반환하는 싱글톤 구현!]

    //스키마 연결
    private static String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    private static String user = "scott";
    private static String pw = "tiger";

    //일반적으로 사용되는 방법
    public static Connection conn = null;

    static { //jvm이 static을 메서드 영역에 저장할 때 실행되는 블럭 ,
        // 그러나 connect는 한번하고 끊을수 없으므로 싱글톤으로구현!
        try {
            conn = DriverManager.getConnection(url, user, pw);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection beanConn; //1. private로 바로 접근하지 못하도록 만들기

    public static Connection getConn() throws SQLException { //2. public으로 안전하게 접근할 함수를 정의

        //안전하게 반환하도록 검사
//        if (beanConn != null && !beanConn.isClosed()) {         //beanConn 이 널이 아니거나 닫혀있을경우
//            return beanConn;                                    //beanConn 을 반환
//        } else {                                                //다른경우
//            beanConn = DriverManager.getConnection(url, user, pw); //beanConn은 DriverManger것을 참조
//        }

        //더 간단한 작성
        if (beanConn == null || beanConn.isClosed()) {             //beanConn이 null이거나 닫힌경우
            beanConn = DriverManager.getConnection(url, user, pw); //DriverManager를 참조
        }
        return beanConn;                                            //그 외엔 beanConn을 반환
    }

}


class DBFactoryTest{
    public static void main(String[] args) {
        //[2. 접속 테스트]
        // DNAME을 불러와보기

        String sql="SELECT * FROM DEPT"; //다불러왔지만 sout 에서 호출하는게 DNAME이므로,
        try(Connection conn=DBFactory.getConn(); // () 안에 작성 => autoclose
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql)){
            while(rs.next()){
                System.out.println(rs.getString("DNAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("///////////////////////close 이후 한번 더 호출해보기///////////////////////");

        sql="SELECT * FROM DEPT"; //다불러왔지만 sout 에서 호출하는게 DNAME이므로,
        try(Connection conn=DBFactory.getConn(); // () 안에 작성 => autoclose
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql)){
            while(rs.next()){
                System.out.println(rs.getString("DNAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}