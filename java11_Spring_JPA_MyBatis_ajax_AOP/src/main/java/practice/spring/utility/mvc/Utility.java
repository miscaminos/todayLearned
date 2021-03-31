package spring.utility.webtest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import spring.model.reply.ReplyMapper;

public class Utility {

	/**오늘,어제,그제 날짜 가져오기
	 * @return List- 날짜들 저장 SimpleDateFormat("yyyy-MM-dd")
	 */
	public static List<String> getDay() {
		List<String> list = new ArrayList<String>();

		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		for (int j = 0; j < 3; j++) {
			list.add(sd.format(cal.getTime()));
			cal.add(Calendar.DATE, -1);
		}

		return list;
	}

	/**등록날짜와 오늘,어제,그제날짜와 비교
	 * @param wdate - 등록날짜
	 * @return - 	  true:오늘,어제,그제중 등록날짜와 같음 false:오늘,어제,그제 날짜가 등록날짜와 다 다름
	 */
	public static boolean compareDay(String wdate) {
		boolean flag = false;
		List<String> list = getDay();
		if (wdate.equals(list.get(0)) || wdate.equals(list.get(1)) || wdate.equals(list.get(2))) {
			flag = true;
		}
		return flag;
	}
	/**null여부 체크
	 * @param str - 체크할 문자열
	 * @return - str이 null이라면 빈 문자열을 반환, null이 아니면 str자체를 반환
	 */
	public static String checkNull(String str) {
		if (str == null) {
			str = "";
		}
		return str;
	}

	/**게시판 글 페이징
	 * @param totalRecord - 전체 레코드수
	 * @param nowPage  -    현재 페이지
	 * @param recordPerPage-페이지당 레코드 수
	 * @param col  -        검색 컬럼
	 * @param word  -       검색어
	 * @return 페이징 생성 문자열 (html로 작성됨)
	 */
	public static String paging(int totalRecord, int nowPage, int recordPerPage, String col, String word) {
		int pagePerBlock = 5; // 블럭당 페이지 수
		int totalPage = (int) (Math.ceil((double) totalRecord / recordPerPage)); // 전체 페이지
		int totalGrp = (int) (Math.ceil((double) totalPage / pagePerBlock));// 전체 그룹
		int nowGrp = (int) (Math.ceil((double) nowPage / pagePerBlock)); // 현재 그룹
		int startPage = ((nowGrp - 1) * pagePerBlock) + 1; // 특정 그룹의 페이지 목록 시작
		int endPage = (nowGrp * pagePerBlock); // 특정 그룹의 페이지 목록 종료

		StringBuffer str = new StringBuffer();
		str.append("<div style='text-align:center'>");
		str.append("<ul class='pagination'> ");
		int _nowPage = (nowGrp - 1) * pagePerBlock; // 10개 이전 페이지로 이동
		if (nowGrp >= 2) {
			str.append("<li><a href='./list?col=" + col + "&word=" + word + "&nowPage=" + _nowPage + "'>이전</A></li>");
		}

		for (int i = startPage; i <= endPage; i++) {
			if (i > totalPage) {
				break;
			}

			if (nowPage == i) {
				str.append("<li class='active'><a href=#>" + i + "</a></li>");
			} else {
				str.append(
						"<li><a href='./list?col=" + col + "&word=" + word + "&nowPage=" + i + "'>" + i + "</A></li>");
			}
		}
		_nowPage = (nowGrp * pagePerBlock) + 1; // 10개 다음 페이지로 이동
		if (nowGrp < totalGrp) {
			str.append("<li><A href='./list?col=" + col + "&word=" + word + "&nowPage=" + _nowPage + "'>다음</A></li>");
		}
		str.append("</ul>");
		str.append("</div>");

		return str.toString();
	}

