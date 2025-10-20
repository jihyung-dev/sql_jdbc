package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class L05_DeptDaoImp implements L05_DeptDao {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public L05_DeptDaoImp(Connection conn){
        this.conn=conn;

    }


    @Override
    public int insertOne(L05_DeptDto dept) throws SQLException {
        return 0;
    }

    @Override
    public int updateOne(L05_DeptDto dname) throws SQLException {
        return 0;
    }

    @Override
    public int deleteOne(L05_DeptDto loc) throws SQLException {
        return 0;
    }

    @Override
    public List<L05_DeptDto> findAll() throws SQLException {
        List<L05_DeptDto> deptList=null;
        String sql="SELECT * FROM DEPT";
        pstmt=conn.prepareStatement(sql);
        rs=pstmt.executeQuery();

        deptList=new ArrayList<>();
        while (rs.next()){
            L05_DeptDto dept=new L05_DeptDto();
            dept.setDeptno(rs.getInt("deptno"));
            dept.setDname(rs.getString("dname"));
            dept.setLoc(rs.getString("loc"));
            deptList.add(dept);
        }
        return deptList;
    }

    @Override
    public List<L05_DeptDto> findByDname(String dname) throws SQLException {
        List<L05_DeptDto> deptList=null;
        String sql="SELECT * From DEPT WHERE DNAME = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,dname);
        rs=pstmt.executeQuery();

        deptList = new ArrayList<>();
        while (rs.next()){
            L05_DeptDto dept = new L05_DeptDto();
            dept.setDeptno(rs.getInt("deptno"));
            dept.setDname(rs.getString("dname"));
            dept.setLoc(rs.getString("loc"));
            deptList.add(dept);
        }
        return deptList;
    }

    @Override
    public L05_DeptDto findByDeptno(int deptno) throws SQLException {
        L05_DeptDto dept=null;
        String sql="SELECT * FROM DEPT WHERE DEPTNO = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,deptno);
        rs=pstmt.executeQuery();

        while (rs.next()){
            dept = new L05_DeptDto();
            dept.setDeptno(rs.getInt("deptno"));
            dept.setDname(rs.getString("dname"));
            dept.setLoc(rs.getString("loc"));
        }
        return dept;
    }
}
