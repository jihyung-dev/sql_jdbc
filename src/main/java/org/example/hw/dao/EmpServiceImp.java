package org.example.hw.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;



public class EmpServiceImp implements EmpService{
//private EmpDaoImp empDaoimp  //의존역전 원칙에 의해 Imp가 아닌 EmpDao로 구현
    private EmpDao empDao;
    private DeptDao deptDao;
    private Connection conn; // 사용자가 생성해서 전달

    public EmpServiceImp (Connection conn) {
        this.conn=conn;
        this.empDao=new EmpDaoImp(conn);
        this.deptDao=new DeptDaoImp(conn);
    }

    //트랜잭션 : 데이터 수정이 2번이상 일어날 때, 문제가 생기면 save point로 되돌리는 것
    // 현재 : register : 2번이상 수정(DML : insert update delete)하지 않기 때문에 필요 없음!
    // but 수업이니 작성

    @Override
    public boolean register(EmpDto emp) throws SQLException,IllegalArgumentException {
        //원자성
        try{
            conn.setAutoCommit(false);  //autocommit 비활성화
            conn.commit();              //savepoint 작성

            //사번
            //EmpDto.empno int 없으면 => 0
                if(emp.getEmpno()<=0) throw new IllegalArgumentException("사번을 입력하세요");
                //중복사번검사
                    //emp의 empno를 empDao.Empno에서 찾아서 existEmpno에 참조
                EmpDto existEmpno=empDao.findByEmpno(emp.getEmpno());//이미 사번이 있는지?
                    //existEmpno가 널이 아니면 == 이미 Dao.Empno에 empno가 존재하면 //예외 반환
                if(existEmpno!=null) throw new IllegalArgumentException("이미 등록된 사번입니다.");

            //이름  //null, 공백, 여러줄 공백 예외처리
                if(emp.getEname()==null || emp.getEname().isBlank()) throw new IllegalArgumentException("이름을 입력하세요.");
                                      //or = 이거나, 둘중하나만 만족해도

            //급여 null 가능=> getSal 타입=> double -> wrapper class Double , 0보다 커야함
                if(emp.getSal()!= null && emp.getSal()<0) throw new IllegalArgumentException("급여는 0보다 커야합니다.");
                                    // and = 둘다 만족하면, null이 아니면서, 음수이면
            //커미션 0보다 커야함
                if(emp.getComm()!= null && emp.getComm()<0) throw new IllegalArgumentException("커미션은 0보다 커야합니다.");

            //상사의 사번 null허용 & null이 아닐 경우, empno중에 찾아서 참조 , 없을시 예외
                if(emp.getMgr()!=null){
                    EmpDto existMgr=empDao.findByEmpno(emp.getMgr());
                    if (existMgr==null) throw new IllegalArgumentException("존재하지 않는 상사번호입니다.");
                }
            //부서번호 확인 //null이면 없는 부서번호, //null이 아니면, deptno중 찾아서 참조, 없을시엔 예외
                if(emp.getDeptno()!=null){
                    DeptDto existDeptno=deptDao.findByDeptno(emp.getDeptno());
                    if (existDeptno==null) throw new IllegalArgumentException("존재하지 않는 부서입니다.");
                } else { throw new IllegalArgumentException("없는 부서 번호입니다.");
                }

                //이부분은 반드시 확인해볼것! (1) 없는 부서번호로 넣어보기
                //                       (2)있는 부서 번호 넣기
            int register=empDao.insertOne(emp);
            return register==1;
        } catch (Exception e) {
            conn.rollback();           //savepoint로 되돌리기
            throw e;                   //예외 전달
        } finally {
            conn.setAutoCommit(true);  //autocommit 활성화
        }
    }

    @Override
    public boolean modify(EmpDto emp) throws SQLException {
        return false;
    }

    @Override
    public boolean remove(int empno) throws SQLException {
        //없으면 삭제 불가능, 이미 없음 등을 띄울수 있으나,
        return empDao.delteOne(empno) == 1;
        //delete Query의 결과가 1이면 1개 삭제 성공
        //                    0이면 1개 삭제 실패
    }


    //Dao것 그대로 사용
    @Override
    public List<EmpDto> readAll() throws SQLException {
        return empDao.findAll();
    }

    //Dao것 그대로 사용
    @Override
    public List<EmpDto> readByEname(String ename) throws SQLException {
        //빈 배열이 올경우에는 이름이 틀렸다고 예외상황을 설정해보기
        try{
            conn.setAutoCommit(false);
            conn.commit();
            List<EmpDto> empDtoList =null;
                if(ename.isBlank() ){       //너만 살아남았다...
                    throw new IllegalArgumentException("이름을 입력해주세요");
                }
                /* //개발자의 오지랖 부분, 오류처리하지않아도 되는것을 오류 처리함
                      ex) 검색을 했을때, 검색결과가 없습니다. 라고 하면되는 것을, 오류처리해버림
                   else if (!ename.trim().isEmpty()){
                    System.out.println("조회한 이름은 "+ename);
                    empDtoList = empDao.findByEname(ename);
                    if(empDtoList.isEmpty()){
                        throw new IllegalArgumentException("존재하지 않는 이름입니다.");
                    }
                } else {
                    return empDtoList;
                } */


        } catch (Exception e) {
            conn.setAutoCommit(true);
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
        return empDao.findByEname(ename);
    }

    //Dao것 그대로 사용
    @Override
    public EmpDto readOne(int empno) throws SQLException {
        return empDao.findByEmpno(empno);
    }
}
