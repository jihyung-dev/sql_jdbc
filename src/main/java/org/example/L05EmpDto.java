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
    private int mgr;
    //private java.util.Date hiredate; //타입충돌할수있으므로, package까지 작성
    private LocalDate hiredate;
    private double sal;
    private double comm;
    private int deptno;

    @Override
    public String toString() {
        return "L05EmpDto{" +
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
    public void setEmpno(int empno){
        this.empno=empno;
    }

    //getter : 반환타입쓰고, 매개변수 없으며, 주기만 하면 됨
    public int getEmpno(){
        return this.empno;
    }

    //ename
    public void setEname(String ename){
        this.ename=ename;
    }
    public String getEname(){
        return this.ename;
    }
    //거의 대부분의 개발툴은 get set 자동완성을 제공함 _ 정형화 되어있기 때문에 [직접 type 금지~!]

    //lombok : compile 할때 자동완성해주는 library => spring 할때 할 예정

    public int getDeptno() {
        return deptno;
    }
    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }
    public double getComm() {
        return comm;
    }
    public void setComm(double comm) {
        this.comm = comm;
    }
    public double getSal() {
        return sal;
    }
    public void setSal(double sal) {
        this.sal = sal;
    }
    public LocalDate getHiredate() {
        return hiredate;
    }
    public void setHiredate(LocalDate hiredate) {
        this.hiredate = hiredate;
    }
    public int getMgr() {
        return mgr;
    }
    public void setMgr(int mgr) {
        this.mgr = mgr;
    }
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }



}
