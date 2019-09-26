package AddressBook;

public class Person {
	
	private String name;
	private String number;
	public Location address=new Location();
	public void Setname(String s) {name=s;}
	public void Setnumber(String s) {number=s;}
	public String display() {
		StringBuffer result=new StringBuffer();
		result.append("\t{\n"+"\t\t"+"\"姓名\":"+name+",\n"+"\t\t"+"\"电话号码\":"+number+",\n");
		String result2= address.displayFIVE();
		return result.toString()+result2;
	}
}
