package com.suning.sssp.repository;

import com.suning.sssp.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yqx
 * @Company https://www.suning.com
 * @date 2020/11/26 19:23
 * @desc
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    /**
     * 通过名字查询用户
     * @param lastName
     * @return
     */
    Employee getByLastName(String lastName);
}
