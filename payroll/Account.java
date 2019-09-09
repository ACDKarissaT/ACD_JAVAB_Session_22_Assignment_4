package payroll;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private Integer accountId;
	
	@Column(name="type", unique=false, nullable=false)
	private String type;
	
	@Column(name="bank_name", unique=false, nullable=false)
	private String bankName;
	
	@Column(name="account_number", unique=false, nullable=false)
	private Integer accountNumber;
	
	@Column(name="branch_code", unique=false, nullable=false)
	private String branchCode;
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	@OneToOne(mappedBy="account")
	private Employee employee;

	public Account(String type, String bankName, Integer accountNumber, String branchCode,
			Employee employee) {
		super();
		this.accountId = accountId;
		this.type = type;
		this.bankName = bankName;
		this.accountNumber = accountNumber;
		this.branchCode = branchCode;
		this.employee = employee;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", type=" + type + ", bankName=" + bankName + ", accountNumber="
				+ accountNumber + ", branchCode=" + branchCode + ", employee=" + employee + "]";
	}
}
