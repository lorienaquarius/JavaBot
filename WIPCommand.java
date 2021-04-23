package Commands;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.*;
import java.io.*;
public class WIPCommand extends ListenerAdapter{
	
	final String WIP_FILE = "WIP.txt";
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		String[] message = e.getMessage().getContentRaw().split(" ");
		if(message[0].equalsIgnoreCase("$WIP")) {
			if(message.length > 1) {
				switch(message[1]) {
				case "get":
					try {
						String line;
						String sendMessage = "";
						BufferedReader br = new BufferedReader(new FileReader(new File(WIP_FILE)));
						while((line = br.readLine()) != null) {
							sendMessage += line;
						}
						br.close();
						if(sendMessage.length() != 0) {
							System.out.println(sendMessage.length());
							e.getChannel().sendMessage(sendMessage).queue();
						} else {
							e.getChannel().sendMessage("Nothing in work in progress").queue();
						}
						
						break;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				case "add":
					try {
						FileWriter fr = new FileWriter(new File(WIP_FILE), true);
						
						for(int i = 2; i < message.length; i++) {
							fr.append(message[i] + " ");
						}
						fr.append("." + System.lineSeparator());
						fr.close();
						
						e.getChannel().sendMessage("Work in progress idea sent!").queue();
						break;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch(IndexOutOfBoundsException f) {
						WIPHelp(e.getChannel());
						break;
					}
				default:
					WIPHelp(e.getChannel());
					break;
				}
			}
		}
	}
	public static void WIPHelp(TextChannel channel) {
		channel.sendMessage("Use command like so: \n$WIP get \n$WIP add [suggestion]").queue();
	}

}
