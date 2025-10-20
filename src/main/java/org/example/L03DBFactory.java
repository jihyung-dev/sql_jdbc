package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class L03DBFactory {
    public int b = 200;
    int a = 100;
    protected int c;
    private int d;
    static private int e = 2000; // [클래스변수] 클래스이름을 가져다쓰지만, 클래스와는 별개로 독립적으로 존재함, 이 자체로 데이터로 존재
    //class 전역(가장 상위 scope)에 선언된 변수 => 필드
    //필드는 데이터로 존재하지 않음, 설계도일 뿐!

    //static : 클래스 변수
    //접근지정자  public(모두) default(같은패키지) protected(상속) private(class)

    private static String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    private static String user = "scott";
    private static String pw = "tiger";

    //public static Connection conn = DriverManager.getConnection(url,user,pw);

    //일반적으로 사용되는 1개의 객체를 자원으로 사용하는 방법 : 문제를 컨트롤하기 힘들다!
    public static Connection conn = null;
    static {
        try {
            conn = DriverManager.getConnection(url,user,pw);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }     // jvm이 static을 메서드 영역에 저장할 때 실행되는 블럭 (초기실행)

    //싱글톤 디자인 패턴으로 1개의 자원을 안전하게 공유하는 방법! //getConn으로 활용
    private static Connection beanConn; //1. private로 바로 접근하지 못하게 하기
    public static Connection getConn() throws SQLException{ //2. public으로 안전하게 접근할 함수를 정의

        //3. 안전하게 반환하도록 검사를 진행 // 일반적인 방법
//        if(beanConn!=null && !beanConn.isClosed()){
//            return beanConn;
//        }else {
//            beanConn=DriverManager.getConnection(url,user,pw)
//        }
        //3. 안전하게 반환하도록 검사를 진행
        if(beanConn==null || beanConn.isClosed()){
            beanConn=DriverManager.getConnection(url,user,pw);
        }
        return beanConn;
    }



    // 테스트
    public static void main(String[] args) throws SQLException{
//        System.out.println(a);
        conn.close();
        System.out.println(conn); //위험한 상황
        System.out.println(conn.isClosed()); //끊어져 있음 (에도 불구하고 conn이 살아있음)

        getConn().close();
        System.out.println(getConn()); //close 되어 있다면 매번 새로 만들기 때문에 안전
        System.out.println(getConn().isClosed()); //끊어져있지 않음 (매번 새로 연결하기에 연결 중)

        System.out.println(e);
        L03DBFactory dbFactory = new L03DBFactory();
        System.out.println(dbFactory.a); //필드가 데이터로 존재하려면 꼭 객체가 되어야함 [객체지향문법]

    }

}
