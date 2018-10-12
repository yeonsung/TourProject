<%@page import="java.util.ArrayList"%>
<%@page import="model.vo.MemberVO"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%

//String path1 = "C:/kyj/webpro2/eclipse/workspace/TourProject/WebContent/upload"; // 이미지가 저장될 주소1

String path2 = request.getSession().getServletContext().getRealPath("/") + File.separator +"upload"+File.separator+((MemberVO)(session.getAttribute("vo"))).getId();
File file = new File(path2);
if(!file.exists()){
	file.mkdirs();
} 

String path = request.getSession().getServletContext().getRealPath("/") + File.separator + "upload"+File.separator+((MemberVO)(session.getAttribute("vo"))).getId(); //즉시 새로고침되도록
//String path = request.getSession().getServletContext().getRealPath("/") + File.separator + "upload";
String filename = "";

if(request.getContentLength() > 10*1024*1024 ){
%>
	<script>alert("업로드 용량(총 10Mytes)을 초과하였습니다.");history.back();</script>
<%
	return;

} else {

	try {
		MultipartRequest multi=new MultipartRequest(request, path, 15*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
	
		java.text.SimpleDateFormat formatter2 = new java.text.SimpleDateFormat ("yyyy_MM_dd_HHmmss", java.util.Locale.KOREA);
		int cnt = 1;
		String upfile = (multi.getFilesystemName("Filedata"));
		if (!upfile.equals("")) {
			String dateString = formatter2.format(new java.util.Date()); // 날짜업데이트?
			String moveFileName = dateString + upfile.substring(upfile.lastIndexOf(".") ); //날짜시간.jpg
			String fileExt = upfile.substring(upfile.lastIndexOf(".") + 1); // jpg
			File sourceFile = new File(path + File.separator + upfile); //경로.실제파일명 
			File targetFile = new File(path + File.separator + moveFileName); //경로.날짜시간파일명
			sourceFile.renameTo(targetFile); // 경로.날짜시간파일명으로 이름바꾸기
			filename = moveFileName; // 날짜시간파일명 넣어주기.
			System.out.println("upfile : " + upfile);
			System.out.println("targetFile : " + targetFile);
			System.out.println("moveFileName : " + moveFileName);
			System.out.println("filename : " + filename);
			System.out.println("moveFileName : " + moveFileName);
			sourceFile.delete();
			
			
			
			%>
			<form id="fileform" name="fileform" method="post" action="write.do">
				<input type="hidden" name="filename" value="<%=filename%>">
				<input type="hidden" name="path" value="<%=path%>">
				<input type="hidden" name="fcode" value="<%=path%>">
			</form>
			<%
		}
	} catch (Exception e) {
		System.out.println("e : " + e.getMessage());
	}
}
%>

<script type="text/javascript">
	function fileAttach(){ 
		f = document.fileform;
		fpath = f.path.value;
	    fname = f.filename.value; 
	    fcode = fpath + fname;
	    
	   
	    try{
             window.opener.pasteHTML(fname); 
	    	
	    	 window.close();
	    }catch(e){ 
//             alert(e); 
	    }
	}
	fileAttach();
	this.window.close();
</script>

