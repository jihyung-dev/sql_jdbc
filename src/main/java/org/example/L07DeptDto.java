package org.example;

public class L07DeptDto {
/*
DEPTNO NUMBER(2) => byte[1] short[2] int(*)[4] long[8]
                                java의 숫자의 기본 type
DNAME VARCHAR(14) => String(*), char[14]_인코딩문제 있을 수 있음

LOC VARCHAR(13)
*/
    //캡슐화

    private int deptno;     //부서번호  //내 클래스 내부에서만 접근 가능한!
    private String dname;   //부서이름  //데이터가 중요하므로 직접접근을 막아둠
    private String loc;     //부서위치

    //생성자로 한번에 정리해보기
    //생성자 : 타입이 객체가 될 때 초기값(default)을 정의

    //Overload _ 이름은 1개인데 여러가지 역할을 하는 것 => 다형성 //객체지향문법
    //오버로드, 오버라이드, 타입의 다형성_부모타입을 자식이 정의하는 것

    public L07DeptDto(){} //생성자를 정의하면 기본생성자가 사라지므로, 매개변수가 비어있는 생성자 정의
    public L07DeptDto(int deptno,String dname,String loc){
        this.deptno=deptno;
        this.dname=dname;
        this.loc=loc;
    }




    //dto : 데이터를 저장(set) 및 전송(get)
    public void setDeptno(int deptno) {
        this.deptno = deptno;  // 매개변수를 객체의 deptno가 참ㅈ
    }
    public int getDeptno() { //받을게 없음, 보내줄 거니까
        return this.deptno;  //객체의 deptno를 반환
    }
    public void setDname(String dname) {
        this.dname = dname;
    }
    public String getDname() {
        return this.dname;
    }
    public void setLoc(String loc) {
        this.loc = loc;
    }
    public String getLoc() {
        return loc;
    }

//    @Override //재정의 _ 부모가 가지고있는것을 올라타다 (바꾸겠다) //@부모한테 가지고있는지 검사
//    public String toString(){
//        String str=""; //기본값으로 null을 주기보단 "" 빈것으로
//        str+="deptno: "+deptno+",";
//        str+="dname: "+dname+",";
//        str+="loc: "+loc+",";
//        return str;
//    }
//

    @Override
    public String toString() {
        return "{" +
                "deptno=" + deptno +
                ", dname='" + dname + '\'' +
                ", loc='" + loc + '\'' +
                "}\n";
    }
}

class T{
    public static void main(String[] args) {
        L07DeptDto deptDto=new L07DeptDto();
        //deptDto.deptno=10;
        deptDto.setDeptno(10);
        deptDto.setDname("리서치");
        deptDto.setLoc("서울");

        System.out.println(deptDto.getDeptno());
        System.out.println(deptDto.getDname());
        System.out.println(deptDto.getLoc());
        System.out.println(deptDto);

        L07DeptDto deptDto2=new L07DeptDto(20,"개발","부산");
        System.out.println(deptDto2);
    }
}
