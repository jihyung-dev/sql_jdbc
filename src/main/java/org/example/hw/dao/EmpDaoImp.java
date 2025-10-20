package org.example.hw.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


    //[5. DeptDaoImp 구현]
    // 1.implements EmpDao 작성
    // 2.메서드 구현
    // 3.사용할 필드 구성 : conn, pstmt, rs
    // 생성자 규칙을 사용하여 항상 conn을 가지고 생성하도록 하기

public class EmpDaoImp implements EmpDao{

    //필드 작성
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public EmpDaoImp(Connection conn){this.conn=conn;}

    @Override
    public List<EmpDto> findAll() throws SQLException {
        List<EmpDto> emps = null;                       //빈 담을 것 생성
        String sql = "SELECT * FROM EMP";               //SQL 구문 작성
        try (Statement stmt = conn.createStatement();   //연결 구성 및 진술 구성(연결이므로 try - catch 구문으로 구성)
             ResultSet rs = stmt.executeQuery(sql)) {   //SQL진술을 통해 결과값 구성

            emps = new ArrayList<>();                   //전체테이블은 여러행 결과값이므로 배열로 작성

            while (rs.next()) {                         //반복문을 통해 구성
                int empno = rs.getInt("empno");
                String ename = rs.getString("ename");
                String job = rs.getString("job");
                int mgr = rs.getInt("mgr");
                Date hiredate = rs.getDate("hiredate");
                double sal =rs.getDouble("sal");
                double comm = rs.getDouble("comm");
                int deptno = rs.getInt("deptno");

                EmpDto empDto = new EmpDto(empno,ename,job,mgr,hiredate,sal,comm,deptno); //가져온 데이터들을 empDto에 저장

                emps.add(empDto);                                                  //저장한 empDto를 emps배열에추가
            }
            return emps;                                                           //emps 값 반환
        }
    }
    @Override
    public EmpDto findByEmpno(int empno) throws SQLException {
        EmpDto emp=null;
        String sql="SELECT * FROM EMP WHERE EMPNO = ?";

        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, empno); //이 문장은 close 객체가 아니므로 ()안에 작성 불가,
            try (ResultSet rs = pstmt.executeQuery()) { //rs 구문도 autoclose하기위해  try() 구문에 넣기
                while(rs.next()){
                    int empno2=rs.getInt("empno");
                    String ename=rs.getString("ename");
                    String job=rs.getString("job");
                    int mgr=rs.getInt("mgr");
                    Date hiredate=rs.getDate("hiredate");
                    double sal=rs.getDouble("sal");
                    double comm=rs.getDouble("comm");
                    int deptno=rs.getInt("deptno");

                     emp = new EmpDto(empno2,ename,job,mgr,hiredate,sal,comm,deptno);
                }
            }
        }
        return emp;
    }
    @Override
    public EmpDto findByEname(String ename) throws SQLException {
        EmpDto emp=null;
        String sql = "SELECT * FROM EMP WHERE ENAME = ?";
        try(PreparedStatement pstmt=conn.prepareStatement(sql)){
            pstmt.setString(1,ename);
            try(ResultSet rs=pstmt.executeQuery()){
                while(rs.next()){
                    int empno=rs.getInt("empno");
                    String ename2=rs.getString("ename");
                    String job=rs.getString("job");
                    int mgr=rs.getInt("mgr");
                    Date hiredate=rs.getDate("hiredate");
                    double sal=rs.getDouble("sal");
                    double comm=rs.getDouble("comm");
                    int deptno=rs.getInt("deptno");

                     emp=new EmpDto(empno,ename2,job,mgr,hiredate,sal,comm,deptno);
                }
            }
        }
        return emp;
    }

    //등록하기
    @Override
    public int insertOne(EmpDto emp) throws SQLException {
        int insertOne=0;
        String sql = "INSERT INTO (empno,ename,job,mgr,hiredate,sal,comm,deptno) " +
                "VALUE(?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1,emp.getEmpno());
            ps.setString(2, emp.getEname());
            ps.setString(3, emp.getJob());
            ps.setObject(4,emp.getMgr()); // null이 있으므로 Object로 받기

            java.sql.Date hiredate=null;
            if(emp.getHiredate()!=null)//emp의 고용일이 null이 아닐때 실행
                hiredate=java.sql.Date.valueOf(emp.getHiredate());
            ps.setDate(5,hiredate);
            ps.setObject(6,emp.getSal());
            ps.setObject(7,emp.getComm());
            ps.setObject(8,emp.getDeptno());
            insertOne=ps.executeUpdate();
        }
        return insertOne ;
    }
    @Override
    public int updateOne(EmpDto emp) throws SQLException {
        return 0;
    }
    @Override
    public int delteOne(int empno) throws SQLException {
        return 0;
    }
}

//테스트!
//[6. V 등록 -> V 전체조회 -> V 수정 -> V 부분조회 -> 삭제 V //테스트           + @ 카드]

class EmpDaoImpTest{
    static void main() throws SQLException {

        EmpDao empDao = new EmpDaoImp(DBFactory.getConn());

        //전체조회
        List<EmpDto> emps=empDao.findAll();
        System.out.println(emps);

        //부분조회 (empNo로 조회)
        EmpDto empNo=empDao.findByEmpno(7839);
        System.out.println("empNo으로조회: "+empNo);

        //부분조회 (empName으로 조회)
        EmpDto empName=empDao.findByEname("BLAKE");
        System.out.println("empName으로조회: "+empName);

        //등록하기


    }
}


