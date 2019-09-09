package payroll;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="employee_id", unique=true, nullable=false)
	private Integer employeeId;
	
	@Column(name="employee_name", unique=false, nullable=false)
	private String name;
	
	@Column(name="employee_email", unique=false, nullable=false)
	private String email;
	
	@Column(name="employee_phone", unique=false, nullable=false)
	private Integer phone;
	
	@ManyToOne
	@JoinColumn(name="salary_id")
	private Salary salary;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="account_id")
	private Account account;
	
	@Column(name="employee_overpay", unique=false, nullable=false)
	private Double overpay;
	
	@Column(name="employee_is_active", unique=false, nullable=false)
	private Boolean isActive;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="employee")
	private Set<SalaryForMonth> salaryForMonth = new HashSet<SalaryForMonth>();

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Set<SalaryForMonth> getSalaryForMonth() {
		return salaryForMonth;
	}

	public void setSalaryForMonth(Set<SalaryForMonth> salaryForMonth) {
		this.salaryForMonth = salaryForMonth;
	}

	public Employee(String name, String email, Integer phone, Salary salary, Account account,
			Double overpay, Boolean isActive) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.salary = salary;
		this.account = account;
		this.overpay = overpay;
		this.isActive = isActive;
	}
	
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", salary=" + salary + ", account=" + account + ", overpay=" + overpay + ", isActive=" + isActive
				+ "]";
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer id) {
		this.employeeId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public Salary getSalary() {
		return salary;
	}

	public void setSalary(Salary salary) {
		this.salary = salary;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Double getOverpay() {
		return overpay;
	}

	public void setOverpay(Double overpay) {
		this.overpay = overpay;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	public SalaryForMonth getSalaryForMonth(Date date, String status) {
		Double base = overpay + salary.getBaseSalary();
		Double travel = base * salary.getTravelAllowance() / 100;
		Double food = base * salary.getFoodAllowance() / 100;
		Double insurance = base * salary.getInsurance() / 100;
		return new SalaryForMonth(date, this, base, travel, food, insurance, base + travel + food + insurance, status);
	}
	
	public SalaryForMonth getSalaryForMonth( Date date, String status, Integer days, Integer daysThisMonth ) {
		Double base = (overpay + salary.getBaseSalary()) * Double.valueOf(days) / daysThisMonth ;
		Double travel = base * salary.getTravelAllowance() / 100;
		Double food = base * salary.getFoodAllowance() / 100;
		Double insurance = base * salary.getInsurance() / 100;
		return new SalaryForMonth(date, this, base, travel, food, insurance, base + travel + food + insurance, status);
	}
	
}
