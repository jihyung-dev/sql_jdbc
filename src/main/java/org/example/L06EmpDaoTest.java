package org.example;

import java.sql.SQLException;
import java.time.LocalDate;

public class L06EmpDaoTest {
    public static void main(String[] args) throws SQLException {


        //연결
        L05EmpDao empDao=new L05EmpDaoImp(L03DBFactory.getConn());

        //전체조회
        System.out.println("***전체조회***");
        System.out.println(empDao.findAll());

        //상세조회(이름)
        //System.out.println(empDao.findByEname("SMITH"));
        System.out.println("***이름검색***");
        System.out.println(empDao.findByEname("king"));

        //삭제by사번
        System.out.println("***9999사원 삭제***");
        int del=empDao.deleteOne(9999);
        if(del==1) {System.out.println("***삭제성공***");
        } else {
            System.out.println("***삭제할 레코드가 없음***"); //레코드, 개체, 객체, 튜플, 로우, 등 부르는 말이 많음
        }

        //추가by사번
        L05EmpDto emp=new L05EmpDto();
        emp.setEmpno(9999);
        emp.setEname("최경민"); //10
        emp.setJob("DEVELOPER");
        emp.setDeptno(10); //없는 14부서를 참조 => 오류 (참조으 ㅣ무결성)
        emp.setMgr(null); //없는 상사를 참조 =>참조 무결성 (FK 지정을 안해서 오류 안뜸)
        emp.setSal(1000.11);
        emp.setComm(100.22);
        emp.setHiredate(LocalDate.of(2000,10,10));
        System.out.println("9999사원등록:"+empDao.insertOne(emp));

        //상세조회
        System.out.println("***9999 사번검색***");
        System.out.println(empDao.findByEmpno(9999));

        //수정by사번
        emp.setEname("최경만"); //10
        emp.setJob("BACKEND");
        emp.setDeptno(20); //없는 14부서를 참조 => 오류 (참조으 ㅣ무결성)
        emp.setMgr(7843); //없는 상사를 참조 =>참조 무결성 (FK 지정을 안해서 오류 안뜸)
        emp.setSal(1234.11);
        emp.setComm(123.22);
        emp.setHiredate(LocalDate.of(2022,2,22));

        System.out.println("수정하기위한 dto : "+emp);

        int update=empDao.updateOne(emp);
        if(update>0){
            System.out.println("***9999 사원수정 성공***");
        } else {
            System.out.println("***9999 사원수정 실패***");
        }
            System.out.println(empDao.findByEmpno(9999));;

        //삭제 (등록한것을 삭제, 다시 등록하기 위해)
        del=empDao.deleteOne(9999);
        if(del==1) {System.out.println("***삭제성공***");
        } else {
            System.out.println("***삭제할 레코드가 없음***"); //레코드, 개체, 객체, 튜플, 로우, 등 부르는 말이 많음
        }



        //findByLikeEname - 만들어보기
    }
}
