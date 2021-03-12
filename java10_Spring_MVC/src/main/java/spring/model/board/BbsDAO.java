package spring.model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import spring.utility.board.DBClose;
import spring.utility.board.DBOpen;

@Repository
public class BbsDAO {
	
	public boolean checkRefnum(int bbsno) {
		boolean flag = false;
		Connection con = DBOpen.open();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    StringBuffer sql = new StringBuffer();
	    sql.append(" SELECT count(*) FROM bbs");
	    sql.append(" WHERE refnum = ? ");
	    
	    try {
	        pstmt = con.prepareStatement(sql.toString());
	        pstmt.setInt(1, bbsno);
	        
	        rs = pstmt.executeQuery();
	        rs.next();
	        int cnt = rs.getInt(1);
	        
	        if(cnt>0) flag = true; //부모글 존재한면 true

	      } catch (SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	      } finally {
	        DBClose.close(con, pstmt, rs);
	      }
	    return flag;
	}
	
  public void upAnsnum(Map map) {
    Connection con = DBOpen.open();
    PreparedStatement pstmt = null;
    StringBuffer sql = new StringBuffer();
    int grpno = (Integer) map.get("grpno");
    int ansnum = (Integer) map.get("ansnum");
    sql.append(" update bbs ");
    sql.append(" set ansnum = ansnum + 1 ");
    sql.append(" where grpno = ?  ");
    sql.append(" and ansnum > ?  ");

    try {
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, grpno);
      pstmt.setInt(2, ansnum);

      pstmt.executeUpdate();

    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      DBClose.close(con, pstmt);
    }

  }

  public boolean createReply(BbsDTO dto) {
    boolean flag = false;
    Connection con = DBOpen.open();
    PreparedStatement pstmt = null;
    StringBuffer sql = new StringBuffer();
    sql.append(" INSERT INTO bbs(bbsno, wname, title, ");
    sql.append(" content, passwd, wdate, grpno,   ");
    sql.append(" indent, ansnum, refnum, filename, filesize)  "); //부모글 등록시와는 다르게 indent, ansnum도 값을 넣는다.
    sql.append(" VALUES( ");
    sql.append(" (SELECT NVL(MAX(bbsno), 0)+1 FROM bbs),");
    sql.append(" ?, ?, ?, ?, sysdate, ");
    sql.append(" ?, ?, ? ,?,?,?) ");

    try {
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, dto.getWname());
      pstmt.setString(2, dto.getTitle());
      pstmt.setString(3, dto.getContent());
      pstmt.setString(4, dto.getPasswd());
      pstmt.setInt(5, dto.getGrpno());// ★부모의grpno
      pstmt.setInt(6, dto.getIndent() + 1);// ★부모의indent+1
      pstmt.setInt(7, dto.getAnsnum() + 1);// ★부모의ansnum+1
      pstmt.setInt(8, dto.getBbsno());
      pstmt.setString(9, dto.getFilename());
      pstmt.setInt(10, dto.getFilesize());

      int cnt = pstmt.executeUpdate();

      if (cnt > 0)
        flag = true;

    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      DBClose.close(con, pstmt);
    }

    return flag;
  }

  public BbsDTO readReply(int bbsno) {
    BbsDTO dto = null;
    Connection con = DBOpen.open();
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    StringBuffer sql = new StringBuffer();
    sql.append(" select bbsno, grpno, indent, ansnum, title ");
    sql.append(" from bbs ");
    sql.append(" where bbsno = ? ");

    try {
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, bbsno);

      rs = pstmt.executeQuery();

      if (rs.next()) {
        dto = new BbsDTO();
        dto.setBbsno(rs.getInt("bbsno"));
        dto.setGrpno(rs.getInt("grpno"));
        dto.setIndent(rs.getInt("indent"));
        dto.setAnsnum(rs.getInt("ansnum"));
        dto.setTitle(rs.getString("title"));
      }

    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      DBClose.close(con, pstmt, rs);
    }

    return dto;
  }

  public boolean delete(int bbsno) {
    boolean flag = false;
    Connection con = DBOpen.open();
    PreparedStatement pstmt = null;
    StringBuffer sql = new StringBuffer();
    sql.append(" delete from bbs ");
    sql.append(" where bbsno = ? ");

    try {
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, bbsno);

      int cnt = pstmt.executeUpdate();
      if (cnt > 0)
        flag = true;

    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      DBClose.close(con, pstmt);
    }

    return flag;
  }

  public boolean passCheck(Map map) {
    boolean flag = false;
    Connection con = DBOpen.open();
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int bbsno = (Integer) map.get("bbsno");
    String passwd = (String) map.get("passwd");

    StringBuffer sql = new StringBuffer();
    sql.append(" SELECT COUNT(bbsno) as cnt  ");
    sql.append(" FROM bbs  ");
    sql.append(" WHERE bbsno=? AND passwd=? ");

    try {
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, bbsno);
      pstmt.setString(2, passwd);

      rs = pstmt.executeQuery();
      rs.next();
      int cnt = rs.getInt("cnt");

      if (cnt > 0)
        flag = true; // 올바른 패스워드

    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      DBClose.close(con, pstmt, rs);
    }

    return flag;
  }

  public boolean update(BbsDTO dto) {
    boolean flag = false;
    Connection con = DBOpen.open();
    PreparedStatement pstmt = null;
    StringBuffer sql = new StringBuffer();
    sql.append(" UPDATE bbs  ");
    sql.append(" SET         ");
    sql.append("      wname   = ?,  ");
    sql.append("      title   = ?,  ");
    sql.append("      content = ?  ");
    if(dto.getFilesize()>0) {
    	sql.append(" , filename=?,");
    	sql.append("   filesize=? ");
    }
    sql.append(" WHERE bbsno  = ?  ");

    try {
      int i=0; //if문이 실행 될때, 안될대 가만하기위해 index를 i로 대채한다. (++i로 일씩 증가.)
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(++i, dto.getWname());
      pstmt.setString(++i, dto.getTitle());
      pstmt.setString(++i, dto.getContent());
      if(dto.getFilesize()>0) {
    	  pstmt.setString(++i, dto.getFilename());
    	  pstmt.setInt(++i, dto.getFilesize());
      }
      pstmt.setInt(++i, dto.getBbsno());

      int cnt = pstmt.executeUpdate();
      if (cnt > 0)
        flag = true;

    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      DBClose.close(con, pstmt);
    }

    return flag;
  }

  public BbsDTO read(int bbsno) {
    BbsDTO dto = null;
    Connection con = DBOpen.open();
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    StringBuffer sql = new StringBuffer();
    sql.append(" SELECT bbsno, wname, title, content,  viewcnt, ");
    sql.append(" to_char(wdate,'yyyy-mm-dd') wdate, filename ");
    sql.append(" FROM bbs   ");
    sql.append(" WHERE bbsno = ?  ");

    try {
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, bbsno);

      rs = pstmt.executeQuery();

      if (rs.next()) {
        dto = new BbsDTO();
        dto.setBbsno(rs.getInt("bbsno"));
        dto.setWname(rs.getString("wname"));
        dto.setTitle(rs.getString("title"));
        dto.setContent(rs.getString("content"));
        dto.setViewcnt(rs.getInt("viewcnt"));
        dto.setWdate(rs.getString("wdate"));
        dto.setFilename(rs.getString("filename"));
      }

    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      DBClose.close(con, pstmt, rs);
    }

    return dto;
  }

  public void upViewcnt(int bbsno) {
    Connection con = DBOpen.open();
    PreparedStatement pstmt = null;
    StringBuffer sql = new StringBuffer();
    sql.append(" update bbs ");
    sql.append(" set viewcnt = viewcnt + 1 ");
    sql.append(" where bbsno = ? ");

    try {
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, bbsno);

      pstmt.executeUpdate();

    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      DBClose.close(con, pstmt);
    }

  }

  public int total(String col, String word) {
    int total = 0;
    Connection con = DBOpen.open();
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    StringBuffer sql = new StringBuffer();
    sql.append(" select count(*) ");
    sql.append(" from bbs ");

    if (word.trim().length() > 0 && col.equals("title_content")) {
      sql.append(" where title like '%'||?||'%' ");
      sql.append(" or content like '%'||?||'%' ");
    } else if (word.trim().length() > 0) {
      sql.append(" where " + col + " like '%'||?||'%' ");
    }
    try {
      pstmt = con.prepareStatement(sql.toString());
      if (word.trim().length() > 0 && col.equals("title_content")) {
        pstmt.setString(1, word);
        pstmt.setString(2, word);
      } else if (word.trim().length() > 0) {
        pstmt.setString(1, word);
      }
      rs = pstmt.executeQuery();
      if (rs.next()) {
        total = rs.getInt(1);
      }

    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      DBClose.close(con, pstmt, rs);
    }

    return total;
  }

  public List<BbsDTO> list(Map<String, Object> map) {
    List<BbsDTO> list = new ArrayList<BbsDTO>();
    Connection con = DBOpen.open();
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    String col = (String) map.get("col");
    String word = (String) map.get("word");
    int sno = (Integer) map.get("sno");
    int eno = (Integer) map.get("eno");
    StringBuffer sql = new StringBuffer();
    sql.append(" SELECT bbsno, wname, title, viewcnt, wdate, ");
    sql.append(" grpno, indent, ansnum, r, filename  ");
    sql.append(" FROM(    ");
    sql.append("    SELECT bbsno, wname, title, viewcnt, wdate, ");
    sql.append("    grpno, indent, ansnum,rownum r, filename   ");
    sql.append("    FROM(    ");
    sql.append("       SELECT bbsno, wname, title, viewcnt, wdate ");
    sql.append("      ,grpno,indent,ansnum, filename ");
    sql.append("       FROM bbs   ");
    if (word.trim().length() > 0 && col.equals("title_content")) {
      sql.append("     where title like '%'||?||'%' ");
      sql.append("     or content like '%'||?||'%' ");
    } else if (word.trim().length() > 0) {
      sql.append("     where " + col + " like '%'||?||'%' ");
    }

    sql.append("     order by grpno desc, ansnum ASC ");
    sql.append("  )  ");
    sql.append(" )WHERE r>=? and r<=?  ");
    try {
      pstmt = con.prepareStatement(sql.toString());
      int i = 0;
      if (word.trim().length() > 0 && col.equals("title_content")) {
        pstmt.setString(++i, word);
        pstmt.setString(++i, word);
      } else if (word.trim().length() > 0) {
        pstmt.setString(++i, word);
      }
      pstmt.setInt(++i, sno);
      pstmt.setInt(++i, eno);
      
      rs = pstmt.executeQuery();

      while (rs.next()) {
        BbsDTO dto = new BbsDTO();
        dto.setBbsno(rs.getInt("bbsno"));
        dto.setWname(rs.getString("wname"));
        dto.setTitle(rs.getString("title"));
        dto.setWdate(rs.getString("wdate"));
        dto.setGrpno(rs.getInt("grpno"));
        dto.setIndent(rs.getInt("indent"));
        dto.setAnsnum(rs.getInt("ansnum"));
        dto.setFilename(rs.getString("filename"));

        list.add(dto);
      }

    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      DBClose.close(con, pstmt, rs);
    }

    return list;
  }

