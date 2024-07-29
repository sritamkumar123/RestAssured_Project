package ComplexObjectToString;

import java.util.List;

public class PojoParentObjectClass {
	
	private String companyname;
	private String street;
	private String city;
	private String state;
	private int pin;
	private List<String> bankaccount;
	private List<Pojo2ndLevelObjectClass> employees;
	
	
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public List<String> getBankaccount() {
		return bankaccount;
	}
	public void setBankaccount(List<String> bankaccount) {
		this.bankaccount = bankaccount;
	}
	public List<Pojo2ndLevelObjectClass> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Pojo2ndLevelObjectClass> employees) {
		this.employees = employees;
	}
	
    
	
	
}
