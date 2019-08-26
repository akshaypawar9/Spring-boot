package com.JPA.demo.Service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.JPA.demo.Controller.BaseController;
import com.JPA.demo.Entity.Employee;
import com.JPA.demo.Repository.EmployeeRepository;
import net.sf.json.JSONObject;

@Service
public class EmployeeService extends BaseController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	public ResponseEntity<String> findAll() {
		JSONObject empObj = new JSONObject();
		ArrayList<Employee> emp = (ArrayList<Employee>) employeeRepository.findAll();
		empObj.put("empList", emp);
		ResponseEntity<String> response;
		response = getResponseInJsonType(empObj.toString(), HttpStatus.OK);
		return response;
	}

	public ResponseEntity<String> findById(int id) {
		JSONObject empObj = new JSONObject();
		Optional<Employee> employee = employeeRepository.findById(id);
		ResponseEntity<String> response;
		if (employee.isPresent()) {
			empObj.put("employee", employee.get());
			response = getResponseInJsonType(empObj.toString(), HttpStatus.OK);
			return response;
		} else {
			empObj.put("RESPONSE","Employee not Found");
			response = getResponseInJsonType(empObj.toString(), HttpStatus.OK);
			return response;
		}
	}

	public ResponseEntity<String> createOrupdateEmployee(Employee employee) {
		JSONObject empObj = new JSONObject();
		Optional<Employee> employeeEntity = employeeRepository.findById(employee.getId());
		ResponseEntity<String> response;
		if (employeeEntity.isPresent()) {
			Employee emp = employeeEntity.get();
			emp.setFname(employee.getFname());
			emp.setLname(employee.getLname());
			emp.setAddr(employee.getAddr());
			empObj.put("employee", employeeRepository.save(emp));
			empObj.put("RESPONSE", "Employee Updated...");
			response = getResponseInJsonType(empObj.toString(), HttpStatus.OK);
			return response;
		} else {
			empObj.put("employee", employeeRepository.save(employee));
			empObj.put("RESPONSE", "Employee Created...");
			response = getResponseInJsonType(empObj.toString(), HttpStatus.OK);
			return response;
		}

	}
	
	public ResponseEntity<String> deleteEmployee(int id) {
		JSONObject empObj = new JSONObject();
		Optional<Employee> employee = employeeRepository.findById(id);
		ResponseEntity<String> response;
		if (employee.isPresent()) {
			employeeRepository.delete(employee.get());
			empObj.put("RESPONSE", "Employee Deleted...");
			response = getResponseInJsonType(empObj.toString(), HttpStatus.OK);
			return response;
		} else {
			empObj.put("RESPONSE","Employee not Found");
			response = getResponseInJsonType(empObj.toString(), HttpStatus.OK);
			return response;
		}
	}

}