//  public List<BbsDTO> list(Map map) {
//    List<BbsDTO> list = new ArrayList<BbsDTO>();
//    Connection con = DBOpen.open();
//    PreparedStatement pstmt = null;
//    ResultSet rs = null;
//
//    String col = (String) map.get("col");
//    String word = (String) map.get("word");
//
//    StringBuffer sql = new StringBuffer();
//    sql.append(" SELECT bbsno, wname, title,  grpno, indent, ansnum ");
//    sql.append(" FROM bbs   ");
//
//    if (word.trim().length() > 0 && col.equals("title_content")) {
//      sql.append(" where title like '%'||?||'%' ");
//      sql.append(" or content like '%'||?||'%' ");
//    } else if (word.trim().length() > 0) {
//      sql.append(" where " + col + " like '%'||?||'%' ");
//    }
//
//    sql.append(" order by grpno desc, ansnum ASC ");
//
//    try {
//      pstmt = con.prepareStatement(sql.toString());
//
//      if (word.trim().length() > 0 && col.equals("title_content")) {
//        pstmt.setString(1, word);
//        pstmt.setString(2, word);
//      } else if (word.trim().length() > 0) {
//        pstmt.setString(1, word);
//      }
//
//      rs = pstmt.executeQuery();
//
//      while (rs.next()) {
//        BbsDTO dto = new BbsDTO();
//        dto.setBbsno(rs.getInt("bbsno"));
//        dto.setWname(rs.getString("wname"));
//        dto.setTitle(rs.getString("title"));
//        dto.setGrpno(rs.getInt("grpno"));
//        dto.setIndent(rs.getInt("indent"));
//        dto.setAnsnum(rs.getInt("ansnum"));
//
//        list.add(dto);
//      }
//
//    } catch (SQLException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    } finally {
//      DBClose.close(con, pstmt, rs);
//    }
//
//    return list;
//  }

