package org.example;

import org.example.ex.EmpAppCards;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class L05EmpDaoImp implements L05EmpDao{
    private Connection conn;

    //생성자 규칙을 통해 항상 conn을 가지고 생성 -> 접속객체 없이는 객체가 될 수 없음 ( conn!=null )
    public L05EmpDaoImp(Connection conn){
        this.conn=conn;

    }
    @Override
    public int insertOne(L05EmpDto emp) throws SQLException {
        /*
        int insertOne=0;
        String sql="INSERT INTO EMP (EMPNO,ENAME,DEPTNO,SAL,HIREDATE,COMM) VALUES (?,?,?,?,?,?)";
        try(PreparedStatement pstmt=conn.prepareStatement(sql)){
            pstmt.setInt(1, emp.getEmpno());
            pstmt.setString(2,emp.getEname());
            pstmt.setObject(3,emp.getDeptno());
            pstmt.setObject(4,emp.getSal());

            //고용일 가져오기
            java.sql.Date hiredate = Date.valueOf(emp.getHiredate());
            pstmt.setDate(5,hiredate);//Hiredate

            //커미션 가져오기
            pstmt.setObject(6,emp.getComm());

            insertOne=pstmt.executeUpdate();
        }
        */
        int insertOne=0;             //순서를 기억한다면 생략해도 괜찮!
        String sql="INSERT INTO EMP (EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO) " +
                " VALUES (?,?,?,?,?,?,?,?)";
        try(PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1,emp.getEmpno());
            ps.setString(2,emp.getEname());
            ps.setString(3,emp.getJob());
            //ps.setInt(4,null); 오류가됨 => 따라서 setObject로 설정
            ps.setObject(4,emp.getMgr());
//            ps.setDate(5,emp.getHiredate());
            //아까는 java.sql.Date -> LocalDate로 변환했지만
            //이번에는 LocalDate -> java.sql.Date로 변환 : "2025-01-05" => oracle Date로 바로 변환!
            //but 반드시 이렇게 할 필요없음, 대부분의 DB는 String을 Date로 잘 변환시킴
//            ps.setString(5,emp.getHiredate().toString());
            java.sql.Date hiredate=null;
            //emp의 Hiredate가 null이 아닐때에, 얻은정보의 value는 sql의 date
            if(emp.getHiredate()!=null)hiredate=java.sql.Date.valueOf(emp.getHiredate());
            ps.setDate(5,hiredate);
            //ps.setString(5,emp.getHiredate().toString());
            ps.setObject(6,emp.getSal());
            ps.setObject(7,emp.getComm());
            ps.setObject(8,emp.getDeptno());
            insertOne=ps.executeUpdate();
        }
        return insertOne;
    }
    //updateSal : "UPDATE EMP SET SAL=? WEHRE EMPNO=?"
    @Override
    public int updateOne(L05EmpDto emp) throws SQLException {
        int updateOne=0;            //empno는 고쳐쓰는것 아님 (PK)
        String sql="UPDATE EMP SET ENAME=?, JOB=?, MGR=?, HIREDATE=?, SAL=?, COMM=?, DEPTNO=? WHERE EMPNO=?";
        try(PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1, emp.getEname());
            ps.setString(2, emp.getJob());
            ps.setObject(3,emp.getMgr());
            ps.setString(4,(emp.getHiredate()!=null)? emp.getHiredate().toString():null); //String으로 넘기기
            ps.setObject(5,emp.getSal());
            ps.setObject(6,emp.getComm());
            ps.setObject(7,emp.getDeptno());
            ps.setInt(8,emp.getEmpno());

            updateOne=ps.executeUpdate();
        }
        return updateOne;
    }

    //삭제
    @Override
    public int deleteOne(int empno) throws SQLException {
        int deleteOne=0;
        String sql="DELETE FROM EMP WHERE empno=?";
        try (PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1,empno);
            deleteOne=ps.executeUpdate();
        }
        return deleteOne;
    }

    //전체 조회
    @Override
    public List<L05EmpDto> findAll() throws SQLException {
        List<L05EmpDto> empList=null;
        String sql="SELECT * FROM EMP";
        try (PreparedStatement pstmt=conn.prepareStatement(sql)){
            try (ResultSet rs=pstmt.executeQuery()){
                empList=new ArrayList<>();
                while (rs.next()){
                    //L05EmpDto emp=new L05EmpDto();
            /* 기존의 사용법 -> 새로운 방법으로
            emp.setEmpno(rs.getInt("empno"));
            emp.setEname(rs.getString("ename"));
            emp.setJob(rs.getString("job"));
            emp.setDeptno(rs.getInt("deptno"));
            emp.setMgr(rs.getInt("mgr"));
            //고용일
            java.sql.Date hiredate=rs.getDate("hiredate");
            if(hiredate!=null){
                emp.setHiredate(hiredate.toLocalDate()); //단일행일경우 중괄호 생략 가능
            }

            emp.setSal(rs.getDouble("sal"));
            emp.setComm(rs.getDouble("comm"));
            */

                    int empno=rs.getInt("empno");
                    String ename=rs.getString("ename");

                    //자바는 기본형이 null을 참조하는 것이 불가능함!, 기본형의 없음 => 0으로 표현
                    //기본형타입의 자료형 랩퍼클래스(Byte,Short,Integer,Long, Float,Double)

                    //** 주의!! **
                    //null일 수 있는 기본형 데이터 필드는 Object로 가져와서 랩퍼클래스로 변환!!

                    //int deptno=rs.getInt("deptno");  //null:부서가 없음, 0: 부서번호 0
                    //Integer deptno=(Integer) rs.getObject("deptno");
                    BigDecimal deptnoDec=rs.getBigDecimal("deptno");
//            Integer deptno=(deptnoDec!=null)?deptnoDec.intValue():null;

                    Integer deptno = Optional.ofNullable(rs.getBigDecimal("deptno"))
                            .map(BigDecimal::intValue)
                            .orElse(null);


                    //int mgr =rs.getInt("mgr");       //null:상사 부재, 0: 상사번호 0
//            Integer mgr = (Integer) rs.getObject("mgr");
                    BigDecimal mgrDec=rs.getBigDecimal("mgr");
                    Integer mgr=(mgrDec!=null)?mgrDec.intValue():null;

                    //Double comm=(Double) rs.getObject("comm");
                    BigDecimal commDec=rs.getBigDecimal("comm");
                    Double comm=(commDec!=null)?commDec.doubleValue():null;

                    //Double sal=(Double) rs.getObject("sal");
                    BigDecimal salDec=rs.getBigDecimal("sal");
                    Double sal=(salDec!=null)?salDec.doubleValue():null;

                    //hiredate
                    //ResultSet -> DB에서 가져온 Date를 java.sql.Date or java.util.Date로 반환
                    //요즘 자바에서는 LocalDate* 를 주로 사용
                    java.sql.Date hiredate=rs.getDate("hiredate");
                    LocalDate hiredateLocal=(hiredate!=null)?hiredate.toLocalDate():null; //hiredate가 null이 아닐때, toLocalDate, 널인경우 : null로 반환
                    //LocalDate.parse("2025-09-25")

                    L05EmpDto emp=new L05EmpDto();
                    emp.setEmpno(empno);
                    emp.setEname(ename);
                    emp.setComm(comm);
                    emp.setSal(sal);
                    emp.setMgr(mgr);
                    emp.setJob(rs.getString("job"));
                    emp.setDeptno(deptno);
                    emp.setHiredate(hiredateLocal);
                    empList.add(emp);
                }
            }
        }
        return empList;
        //1. 반환할 것을 이름지어서 반환
    }
    // 이름 조회
    // ename 호출해보기 (findbyename)으로,  단일건 조회
    @Override
    public List<L05EmpDto> findByEname(String ename) throws SQLException {
        /*
        //#1 findAll 방법과 똑같이 해보기
        List<L05EmpDto> empList = null;
        String sql = "SELECT EMP.*,NVL(COMM,0) comm_val  FROM EMP WHERE ENAME = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, ename);
        rs = pstmt.executeQuery();

        empList = new ArrayList<>();
        while (rs.next()) {
            L05EmpDto emp = new L05EmpDto();
            emp.setEmpno(rs.getInt("empno")); //사번
            emp.setEname(rs.getString("ename")); //이름
            emp.setJob(rs.getString("job")); //직업
            emp.setDeptno(rs.getInt("deptno")); //부서번호
            emp.setMgr(rs.getInt("mgr")); // 매니저(번호)

            //고용일자
            java.sql.Date hiredate=rs.getDate("hiredate");     //자바의 SQL의 DATE정보인 hiredate에 가져온 hiredate를 참조
            if(hiredate!=null) emp.setHiredate(hiredate.toLocalDate());  //hiredate가 null이 아닐시,
                                                                         //emp의hiredate를 hiredate를 로컬데이트로 변환쓰

            emp.setSal(rs.getDouble("sal")); //급여

            //커미션
            if ((rs.getObject("comm") != null)) {
                emp.setComm(rs.getDouble("comm"));
            } else {emp.setComm(0.0);}
            emp.setComm(rs.getDouble("comm_val")); //null이 있어서 이렇게 뜨는거는건가 싶은데요?

            emp.setDeptno(rs.getInt("deptno"));
            empList.add(emp);
            }
        return empList;
            */

        //findByLikeEname
        //String sql="SELECT * FROM EMP WHERE ename LIKE 'K% ?"     //(오류)
        //String sql="SELECT * FROM EMP WHERE ename LIKE %'K'% ?"   //(오류)
        //String sql="SELECT * FROM EMP WHERE ename LIKE %+K+% ?"   //(오류)
        //String sql="SELECT * FROM EMP WHERE ename LIKE %||K||% ?" //(정답) _ oracle의문자열 더하기

        List<L05EmpDto> emps=null;
        String sql="SELECT * FROM EMP WHERE UPPER(ename)=UPPER(?)"; //대소문자 구분없이 조회
        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,ename);
            try (ResultSet rs = pstmt.executeQuery()){
                //이름이 같은 사람이 여러명일 수 있으므로, List로 객체만들기
                    emps=new ArrayList<>();
                    while(rs.next()){
                        //함수사용
                        L05EmpDto emp=mapRow(rs);
                        /*
                        L05EmpDto emp=new L05EmpDto();
                        emp.setEmpno(rs.getInt("empno"));
                        emp.setEname(rs.getString("ename"));
                        emp.setJob(rs.getString("job"));
                        if(rs.getDate("hiredate")!=null)                           //받은 hiredate가 null이아니면
                            emp.setHiredate(rs.getDate("hiredate").toLocalDate()); // LocalDate로 형변환해서 넘겨줄테다
                        if(rs.getBigDecimal("comm")!=null)                         //BigDecimal로 받기
                            emp.setComm(rs.getDouble("comm"));
                        if(rs.getObject("sal")!=null)                              //Object로 받기
                            emp.setSal(rs.getDouble("sal"));
                        if(rs.getObject("mgr")!=null)
                            emp.setMgr(rs.getInt("mgr"));
                            */
                        emps.add(emp);
                }
            }
        }
        return emps;
    }

    //사번 조회
    @Override
    public L05EmpDto findByEmpno (int empno) throws SQLException {
        /*
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
        return emp;*/
        System.out.println(empno);
        L05EmpDto emp=null;
        String sql="SELECT * FROM EMP WHERE EMPNO = ?";
        try (PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1,empno);
            try(ResultSet rs=ps.executeQuery()){
                if(rs.next()){
                    System.out.println(rs.getInt("empno")+"??");
                    //똑같은일을 또하면 => 함수화
                    emp=mapRow(rs);
                }
            }
        }
        System.out.println(emp);

        return emp;
    }


    //계속사용할것을 함수화 ( rs.next()에서 불러올 것을 함수화 만ㄷ르기)
        //mapping을 한다 한줄을 //함수화
        public L05EmpDto mapRow(ResultSet rs) throws SQLException {
            L05EmpDto emp=new L05EmpDto();

            emp.setEmpno(rs.getInt("empno"));

            emp.setEname(rs.getString("ename"));

            emp.setJob(rs.getString("job"));

            if(rs.getDate("hiredate")!=null)
                emp.setHiredate(rs.getDate("hiredate").toLocalDate());

            if(rs.getBigDecimal("comm")!=null)
                emp.setComm(rs.getDouble("comm"));

            if(rs.getObject("sal")!=null)
                emp.setSal(rs.getDouble("sal"));

            if(rs.getObject("mgr")!=null)
                emp.setMgr(rs.getInt("mgr"));

            if(rs.getObject("deptno")!=null)
                emp.setDeptno(rs.getInt("deptno"));

            return emp;
        }
}