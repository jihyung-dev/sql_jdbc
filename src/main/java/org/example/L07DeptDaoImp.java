package org.example;

import oracle.jdbc.proxy.annotation.Pre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class L07DeptDaoImp implements L07DeptDao {
    private final Connection conn; //연결은 바뀌지 않음

    public L07DeptDaoImp(Connection conn) { //final 정의했을때 connection을 받아서 사용
        this.conn = conn;
    }

    // TDD : Test Driven Development : 테스트 주도 개발

    @Override
    //List이므로 리스트객체 만들고 추가까지해줘야함
    public List<L07DeptDto> findAll() throws SQLException {
        List<L07DeptDto> depts = null;
        String sql = "SELECT * FROM DEPT";
        try ( //autoclose 영역
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            depts=new ArrayList<>(); //리스트를 객체로 만들기

            while (rs.next()) {
                int deptno = rs.getInt("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                L07DeptDto deptDto=new L07DeptDto(deptno,dname,loc); //위에서 참조한것을 저장

                depts.add(deptDto);//리스트에 데이터 추가하기
            }
        }
        return depts;
    }

    @Override
    //여기서는 하나의 행만 반환하면 됨
    public L07DeptDto findByDeptno(int deptno) throws SQLException {
        L07DeptDto dept=null;
        String sql="SELECT * FROM DEPT WHERE DEPTNO = ?";
        try(PreparedStatement pstmt=conn.prepareStatement(sql) //이 안에는 close시킬 객체만 널을 수 있음
                                                              ){ //따라서 pstmt.는 들어올 수 없음
            pstmt.setInt(1,deptno);
            try(ResultSet rs=pstmt.executeQuery()){
                if(rs.next()){ //결과가 하나만 나오므로 while 대신 if 사용 가능
                    int deptno2 = rs.getInt("deptno");
                    String dname = rs.getString("dname");
                    String loc = rs.getString("loc");
                    dept=new L07DeptDto(deptno2,dname,loc);
                }
            }
        }
        return dept;
    }

    @Override
    public int insertOne(L07DeptDto deptDto) throws SQLException {
        int insertOne=0;
        String sql="INSERT INTO DEPT (DEPTNO,DNAME,LOC) VALUES (?,?,?)";
        try(PreparedStatement pstmt=conn.prepareStatement(sql)){
            pstmt.setInt(1,deptDto.getDeptno());
            pstmt.setString(2,deptDto.getDname());
            pstmt.setString(3,deptDto.getLoc());
            insertOne=pstmt.executeUpdate(); // DML => 몇개 성공 => long or int 반환 (20억개)
        }
        return insertOne;
    }

    @Override
    public int updateOne(L07DeptDto deptDto) throws SQLException {
        int updateOne=0;
        String sql="UPDATE DEPT SET DNAME=?, LOC=? WHERE DEPTNO=?";
        try (PreparedStatement pstmt=conn.prepareStatement(sql)){
            pstmt.setString(1,deptDto.getDname());
            pstmt.setString(2,deptDto.getLoc());
            pstmt.setInt(3,deptDto.getDeptno());
            updateOne=pstmt.executeUpdate();
        }
        return updateOne;
    }

    @Override
    public int deleteOne(int deptno) throws SQLException {
        int deleteOne=0;
        String sql="DELETE FROM DEPT WHERE DEPTNO=?";
        try (PreparedStatement pstmt=conn.prepareStatement(sql)){
            pstmt.setInt(1,deptno);
            deleteOne=pstmt.executeUpdate();
        }
        return deleteOne;
    }
}