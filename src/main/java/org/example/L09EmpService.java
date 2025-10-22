package org.example;

import java.sql.SQLException;
import java.util.List;

public interface L09EmpService {                    //interface구현후 class로 구현
    //어떤기능을 구현할까?              //반환
    // 사원등록 register(EmpDto emp)  boolean or EmpDto    //insert와 구분!
    // 사원수정 modify(EmpDto emp)    boolean or EmpDto
    // 사원삭제 remove(empno)         boolean
    // 사원전체조회 readAll()          List<EmpDto>
    // 사원이름조회 readByEname()
    // 상세조회    readOne()

    //서비스가 간단할수록 dao와 service가 거의 유사
    //작은기업 -> service를 구현하지 않는 경우도 존재!!
                                                      //사원이름이 없거나, 길이가 넘거나, 급여가 잘못등록
    //DML                                             //IllegalArgumentException: 입력오류
    boolean register(L05EmpDto emp) throws SQLException,IllegalArgumentException;
    boolean modify(L05EmpDto emp) throws SQLException,IllegalArgumentException;
    boolean remove(int empno) throws SQLException;

                                                                //다형성 -> 오버로드, 오버라이드, 타입의 다형성
    //Bean 용                                                   //오버로드 (이름이 같은데 매개변수가 다른것) ->
    boolean register(L11EmpValidBean emp) throws SQLException, IllegalArgumentException;
    boolean modify(L11EmpValidBean emp) throws SQLException, IllegalArgumentException;



    //DQL
    List<L05EmpDto> readAll() throws SQLException;
    List<L05EmpDto> readByEname(String ename) throws SQLException;
    L05EmpDto readOne(int empno) throws SQLException;





}
