package org.example;

import java.time.LocalDate;

//유효성검사
public class L11EmpValidBean {
    //Dto : 순수 캡슐화 객체 (getter / setter 만 존재), 검증이 필요없는 데이터 저장 및 전송
    //Bean : dto + 검증 (직렬화시 오류 발생)
    //Entity : 데이터베이스 전용 Dto(데이터베이스 자동맵핑 기술이 포함되어 직렬화시 오류발생 가능성 존재!)
    //EmpDto와 EmpBean의 필드는 같을수도있고, 다를 수도 있음
    private int empno;
    private String ename;
    private String job;
    private LocalDate hiredate=LocalDate.now(); //기본값 (Bean 전용!!)
    private Integer mgr; //nullable // Emp.empno FK : 생성불가
    private Double sal;  //nullable
    private Double comm; //nullable
    private Integer deptno; //DEPT.deptno FK : 생성불가(참조중)

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        if(empno<=0)throw new IllegalArgumentException("사번은 0보다 커야합니다.");
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        //if(ename==null)return;
        //"", " "

        //이름이 null이거나, name이 공백이면 예외를 위임
        if(ename==null || ename.isBlank()) throw new IllegalArgumentException("이름은 꼭 입력해야합니다.");
        //길이가 10이하 "최경민          "
        if(ename.trim().length()>=10) throw new IllegalArgumentException("이름은 10자 이하입니다.");
        //유혀성(Valid)검사 => Bean
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public LocalDate getHiredate() {
        return hiredate;
    }

    public void setHiredate(LocalDate hiredate) {
        this.hiredate = hiredate;
    }

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public Double getSal() {
        return sal;
    }

    public void setSal(Double sal) {;
        if(sal!=null && sal<0) throw new IllegalArgumentException("급여는 0보다 커야 합니다.");
        this.sal = sal;
    }

    public Double getComm() {
        return comm;
    }

    public void setComm(Double comm) {
        if(comm!=null){
            if(comm<0) throw new IllegalArgumentException("커미미션은 0보다 커야합니다.");
        }
        this.comm = comm;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }
}
