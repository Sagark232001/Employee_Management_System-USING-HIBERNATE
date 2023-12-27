package com.qsp.ems.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import javax.persistence.Table;

@Entity
@Table(name ="project_table")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_id")
	private int id;
	
//	@Column(name = "project_name" , nullable = false, unique = true)
	@Column(name = "project_name")
	private String projectname;
	
	@Column(name = "project_desription" )
	private String projectDescription;
	
	@ManyToMany(mappedBy = "projects")
	private List<Employee> employees;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	

	public Project() {
		super();
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", projectname=" + projectname + ", projectDesription=" + projectDescription  + "]";
	}

	


}
