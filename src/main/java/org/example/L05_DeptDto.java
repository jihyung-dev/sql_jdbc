package org.example;

public class L05_DeptDto {
/*DEPTNO
DNAME
LOC*/

    //누구도 접근할 수 없고, 함수로만 접근 가능 => [private]
    private int deptno;
    private String dname;
    private String loc;

    //생성을 활용하여 출력 만들어주기 toString-Override, 마지막줄에 ""로 변경 및 라인개행
    @Override
    public String toString() {
        return "L05DeptDto{" +
                "deptno=" + deptno +
                ", dname='" + dname + '\'' +
                ", loc='" + loc + '\'' +
                "}\n";
    }

    //getter setter 캡슐화 : 생성 탭 활용
    public int getDeptno() {
        return deptno;
    }
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


}
