package com.qsp.ems.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name = "employee_table")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private int employeeid;
	
	@Column(name = "employee_name" , nullable = false, unique = true)
	private String employeename;
	
	@Column(name = "employee_salary" , nullable = true)
	private double salary;
	
	@Column(name = "employee_position", nullable = true)
	private String position;
	
	@UpdateTimestamp
	@Column(name = "date_of_join")
	private LocalDateTime dateOfJoin;
	
	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;

	@ManyToMany
	@JoinTable(joinColumns = @JoinColumn(name = "employee_id") , inverseJoinColumns = @JoinColumn(name = "project_id"))
	private List<Project> projects;
	

	public String getEmployeename() {
		return employeename;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public LocalDateTime getDateOfJoin() {
		return dateOfJoin;
	}

	public void setDateOfJoin(LocalDateTime dateOfJoin) {
		this.dateOfJoin = dateOfJoin;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	

	public int getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Employee() {
		super();
	}

	@Override
	public String toString() {
		return "Employee [employeeid=" + employeeid + ", employeename=" + employeename + ", salary=" + salary
				+ ", position=" + position + ", dateOfJoin=" + dateOfJoin +  "]";
	}

		
	
	
}
