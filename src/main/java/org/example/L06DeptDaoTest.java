package org.example;

import java.sql.SQLException;

public class L06DeptDaoTest {
    public static void main(String[] args) throws SQLException {
        L05DeptDao deptDao=new L05DeptDaoImp(L03DBFactory.getConn());
        //전체를 조회
        System.out.println(deptDao.findAll());
        //부서 이름이 SALES인 것을 조회
        System.out.println(deptDao.findByDname("SALES"));
        //부서번호가 20인 것을 조회
        System.out.println(deptDao.findByDeptno(20));


    }
}
