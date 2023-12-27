package com.qsp.ems.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.qsp.ems.controller.Controller;
import com.qsp.ems.model.Department;
import com.qsp.ems.model.Employee;
import com.qsp.ems.model.Project;

public class View {
	static {
		System.out.println("-----------------------------welcome-----------------------------");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pgadmin");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Controller controller = new Controller();
		Scanner myInput = new Scanner(System.in);
		do {
			System.out.println("1.TO FIND EMPLOYEE");
			System.out.println("2.TO ADD EMPLOYEE");
			System.out.println("3.TO REMOVE EMPLOYEE");
			System.out.println("4.TO UPDATE EMPLOYEE");
			
			System.out.println("5.TO FIND DEPARTMENT");
			System.out.println("6.TO ADD DEPARTMENT");
			
			System.out.println("7.TO FIND PROJECT");
			System.out.println("8.TO ADD PROJECT");
			System.out.println("9. TO FETCH ALL EMPLOYEES");
			System.out.println("10. TO FETCH ALL DEPARTMENTS");
			System.out.println("11. TO FETCH ALL PROJECTS");
			System.out.println("0.TO EXIT");

			int operation = myInput.nextInt();
			myInput.nextLine();
			switch (operation) {
			case 0:
				System.out.println("EXITED");
				myInput.close();
				System.exit(1);
				break;
			case 1:
				System.out.println("ENTER EMPLOYEE ID TO FIND");
				 int employeeIdToFind = myInput.nextInt();
				 Employee employeeFound = entityManager.find(Employee.class, employeeIdToFind);
				 if (employeeFound != null) {
					System.out.println(employeeFound);
				} else {
					System.out.println("EMPLOYEE WITH THIS ID NOT EXIST");
				}
				break;
			case 2:
				Employee employeeToAdd = new Employee();
				System.out.println("ENTER EMPLOYEE NAME");
				employeeToAdd.setEmployeename(myInput.nextLine());
				System.out.println("ENTER EMPLOYEE SALARY");
				employeeToAdd.setSalary(myInput.nextInt());
				myInput.nextLine();
				System.out.println("ENTER EMPLOYEE POSITION");
				employeeToAdd.setPosition(myInput.nextLine());
				ArrayList<Project> projectList = new ArrayList<Project>();
				employeeToAdd.setProjects(projectList);
				
				if (controller.addEmployee(employeeToAdd)) {
					System.out.println("Entered");
				} else {
					System.out.println("Not entered");
				}
				
				break;
			case 3:
				
				System.out.println("TO REMOVE EMPLOYEE ENTER ID");
				int empId = myInput.nextInt();
				myInput.nextLine();
				if (controller.removeEmployee(empId)) {
					System.out.println("Employee removed");
				} else {
					System.out.println("Employee not removed");
				}
				break;
				
			case 4:
				Employee empToUpdate = new Employee();
				
				System.out.println("TO UPDATE EMPLOYEE ENTER ID");
				empToUpdate.setEmployeeid(myInput.nextInt());
				myInput.nextLine();
				Employee foundEmployee = controller.findEmployee(empToUpdate.getEmployeeid());
				System.out.println("TO CHANGE EMPLOYEE NAME PRESS 1");
				int updateChoice = myInput.nextInt();
				myInput.nextLine();
				if (updateChoice == 1) {
					System.out.println("ENTER NEW NAME");
					foundEmployee.setEmployeename(myInput.nextLine());
				}
				System.out.println("TO CHANGE EMPLOYEE SALARY PRESS 1");
				
				updateChoice = myInput.nextInt();
				myInput.nextLine();
				if (updateChoice == 1) {
					System.out.println("ENTER NEW SALARY");
					foundEmployee.setSalary(myInput.nextDouble());
					myInput.nextLine();
				}
				
				System.out.println("TO CHANGE EMPLOYEE POSITION PRESS 1");
				updateChoice = myInput.nextInt();
				myInput.nextLine();
				if (updateChoice == 1) {
					System.out.println("ENTER NEW POSITION");
					foundEmployee.setPosition(myInput.nextLine());
					
				}
				//----------------------------------------
				System.out.println("TO CHANGE EMPLOYEE DEPARTMENT PRESS 1");
				updateChoice = myInput.nextInt();
				myInput.nextLine();
				if (updateChoice == 1) {
					System.out.println("ENTER DEPARTMENT ID");
					Department foundDepartment = controller.findDepartment(myInput.nextInt());
					myInput.nextLine();
					if (foundDepartment != null) {
						foundEmployee.setDepartment(foundDepartment);
					} 	
				}
				//-------------------
				
				System.out.println("TO CHANGE EMPLOYEE PROJECT DETAILS PRESS 1");
				updateChoice = myInput.nextInt();
				myInput.nextLine();
				if (updateChoice == 1) {
					System.out.println("ENTER PROJECT ID");
					Project foundProject = controller.findProject(myInput.nextInt());
					myInput.nextLine();
					
					if (foundProject != null) {

						List<Project> projects = foundEmployee.getProjects();
						if (projects != null) {
							projects.add(foundProject);
						}
						foundEmployee.setProjects(projects);
					} 	
				}
				if (controller.updateEmployee(foundEmployee)) {
					System.out.println("updated");
				} else {
					System.out.println("not updated");
				}
				
				
				break;
			case 5:
				System.out.println("TO FIND DEPARTMENT ENTER DEPTID");
				Department departmentToshow = controller.findDepartment(myInput.nextInt());
				myInput.nextLine();
				if (departmentToshow != null) {
					System.out.println(departmentToshow);
				} else {
					System.out.println("DEPARTMENT NOT FOUND");
				}
				break;
			case 6:
				System.out.println("6.TO ADD DEPARTMENT");
				Department departmentToadd = new Department();
				System.out.println("ENTER DEPARTMENT NAME");
				departmentToadd.setDepartmentname(myInput.nextLine());
				ArrayList<Employee> emplist = new ArrayList<Employee>();
				departmentToadd.setEmployees(emplist);
				if (controller.addDepartment(departmentToadd)) {
					System.out.println("DEPARTMENT ADDED");
				} else {
					System.out.println("DEPARTMENT NOT ADDED");
				}
				
				break;
			case 7:
				System.out.println("7.TO FIND PROJECT");
				System.out.println("TO FIND PROJECT ENTER ID");
				Project foundProject = controller.findProject(myInput.nextInt());
				myInput.nextLine();
				if (foundProject != null) {
					System.out.println(foundProject);
				} else {
					System.out.println("PROJECT NOT FOUND");
				}

				break;
			case 8:
				System.out.println("8.TO ADD PROJECT");
				Project projectToAdd = new Project();
				System.out.println("ENTER PROJECT NAME");
				projectToAdd.setProjectname(myInput.nextLine());
				System.out.println("ENTER PROJECT DESCRIPTION");
				projectToAdd.setProjectDescription(myInput.nextLine());
				ArrayList<Employee> emplistProject = new ArrayList<Employee>();
				projectToAdd.setEmployees(emplistProject);
				if (controller.addProject(projectToAdd)) {
					System.out.println("PROJECT ADDED");
				} else {
					System.out.println("PROJECT ADDED");
				}
				
				break;
			case 9:
				
				System.out.println("9. TO FETCH ALL EMPLOYEES");
				List<Employee> findAllEmployees = controller.findAllEmployees();
				for (Employee employee : findAllEmployees) {
					System.out.println(employee);
				}
				break;
			case 10:
				
				System.out.println("10. TO FETCH ALL DEPARTMENTS");
				List<Department> findAllDepartments = controller.findAllDepartments();
				for (Department department : findAllDepartments) {
					System.out.println(department);
				}
				break;
			case 11:
				
				System.out.println("11. TO FETCH ALL PROJECTS");
				List<Project> findAllProjects = controller.findAllProjects();
				for (Project project : findAllProjects) {
					System.out.println(project);
				}
				
				break;	
			default:
				break;
			}
		} while (true);
		
		
	}

}
