package org.example.hw.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class EmpServiceTest {
    static void main() {

        //연결
        try( Connection conn=DBFactory.getConn()){
            EmpService empService=new EmpServiceImp(conn);


            //상세조회(이름)
            System.out.println("readByEname 테스트");
            System.out.println(empService.readByEname("JONES"));

            //삭제by사번
            System.out.println("3333 삭제"+empService.remove(3333));
            System.out.println("7777사원 삭제"+empService.remove(7777));
//            System.out.println("7879사원 삭제"+empService.remove(7879));

            //추가by사번
            EmpDto emp=new EmpDto();
            emp.setEmpno(3333);
            emp.setEname("테스트");
            emp.setSal(500.0);
            emp.setComm(99.0);
            emp.setMgr(7788);
            emp.setDeptno(30);
            System.out.println("register 테스트");
            System.out.println(emp);
            System.out.println("등록 성공:"+empService.register(emp));
            System.out.println("readOne 테스트 3333");
            System.out.println(empService.readOne(3333));


            emp.setSal(10000.0);
            emp.setMgr(1111);
            System.out.println("수정 성공:"+empService.modify(emp));
            System.out.println(empService.readOne(3333));

            //전체조회
            System.out.println("readAll 테스트");
            System.out.println(empService.readAll());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
