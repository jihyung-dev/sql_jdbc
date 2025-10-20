package org.example.hw.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EmpServiceImp implements EmpService{
//private EmpDaoImp empDaoimp  //의존역전 원칙에 의해 Imp가 아닌 EmpDao로 구현
    private EmpDao empDao;
    private DeptDao deptDao;
    private Connection conn; // 사용자가 생성해서 전달

    public EmpServiceImp (Connection conn) {
        this.conn=conn;
        this.empDao=new EmpDaoImp(conn);
        this.deptDao=new DeptDaoImp(conn);
    }

    //트랜잭션 : 데이터 수정이 2번이상 일어날 때, 문제가 생기면 save point로 되돌리는 것
    // 현재 : register : 2번이상 수정(DML : insert update delete)하지 않기 때문에 필요 없음!
    // but 수업이니 작성

    @Override
    public boolean register(EmpDto emp) throws SQLException {





        return false;
    }

    @Override
    public boolean modify(EmpDto emp) throws SQLException {
        return false;
    }

    @Override
    public boolean remove(int empno) throws SQLException {
        return false;
    }

    @Override
    public List<EmpDto> readAll() throws SQLException {
        return List.of();
    }

    @Override
    public List<EmpDto> readByEname(String ename) throws SQLException {
        return List.of();
    }

    @Override
    public EmpDto readOne(int empno) throws SQLException {
        return null;
    }
}
