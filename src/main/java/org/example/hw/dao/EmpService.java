package org.example.hw.dao;

import java.sql.SQLException;
import java.util.List;

public interface EmpService { //interface 구성 후 class로 구현
    //어떤기능을 구현할지
    //  사원등록 register(EmpDto emp) boolean or EmpDto
    //  사원수정 modify(EmpDto emp)   boolean or EmpDto
    //  사원삭제 remove(empno)        boolean
    //  사원전체조회 readAll           List<EmpDto>
    //  사원이름조회 readByEname()
    //  상세조회    readOne()

    // 서비스가 간단할수록 dao와 service가 거의 유사
    // 작은기업 -> service를 구현하지 않는 경우도 존재

                    //조작
    //DML(Data Manipulation Language) -> register, modify, remove
    boolean register(EmpDto emp) throws SQLException,IllegalArgumentException;
    boolean modify(EmpDto emp) throws SQLException,IllegalArgumentException;
    boolean remove(int empno) throws SQLException;

    //DQL(Data Quary Language) : SELECT ~~
    List<EmpDto> readAll() throws SQLException;
    List<EmpDto> readByEname(String ename) throws SQLException;
    EmpDto readOne(int empno) throws SQLException;












}
