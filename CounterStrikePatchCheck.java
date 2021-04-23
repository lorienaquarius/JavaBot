package ContinuousChecks;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import general.CheckerThreadHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

public class CounterStrikePatchCheck implements Runnable{
	
	TextChannel textChannel;
	public CounterStrikePatchCheck(TextChannel textChannel) {
		this.textChannel = textChannel;
	}
	
	public static final File READ_FILE = new File("BotCheckData.txt");
	public static final String BLOG_URL = "https://blog.counter-strike.net/index.php/category/updates/";
	public static String baseURL = "https://blog.counter-strike.net/index.php/";
	public static final String IMAGE_URL = "https://games.mxdwn.com/wp-content/uploads/2020/02/csgo-1.jpg";
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			URL url = new URL(BLOG_URL);
			URLConnection con = url.openConnection();
			
			baseURL = formatBase(baseURL);
			String newUrl = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			Boolean checker = true;
			String analysis = CheckerThreadHandler.csPrevHTMLLine;
			
			while((line = br.readLine()) != null && checker) {
				if(line.contains(baseURL)) {
					analysis = line;
					checker = false;
				}
			}
			
			if(!analysis.trim().equals(CheckerThreadHandler.csPrevHTMLLine.trim())) {
				newUrl = analysis.substring(analysis.indexOf("\"") + 1, analysis.lastIndexOf("\""));
				sendEmbed(newUrl);
				CheckerThreadHandler.csPrevHTMLLine = analysis;
				updateFile(analysis);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
	public void sendEmbed(String url) {
		EmbedBuilder embed = new EmbedBuilder();
		
		embed.setTitle("New CS:GO update! click here for notes", url);
		embed.setImage(IMAGE_URL);
		
		embed.setColor(Color.decode("#ffa500"));
		embed.appendDescription("new CS:GO patch");
		
		textChannel.sendMessage(embed.build()).queue();
	}
	public String formatBase(String base) {
		LocalDate today = LocalDate.now();
		int month = today.getMonthValue();
		int year = today.getYear();
		String formatMonth;
		
		if(month < 10) {
			formatMonth = "0" + month;	
		} else {
			formatMonth = "" + month;
		}
		return base + year + "/" + formatMonth + "/";
	}
	
	public void updateFile(String url) {
		List<String> curContents = new LinkedList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(READ_FILE));
			String Line;
			while((Line = reader.readLine()) != null) {
				curContents.add(Line);
			}
			reader.close();
			curContents.set(2, "CS Current HTML Line: " + url);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			FileWriter writer = new FileWriter(READ_FILE);
			for(String i : curContents) {
				writer.write(i + System.lineSeparator());
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
