package com.example.service.impl;

import com.example.mapper.EmpMapper;
import com.example.pojo.Emp;
import com.example.pojo.EmpPageQueryDTO;
import com.example.pojo.PageResult;
import com.example.service.EmpService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    /**
     * PageHelper.startPage(pageNum, pageSize)：必须紧贴查询语句之前调用，自动添加分页参数
     * PageInfo：封装了分页详细信息（总页数、当前页、是否有下一页等）
     * @param dto
     * @return
     */
    @Override
    public PageResult pageQuery(EmpPageQueryDTO dto) {
        //启动分页 填写参数：查看分页的第几页，一页显示的数量
        PageHelper.startPage(dto.getPage(),dto.getPageSize());
        //执行查询
        List<Emp> list = empMapper.pageQuery(dto);
        //将查询到的结果集合封装
        PageInfo<Emp> pageInfo = new PageInfo<>(list);

        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public void deleteEmps(List<Integer> ids) {
        empMapper.deleteEmps(ids);
    }

    @Override
    public void addEmp(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.addEmp(emp);
    }
}
