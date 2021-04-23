package general;
import java.util.List;

import Commands.BanishCommand;
import Commands.ClutchCommand;
import Commands.HelpCommand;
import Commands.InfoCommand;
import Commands.OtherClutchCommand;
import Commands.RecruitCommand;
import Commands.RestoreCommand;
import Commands.WIPCommand;
import Events.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;

public class ListenerThreadHandler implements Runnable{
	public static Storage storage = new Storage();
	JDA jda;
	public ListenerThreadHandler(JDA jda) {
		this.jda = jda;
	}
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long ID = 372867498258726913l;
		
		/***********************************/
		/***********Random Events***********/
		/***********************************/
		
		jda.addEventListener(new HelloEvent());
		jda.addEventListener(new CensorEvent());
		jda.addEventListener(new CrodieEvent());
		jda.addEventListener(new JsutEvent());
		jda.addEventListener(new DadJokeEvent());
		jda.addEventListener(new ForbiddenEvent());
		jda.addEventListener(new JoinEvent());
		
		/***********************************/
		/**************Commands*************/
		/***********************************/
		
		jda.addEventListener(new RecruitCommand());
		jda.addEventListener(new BanishCommand());
		jda.addEventListener(new RestoreCommand());
		jda.addEventListener(new HelpCommand());
		jda.addEventListener(new WIPCommand());
		jda.addEventListener(new ClutchCommand());
		jda.addEventListener(new OtherClutchCommand());
		jda.addEventListener(new InfoCommand());

		//Test Server
		//jda.getGuildById(372867498258726913l).getTextChannelById(372867498812637186l).sendMessage("Hey all! JsutBot is now online!").queue();
		
		//Jsut Gang
		//jda.getGuildById(209128805418139648l).getTextChannelById(209128805418139648l).sendMessage("Hey all! JsutBot is now online!").queue();
		
		
	}

}
