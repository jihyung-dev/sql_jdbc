package org.example.hw.dao;

import java.sql.Date;
import java.util.Locale;

public class EmpDto {
    //[3. DEPT 테이블의 dto 클래스 구현 //심심하면 empdto 도하고]

    //Dto는 Database Transfer Object 로,  넘겨주는 중간 역할!

//EMP 테이블 구성
/*
EMPNO
ENAME
JOB
MGR
HIREDATE
SAL
COMM
DEPTNO
*/
    //1. 테이블 구성 보기
    //2. 그 테이블에 맞는 필드 생성하기.
    //3. 해당 필드는 직접 접근을 불가능하게 만들어줘야하므로 private 접근지정자 추가
    //4. 캡슐화 (get/set 규칙, 자동생성 가능)
    //5. toString 재정의

    //6. 생성자
    //생성자는 언제 추가하지? 맨 마지막에하나? 음?

    public EmpDto (){} //기본도 만들어주기
    public EmpDto(int empno, String ename, String job, int mgr, Date hiredate, double sal, double comm, int deptno) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }

    private int empno;
    private String ename;
    private String job;
    private int mgr;
    private Locale hiredate; //util 꺼? or sql꺼? 여기는 sql 꺼를 쓰니가 sql꺼를 불러와보자
    private double sal;
    private double comm;
    private int deptno;

    //getter 규칙 : 반환타입, 매개변수 X, 건네주기
    public int getEmpno(){
        return empno;
    }
    //setter 규칙 : void, 매개변수_필드와 같은것, this.필드
    public void setEmpno(int empno){
        this.empno=empno;
    }


    //자동생성
    public String getEname() {
        return ename;
    }
    public void setEname(String ename) {
        this.ename = ename;
    }
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
    public int getMgr() {
        return mgr;
    }
    public void setMgr(int mgr) {
        this.mgr = mgr;
    }
    public double getSal() {
        return sal;
    }
    public void setSal(double sal) {
        this.sal = sal;
    }
    public double getComm() {
        return comm;
    }
    public void setComm(double comm) {
        this.comm = comm;
    }
    public int getDeptno() {
        return deptno;
    }
    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(Locale hiredate) {
        this.hiredate = hiredate;
    }

    @Override
    public String toString() {
        return "{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", hiredate=" + hiredate +
                ", sal=" + sal +
                ", comm=" + comm +
                ", deptno=" + deptno +
                "}\n";
    }
}
