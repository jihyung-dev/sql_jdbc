package org.example.hw;

import javax.swing.plaf.synth.SynthRadioButtonMenuItemUI;
import java.sql.*;

public class H02PSTMT {
    public static void main(String[] args) {
        String url="jdbc:oracle:thin:@//localhost:1521/XEPDB1";
        String user="scott";
        String pw="tiger";
        try ( Connection conn = DriverManager.getConnection(url,user,pw)){

            //4. 직원삭제하기
            try (PreparedStatement pstmt=conn.prepareStatement("DELETE FROM EMP WHERE empno IN (?,?)")){
                pstmt.setInt(1,9001);
                pstmt.setInt(2,9002);
                int del=pstmt.executeUpdate();
                if(del==1){
                    System.out.println("9001 삭제성공");
                }
            }
            //System.out.println(conn);

            //Statement stmt= conn.createStatement();

            try(Statement stmt=conn.createStatement()){ //to auto close
                // 1. 직원등록하기
                String sql="INSERT INTO EMP (empno,ename,job,sal,deptno) " +
                        "VALUES (9001,'ALLEN2','SALESMAN',1600.00,30)";
                int insert=stmt.executeUpdate(sql);
                if(insert==1){
                    System.out.println("ALLEN2 등록성공");
                }
                //statement 는 일회용!
            }

            // 2. ptsmt로 등록하기
            String sql="INSERT INTO EMP(empno,ename,job,sal,deptno) VALUES (?,?,?,?,?)";

            try (PreparedStatement psmt=conn.prepareStatement(sql)){
                psmt.setInt(1,9002);
                psmt.setString(2,"SMITH2");
                psmt.setString(3,"CLERK");
                psmt.setInt(4,800);
                psmt.setInt(5,20);
                int insert=psmt.executeUpdate();
                if (insert > 0) {
                    System.out.println("SMITH2 등록성공");
                }

            }


            try(Statement stmt=conn.createStatement()){
                ResultSet rs=stmt.executeQuery("SELECT * FROM EMP");
                //String str="";
                StringBuilder sb=new StringBuilder();
                while(rs.next()){
                    int empno=rs.getInt("EMPNO");
                    String ename=rs.getString("ENAME");
                    String job=rs.getString("JOB");
                    double sal=rs.getDouble("SAL");
                    int deptno=rs.getInt("DEPTNO");
                    // str+=empno+" | "+ename+" | "+job+" | "+sal+" | "+deptno;
                    // 문자열 더하기는 객체를 많이 생성 => 메모리를 많이 사용
                    //멀티스레드환경에서는 buffer 단일에서는 build를 사용
                    sb.append(empno);
                    sb.append(" | ");
                    sb.append(ename);
                    sb.append(" | ");
                    sb.append(job);
                    sb.append(" | ");
                    sb.append(sal);
                    sb.append(" | ");
                    sb.append(deptno);
                    sb.append(" \n ");
                }
                //System.out.println(str);
                System.out.println(sb.toString());
            }

            //7. 조인을 사용하여 사원과 부서명 함께 조회하기
            // #1. stmt 방식으로 하기
                 sql="SELECT e.*, d.dname, e.ename 이름, e.sal 급여 FROM EMP e LEFT JOIN DEPT d ON e.deptno=d.deptno";

            // #2. 서브쿼리 방식으로 추가하기
            //sql="SELECT e.*,(SELECT dname FROM dept WHERE deptno=e.deptno) as dname FROM EMP e";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql);){
                 StringBuilder sb=new StringBuilder();
                while (rs.next()){
                    int empno=rs.getInt("EMPNO");
                    int deptno=rs.getInt("DEPTNO");
                    int sal=rs.getInt("급여");
                    int comm=rs.getInt("COMM");
                    String ename=rs.getString("이름");
                    String dname=rs.getString("DNAME");
                    sb.append(empno+" | "+deptno+" | "+sal+" | "+comm+" | "+ename+" | "+dname+"\n");
                }
                System.out.println(sb.toString());
            }

            sql="SELECT deptno, AVG(sal) \"부서별 평균 급여\" FROM EMP GROUP BY deptno";
            try(Statement stmt=conn.createStatement();
                ResultSet rs=stmt.executeQuery(sql)){
                StringBuilder sb=new StringBuilder();
                while(rs.next()){
                    int deptno=rs.getInt("deptno");
                    double salAvg=rs.getDouble("부서별 평균 급여");
                    sb.append(deptno+" | "+salAvg);

                }
                }

            }

         catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
