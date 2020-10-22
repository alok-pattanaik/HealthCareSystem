package com.cg.hcs.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.cg.hcs.utility.JpaUtility;
import com.cg.hcs.utility.StringSequenceIdGenerator;
/***************************************
 * 
 * Description : HealthCareSystem Test class
 * @author : Pratik Prakash
 * @Date : 12/10/2020
 * 
 ***************************************/


@Entity
@Table(
		uniqueConstraints= 
		@UniqueConstraint (columnNames={"testName","centerId"})
		
		)

public class Test 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_seq")
    @GenericGenerator(
        name = "test_seq", 
        strategy = "com.cg.hcs.utility.StringSequenceIdGenerator", 
        parameters = {
            @Parameter(name = StringSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = StringSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "T"),
            @Parameter(name = StringSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%08d") })
	@Column(name = "testId")
	private String testId;
	private String testName;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "centerId", referencedColumnName="center_id")
	private DiagnosticCenter center;
	
	@OneToMany(mappedBy = "test",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Appointment> listOfAppointments = new ArrayList<Appointment>();
	
	/*
	 * @PreRemove public void changeAppointmentStatus() { EntityManagerFactory
	 * factory = JpaUtility.getFactory(); EntityManager manager =
	 * factory.createEntityManager(); EntityTransaction transaction =
	 * manager.getTransaction(); transaction.begin(); TypedQuery<Appointment> query
	 * =
	 * manager.createQuery("SELECT a from appointment a WHERE a.center.centerId = '"
	 * +getCenter().getCenterId()+"'", Appointment.class); List<Appointment>
	 * appointmentsList = query.getResultList(); if(appointmentsList == null)
	 * return; for(Appointment appointment : appointmentsList) {
	 * appointment.setAppStatus('R'); manager.persist(appointment); }
	 * transaction.commit(); manager.close(); }
	 */
	public Test() {
		super();
	}
	
	
	
	public Test(String testId) {
		super();
		this.testId = testId;
	}



	public Test(String testName, DiagnosticCenter center) {
		super();
		this.testName = testName;
		this.center = center;
	}

	

	public Test(String testId, String testName, DiagnosticCenter center) {
		super();
		this.testId = testId;
		this.testName = testName;
		this.center = center;
	}
	
	

	public Test(String testId, String testName, DiagnosticCenter center, List<Appointment> listOfAppointments) {
		super();
		this.testId = testId;
		this.testName = testName;
		this.center = center;
		this.listOfAppointments = listOfAppointments;
	}



	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public DiagnosticCenter getCenter() {
		return center;
	}

	public void setCenter(DiagnosticCenter center) {
		this.center = center;
	}

	public List<Appointment> getListOfAppointments() {
		return listOfAppointments;
	}

	public void setListOfAppointments(List<Appointment> listOfAppointments) {
		this.listOfAppointments = listOfAppointments;
	}



	@Override
	public String toString() {
		return "Test [testId=" + testId + ", testName=" + testName + "]";
	}

}