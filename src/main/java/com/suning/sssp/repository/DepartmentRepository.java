package com.suning.sssp.repository;

import com.suning.sssp.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * @author yqx
 * @Company https://www.suning.com
 * @date 2020/11/27 9:25
 * @desc
 */
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    /**
     * springdata和jpa整合实现二级缓存
     * @return
     */
    @QueryHints({@QueryHint(name = org.hibernate.ejb.QueryHints.HINT_CACHEABLE,value = "true")})
    @Query("from Department d")
    List<Department> getAll();
}
