package org.example;

import java.sql.SQLException;
import java.util.List;

public interface L05DeptDao {
/* 사원관리자 페이지를 만들 예정인데 어떤 기능이 있을까?
* 사원등록
* 사원수정
* 사원삭제
* 사원전체조회
* 사원이름조회
* 사원상세 => 사원번호조회
* */

/*부서 관리 페이지
*
* */

//  public int insertOne (int dept, String dname ....);
    public int insertOne (L05DeptDto dept) throws SQLException;
    public int updateOne (L05DeptDto dname) throws SQLException;
    public int deleteOne (L05DeptDto loc) throws SQLException;
    //Delete From dept where deptno=? 일시엔, dept 모두가 필요하지 않음

    //SELECT * FROM DEPT;
    public List<L05DeptDto> findAll() throws SQLException;
    public List<L05DeptDto> findByDname(String dname) throws SQLException;
    public L05DeptDto findByDeptno(int deptno) throws SQLException;

    //List를 사용하는 이유, 추후에 다른 형변환을 변하게 하기위해

    //UK, PK가 아니면, 중복가능성이 있다. -> list로 한다.

}
