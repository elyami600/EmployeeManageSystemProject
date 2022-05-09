package com.cognixia.jump.util;

import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.exceptions.EmployeeNotFoundException;
import com.cognixia.jump.model.Employee;

public class EmployeeManagerInMemory implements EmployeeManager {
	
	private static int idCounter = 1;
	private static List<Employee> employeeList = new ArrayList<Employee>();
	
	static {
		employeeList.add(new Employee(idCounter++, "Tom", "HR", 50000, "tom@email.com"));
		employeeList.add(new Employee(idCounter++, "Mary", "HR", 50000, "mary@email.com"));
		employeeList.add(new Employee(idCounter++, "Anna", "IT", 50000, "anna@email.com"));
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeList;
	}

	@Override
	public Employee findEmployeeById(int id) throws EmployeeNotFoundException {
		
		for(Employee e : employeeList) {
			if(e.getId() == id) {
				return e;
			}
		}
		
		throw new EmployeeNotFoundException(id);
	}

	@Override
	public boolean createEmployee(Employee empl) {
		
		// reset id to be unique using the counter
		empl.setId(idCounter++);
		
		employeeList.add(empl);
		
		return false;
	}

	@Override
	public boolean deleteEmployee(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEmployee(Employee empl) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Employee> getEmployeesByDepartment(String dept) {
		// TODO Auto-generated method stub
		return null;
	}

}
