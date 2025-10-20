package org.example;

import java.time.LocalDate;

public class L05EmpDto {
/*EMPNO
ENAME
JOB
MGR
HIREDATE
SAL NUMBER(7,2)
COMM
DEPTNO*/
    //누구도 접근할 수 없음, 함수로만 접근 가능 [private]
    private int empno;
    private String ename;
    private String job;
    private Integer mgr;
    //private java.util.Date hiredate; //타입충돌할수있으므로, package까지 작성
    private LocalDate hiredate;
    private Double sal;
    private Double comm;
    private Integer deptno;

    public L05EmpDto(){}

    public L05EmpDto(int empno,             //null일 수 있는 데이터 랩퍼클래스로 변환
                     String ename,
                     Double sal,            //*
                     Integer deptno,        //*
                     LocalDate hiredate,    //*
                     String job,
                     Integer mgr,           //*
                     Double comm            //*
    ) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
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

    //getter setter 캡슐화

    //setter규칙 : void, 매개변수 : 필드와 같은것, this.필드와 같은것
    //getter : 반환타입쓰고, 매개변수 없으며, 주기만 하면 됨
    //거의 대부분의 개발툴은 get set 자동완성을 제공함 _ 정형화 되어있기 때문에 [직접 type 금지~!]
    //lombok : compile 할때 자동완성해주는 library => spring 할때 할 예정


    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

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

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public LocalDate getHiredate() {
        return hiredate;
    }

    public void setHiredate(LocalDate hiredate) {
        this.hiredate = hiredate;
    }

    public Double getSal() {
        return sal;
    }

    public void setSal(Double sal) {
        this.sal = sal;
    }

    public Double getComm() {
        return comm;
    }

    public void setComm(Double comm) {
        this.comm = comm;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }
}
