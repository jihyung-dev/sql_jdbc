package org.example.hw.dao;

import oracle.jdbc.proxy.annotation.Pre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//[5. DeptDaoImp 구현]
public class DeptDaoImp implements DeptDao{

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    //생성자규칙을통해 항상 conn을 가지고 생성하도록 만들기 (연결상태 지속)
    public DeptDaoImp(Connection conn){
        this.conn=conn;
    }

    //새로운부서 추가하기
    @Override
    public int insertOne(DeptDto deptDto) throws SQLException {
        int insertOne=0;
        String sql="INSERT INTO DEPT (DEPTNO,DNAME,LOC) VALUES (?,?,?)";
        try(PreparedStatement pstmt=conn.prepareStatement(sql)){
            pstmt.setInt(1,deptDto.getDeptno());
            pstmt.setString(2,deptDto.getDname());
            pstmt.setString(3,deptDto.getLoc());
            insertOne=pstmt.executeUpdate(); //DML (Insert, Update, Delete) => long or int로 반환
        }
        return insertOne;
    }

    //수정하기
    @Override
    public int updateOne(DeptDto deptDto) throws SQLException {
        int updateOne=0;
        String sql="UPDATE DEPT SET DNAME=?, LOC=? WHERE DEPTNO=?"; //SQL문 _ UPDATE + SET
        try (PreparedStatement pstmt=conn.prepareStatement(sql)){   // ? 썼으니까 PreparedStatement 쓰기
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
        try(PreparedStatement pstmt=conn.prepareStatement(sql)){
            pstmt.setInt(1,deptno);
            deleteOne=pstmt.executeUpdate();
        }
        return deleteOne;
    }

    //전체조회하기
    @Override
    public List<DeptDto> findAll() throws SQLException {
        List<DeptDto> deptList=null;
        String sql="SELECT * FROM DEPT";
        pstmt=conn.prepareStatement(sql);
        rs=pstmt.executeQuery();

        deptList=new ArrayList<>();
        while(rs.next()){
            DeptDto dept=new DeptDto();
            dept.setDeptno(rs.getInt("deptno"));
            dept.setDname(rs.getString("dname"));
            dept.setLoc(rs.getString("loc"));

            deptList.add(dept);
        }
        return deptList;
    }

    //부서이름으로찾기
    @Override
    public DeptDto findByDname(String dname) throws SQLException {
        DeptDto dept=null;                                             //dept 초기화
        String sql="SELECT * FROM DEPT WHERE DNAME = ?";               //SQL 문 작성
        try (PreparedStatement pstmt = conn.prepareStatement(sql)){    //autoclose객체 넣기
             pstmt.setString(1,dname);                     //?에 채울 내용
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    int deptno = rs.getInt("deptno");
                    String dname2 = rs.getString("dname");
                    String loc = rs.getString("loc");
                    dept=new DeptDto(deptno,dname2,loc);                //생성자가 있어야 매개변수를 넣지요!
                }
            }
        }
        return dept;
    }

    //부서위치로찾기
    @Override
    public DeptDto findByLocation(String loc) throws SQLException {
        return null;
    }

    @Override
    public DeptDto findByDeptno(int deptno) throws SQLException {
        DeptDto dept=null;
        String sql="SELECT * FROM DEPT WHERE DEPTNO =?";
        try( PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1,deptno);
            try(ResultSet rs=ps.executeQuery()){
                if (rs.next()){
                    int deptno2=rs.getInt("deptno");
                    String dname=rs.getString("dname");
                    String loc=rs.getString("loc");
                    dept = new DeptDto(deptno2,dname,loc);
                }
            }
        }
        return dept;
    }
}

class DeptDaoImpTest{
    public static void main(String[] args) throws SQLException{
        //[6. V 등록 -> V 전체조회 -> V 수정 -> V 부분조회 -> 삭제 V //테스트           + @ 카드]

        DeptDao deptDao=new DeptDaoImp(DBFactory.getConn());

        //부서삭제
        int delete=deptDao.deleteOne(50);
        String delMsg=(delete>0)?"삭제성공":"삭제실패";
        System.out.println(delMsg);

        //부서 등록
        DeptDto inserDept=new DeptDto(50,"멍충","갱냄");
        int insert=deptDao.insertOne(inserDept);
        String msg=(insert>0)?"등록성공":"등록실패";
        System.out.println(msg);

        //전체조회
        List<DeptDto> depts=deptDao.findAll();
        System.out.println(depts);


        //부서수정
        DeptDto updateDept=new DeptDto(50,"똑똑","강북");
        int update=deptDao.updateOne(updateDept);
        msg=(update>0)?"수정성공":"수정실패";
        System.out.println(msg);

        //부서이름 조회
        DeptDto dept = deptDao.findByDname("똑똑");
        System.out.println(dept);

        //부서삭제
        delete=deptDao.deleteOne(50);
        delMsg=(delete>0)?"삭제성공":"삭제실패";
        System.out.println(delMsg);

    }
}


