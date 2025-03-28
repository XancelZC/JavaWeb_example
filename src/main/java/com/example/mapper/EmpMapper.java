package com.example.mapper;

import com.example.pojo.Emp;
import com.example.pojo.EmpPageQueryDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpMapper {

    List<Emp> pageQuery(EmpPageQueryDTO dto);

    //动态sql for-each实现
    void deleteEmps(List<Integer> ids);

    @Insert("insert into emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "VALUES (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void addEmp(Emp emp);

    @Select("select *from emp where id = #{id}")
    Emp findById(Integer id);


    void changeEmp(Emp emp);

    @Select("select *from emp where username = #{username}")
    Emp findByUsername(String username);
}
