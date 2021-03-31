package practice.spring.model.bbs;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

/**
 * Entity implementation class for Entity: BbsVO
 *
 */
@Entity

//BBS라는 테이블과 연동한다는것을 선언 (클래스 이름이 다르기때문에 선언이 필요함.)
@Table(name="BBS") 
public class BbsVO implements Serializable {

	// Id annotation: Id가 표기된 변수가 BBS 테이블의 primary key column이다.
	@Id
	//SequenceGenrator의 name과 GeneratedValue의 generator가 같아야함!! 만든것을 가져다가 사용하는것이기때문에
	@SequenceGenerator(name="BBSNO_SEQ", 
						sequenceName="SEQ_BBS", 
						allocationSize=1)
	@GeneratedValue(generator = "BBSNO_SEQ")
	private int bbsno;
	
	private String wname;
	
	private String title;
	
	private String content;
	
	private String passwd;
	
	private int viewcnt;
	
	@Temporal(TemporalType.DATE)
	private Date wdate = new Date();
	
	@SequenceGenerator(name="GRPNO_SEQ", 
						sequenceName="SEQ_BBS", 
						allocationSize=1)
	@GeneratedValue(generator = "GRPNO_SEQ")
	private int grpno;
	
	private int indent;
	
	private int ansnum;
	
	private String filename;
	
	private int filesize;
	
	@Transient
	private MultipartFile filenameMF;
	
	public BbsVO() {
		super();
	}

	public int getBbsno() {
		return bbsno;
	}

	public void setBbsno(int bbsno) {
		this.bbsno = bbsno;
	}

	public String getWname() {
		return wname;
	}

	public void setWname(String wname) {
		this.wname = wname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public int getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}

	public Date getWdate() {
		return wdate;
	}

	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}

	public int getGrpno() {
		return grpno;
	}

	public void setGrpno(int grpno) {
		this.grpno = grpno;
	}

	public int getIndent() {
		return indent;
	}

	public void setIndent(int indent) {
		this.indent = indent;
	}

	public int getAnsnum() {
		return ansnum;
	}

	public void setAnsnum(int ansnum) {
		this.ansnum = ansnum;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getFilesize() {
		return filesize;
	}

	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}

	public MultipartFile getFilenameMF() {
		return filenameMF;
	}

	public void setFilenameMF(MultipartFile filenameMF) {
		this.filenameMF = filenameMF;
	}

	@Override
	public String toString() {
		return "BbsVO [bbsno=" + bbsno + ", wname=" + wname + ", title=" + title + ", content=" + content + ", passwd="
				+ passwd + ", viewcnt=" + viewcnt + ", wdate=" + wdate + ", grpno=" + grpno + ", indent=" + indent
				+ ", ansnum=" + ansnum + ", filename=" + filename + ", filesize=" + filesize + ", filenameMF="
				+ filenameMF + "]";
	}
	
	
}
