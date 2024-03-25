package auca.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="student_registration")
public class StudentRegistration {

	
	@Id
	@Column(name="student_registration_id")
	private int studentRegistrationId;
	
	@OneToOne
	@JoinColumn(name="academic_unit")
	private AcademicUnit department_id;

	
	public StudentRegistration() {
		super();
	}

	public StudentRegistration(int studentRegistrationId, AcademicUnit department_id) {
		super();
		this.studentRegistrationId = studentRegistrationId;
		this.department_id = department_id;
	}

	public int getStudentRegistrationId() {
		return studentRegistrationId;
	}

	public void setStudentRegistrationId(int studentRegistrationId) {
		this.studentRegistrationId = studentRegistrationId;
	}

	public AcademicUnit getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(AcademicUnit department_id) {
		this.department_id = department_id;
	}
	
	
	
}
