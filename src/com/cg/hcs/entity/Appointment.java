package com.cg.hcs.entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.cg.hcs.utility.StringSequenceIdGenerator;


@Entity
@Table(
		uniqueConstraints= 
		{@UniqueConstraint (columnNames={"appDate","testId"}),
		 @UniqueConstraint (columnNames={"appDate","userId"})
		}
		
		)
public class Appointment 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int appId;
	private String appDate;
	private char appStatus;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="testId", referencedColumnName="testId")
	private Test test;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="userId", referencedColumnName="userId")
	private Users user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "centerId", referencedColumnName = "center_id")
	private DiagnosticCenter center;
	
	public Appointment() {
		super();
	}


	public Appointment(String appDate, char appStatus, Test test, Users user) {
		super();
		this.appDate = appDate;
		this.appStatus = appStatus;
		this.test = test;
		this.user = user;
	}

	public Appointment(int appId, String appDate, char appStatus, Test test,
			Users user) {
		super();
		this.appId = appId;
		this.appDate = appDate;
		this.appStatus = appStatus;
		this.test = test;
		this.user = user;
	}


	public int getAppId() {
		return appId;
	}


	public void setAppId(int appId) {
		this.appId = appId;
	}


	public String getAppDate() {
		return appDate;
	}


	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}


	public char getAppStatus() {
		return appStatus;
	}


	public void setAppStatus(char appStatus) {
		this.appStatus = appStatus;
	}


	public Test getTest() {
		return test;
	}


	public void setTest(Test test) {
		this.test = test;
	}


	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}


	public DiagnosticCenter getCenter() {
		return center;
	}


	public void setCenter(DiagnosticCenter center) {
		this.center = center;
	}


	@Override
	public String toString() {
		return "Appointment [appId=" + appId + ", appDate=" + appDate + ", appStatus=" + appStatus +"]";
	}


	
	
}