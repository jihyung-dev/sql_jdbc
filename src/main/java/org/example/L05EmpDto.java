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
    private int empno; //final을 사용하면 빈생성자 사용불가 (for getter / setter)
    private String ename;
    private String job;
    private Integer mgr;
    //private java.util.Date hiredate; //타입충돌할수있으므로, package까지 작성
    private LocalDate hiredate;
    private Double sal;
    private Double comm;
    private Integer deptno;



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
    // : 단순한 데이터 담고 전달하기위한 것
    //              로직, 유효성 검사, 형변환 포함 X

    //setter규칙 : void, 매개변수 : 필드와 같은것, this.필드와 같은것
    //getter : 반환타입쓰고, 매개변수 없으며, 주기만 하면 됨
    //거의 대부분의 개발툴은 get set 자동완성을 제공함 _ 정형화 되어있기 때문에 [직접 type 금지~!]
    //lombok : compile 할때 자동완성해주는 library => spring 할때 할 예정

    public L05EmpDto(){} //get set 도 사용하고 싶은경우, 꼭 작성!

        //Builder.builder()내부클래스만 호출하는 생성자!
    private L05EmpDto(Builder builder){
        this.empno=builder.empno;
        this.ename=builder.ename;
        this.job=builder.job;
        this.sal=builder.sal;
        this.comm=builder.comm;
        this.mgr=builder.mgr;
        this.hiredate=builder.hiredate;
        this.deptno=builder.deptno;
    }

    // *** Builder 추가 !! ***
    //내부클래스 : 외부클래스가 객체일때만 생성가능 -> 접근제어자 static으로 생성
    //public class Builder{}
    public static class Builder { //static 내부 클래스는 독립체이므로, 내부클랙스처럼 객체를 만들지 않아도됨
        private int empno;
        private String ename;
        private String job;
        private Integer mgr;
        //private java.util.Date hiredate; //타입충돌할수있으므로, package까지 작성
        private LocalDate hiredate;
        private Double sal;
        private Double comm;
        private Integer deptno;

        //생성자규칙 강제
        public Builder(int empno, String ename){
            this.empno=empno;
            this.ename=ename;
        }

        //public void setEmpno(int empno){this.empno=empno;}
        public Builder empno(int empno){
            this.empno=empno;
            return this;
        }
        public Builder ename(String ename){
            this.ename=ename;
            return this;
        }

        public Builder job(String job) {
            this.job = job;
            return this;
        }

        public Builder mgr(Integer mgr) {
            this.mgr = mgr;
            return this;
        }

        public Builder hiredate(LocalDate hiredate) {
            this.hiredate = hiredate;
            return this;
        }

        public Builder sal(Double sal) {
            this.sal = sal;
            return this;
        }

        public Builder comm(Double comm) {
            this.comm = comm;
            return this;
        }

        public Builder deptno(Integer deptno) {
            this.deptno = deptno;
            return this;
        }
        public L05EmpDto builder(){
            return new L05EmpDto(this);
        }

    }

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
class BuilderTest{
    static void main() {
        //L05EmpDto.Builder builder= new L05EmpDto().new Builder(); //객체여야함!
        L05EmpDto.Builder builder = new L05EmpDto.Builder(1111,"경민") //외부객체 생성없이 바로 객체 생성 가능
                //.empno(111) .ename("경민")     //자기자신을 반환하므로 계속 작성 가능
                .deptno(33)                     //↓
                .sal(222.0)                     //↓
                .comm(444.0)                    //↓
                .job("개발자")                   //↓
                .mgr(7788)                      //↓
                .hiredate(LocalDate.now());     //↓

        L05EmpDto emp=new L05EmpDto.Builder(2222,"최경민만")
                //.empno(111) .ename("경민")     //자기자신을 반환하므로 계속 작성 가능
                .deptno(33)                     //↓,  순서가 뒤죽박죽이어도 상관 X
                .job("개발자")                   //↓
                .comm(444.0)                    //↓
                .hiredate(LocalDate.now())      //↓
                .mgr(7788)                      //↓
                .sal(222.0)                     //↓
                .builder();
        System.out.println(emp);
    }



}
