package ContinuousChecks;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.net.*;
import java.util.*;

import general.CheckerThreadHandler;

import java.io.*;
public class LeaguePatchCheck implements Runnable {
	
	final String basePatchURL = "https://na.leagueoflegends.com/en-us/news/game-updates/patch-";
	final String LEAGUE_IMAGE = "https://cdn1.dotesports.com/wp-content/uploads/2019/09/12195522/league-of-legends.jpg";
	ClassLoader classLoader = ClassLoader.getSystemClassLoader();
	final File READ_FILE = new File("BotCheckData.txt");
	TextChannel textChannel;
	
	public LeaguePatchCheck(TextChannel textChannel) {
		this.textChannel = textChannel;
	}
	
	public void run(){
		// TODO Auto-generated method stub
		String toCheck = "";
		int year = 0;
		int patch = 0;
		
			try {
				BufferedReader br = new BufferedReader(new FileReader(READ_FILE));
				String[] readLine = br.readLine().split(" ");
				br.close();
				year = Integer.parseInt(readLine[readLine.length - 2]);
				patch = Integer.parseInt(readLine[readLine.length - 1]);
				toCheck = formatPatchUrl(basePatchURL, year, patch + 1);
				
				
			} catch(IOException e) {
				System.out.println("File not named properly, check spelling");
				e.printStackTrace();
			}

			if(CheckerThreadHandler.leagueSiteExists(toCheck)) {
				//Need to create an embed

				
				updateFile(READ_FILE, year, patch, toCheck);
				sendEmbed(toCheck, year, patch + 1);
				
			} else{
				toCheck = formatPatchUrl(basePatchURL, year + 1, 1);
				if(CheckerThreadHandler.leagueSiteExists(toCheck)) {
					updateFile(READ_FILE, year + 1, 1, toCheck);
					sendEmbed(toCheck, year + 1, 1);
				}
			}
			return;
		
	}
	
	public String formatPatchUrl(String baseURL, int year, int patch) {
		
		return baseURL + year + "-" + patch + "-notes/";
		
	}

	public void updateFile(File fileLocation,  int year, int patch, String url) {
		List<String> curContents = new LinkedList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(READ_FILE));
			String Line;
			while((Line = reader.readLine()) != null) {
				curContents.add(Line);
			}
			reader.close();
			curContents.set(0, "League Current Patch URL: " + url + " " + year + " " + (patch + 1));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			FileWriter writer = new FileWriter(fileLocation);
			for(String i : curContents) {
				writer.write(i + System.lineSeparator());
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void sendEmbed(String url, int year, int patch) {
		EmbedBuilder embed = new EmbedBuilder();
		embed.setImage(LEAGUE_IMAGE);
		embed.setTitle("New league patch! Click here for notes", url);
		embed.appendDescription("League has been updated to " + year + "." + patch);
		textChannel.sendMessage(embed.build()).queue();
	}

}