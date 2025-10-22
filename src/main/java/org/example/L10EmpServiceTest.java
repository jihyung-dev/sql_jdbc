package org.example;

import java.sql.Connection;
import java.sql.SQLException;

public class L10EmpServiceTest {
    public static void main(String[] args) {

        try(Connection conn=L03DBFactory.getConn();) {
            L09EmpService empService=new L09EmpServiceImp(conn);

            System.out.println("readAll 테스트");
            System.out.println(empService.readAll());

            System.out.println("readByEname 테스트");
            System.out.println(empService.readByEname("scott"));

            System.out.println("9000삭제 "+empService.remove(9000));

            L05EmpDto emp=new L05EmpDto();
            emp.setEmpno(6666);
            emp.setEname("테스트");
            emp.setSal(500.0);
            emp.setComm(99.0);
            emp.setMgr(7788);
            emp.setDeptno(40); //참조의무결성 위반!!!
            System.out.println(emp);
            System.out.println("등록 성공:"+empService.register(emp));
            System.out.println("readOne 테스트 6666");
            System.out.println(empService.readOne(6666));

            emp.setSal(1000.0);
            emp.setMgr(1111);
            System.out.println("수정 성공:"+empService.modify(emp));
            System.out.println(empService.readOne(6666));

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
