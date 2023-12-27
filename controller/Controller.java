package com.qsp.ems.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.qsp.ems.model.Department;
import com.qsp.ems.model.Employee;
import com.qsp.ems.model.Project;

public class Controller {
	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pgadmin");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();
	
	
	public Employee findEmployee(int empId) {
		Employee employee = entityManager.find(Employee.class, empId);
		return employee;
	}
	
	
	public Project findProject(int projectId) {
		Project project = entityManager.find(Project.class, projectId);
		return project;
	}
	
	public Department findDepartment(int deptId) {
		Department department  = entityManager.find(Department.class, deptId);
		return department;
	}
	
	
	public boolean addEmployee(Employee empToAdd) {
		Employee foundEmployee = findEmployee(empToAdd.getEmployeeid());
		if (foundEmployee == null) {
			entityTransaction.begin();
						
			entityManager.persist(empToAdd);
			entityTransaction.commit();
			
			return true;
		}
		return true;
	}
	
	public boolean addDepartment(Department deptToAdd) {
		
		Department foundDepartment = findDepartment(deptToAdd.getId());
		if (foundDepartment == null) {
			
			entityTransaction.begin();
			entityManager.persist(deptToAdd);
			entityTransaction.commit();
			
			return true;
		}
		return true;
	}
	public boolean addProject(Project projectToAdd) {
		
		Project foundProject = findProject(projectToAdd.getId());
		if (foundProject == null) {
			
			entityTransaction.begin();
			entityManager.persist(projectToAdd);
			entityTransaction.commit();
			
			return true;
		}
		return true;
	}
	

	public boolean removeEmployee(int empId) {
		
		Employee foundEmployee = findEmployee(empId);
		if (foundEmployee != null) {
			
			entityTransaction.begin();
			entityManager.remove(foundEmployee);
			entityTransaction.commit();
			
			return true;
		}
		return false;
	}

	public boolean removeDepartment(int deptId) {
		Department findDepartment = findDepartment(deptId);
		if (findDepartment != null) {

			entityTransaction.begin();
			entityManager.remove(findDepartment);
			entityTransaction.commit();

			return true;
		}
		return false;
	}
	
	public boolean removeProject(int projectId) {
		Project findProject = findProject(projectId);
		if (findProject != null) {

			entityTransaction.begin();
			entityManager.remove(findProject);
			entityTransaction.commit();

			return true;
		}
		return false;
	}
	public boolean updateEmployee(Employee empToUpdate) {
		
		Employee foundEmployee = findEmployee(empToUpdate.getEmployeeid());
		if (foundEmployee != null) {
			foundEmployee.setEmployeename(empToUpdate.getEmployeename());
			foundEmployee.setSalary(empToUpdate.getSalary());
			foundEmployee.setPosition(empToUpdate.getPosition());
			foundEmployee.setProjects(empToUpdate.getProjects());
			foundEmployee.setDepartment(empToUpdate.getDepartment());
			//-------------------------------------
			Department department = foundEmployee.getDepartment();
			List<Employee> employees = department.getEmployees();
			employees.add(foundEmployee);
			department.setEmployees(employees);
			updateDepartment(department);
			//-------------------------------------
//			entityTransaction.begin();	
//			entityManager.merge(department);
//			entityTransaction.commit();
			//-------------------------------------
			entityTransaction.begin();
			entityManager.merge(foundEmployee);
			entityTransaction.commit();
			return true;
		} 
		return false;
		
	}
	
	public boolean updateDepartment(Department deptToUpdate) {
		Department foundDepartment = findDepartment(deptToUpdate.getId());
		if (foundDepartment != null) {
			foundDepartment.setDepartmentname(deptToUpdate.getDepartmentname());
			foundDepartment.setEmployees(deptToUpdate.getEmployees());

			entityTransaction.begin();
			
			entityManager.merge(foundDepartment);
			
			entityTransaction.commit();
			return true;
		} 
		return false;
		
	}
	
	public boolean updateProject(Project projectToUpdate) {
		Project foundProject = findProject(projectToUpdate.getId());
		if (foundProject != null) {
			foundProject.setProjectname(projectToUpdate.getProjectname());
			foundProject.setProjectDescription(projectToUpdate.getProjectDescription());
			foundProject.setEmployees(projectToUpdate.getEmployees());
			
			entityTransaction.begin();
			entityManager.merge(foundProject);
			entityTransaction.commit();
			
			return true;
		} 
		
		return false;
	}
	
	public List<Employee> findAllEmployees() {
		String jpql ="select e from Employee e ";
		TypedQuery<Employee> createQuery = entityManager.createQuery(jpql, Employee.class);
		List<Employee> resultList = createQuery.getResultList();
		return resultList;
	}
	public List<Department> findAllDepartments() {
		String jpql ="select s from Department s ";
		TypedQuery<Department> createQuery = entityManager.createQuery(jpql, Department.class);
		List<Department> resultList = createQuery.getResultList();
		return resultList;
	}
	public List<Project> findAllProjects() {
		String jpql ="select e from Project e ";
		TypedQuery<Project> createQuery = entityManager.createQuery(jpql, Project.class);
		List<Project> resultList = createQuery.getResultList();
		return resultList;
	}
	
}
