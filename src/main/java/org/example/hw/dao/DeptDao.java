package org.example.hw.dao;

import java.sql.SQLException;
import java.util.List;

public interface DeptDao {
    //[4. 내가 생각하는 부서관리 앱에서 사용될 CRUD 설계]
        //부서 위치 조회
        //부서 이름 조회
        //부서 이름 수정
        //부서 추가
        //부서 삭제

    //부서 추가
    int insertOne(DeptDto deptDto) throws SQLException;
    //부서 이름 수정
    int updateOne(DeptDto deptDto) throws SQLException;
    //부서 삭제
    int deleteOne(int deptno) throws SQLException;
    //부서 전체 조회
    List<DeptDto> findAll() throws SQLException;
    //부서 이름 조회
    DeptDto findByDname(String dname) throws SQLException;
    //부서 위치 조회
    DeptDto findByLocation(String loc) throws SQLException;






}
