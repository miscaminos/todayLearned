package com.study.utility;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import com.study.contents.Contents;
import com.study.contents.ReplyMapper;


public class Utility {
	
	public static int rcount(int contentsno, ReplyMapper rmapper) {
		 return rmapper.rcount(contentsno);
	}
	public static String rpaging(int total, int nowPage, int recordPerPage, String col, String word, String url,
			int nPage, int contentsno) {
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
			str.append("<li><a href='" + url + "?col=" + col + "&word=" + word + "&nowPage=" + nowPage + "&contentsno="
					+ contentsno + "&nPage=" + _nowPage + "'>이전</A></li>");
		}

		for (int i = startPage; i <= endPage; i++) {
			if (i > totalPage) {
				break;
			}

			if (nPage == i) {
				str.append("<li class='active'><a href=#>" + i + "</a></li>");
			} else {
				str.append("<li><a href='" + url + "?col=" + col + "&word=" + word + "&nowPage=" + nowPage + "&contentsno="
						+ contentsno + "&nPage=" + i + "'>" + i + "</A></li>");
			}
		}

		_nowPage = (nowGrp * pagePerBlock) + 1; // 10개 다음 페이지로 이동
		if (nowGrp < totalGrp) {
			str.append("<li><A href='" + url + "?col=" + col + "&word=" + word + "&nowPage=" + nowPage + "&contentsno="
					+ contentsno + "&nPage=" + _nowPage + "'>다음</A></li>");
		}
		str.append("</ul>");
		str.append("</div>");

