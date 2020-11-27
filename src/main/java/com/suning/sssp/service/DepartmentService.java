package com.suning.sssp.service;

import com.suning.sssp.domain.Department;
import com.suning.sssp.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yqx
 * @Company https://www.suning.com
 * @date 2020/11/27 9:41
 * @desc
 */
@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional(readOnly=true)
    public List<Department> getAll(){
        return departmentRepository.getAll();
    }
}

