package Commands;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class RecruitCommand extends ListenerAdapter{
	LinkedList<String> games = new LinkedList<String>(Arrays.asList(
			"league", "league of legends", "cs", "counterstrike", "csgo", "r6", "rainbow", "rainbow6", "siege", "counter", "skribbl.io", "skribbl", "overwatch", "gta", "gtav", "halo",
			"minecraft", "apex", "legends", "long live santa", "long", "for", "for honour", "path", "path of exile", "poe", "7 days to die", "7", "lol", "club", "penguin", "clubpenguin"
			));
	LinkedList<String> league = new LinkedList<String>(Arrays.asList(
			"league", "league of legends", "lol"
			));
	LinkedList<String> counterStrike = new LinkedList<String>(Arrays.asList(
			"cs", "csgo", "counter"
			));
	LinkedList<String> rainbow = new LinkedList<String>(Arrays.asList(
			"r6", "rainbow", "rainbow6", "siege"
			));
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		int maxParty = 0;
		String[] message = e.getMessage().getContentRaw().split(" ");
		Role legends = e.getGuild().getRoleById(699327868445917295l);
		Role cs = e.getGuild().getRoleById(699327450554957913l);
		Role r6 = e.getGuild().getRoleById(699329121809072170l);
		Role overwatch = e.getGuild().getRoleById(699329076963573801l);
		
		Role toCall = null;
		
		if(message.length > 1 && message[0].equals("$recruit")) {
			
			if(message[0].equalsIgnoreCase("$recruit") && games.contains(message[1].toLowerCase())) {
				int party = 0;
				if(league.contains(message[1].toLowerCase())) {
					List<VoiceChannel> channels = e.getGuild().getVoiceChannelsByName("League of Legends", true);
					List<Member> channelMembers = channels.get(0).getMembers();
					party = channelMembers.size();
					maxParty = 5; 
					toCall = legends;
					
				} else if(counterStrike.contains(message[1].toLowerCase())) {
					
					List<VoiceChannel> channels = e.getGuild().getVoiceChannelsByName("CSGO", true);
					List<Member> channelMembers = channels.get(0).getMembers();
					party = channelMembers.size();
					maxParty = 5;
					toCall = cs;
				} else if(rainbow.contains(message[1].toLowerCase())) {
					List<VoiceChannel> channels = e.getGuild().getVoiceChannelsByName("Rainbow Six", true);
					List<Member> channelMembers = channels.get(0).getMembers();
					party = channelMembers.size();
					maxParty = 5;
					toCall = r6;
				} else if(message[1].equalsIgnoreCase("overwatch")) {
					List<VoiceChannel> channels = e.getGuild().getVoiceChannelsByName("Overwatch", true);
					List<Member> channelMembers = channels.get(0).getMembers();
					party = channelMembers.size();
					maxParty = 6;
					toCall = overwatch;
				}
				if(maxParty > 0) {
					
					e.getChannel().sendMessage(toCall.getAsMention() + " Looking for " + Math.max((maxParty - party), 0) + " more people to join " + message[1]).queue();
				} else {
					e.getChannel().sendMessage("@everyone looking for more people to join: " + message[1]).queue();
				}
				
				
			} else {
				e.getChannel().sendMessage("Game not recognized. Please make sure there is a channel for the game you wish to play, and try again.").queue();
			}
		}
		else if (message[0].equalsIgnoreCase("$recruit")) {
			recruitHelp(e.getChannel());
		} 
		
		
	}
	public static void recruitHelp(TextChannel channel) {
		channel.sendMessage("Use command like so: \n$recruit [gameName], while in the desired voice channel").queue();
	}
}
