package com.suning.sssp.controller;

import com.suning.sssp.domain.Employee;
import com.suning.sssp.service.DepartmentService;
import com.suning.sssp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Map;

/**
 * @author yqx
 * @Company https://www.suning.com
 * @date 2020/11/26 19:29
 * @desc
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;


    @RequestMapping("/emps")
    public String list(@RequestParam(value = "pageNo",required = false,defaultValue = "1")String pageNoStr, Map<String,Object> map){
        int pageNo = 1;
        try {
           pageNo = Integer.parseInt(pageNoStr);
           if(pageNo<1){
               pageNo = 1;
           }
        }catch (Exception e){ }
        Page<Employee> page = employeeService.getPage(pageNo, 5);
        map.put("page",page);
        return "emp/list";

    }
    @RequestMapping(value="/emp",method= RequestMethod.GET)
    public String input(Map<String,Object> map){
        map.put("departments", departmentService.getAll());
        map.put("employee", new Employee());
        return "emp/input";
    }
    @ResponseBody
    @RequestMapping(value="/ajaxValidateLastName",method=RequestMethod.POST)
    public String validateLastName(@RequestParam(value="lastName",required=true) String lastName){
        Employee employee = employeeService.getByLastName(lastName);
        if(employee == null){
            return "0";
        }else{
            return "1";
        }
    }
    @RequestMapping(value="/emp",method=RequestMethod.POST)
    public String save(Employee employee){
        employeeService.save(employee);
        return "redirect:/emps";
    }

    /**
     * 表单回显的原理
     * 实际上表单的回显是由springmvc的form标签完成的
     * 在controller方法中，向request中添加一个属性。键：springmvc form：form标签modelAttribute属性值 值：包含回显信息的一个bean对象
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value="/emp/{id}", method=RequestMethod.GET)
    public String input(@PathVariable("id") Integer id, Map<String, Object> map){
        Employee employee = employeeService.get(id);
        map.put("employee", employee);
        map.put("departments", departmentService.getAll());
        return "emp/input";
    }

    @RequestMapping(value="/emp/{id}",method=RequestMethod.PUT)
    public String update(Employee employee){
        employeeService.save(employee);
        return "redirect:/emps";
    }

    @ModelAttribute
    public void getEmployee(@RequestParam(value="id",required=false) Integer id, Map<String, Object> map){
        if(id != null){
            Employee employee = employeeService.get(id);
            employee.setDepartment(null);
            map.put("employee", employee);
        }
    }

    @RequestMapping(value="/emp/{id}",method=RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id){
        employeeService.delete(id);
        return "redirect:/emps";
    }

}
