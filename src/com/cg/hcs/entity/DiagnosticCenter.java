package com.cg.hcs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.cg.hcs.utility.StringSequenceIdGenerator;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;

@Entity
@Table(
		uniqueConstraints= 
		@UniqueConstraint (columnNames={"centerName","contactNumber"})
		
		)

public class DiagnosticCenter 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "center_seq")
    @GenericGenerator(
        name = "center_seq", 
        strategy = "com.cg.hcs.utility.StringSequenceIdGenerator", 
        parameters = {
            @Parameter(name = StringSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = StringSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "C"),
            @Parameter(name = StringSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	@Column(name = "center_id")
	private String centerId;
	private String centerName;
	private String centerAddress;
	private Long contactNumber;
	@OneToMany(mappedBy = "center", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Test> listOfTests = new ArrayList<Test>();
	
	@OneToMany(mappedBy = "center", cascade = CascadeType.ALL)
	private List<Appointment> listOfApps = new ArrayList<Appointment>();
	
	
	
	public DiagnosticCenter() {
		super();
	}

	public DiagnosticCenter(String centerId,String purpose)
	{
		this.centerId = centerId;
	}
	
	public DiagnosticCenter(String centerName,String centerAddress,Long contactNumber) 
	{
		super();
		this.centerName = centerName;
		this.centerAddress = centerAddress;
		this.contactNumber = contactNumber;
		Test defaultTest1 = new Test("Blood Group",this);
		Test defaultTest2 = new Test("Blood Sugar",this);
		Test defaultTest3 = new Test("Blood Pressure",this);
		ArrayList<Test> listOfTests = new ArrayList<Test>();
		listOfTests.add(defaultTest1);
		listOfTests.add(defaultTest2);
		listOfTests.add(defaultTest3);
		
		this.listOfTests = listOfTests;
	}
	

	public String getCenterId() {
		return centerId;
	}
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public List<Test> getListOfTests() {
		return listOfTests;
	}
	public void setListOfTests(List<Test> listOfTests) {
		this.listOfTests = listOfTests;
	}
	
	public List<Appointment> getListOfApps() {
		return listOfApps;
	}

	public void setListOfApps(List<Appointment> listOfApps) {
		this.listOfApps = listOfApps;
	}

	public String getCenterAddress() {
		return centerAddress;
	}

	public void setCenterAddress(String centerAddress) {
		this.centerAddress = centerAddress;
	}

	public Long getContactNumber() {
		return contactNumber;
	}

	public void setContactNo(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Override
	public String toString() {
		return "DiagnosticCenter [centerId=" + centerId + ", centerName=" + centerName + ", centerAddress=" + centerAddress
				+ ", contactNumber=" + contactNumber +  "]";
	}

	
	
	
}