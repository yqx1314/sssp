package com.suning.sssp.service;

import com.suning.sssp.domain.Employee;
import com.suning.sssp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author yqx
 * @Company https://www.suning.com
 * @date 2020/11/26 19:25
 * @desc
 */
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public Page<Employee> getPage(int pageNo,int pageSize){
        PageRequest pageable = new PageRequest(pageNo - 1, pageSize);
        return employeeRepository.findAll(pageable);
    }
    @Transactional(readOnly = true)
    public Employee getByLastName(String lastName){
        return employeeRepository.getByLastName(lastName);
    }
    @Transactional(rollbackFor = Exception.class)
    public void save(Employee employee){
        if(employee.getId() == null){
            employee.setCreateTime(new Date());
        }
        employeeRepository.saveAndFlush(employee);
    }
    @Transactional(readOnly=true)
    public Employee get(Integer id){
        return employeeRepository.getOne(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id){
        employeeRepository.deleteById(id);
    }


}