//  public List<BbsDTO> list() {
//    List<BbsDTO> list = new ArrayList<BbsDTO>();
//    Connection con = DBOpen.open();
//    PreparedStatement pstmt = null;
//    ResultSet rs = null;
//
//    StringBuffer sql = new StringBuffer();
//    sql.append(" SELECT bbsno, wname, title,  grpno, indent, ansnum ");
//    sql.append(" FROM bbs   ");
//    sql.append(" order by bbsno desc ");
//
//    try {
//      pstmt = con.prepareStatement(sql.toString());
//
//      rs = pstmt.executeQuery();
//
//      while (rs.next()) {
//        BbsDTO dto = new BbsDTO();
//        dto.setBbsno(rs.getInt("bbsno"));
//        dto.setWname(rs.getString("wname"));
//        dto.setTitle(rs.getString("title"));
//        dto.setGrpno(rs.getInt("grpno"));
//        dto.setIndent(rs.getInt("indent"));
//        dto.setAnsnum(rs.getInt("ansnum"));
//
//        list.add(dto);
//      }
//
//    } catch (SQLException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    } finally {
//      DBClose.close(con, pstmt, rs);
//    }
//
//    return list;
//  }

  public boolean create(BbsDTO dto) {
    boolean flag = false;
    Connection con = DBOpen.open();
    PreparedStatement pstmt = null;
    StringBuffer sql = new StringBuffer();
    sql.append(" INSERT INTO bbs(bbsno, wname, title, ");
    sql.append(" content, passwd, wdate, grpno, filename, filesize)   ");
    sql.append(" VALUES( ");
    sql.append(" (SELECT NVL(MAX(bbsno), 0)+1 FROM bbs),");
    sql.append(" ?, ?, ?, ?, sysdate, ");
    sql.append(" (SELECT NVL(MAX(grpno), 0)+1 FROM bbs) ,?,?)");

    try {
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, dto.getWname());
      pstmt.setString(2, dto.getTitle());
      pstmt.setString(3, dto.getContent());
      pstmt.setString(4, dto.getPasswd());
      pstmt.setString(5, dto.getFilename());
      pstmt.setInt(6, dto.getFilesize());

      int cnt = pstmt.executeUpdate();
      //executeUpdate(): insert, edit, delete된 갯수를 return해줌.

      if (cnt > 0)
        flag = true;

    } catch (SQLException e) {

      e.printStackTrace();
    } finally {
      DBClose.close(con, pstmt);
    }

    return flag;
  }
}