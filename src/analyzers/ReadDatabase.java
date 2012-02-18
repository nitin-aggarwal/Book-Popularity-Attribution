package analyzers;

import generics.Document;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import mainDoc.ConnDB;

public class ReadDatabase {

	public Document readLexical(Document doc) throws SQLException, ClassNotFoundException
	{
		ConnDB.ConnDb();
		Statement stmt= ConnDB.con.createStatement();
		
		String sql="Select * from lexical where id = '"+doc.title+"'";
		ResultSet rs=stmt.executeQuery(sql);
		if(rs.next())
		{	
			doc.countCharacters = (long)rs.getFloat(2);
			doc.countParagraphs = (long)rs.getFloat(3);
			doc.flesch = rs.getFloat(4);
			doc.stopWords = (long)rs.getFloat(5);
			doc.countSyllable = (long)rs.getFloat(6);
			doc.countWords = (long)rs.getFloat(7);
			doc.countSentences = (long)rs.getFloat(8);
			doc.countSentences1 = (long)rs.getFloat(9);
		}
		
		stmt.close();
		rs.close();
		ConnDB.con.close();
		
		return doc;
	}
	
	public Document readSyntactic(Document doc) throws SQLException, ClassNotFoundException
	{
		ConnDB.ConnDb();
		Statement stmt= ConnDB.con.createStatement();
		
		String sql="Select * from syntactic where id = '"+doc.title+"'";
		ResultSet rs=stmt.executeQuery(sql);
		if(rs.next())
		{	
			doc.coarsePOSNoun = (long)rs.getFloat(2);
			doc.coarsePOSPronoun = (long)rs.getFloat(3);
			doc.coarsePOSVerb = (long)rs.getFloat(4);
			doc.coarsePOSPrep = (long)rs.getFloat(5);
			doc.coarsePOSAdverb = (long)rs.getFloat(6);
			doc.coarsePOSAdjective = (long)rs.getFloat(7);
			doc.countPunc = (long)rs.getFloat(8);
			doc.foreignWords = (long)rs.getFloat(9);
		}
		
		stmt.close();
		rs.close();
		ConnDB.con.close();
		
		return doc;
	}
	
	public Document readPunc(Document doc) throws SQLException, ClassNotFoundException
	{
		ConnDB.ConnDb();
		Statement stmt= ConnDB.con.createStatement();
		
		String sql="Select * from punc where id = '"+doc.title+"'";
		ResultSet rs=stmt.executeQuery(sql);
		if(rs.next())
		{	
			doc.pun.comma = (long)rs.getFloat(2);
			doc.pun.doubleQuotes = (long)rs.getFloat(3);
			doc.pun.singleQuotes = (long)rs.getFloat(4);
			doc.pun.exclamation = (long)rs.getFloat(5);
			doc.pun.questionM = (long)rs.getFloat(6);
			doc.pun.ellipsis = (long)rs.getFloat(7);
			doc.pun.colon = (long)rs.getFloat(8);
			doc.pun.fullStop = (long)rs.getFloat(9);
			doc.pun.semiColon = (long)rs.getFloat(10);
		}
		
		stmt.close();
		rs.close();
		ConnDB.con.close();
		
		return doc;
	}
	
	public Document readSemantic(Document doc) throws SQLException, ClassNotFoundException
	{
		ConnDB.ConnDb();
		Statement stmt= ConnDB.con.createStatement();
		
		String sql="Select * from semantic where id = '"+doc.title+"'";
		ResultSet rs=stmt.executeQuery(sql);
		if(rs.next())
		{	
			doc.countNP = rs.getFloat(2);
			doc.countVP = rs.getFloat(3);
			doc.countAdjP = rs.getFloat(4);
			doc.countCP = rs.getFloat(5);
			doc.avgScore = rs.getFloat(6);
			doc.countDepth = rs.getFloat(7);
			doc.countNPCohesion = (long)rs.getFloat(8);
			doc.countWordCohesion = (long)rs.getFloat(9);
		}
		
		stmt.close();
		rs.close();
		ConnDB.con.close();
		
		return doc;
	}
	
	public Document readRating(Document doc) throws SQLException, ClassNotFoundException
	{
		ConnDB.ConnDb();
		Statement stmt= ConnDB.con.createStatement();
		
		String sql="Select rank from ranking where id = '"+doc.title+"'";
		ResultSet rs=stmt.executeQuery(sql);
		if(rs.next())
		{	
			doc.rating = rs.getFloat(1);
		}
		
		stmt.close();
		rs.close();
		ConnDB.con.close();
		
		return doc;
	}
}
