package MockAPisAndSerial_Deserial;

import java.util.List;
import java.util.Map;

public class PojoClass {

    private boolean booleanValue;
	private List<Integer> array;
	private String color;
	private int number;
	private String string;
	private Map<String,String> map;
	private PojoClass1 object;
	private List<PojoClass2> arrayObj;
	
	
	public boolean isBooleanValue() {
		return booleanValue;
	}
	public void setBooleanValue(boolean booleanValue) {
		this.booleanValue = booleanValue;
	}
	public List<Integer> getArray() {
		return array;
	}
	public void setArray(List<Integer> array) {
		this.array = array;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}
	public Map<String, String> getMap() {
		return map;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	public PojoClass1 getObject() {
		return object;
	}
	public void setObject(PojoClass1 object) {
		this.object = object;
	}
	public List<PojoClass2> getArrayObj() {
		return arrayObj;
	}
	public void setArrayObj(List<PojoClass2> arrayObj) {
		this.arrayObj = arrayObj;
	}
	
	
	
}
