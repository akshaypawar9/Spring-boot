package com.JPA.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.JPA.demo.Entity.Employee;
import com.JPA.demo.Service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/*
	 * @GetMapping({"/" , "/index"}) public ModelAndView get() { ModelAndView mav =
	 * new ModelAndView(); ArrayList<Employee> emp = employeeService.findAll();
	 * mav.addObject("emp", emp); return mav; }
	 */

	@PostMapping("/createEmployee")
	public ResponseEntity<String> createEmployee(@RequestBody Employee employee) throws Exception {
		ResponseEntity<String> emp = employeeService.createOrupdateEmployee(employee);
		return emp;
	}

	@PutMapping("/updateEmployee")
	public ResponseEntity<String> updateEmployee(@RequestBody Employee employee) throws Exception {
		ResponseEntity<String> emp = employeeService.createOrupdateEmployee(employee);
		return emp;
	}
	
	@GetMapping("/")
	public ResponseEntity<String> getAllEmployee() {
		ResponseEntity<String> employee = employeeService.findAll();
		return employee;
	}

	@GetMapping("/Employee/{id}")
	public ResponseEntity<String> findById(@PathVariable int id) {
		ResponseEntity<String> emp = employeeService.findById(id);
		return emp;

	}

	@DeleteMapping("/Employee/{id}")
	public ResponseEntity<String> deleteById(@PathVariable int id) {
		ResponseEntity<String> emp = employeeService.deleteEmployee(id);
		return emp;
	}

}
