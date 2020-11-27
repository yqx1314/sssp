package com.suning.sssp;

import com.suning.sssp.domain.Department;
import com.suning.sssp.repository.DepartmentRepository;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * @author yqx
 * @Company https://www.suning.com
 * @date 2020/11/26 18:39
 * @desc
 */
public class SSSPTest {
    private ApplicationContext ctx = null;
    private DepartmentRepository departmentRepository;
    {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        departmentRepository = ctx.getBean(DepartmentRepository.class);
    }
    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }

    /**
     * 测试Repository的二级缓存
     */
    @Test
    public void testRepositorySecondLevelCache(){
        List<Department> departments = departmentRepository.getAll();
        departments = departmentRepository.getAll();
    }
}
