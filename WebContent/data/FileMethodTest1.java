package file.method.step1.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * File class 의 method를 하나하나작성
 */
public class FileMethodTest1 {
	static {
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void main(String[] args) throws SQLException, IOException {
/*		File f = new File("C:\\yjk\\javaPro\\eclipse\\workspace\\java27_File\\src\\file\\method\\step1\\test\\FileMethodTest1.java");
	//	File f1 = new File("src\\file\\method\\step1\\test\\FileMethodTest1.java"); <-- path
		
		System.out.println(f.getName());
		System.out.println(f.getAbsolutePath());
		System.out.println(f.getPath());
		System.out.println(f.isFile());
		System.out.println(f.isDirectory());
		System.out.println("getParent : "+f.getParent());  //////////file의 method getParent() : return String......... 연속으로 못씀
														// 상위 디렉토리 유무 확인할때 쓰임 없으면 getParentFile()상태에서 .mkdirs() 하면 디렉토리 생성 ---> 확인,생성      후 createNewFile()
		
		String name = f.getName();
		System.out.println(name.substring(0, name.indexOf("."))); //확장자제외
		System.out.println(name.substring(name.indexOf(".")+1));//확장자만
*/		Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "tour", "oracle");
		PreparedStatement ps = null;
		
		ArrayList<File> list = new ArrayList<File>();
		list.add(new File("C:\\yjk\\webPro2\\eclipse\\workspace\\TourReview\\WebContent\\data\\강원도.txt"));
		list.add(new File("C:\\yjk\\webPro2\\eclipse\\workspace\\TourReview\\WebContent\\data\\경기도.txt"));
		list.add(new File("C:\\yjk\\webPro2\\eclipse\\workspace\\TourReview\\WebContent\\data\\경상남도.txt"));
		list.add(new File("C:\\yjk\\webPro2\\eclipse\\workspace\\TourReview\\WebContent\\data\\경상북도.txt"));
		list.add(new File("C:\\yjk\\webPro2\\eclipse\\workspace\\TourReview\\WebContent\\data\\광주.txt"));
		list.add(new File("C:\\yjk\\webPro2\\eclipse\\workspace\\TourReview\\WebContent\\data\\대구.txt"));
		list.add(new File("C:\\yjk\\webPro2\\eclipse\\workspace\\TourReview\\WebContent\\data\\대전.txt"));

		list.add(new File("C:\\yjk\\webPro2\\eclipse\\workspace\\TourReview\\WebContent\\data\\부산.txt"));
		list.add(new File("C:\\yjk\\webPro2\\eclipse\\workspace\\TourReview\\WebContent\\data\\서울.txt"));
		list.add(new File("C:\\yjk\\webPro2\\eclipse\\workspace\\TourReview\\WebContent\\data\\울산.txt"));
		list.add(new File("C:\\yjk\\webPro2\\eclipse\\workspace\\TourReview\\WebContent\\data\\인천.txt"));
		list.add(new File("C:\\yjk\\webPro2\\eclipse\\workspace\\TourReview\\WebContent\\data\\전라남도.txt"));
		list.add(new File("C:\\yjk\\webPro2\\eclipse\\workspace\\TourReview\\WebContent\\data\\전라북도.txt"));
		list.add(new File("C:\\yjk\\webPro2\\eclipse\\workspace\\TourReview\\WebContent\\data\\제주도.txt"));
		list.add(new File("C:\\yjk\\webPro2\\eclipse\\workspace\\TourReview\\WebContent\\data\\충청남도.txt"));
		list.add(new File("C:\\yjk\\webPro2\\eclipse\\workspace\\TourReview\\WebContent\\data\\충청북도.txt"));
		
		for(File f : list) {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String city;
			while(( city=br.readLine())!=null) {
		//		String city = br.readLine();
				ps =conn.prepareStatement("INSERT INTO location VALUES(?,?)");
				ps.setString(1, f.getName().replace(".txt", ""));
				ps.setString(2, city);
				ps.executeQuery();
				System.out.println("성공");
				}
		}
		/*System.out.println(f1.getPath());
		System.out.println(f1.getAbsolutePath());*/
		
		
	}

}
