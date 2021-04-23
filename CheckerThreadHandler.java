package general;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.List;

import ContinuousChecks.CounterStrikePatchCheck;
import ContinuousChecks.LeaguePatchCheck;
import ContinuousChecks.MinecraftPatchCheck;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;

public class CheckerThreadHandler implements Runnable {
	JDA jda;
	TextChannel general;
	public static String minecraftPrevUrl;
	public static String csPrevHTMLLine;
	public CheckerThreadHandler(JDA jda) {
		this.jda = jda;
	}
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<TextChannel> channels = jda.getTextChannelsByName("game-updates", true);
		TextChannel general = channels.get(0);
		
		LeaguePatchCheck leagueChecker = new LeaguePatchCheck(general);
		MinecraftPatchCheck minecraftChecker = new MinecraftPatchCheck(general);
		CounterStrikePatchCheck csChecker = new CounterStrikePatchCheck(general);


		
		while(true) {
			
			try {
				getPreviousSites();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Thread leagueThread = new Thread(leagueChecker);
			Thread minecraftThread = new Thread(minecraftChecker);
			Thread csThread = new Thread(csChecker);
			
			leagueThread.start();
			minecraftThread.start();
			csThread.run();
			try {
				Thread.sleep(3600000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

	public static boolean leagueSiteExists(String Url) {
		URL patchUrl;
		try {
			patchUrl = new URL(Url);
			URLConnection con = patchUrl.openConnection();
			int contentLength = con.getContentLength();
			
			if(contentLength == -1) {
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
	public static boolean minecraftSiteExists(String Url) {
		URL patchUrl;
		try {
			patchUrl = new URL(Url);
			URLConnection con = patchUrl.openConnection();
			int contentLength = con.getContentLength();
			
			if(contentLength == -1) {
				return false;
			} else {
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	public void getPreviousSites() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("BotCheckData.txt")));
		String league;
		String minecraft;
		String cs;
		
		league = br.readLine();
		minecraft = br.readLine();
		cs = br.readLine();
		br.close();
		
		
		minecraftPrevUrl = minecraft.split(" ")[4];
		csPrevHTMLLine = cs.replace("CS Current HTML Line:", "");
		
	}
}
