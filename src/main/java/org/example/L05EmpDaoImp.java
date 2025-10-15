package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class L05EmpDaoImp implements L05EmpDao{
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    //생성자 규칙을 통해 항상 conn을 가지고 생성 -> 접속객체 없이는 객체가 될 수 없음 ( conn!=null )
    public L05EmpDaoImp(Connection conn){
        this.conn=conn;

    }
    @Override
    public int insertOne(L05EmpDto emp) throws SQLException {
        return 0;
    }
    @Override
    public int updateOne(L05EmpDto emp) throws SQLException {
        return 0;
    }
    @Override
    public int deleteOne(int empno) throws SQLException {
        return 0;
    }
    @Override
    public List<L05EmpDto> findAll() throws SQLException {
        List<L05EmpDto> empList=null;
        String sql="SELECT * FROM EMP";
        pstmt=conn.prepareStatement(sql);
        rs=pstmt.executeQuery();

        empList=new ArrayList<>();
        while (rs.next()){
            L05EmpDto emp=new L05EmpDto();
            emp.setEmpno(rs.getInt("empno"));
            emp.setEname(rs.getString("ename"));
            emp.setDeptno(rs.getInt("deptno"));

            empList.add(emp);
        }

        return empList;

        //1. 반환할 것을 이름지어서 반환
    }

    // ename 호출해보기 (findbyename)으로,  단일건 조회
    @Override
    public List<L05EmpDto> findByEname(String ename) throws SQLException {
        //#1 findAll 방법과 똑같이 해보기
        List<L05EmpDto> empList = null;
        String sql = "SELECT * FROM EMP WHERE ENAME = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, ename);
        rs = pstmt.executeQuery();

        empList = new ArrayList<>();
        while (rs.next()) {
            L05EmpDto emp = new L05EmpDto();
            emp.setEmpno(rs.getInt("empno"));
            emp.setEname(rs.getString("ename"));
            emp.setJob(rs.getString("job"));
            empList.add(emp);
        }
        return empList;
    }
    @Override
    public L05EmpDto findByEmpno (int empno) throws SQLException {
        L05EmpDto emp = null;
        String sql = "SELECT * FROM EMP WHERE EMPNO = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,empno);
        rs = pstmt.executeQuery();

        while (rs.next()) {
             emp = new L05EmpDto();
            emp.setEmpno(rs.getInt("empno"));
            emp.setEname(rs.getString("ename"));
            emp.setJob(rs.getString("job"));
        }
        return emp;
    }
}