	/**파일 업로드하기
	 * @param mf   -      업로드할 파일
	 * @param basePath -  업로드할 storage 경로
	 * @return - 		  새롭게 storage에 저장한 파일이름
	 */
	public static String saveFileSpring(MultipartFile mf, String basePath) {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		String filename = "";

		// mf 매개변수로 filesize를 부를수도있다.
		long filesize = mf.getSize();

		String originalFilename = mf.getOriginalFilename();
		try {
			if (filesize > 0) { // 파일이 존재한다면(filesize>0)
				// 인풋 스트림을 얻는다.
				inputStream = mf.getInputStream();

				// 현재 서버에 파일 시스템을 가르킴.
				File oldfile = new File(basePath, originalFilename);
				// 동일 이름의 파일이(a.txt) 기존 파일 시스템에 존재하면 덮어쓰기 하지않고, (0)a.txt, (1)a.txt, etc... 생성
				if (oldfile.exists()) {
					for (int k = 0; true; k++) {
						// 파일명 중복을 피하기 위한 일련 번호를 생성하여
						// 파일명으로 조합
						oldfile = new File(basePath, "(" + k + ")" + originalFilename);

						// 조합된 파일명이 존재하지 않는다면, 일련번호가
						// 붙은 파일명 다시 생성
						if (!oldfile.exists()) { // 존재하지 않는 경우
							filename = "(" + k + ")" + originalFilename;
							break;
						}
					}
				} else {
					filename = originalFilename;
				}
				// make server full path to save (storage폴더의 경로. 거기에 저장하기위해)
				String serverFullPath = basePath + "\\" + filename;

				System.out.println("fileName: " + filename);
				System.out.println("serverFullPath: " + serverFullPath);

				// storage안으로 파일을 가져다 놓기위해 파일 내용을 outputStream으로 쓰는것임.
				outputStream = new FileOutputStream(serverFullPath);

				// 버퍼를 만든다.
				int readBytes = 0;
				byte[] buffer = new byte[8192]; // 8192=2^13

				while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {
					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return filename;
	}

	/**파일 삭제하기(update시 사용)
	 * @param upDir         업로드할 경로
	 * @param oldfile       이미 존재하는 파일 이름
	 */
	public static void deleteFile(String upDir, String oldfile) {
		File file = new File(upDir, oldfile);
		if (file.exists())
			file.delete();

	}

	/**댓글 paging 
	 * @param total  -       해당 bbsno 글의 전체 댓글수
	 * @param nowPage -      현재 댓글 페이지
	 * @param recordPerPage -페이지당 댓글 수
	 * @param col      -     검색 컬럼
	 * @param word     -     검색어
	 * @return -페이징 생성 문자열 (html로 작성됨)
	 */
	public static String rpaging(int total, int nowPage, int recordPerPage, String col, String word, String url,
			int nPage, int bbsno) {
		int pagePerBlock = 5; // 블럭당 페이지 수
		int totalPage = (int) (Math.ceil((double) total / recordPerPage)); // 전체 페이지
		int totalGrp = (int) (Math.ceil((double) totalPage / pagePerBlock));// 전체 그룹
		int nowGrp = (int) (Math.ceil((double) nPage / pagePerBlock)); // 현재 그룹
		int startPage = ((nowGrp - 1) * pagePerBlock) + 1; // 특정 그룹의 페이지 목록 시작
		int endPage = (nowGrp * pagePerBlock); // 특정 그룹의 페이지 목록 종료

		StringBuffer str = new StringBuffer();
		str.append("<div style='text-align:center'>");
		str.append("<ul class='pagination'> ");
		int _nowPage = (nowGrp - 1) * pagePerBlock; // 10개 이전 페이지로 이동

		if (nowGrp >= 2) {
			str.append("<li><a href='" + url + "?col=" + col + "&word=" + word + "&nowPage=" + nowPage + "&bbsno="
					+ bbsno + "&nPage=" + _nowPage + "'>이전</A></li>");
		}

		for (int i = startPage; i <= endPage; i++) {
			if (i > totalPage) {
				break;
			}

			if (nPage == i) {
				str.append("<li class='active'><a href=#>" + i + "</a></li>");
			} else {
				str.append("<li><a href='" + url + "?col=" + col + "&word=" + word + "&nowPage=" + nowPage + "&bbsno="
						+ bbsno + "&nPage=" + i + "'>" + i + "</A></li>");
			}
		}

		_nowPage = (nowGrp * pagePerBlock) + 1; // 10개 다음 페이지로 이동
		if (nowGrp < totalGrp) {
			str.append("<li><A href='" + url + "?col=" + col + "&word=" + word + "&nowPage=" + nowPage + "&bbsno="
					+ bbsno + "&nPage=" + _nowPage + "'>다음</A></li>");
		}
		str.append("</ul>");
		str.append("</div>");

		return str.toString();

	}

	/**댓글 수 가져오기 (el로 사용하려면 static이여야 함)
	 * @param bbsno  -  해당 bbsno
	 * @param rmapper - (mybatis용) reply DB table과 연결해주는 ReplyMapper 인터페이스 타입 객체
	 * @return  -	전체 댓글 갯수
	 */
	public static int rcount(int bbsno, ReplyMapper rmapper) {
		return rmapper.rcount(bbsno);
	}

	/**직업코드에 해당하는 직업명을 반환해주기
	 * @param job  -  member/createForm.jsp에서 선택되었던 직업 code
	 * @return  -	  직업명
	 */
	public static String getCodeValue(String code) {
		String jobname = null;
		Map<String, String> codes = new HashMap<String, String>();
		
		codes.put("A01", "회사원");
		codes.put("A02", "전산관련직");
		codes.put("A03", "연구전문직");
		codes.put("A04", "각종학교학생");
		codes.put("A05", "일반자영업");
		codes.put("A06", "공무원");
		codes.put("A07", "의료인");
		codes.put("A08", "법조인");
		codes.put("A09", "종교/언론/예술인");
		codes.put("A10", "기타");
		
		jobname = codes.get(code);
		return jobname;
		
	}
}