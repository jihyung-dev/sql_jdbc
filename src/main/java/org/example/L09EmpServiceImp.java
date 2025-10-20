package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class L09EmpServiceImp implements L09EmpService{

//private L05EmpDaoImp empDaoImp    //의존 역전 원칙에 의해 Imp가 아닌 EmpDao 로 구현
private L05EmpDao empDao;
private L07DeptDao deptDao;

private Connection conn; // 사용자가 생성해서 전달
public L09EmpServiceImp(Connection conn){
    this.conn=conn;
    this.empDao=new L05EmpDaoImp(conn);
    this.deptDao=new L07DeptDaoImp(conn);
    }
    //트랜잭션 : 데이터 수정이 2번이상 일어날 때, 문제가 생기면 save point로 되돌리는 것
    //          현재 register : 2번이상 수정(DML : insert update delete)하지 않기 때문에 필요 없음!
    //     but 수업이니까 작성함
    @Override
    public boolean register(L05EmpDto emp) throws SQLException, IllegalArgumentException {
        try{
            //jdbc conn의 설정 : autoCommit true (자동으로 commit)
            conn.setAutoCommit(false);
            conn.commit(); //save point //생략가능, 명시적
            //사번과 이름이 있는지
            //이름은 길이가 10이하인지 검사
            //급여와 커미션이 있으면 0보다 큰지
            //사번이 이미 등록되어 있는지
            //부서번호가 있다면 부서번호가 있는지(참조의 무결성)
            //상사번호(mgr)이 없으면 오류!                        //to do
            //modify 까지 해보고                                //to do

            //등록

            //사번
            //EmpDto.empno int 없으면 => 0
                if(emp.getEmpno()<=0) throw new IllegalArgumentException("사번을 입력하세요");
                //중복사번검사
                L05EmpDto existEmp=empDao.findByEmpno(emp.getEmpno()); //이미 등록된 사번이 있나?       //empDao의 empno
                if(existEmp!=null) throw new IllegalArgumentException("이미 등록된 사번입니다.");

                //이름
                //String.isBlank() : "", "   " 를 검사 //공백 및 여러줄 공백
                if(emp.getEname()==null || emp.getEname().isBlank()) throw new IllegalArgumentException("이름을 입력하세요");

                //급여
                if(emp.getSal()!=null && emp.getSal()<0) throw new IllegalArgumentException("급여는 0보다 커야합니다.");

                //커미션
                if(emp.getComm()!=null && emp.getComm()<0) throw new IllegalArgumentException("커미션는 0보다 커야합니다.");

                //상사의 사번 : null 허용
                if(emp.getMgr()!=null) {
                    //상사번호가있는지 물어봐서 없는지 확인
                    L05EmpDto existMgr=empDao.findByEmpno(emp.getMgr());         //Mgr에서 가져온 인트가 Empno에 있는지 찾기
                    if(existMgr==null) throw new IllegalArgumentException("상사가 존재하지 않습니다. MGR을 확인"); }

                //부서번호 확인 // Emp.deptno가 null 허용
                if(emp.getDeptno()!=null){
                    L07DeptDto existDept=deptDao.findByDeptno(emp.getDeptno());//등록하려는 부서가 존재하나
                    List<L07DeptDto> depts = deptDao.findAll(); //결과를 depts 리스트에 넣기
                    List<Integer> deptnos=new ArrayList<>();    //Integer만 넣기
                    for (L07DeptDto dept : depts){ deptnos.add(dept.getDeptno());} //반복문
                    if(existDept==null)
                        throw new IllegalArgumentException("존재하는(10,20,30,40) 부서번호를 입력하세요!"+deptnos);
                }
                int insert=empDao.insertOne(emp);
                return insert==0;
        } catch (Exception e) {
                conn.rollback();
                throw e; //오류가 뜨면 오류위임, 상대방도 오류를 받음
        }
    }

    @Override              //구현하기       //like regist
    public boolean modify(L05EmpDto emp) throws SQLException, IllegalArgumentException {
        try {
            conn.setAutoCommit(false);
            conn.commit();

            //체크할 목록들 정리
            // PK인 empno는 항상 존재해야함
            // 수정이므로, 이미존재, 0보다 커야함

            //여기서 데이터는 사용자가 작성하는 곳에서의 데이터가 될 텐데,

            // FK인 deptno는 10,20,30,40 중 하나여야함
            // null 불가, -> null 일시 기입해주세요
            // ename은 공백 및 여러공백 불가
            // job과 hiredate는 일단 null 가능
            // sal 과 comm 은 0보다 커야함
            // deptno는 이미 있는것 중 하나.

            //PK 확인
            if(emp.getEmpno()<0) throw new IllegalArgumentException("수정할 사번을 입력하세요!");
            if(emp.getEname()==null || emp.getEname().isBlank()) throw new IllegalArgumentException("이름은 꼭 입력하세요");
            if(emp.getSal()!=null && emp.getSal()<0) throw new IllegalArgumentException("급여는 0보다 큽니다");
            if(emp.getComm()!=null && emp.getComm()<0) throw new IllegalArgumentException("커미션은 0보다 큽니다");
            //유효성(Valid, Validation)검사
            L05EmpDto existEmp=empDao.findByEmpno(emp.getEmpno());
            if(existEmp==null)
                throw new IllegalArgumentException ("수정할 사원이 없습니다.(이미 삭제된 사원입니다. 등록하고 수정하세요");
            if(emp.getDeptno()!=null){
                L07DeptDto existDept=deptDao.findByDeptno(emp.getDeptno());
                if(existDept==null)
                    throw new IllegalArgumentException("존재하지 않는 부서번호입니다. 확인하세요");
            }
            if(emp.getMgr()!=null){
                L05EmpDto existMgr=empDao.findByEmpno(emp.getMgr());
                if(existMgr==null)
                    throw new IllegalArgumentException("존재하지 않는 상사번호입니다. 확인하세요");
            }
            return empDao.updateOne(emp)==1;

        } catch (Exception e) {
            conn.rollback();
            throw e; //오류 위임하면 그 다음 줄 실행안됨
        }


    }

    @Override
    public boolean remove(int empno) throws SQLException {
        return empDao.deleteOne(empno)==0;
    }

    @Override
    public List<L05EmpDto> readAll() throws SQLException {
        return empDao.findAll();
    }

    @Override
    public List<L05EmpDto> readByEname(String ename) throws SQLException {
        return empDao.findByEname(ename);
    }

    //service : doa=1:1
    @Override
    public L05EmpDto readOne(int empno) throws SQLException {
        return empDao.findByEmpno(empno);
    }
}
