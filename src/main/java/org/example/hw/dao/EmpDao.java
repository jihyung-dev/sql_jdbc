package org.example.hw.dao;

import java.sql.SQLException;
import java.util.List;

public interface EmpDao {  //Dao 는 인터페이스다 요다야 이야이야요

    //[4. 내가 생각하는 부서관리 앱에서 사용될 CRUD 설계]
    //부서 전체 조회
    //상세조회
        //사번으로 사람조회 (단일항)
        //사람이름으로 상세조회 (다항)
                        //부서 번호로 사람 조회 (다항)
                        //JOB으로 사람 조회 (다항)
    //추가 by 사번
    //변경 by 사번
    //삭제 by 사번

    // 추상함수 ( 본문(body)가 없는)

    //전체 조회 //전체이므로 매개변수 필요 X                       //GUI 사용단에서 오류를 알아야 입력값을 바르게 넣어서 사용가능
    List<EmpDto> findAll() throws SQLException;              //이 외에에는 service (롤백, commit 관련해서  항목에서 복습)

    //사번으로 상세 조회
    EmpDto findByEmpno(int empno) throws SQLException;

    //사원이름로 상세 조회
    EmpDto findByEname(String ename) throws SQLException;

    //추가 by 사번
    int insertOne(EmpDto emp) throws SQLException;

    //변경 by 사번
    int updateOne(EmpDto emp) throws SQLException;

    //삭제 by 사번
    int delteOne(int empno) throws SQLException;


}
