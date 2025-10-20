package org.example;

import java.sql.Connection;
import java.sql.SQLOutput;
import java.util.List;

public class L08DeptDaoTest {
    public static void main(String[] args) {
        try (Connection conn=L03DBFactory.getConn()){

            L07DeptDao deptDao=new L07DeptDaoImp(conn);

            //특정부서삭제
            int delete=deptDao.deleteOne(50);
            String delMsg=(delete>0)?"삭제성공":"삭제실패";
            System.out.println(delMsg);

            //특정부서등록
            L07DeptDto insertDept=new L07DeptDto(50,"백개발","가산동");
            int insert=deptDao.insertOne(insertDept);


            String msg=(insert>0)?"등록성공":"등록실패";
            System.out.println(msg);

            //전체조회
            List<L07DeptDto> depts=deptDao.findAll();
            System.out.println(depts);

            //부서수정
            L07DeptDto updateDept=new L07DeptDto(50,"front개발","가산동");
            int update=deptDao.updateOne(updateDept);
            msg=(update>0)?"수정성공":"수정실패";
            System.out.println(msg);

            //특정부서조회
            L07DeptDto dept=deptDao.findByDeptno(50);
            System.out.println(dept);

            //등록이후 삭제
             delete=deptDao.deleteOne(50);
             delMsg=(delete>0)?"삭제성공":"삭제실패";
            System.out.println(delMsg);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
