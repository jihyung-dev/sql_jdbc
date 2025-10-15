package org.example;


import java.sql.SQLException;
import java.util.List;

public interface L07DeptDao {
/*
부서전체조회, SELECT * FROM DEPT;                                1 findAll
부서상세조회, SELECT * FROM DEPT WHERE DEPTNO=?;                 2 findByDeptno
부서등록 INSERT INTO ( dname, loc) VALUES (?,?,?);              3 Insert
부서수정 UPDATE DEPT SET dname=?, loc=? WHEREE deptno=?         4 Update (Modify)
        //부서이름수정 UPDATE DEPT SET dname=? WHEREE deptno=?
        //부서위치수정 UPDATE DEPT SET loc=? WHEREE deptno=?
부서삭제 DELETE FROM DEPT WHERE deptno=?                        5 Delete (Remove)

    INSERT UPDATE DELETE => DML => 성공한 결과가 몇개성공했어인 숫자로 나옴
*/
    List<L07DeptDto> findAll() throws SQLException;
    L07DeptDto findByDeptno(int deptno) throws SQLException;
    int insertOne(L07DeptDto deptDto) throws SQLException;
    int updateOne(L07DeptDto deptDto) throws SQLException;
    int deleteOne(int deptno) throws SQLException;

}

@FunctionalInterface //람다식으로 익명클래스를 대체
interface B{ //모든 함수는 자동으로 public(오픈된 기능), abstract(추상화)
    //가장 추상화된 설계도

    //public static final int a=10;  클래스변수만 정의가능
    int b=20; //자동으로 클래스상수가 됨, 앞에 자동으로 public static이 붙음
//    public void a(){}; //불가
    void e(); //abstract public void e();

}

abstract class Aable implements B{
    int t=20;
    public void d(){};
    //추상화 => 기능(함수)을 추상화 => 재사용 빈도를 높인다.
    abstract public void c(); //{}바디를 생략 => 추상화

}
class A extends Aable{
    int a=10;           //데이터
    public void b(){}   //기능

    @Override
    public void c() {
    }

    @Override
    public void e() {
    }
} //객체가 될 수 있는 설계도

//class 1 implement B{ e(){} } => 익명클래스

class InstanceTest{
    public static void main(String[] args) {
        //Type : class, abstract class, interface
        //객체지향 문법의 추상화!! => 타입을 재사용(**)

        A a = new A();
//        Aable aable=new Aable(); //추상함수를 포함하는 미완성 설계도는 객체가 될 수 없음
        //        Aable aable=new Aable() {  //익명클래스 생략
        //            @Override
        //            public void c() {
        //
        //            }
        //        };

        B b = new B(){
            @Override
            public void e() {

            }
        };
        B b2 = ()->{}; //==e(){} 람다식으로 e() 와 같은 것

    }
}