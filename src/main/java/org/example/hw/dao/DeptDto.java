package org.example.hw.dao;

public class DeptDto {
    //[3. DEPT 테이블의 dto 클래스 구현 //심심하면 empdto 도하고]

//DEPT 테이블 구성
/*
DEPTNO
DNAME
LOC
*/
    public DeptDto () {}
    public DeptDto(int deptno, String dname, String loc) {
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
    }

    //테이블 구성을 순차적으로 나열, 함수로만 접근할 수 있도록 접근지정자 => private
    private int deptno;
    private String dname;
    private String loc;

    //캡슐화 getter setter

    //getter 규칙 : 반환타입쓰고, 매개변수 없으며, 건네주기만 하면 OK
    public int getDeptno() {
        return deptno;
    }
    //setter 규칙 : void, 매개변수 지정 : 필드와 같은것, this.필드와 같은 것
    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }
    public String getDname() {
        return dname;
    }
    public void setDname(String dname) {
        this.dname = dname;
    }
    public String getLoc() {
        return loc;
    }
    public void setLoc(String loc) {
        this.loc = loc;
    }
    //toString을 재정의 해주어 출력시 보기 좋은형태로 바꿔주기 //+라인개행추가

    @Override
    public String toString() {
        return "{" +
                "deptno=" + deptno +
                ", dname='" + dname + '\'' +
                ", loc='" + loc + '\'' +
                "}\n";
    }
}
