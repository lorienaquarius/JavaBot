package ContinuousChecks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import general.CheckerThreadHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

public class MinecraftPatchCheck implements Runnable{
	final String baseURL = "https://www.minecraft.net/en-us/article/minecraft-snapshot-";
	final String imageURL = "https://upload.wikimedia.org/wikipedia/en/thumb/5/51/Minecraft_cover.png/220px-Minecraft_cover.png";
	
	final File READ_FILE = new File("BotCheckData.txt");
	public TextChannel textChannel;
	
	public MinecraftPatchCheck(TextChannel textchannel) {
		this.textChannel = textchannel;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String myURL;
		List<String> Urls = new LinkedList<String>();
		Urls.add(formatURL(baseURL, "a"));
		Urls.add(formatURL(baseURL, "b"));
		Urls.add(formatURL(baseURL, "c"));
		
		
		for(String i : Urls) {
			if(!CheckerThreadHandler.minecraftPrevUrl.equals(i) && CheckerThreadHandler.minecraftSiteExists(i)) {
				sendEmbed(i);
				CheckerThreadHandler.minecraftPrevUrl = i;
				updateFile(i);
				break;
			}
			
		}
		return;
	}
	
	
	public String formatURL(String baseURL, String iteration) {
		LocalDate today = LocalDate.now();
		int year = today.getYear() - 2000;
		int day = today.getDayOfYear();
		int week = (int)(day / 7 + 1);
		String formatWeek;
		
		if(week < 10) {
			formatWeek = "0" + week;
		} else {
			formatWeek = "" + week;
		}
		
		return baseURL + year + "w" + formatWeek + iteration;
	}
	public void sendEmbed(String URL) {
		LocalDate today = LocalDate.now();
		int year = today.getYear() - 2000;
		int day = today.getDayOfYear();
		int week = (int)(day / 7 + 1);
		String formatWeek;
		
		if(week < 10) {
			formatWeek = "0" + week;
		} else {
			formatWeek = "" + week;
		}
		
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("New Minecraft update! click here for the notes", URL);
		embed.setImage(imageURL);
		embed.appendDescription("New Minecraft snapshot " + year + "w" + formatWeek + URL.charAt(URL.length() - 1));
		textChannel.sendMessage(embed.build()).queue();
		
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
			curContents.set(1, "Minecraft Current Patch URL: " + url);
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

