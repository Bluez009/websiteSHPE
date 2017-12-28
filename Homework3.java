import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
//Erik Bracamonte - 111230826 - CSE114
public class Homework3 {
// Used Markdown dingus website as a reference
	public static void main(String[] args) throws Exception {
			Scanner input = new Scanner(System.in);
			System.out.println("Enter the name of your file");
			String fileName = input.nextLine();
			System.out.println("Create name for output file with extension:");
	    	String fileName2 = input.nextLine();
	        File outputFile = new File(fileName2);		//create file2 to store the converted text
			PrintWriter pw2 = new PrintWriter(outputFile);
			FileReader fileReader = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fileReader);
			String whole = readFile(fileName, br);
			System.out.println("\n------------------------------\n"
					+ "New Output file '" + fileName2 + "' created with the converted text below."
							+ "\n------------------------------");
		//Adding Header and Footer to string
			String header = "<!DOCTYPE html>\n<html>\n<head>\n<title>Results of Markdown Translation</title>\n</head>\n<body>\n";
			String footer = "</body>\n</html>";
			whole = header + whole + footer;
			whole = translateMarkdown2(whole);
		    System.out.print(whole);
		//Add contents displayed to output file
	        pw2.println(whole);
	        pw2.close();
	}

	public static String readFile(String fileName, BufferedReader br) throws IOException {
		String line = fileName;
		String whole = "";
		while ((line = br.readLine()) != null) {
			whole += translateMarkdown(line);	    
			whole += "\n";
			}
		br.close();
		return whole;
	}
	
	public static String translateMarkdown(String line) {
    //(1)translateEmphasis
	   	line = line.replaceAll("\\*\\*([^ ].*?[^ ])\\*\\*", "<strong>$1</strong>");		
	//(2)translateStrongEmphasis
	   	line = line.replaceAll("\\*([^ ].*?[^ ])\\*", "<em>$1</em>");	
   	//(5)translateCode
    	line = line.replaceAll("`(.*?)`", "<code>$1</code>");							    	
    	line = line.replaceAll("<code>\\s+(.*?)\\s+</code>","<code>$1</code>");	//used to remove leftover spaces
    //(4)translateImage
	   	line = line.replaceAll("!\\[(.*2?)\\]\\((.*1?)\\“(.*3?)\\”\\)", "<img src=\"$2\" alt=\"$1\" title=\"$3\">");
	   	line = line.replaceAll("src=\"\\s+(.*?)\\s+\"", "src=\"$1\""); 
	   	line = line.replaceAll("src=\"(.*?)\\s+\"", "src=\"$1\"");
    	line = line.replaceAll("src=\"\\s+(.*?)\"", "src=\"$1\"");
    //(3)translateLinks
	   	line = line.replaceAll("\\[(.*1?)\\]\\((.*2?)\\)", "<a href=\"$2\">$1</a>");
	   	line = line.replaceAll("href=\"\\s+(.*?)\\s+\">", "href=\"$1\">");
	   	line = line.replaceAll("href=\"\\s+(.*?)\">", "href=\"$1\">");
	   	line = line.replaceAll("href=\"(.*?)\\s\">", "href=\"$1\">");
   	//(6)translateLists
	   	line = line.replaceAll("\\+\\s(.*?)$", "<li>$1</li>");
	//cut spaces at beginning and end
	   	line = line.trim();
	   	return line;
    }
	
	public static String translateMarkdown2(String whole) {
	//Paragraphs
		whole = whole.replaceAll("<body>\n(.*?)", "<body>\n<p>$1");
		whole = whole.replaceAll("<p>\n(.*?)", "<p>$1");
		whole = whole.replaceAll("\n\n","</p>\n\n<p>");
		whole = whole.replaceAll("<p></p>", "");
		whole = whole.replaceAll("\n(.*?)\n</body>", "\n$1</p>\n</body>");
		whole = whole.replace("<p></body>", "</body>");
	//Lists
		whole = whole.replace("<p><li>", "<ul>\n<li>");
		whole = whole.replace("</li></p>", "</li>\n</ul>");
	    whole = whole.replaceAll("<p></p>", "");
		return whole;
	}
}
