package org.example;

import java.sql.SQLException;
import java.util.List;

public interface L05EmpDao {
/* 사원관리자 페이지를 만들에정인데 어떤 기능이 있을까??
* 사원등록
* 사원수정
* 사원삭제
* 사원삭제
* 사원전체조회
* 사원이름조회
* 사원상세 => 사원번호조회
* */
//  public int insertOne (int empno, String ename ....);
    int insertOne(L05EmpDto emp) throws SQLException;
    int updateOne(L05EmpDto emp) throws SQLException;
    int deleteOne(int empno) throws SQLException;
    //Delete From emp Where empno=? 일시, emp모두가 필요하지않음

    //SELECT * FROM EMP;
    List<L05EmpDto> findAll() throws SQLException;
    List<L05EmpDto> findByEname(String ename)  throws SQLException;

    //'하나'만 반환
    L05EmpDto findByEmpno(int empno)  throws SQLException; //pk, one, empno



    //list로 사용한 이유, LinkedList, 등, Type을 추상적으로 만들어놓으면, 다른것으로 변경하기가 용이함.

}
