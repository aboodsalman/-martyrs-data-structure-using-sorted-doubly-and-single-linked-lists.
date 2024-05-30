package Phase1;

public class Martyr {
	String name, date;
	int age;
	String gender, district, location;
	boolean flag;
	
	
	public Martyr(String name, String date, int age, String gender, String district, String location) {
		super();
		this.name = name;
		this.date = date;
		this.age = age;
		this.gender = gender;
		this.district = district;
		this.location = location;
		flag=false;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		if(gender.equals("M")) return "name: " + name + ", date: " + date + ", age: " + age + ", gender: Male";
		else return "name: " + name + ", date: " + date + ", age: " + age + ", gender: Female";
	}
	// data of a format for writing to the file
	public String getData() {
		return name+","+date+","+age+","+location+","+district+","+gender.charAt(0);
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
}

