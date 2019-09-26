package AddressBook;
import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.io.*;
import java.util.Scanner;


public class Main {

	public static String readFile(String x) {
        String pathname = x;
        String result="[\n";
        try (InputStreamReader ipr = new InputStreamReader(new FileInputStream(pathname),Charset.forName("UTF-8"));
        		BufferedReader br = new BufferedReader(ipr); // 建立一个对象，它把文件内容转成计算机能读懂的语言
        ) {
            String line;
            int i=0;
            while ((line = br.readLine()) != null) {
            	if(i!=0)
            		result=result+",\n";
            	i++;
            	result=result+workout(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        result=result+"\n]";
        return result;
    }
	public static void writeFile(String x,String x2) {
        try {
            File writeName = new File(x2);
            writeName.createNewFile(); 
            try (FileOutputStream writerStream = new FileOutputStream(writeName); 
            		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8")); ) 
            {
                writer.write(x);
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	public static String workout(String str) {
		String result="";
		String[] re=str.split("!|,",3);
		Person someone=new Person();
		someone.Setname(re[1]);
		int i=0,j=0;
		for(i=0;i<re[2].length();i++)
		{
			char a=re[2].charAt(i);
			if(a>'0'&&a<='9')
			{
				char b=re[2].charAt(i+10);
				if(b>='0'&&b<='9')
				{
					someone.Setnumber(re[2].substring(i,i+10));
					re[2]=re[2].replaceFirst(re[2].substring(i,i+11),"");
					break;
				}
			}
		}
		for(i=0;i<AddressLibrary.provinces.length;i++) 
		{
			String Province=AddressLibrary.provinces[i];
			int result1=re[2].indexOf(Province);
			if(result1!=-1)
			{
				for(j=0;j<AddressLibrary.citys[i].length;j++) {
					String city=AddressLibrary.citys[i][j];
					int result2=re[2].indexOf(city);
					if(result2!=-1)
					{
						someone.address.SetProVinceCity(Province,city,i);
						re[2]=re[2].replaceFirst(Province,"");
						if(re[2].charAt(0)=='省')
							re[2]=re[2].replaceFirst("省","");
						re[2]=re[2].replaceFirst(city,"");
						if(re[2].charAt(0)=='市')
							re[2]=re[2].replaceFirst("市","");
						break;
					}
					else if(j==AddressLibrary.citys[i].length) {
						someone.address.SetProVinceCity(Province,null,i);
						re[2]=re[2].replaceFirst(Province,"");
						if(re[2].charAt(0)=='省')
							re[2]=re[2].replaceFirst("省","");
					}
				}
				break;
			}
		}
		
		if(re[0].charAt(0)=='1'){
			someone.address.SetHard("1");
			someone.address.SetFive(re[2]);
		}
		else if(re[0].charAt(0)=='2'){
			someone.address.SetHard("2");
			someone.address.SetAll(re[2],i,j);
		}
		else {
			someone.address.SetHard("3");
			someone.address.SetAllThree(re[2],i,j);
		}
		result=result+someone.display();
		return result;
	}
	
	
	
	public static void main(String[] args) {
		String str=readFile(args[0]);
		writeFile(str, args[1]);
		return;
	}
}