		return str.toString();

	}

   
	/**
	 * 업로드된 파일을 원하는 위치로 이동
	 * 
	 * @param mf
	 * @param basePath
	 * @return
	 */
	public static String saveFileSpring(MultipartFile mf, String basePath) {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		String filename = "";
		long filesize = mf.getSize();
		String originalFilename = mf.getOriginalFilename();
		try {
			if (filesize > 0) { // 파일이 존재한다면
				// 인풋 스트림을 얻는다.
				inputStream = mf.getInputStream();

				File oldfile = new File(basePath, originalFilename);

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
				// make server full path to save
				String serverFullPath = basePath + "\\" + filename;

				System.out.println("fileName: " + filename);
				System.out.println("serverFullPath: " + serverFullPath);

				outputStream = new FileOutputStream(serverFullPath);

				// 버퍼를 만든다.
				int readBytes = 0;
				byte[] buffer = new byte[8192];

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

	public static void deleteFile(String upDir, String oldfile) {
		
		File file = new File(upDir, oldfile);
		if (file.exists())
			file.delete();

	}

	public static synchronized boolean isImage(String file) {
		boolean sw = false;
		if (file != null) {
			file = file.toLowerCase();
			if (file.endsWith("jpg") || file.endsWith(".jpeg") || file.endsWith(".png") || file.endsWith("gif")) {
				sw = true;
			}
		}
		return sw;
	}

	public static synchronized String preview(String upDir, String _src, int width, int height) {
		int RATIO = 0;
		int SAME = -1;

		File src = new File(upDir + "/" + _src);
		String srcname = src.getName();

		String _dest = srcname.substring(0, srcname.indexOf("."));

		File dest = new File(upDir + "/" + _dest + "_t.jpg");

		Image srcImg = null;

		String name = src.getName().toLowerCase();

		if (name.endsWith("jpg") || name.endsWith("bmp") || name.endsWith("png") || name.endsWith("gif")) {
			try {
				srcImg = ImageIO.read(src);
				int srcWidth = srcImg.getWidth(null);
				int srcHeight = srcImg.getHeight(null);
				int destWidth = -1, destHeight = -1;

				if (width == SAME) {
					destWidth = srcWidth;
				} else if (width > 0) {
					destWidth = width;
				}

				if (height == SAME) {
					destHeight = srcHeight;
				} else if (height > 0) {
					destHeight = height;
				}
				if (width == RATIO && height == RATIO) {
					destWidth = srcWidth;
					destHeight = srcHeight;
				} else if (width == RATIO) {
					double ratio = ((double) destHeight) / ((double) srcHeight);
					destWidth = (int) ((double) srcWidth * ratio);
				} else if (height == RATIO) {
					double ratio = ((double) destWidth) / ((double) srcWidth);
					destHeight = (int) ((double) srcHeight * ratio);
				}

				Image imgTarget = srcImg.getScaledInstance(destWidth, destHeight, Image.SCALE_SMOOTH);
				int pixels[] = new int[destWidth * destHeight];
				PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, destWidth, destHeight, pixels, 0, destWidth);

				pg.grabPixels();

				BufferedImage destImg = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
				destImg.setRGB(0, 0, destWidth, destHeight, pixels, 0, destWidth);

				ImageIO.write(destImg, "jpg", dest);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return dest.getName();
	}

	/**
	 * byte 수를 전달받아 자료의 단위를 적용합니다.
	 * 
	 * @param size
	 * @return 1000 → 1000 Byte
	 */
	public static synchronized String unit(long size) {
		String str = "";

		if (size < 1024) { // 1 KB 이하
			str = size + " Byte";
		} else if (size < 1024 * 1024) { // 1 MB 이하
			str = (int) (Math.ceil(size / 1024.0)) + " KB"; // 1048576 보다 작으면 KB
		} else if (size < 1024 * 1024 * 1024) { // 1 GB 이하
			str = (int) (Math.ceil(size / 1024.0 / 1024.0)) + " MB";
		} else if (size < 1024L * 1024 * 1024 * 1024) { // 1 TB 이하
			str = (int) (Math.ceil(size / 1024.0 / 1024.0 / 1024.0)) + " GB";
		} else if (size < 1024L * 1024 * 1024 * 1024 * 1024) { // 1 PT 이하
			str = (int) (Math.ceil(size / 1024.0 / 1024.0 / 1024.0 / 1024.0)) + " TB";
		} else if (size < 1024L * 1024 * 1024 * 1024 * 1024 * 1024) { // 1 EX 이하
			str = (int) (Math.ceil(size / 1024.0 / 1024.0 / 1024.0 / 1024.0 / 1024.0)) + " PT";
		}

		return str;
	}

	public static String pagingBox(String listFile, int cateno, int totalRecord, int nowPage, String word) {
		int pagePerBlock = 5; // 블럭당 페이지 수
		int totalPage = (int) (Math.ceil((double) totalRecord / Contents.RECORD_PER_PAGE)); // 전체 페이지
		int totalGrp = (int) (Math.ceil((double) totalPage / pagePerBlock));// 전체 그룹
		int nowGrp = (int) (Math.ceil((double) nowPage / pagePerBlock)); // 현재 그룹
		int startPage = ((nowGrp - 1) * pagePerBlock) + 1; // 특정 그룹의 페이지 목록 시작
		int endPage = (nowGrp * pagePerBlock); // 특정 그룹의 페이지 목록 종료

		StringBuffer str = new StringBuffer();
		str.append("<div style='text-align:center'>");
		str.append("<ul class='pagination'> ");
		int _nowPage = (nowGrp - 1) * pagePerBlock; // 10개 이전 페이지로 이동
		if (nowGrp >= 2) {
			str.append("<li><a href='" + listFile + "?cateno=" + cateno + "&word=" + word + "&nowPage=" + _nowPage
					+ "'>이전</A></li>");
		}

		for (int i = startPage; i <= endPage; i++) {
			if (i > totalPage) {
				break;
			}

			if (nowPage == i) {
				str.append("<li class='active'><a href=#>" + i + "</a></li>");
			} else {
				str.append("<li><a href='" + listFile + "?cateno=" + cateno + "&word=" + word + "&nowPage=" + i + "'>"
						+ i + "</A></li>");
			}
		}

		_nowPage = (nowGrp * pagePerBlock) + 1; // 10개 다음 페이지로 이동
		if (nowGrp < totalGrp) {
			str.append("<li><A href='" + listFile + "?cateno=" + cateno + "&word=" + word + "&nowPage=" + _nowPage
					+ "'>다음</A></li>");
		}
		str.append("</ul>");
		str.append("</div>");

		return str.toString();
	}

}