package ElliminateEmptyDefaultNull;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RemoveDefaultEmptyNullProperties {
	
		private String firstname;
		private String lastname;
		private int age;
		private double salary;
		private String location;
		private String[] courses;
		private List<String> list;
		private Map<String, String> members;
		
		
		
		public String getFirstname() {
			return firstname;
		}
		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}
		public String getLastname() {
			return lastname;
		}
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public double getSalary() {
			return salary;
		}
		public void setSalary(double salary) {
			this.salary = salary;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public String[] getCourses() {
			return courses;
		}
		public void setCourses(String[] courses) {
			this.courses = courses;
		}
		public List<String> getList() {
			return list;
		}
		public void setList(List<String> list) {
			this.list = list;
		}
		public Map<String, String> getMembers() {
			return members;
		}
		public void setMembers(Map<String, String> members) {
			this.members = members;
		}
		
		
		
	

}
