package AddressBook;


public class Location{
	private String province;
	private String city;
	private String county;
	private String town;
	private String block;
	private String door;
	private String detail;
	private String hard;
	public Location() { province="";city="";county="";town="";block="";door="";detail="";}
	public void SetHard(String ha) {this.hard=ha;}//设置难度
	public void SetProVinceCity(String s1,String s2,int num) {
		if(num==0||num==1||num==20||num==21||num==33||num==34||num==4||num==30||num==29||num==19||num==25)
			;
		else
			this.province=s1+"省";
		this.city=s2+"市";
	}//设置省和市；
	public String displayFIVE() {
		StringBuffer s=new StringBuffer();
		if(hard=="1")
		{
			s.append("\t\t"+"\"地区\":"+"[\n"+"\t\t\t"+"\""+province+"\",\n"+"\t\t\t"+"\""+city+"\",\n"+"\t\t\t"+"\""+county+"\",\n"+"\t\t\t"+"\""+town+"\",\n"+"\t\t\t"+"\""+detail+"\",\n"+"\t\t"+"]\n"+"\t}");
		}
		else if(hard=="2")
		{
			s.append("\t\t"+"\"地区\":"+"[\n"+"\t\t\t"+"\""+province+"\",\n"+"\t\t\t"+"\""+city+"\",\n"+"\t\t\t"+"\""+county+"\",\n"+"\t\t\t"+"\""+town+"\",\n"+"\t\t\t"+"\""+block+"\",\n"+"\t\t\t"+"\""+door+"\",\n"+"\t\t\t"+"\""+detail+"\",\n"+"\t\t"+"]\n"+"\t}");
		}
		return s.toString();
	}//输出
	public void SetFive(String s) {
		for(int i=0;i<s.length();i++)
		{
			if(s.charAt(i)=='县'||s.charAt(i)=='区'){
				if(county=="") {
					county=s.substring(0,i+1);
					s=s.replaceFirst(county,"");
					break;
				}
			}
		}
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)=='街'||s.charAt(i)=='镇'||s.charAt(i)=='乡') {
				if(s.charAt(i+1)=='道'||s.charAt(i+1)=='镇') {
					town=s.substring(0,i+2);
					s=s.replaceFirst(town, "");
					break;
				}
				else {
					town=s.substring(0,i+1);
					s=s.replaceFirst(town, "");
					break;
				}
			}
		}
		detail=s.substring(0,s.length()-1);
	}
	
	
	public int SetAll(String s,int x,int y) {
		int z=-1;
		String str;
		if(x!=AddressLibrary.provinces.length&&y!=AddressLibrary.citys[x].length)
		{
			for(int i=0;i<AddressLibrary.countys[x][y].length;i++) {
				str=AddressLibrary.countys[x][y][i];
				int result=s.indexOf(str);
				if(result!=-1)
				{
					county=str;
					s=s.replaceFirst(str, "");
					z=i;
					break;
				}
						
			}
			if(s.charAt(0)=='县')
			{
				s=s.replaceFirst("县", "");
				county=county+"县";
			}
			else if(s.charAt(0)=='区')
			{
				s=s.replaceFirst("区", "");
				county=county+"区";
			}
		}
		else
		{
			for(int i=0;i<s.length();i++){//县级
				if(s.charAt(i)=='县'||s.charAt(i)=='区'){
					if(county=="") {
						county=s.substring(0,i+1);
						s=s.replaceFirst(county,"");
						break;
					}
				}
			}
		}
		for(int i=0;i<s.length();i++) {//乡镇级
			if(s.charAt(i)=='街'||s.charAt(i)=='镇'||s.charAt(i)=='乡') {
				if(s.charAt(i+1)=='道'||s.charAt(i+1)=='镇') {
					town=s.substring(0,i+2);
					s=s.replaceFirst(town, "");
					break;
				}
				else {
					town=s.substring(0,i+1);
					s=s.replaceFirst(town, "");
					break;
				}
			}
		}
		for(int i=0;i<s.length();i++) {//路名
			if(s.charAt(i)=='路'||s.charAt(i)=='街') {
				block=s.substring(0,i+1);
				s=s.replaceFirst(block, "");
				break;
			}
		}
		for(int i=0;i<s.length();i++)
			if(s.charAt(i)=='号') {
				door=s.substring(0,i+1);
				s=s.replaceFirst(door, "");
				break;
			}
		if(s.length()!=0)
		detail=s.substring(0,s.length()-1);
		return z;
	}
	
	public void SetAllThree(String s,int x,int y) {
		int z=SetAll(s, x, y);
		if(province==""&&city!="")
			province=AddressLibrary.provinces[x];
		else if(city=="") {
			if(province!=""&&county!="")
			{
				for(int i=0;;i++)
				{
					if(AddressLibrary.countys[x][i][y]!=null)
					{
						if(AddressLibrary.countys[x][i][y]==county)
						{
							city=AddressLibrary.citys[x][i];
							break;
						}
					}
					if(i==30)
						break;
				}
			}
		}	
	}
}
