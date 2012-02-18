package mainDoc;
import generics.Document;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBop {
	public static void ranking(int genre_id,String book,String author,float rank,int rankers) throws ClassNotFoundException, SQLException{
		ConnDB.ConnDb();
		Statement stmt=ConnDB.con.createStatement();
		String query="Insert into ranking(book_name,author,rank,raters,dir_id) values('"+book+"','"+author+"',"+rank+","+rankers+","+genre_id+")";
		stmt.executeUpdate(query);
		stmt.close();
        ConnDB.con.close();
	}
	
	public static void truncate_all() throws ClassNotFoundException, SQLException
	{
		String[] sql = {"TRUNCATE binary_pos;", "TRUNCATE binary_words;","TRUNCATE book_master;","TRUNCATE lex_binary;",
				"TRUNCATE lex_quadary;","TRUNCATE lex_tertiary;","TRUNCATE lex_unary;","TRUNCATE quadary_pos;",
				"TRUNCATE quadary_words;","TRUNCATE syn_binary;","TRUNCATE syn_quadary;","TRUNCATE syn_tertiary;",
				"TRUNCATE syn_unary;","TRUNCATE tertiary_pos;","TRUNCATE tertiary_words;","TRUNCATE unary_pos;",
				"TRUNCATE unary_words;","TRUNCATE lexical;","TRUNCATE syntactic;"};
		ConnDB.ConnDb();
		Statement stmt= ConnDB.con.createStatement();
		int r=0;
		for(int i=0;i<sql.length;i++)
			r = stmt.executeUpdate(sql[i]);
		if(r == 0)
			System.out.println();
		stmt.close();
        ConnDB.con.close();
	}
	public static void insert_lex(int id,Document doc) throws ClassNotFoundException, SQLException{
        ConnDB.ConnDb();
        Statement stmt= ConnDB.con.createStatement();
        String sql="Insert into lexical values('"+id+"',"+doc.countCharacters+","+doc.countParagraphs+
        		","+doc.flesch+","+doc.stopWords+","+doc.countSyllable+","+doc.countWords+","+doc.countSentences+
        		","+doc.countSentences1+")";
        stmt.executeUpdate(sql);
    }
    public static void insert_syn(int id,Document doc) throws ClassNotFoundException, SQLException{
        ConnDB.ConnDb();
        Statement stmt= ConnDB.con.createStatement();
        String sql="Insert into syntactic values('"+id+"',"+doc.coarsePOSNoun+","+doc.coarsePOSPronoun+","+
        		doc.coarsePOSVerb+","+doc.coarsePOSPrep+","+doc.coarsePOSAdverb+","+doc.coarsePOSAdjective+
        		","+doc.countPunc+","+doc.foreignWords+")";
        stmt.executeUpdate(sql);
        stmt.close();
        ConnDB.con.close();
    }
    public static void insert_sem(int id,Document doc) throws ClassNotFoundException, SQLException{
        ConnDB.ConnDb();
        Statement stmt= ConnDB.con.createStatement();
        String sql="Insert into semantic values('"+id+"',"+doc.countNP+","+doc.countVP+","+doc.countAdjP+
        		","+doc.countCP+","+doc.avgScore+","+doc.countDepth+","+doc.countNPCohesion+","+doc.countWordCohesion+")";
        stmt.executeUpdate(sql);
        stmt.close();
        ConnDB.con.close();
    }
    public static void insert_punc(int id,Document doc) throws ClassNotFoundException, SQLException{
        ConnDB.ConnDb();
        Statement stmt= ConnDB.con.createStatement();
        String sql="Insert into punc values('"+id+"',"+doc.pun.comma+","+doc.pun.doubleQuotes+","+
        		doc.pun.singleQuotes+","+doc.pun.exclamation+","+doc.pun.questionM+","+doc.pun.ellipsis+
        		","+doc.pun.colon+","+doc.pun.fullStop+","+doc.pun.semiColon+")";
        
        stmt.executeUpdate(sql);
        stmt.close();
        ConnDB.con.close();
    }
      
    
	public static int create_uid(String element,String filename) throws SQLException, ClassNotFoundException{
		int id;
		String process=null;
		ConnDB.ConnDb();
		Statement stmt= ConnDB.con.createStatement();
		if(element.equals("dir"))
			process="dir";
		else if(element.equals("files"))
			process="book";
		String sql="Select id from "+ process+"_master where "+ process +"_name='"+filename+"'";
		ResultSet rs=stmt.executeQuery(sql);
		if(!rs.next()){
			sql="Insert into "+process+"_master(" + process +"_name) values('"+filename+"')";
			stmt.executeUpdate(sql);
			}
		 {	sql="Select id from "+process+"_master where "+process+"_name='"+filename+"'";
			rs=stmt.executeQuery(sql);rs.next();
			 id=rs.getInt(1);
				System.out.println("UID ="+id);
				}
		 stmt.close();
		 rs.close();
		 ConnDB.con.close();
		return(id);
		}
	
}