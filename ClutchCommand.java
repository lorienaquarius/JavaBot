package Commands;

import java.util.Currency;
import java.util.List;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ClutchCommand extends ListenerAdapter{
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		String message[] = e.getMessage().getContentRaw().split(" ");
		
		if(message[0].equalsIgnoreCase("$Clutch") && message.length < 2) {
			Member clutch = e.getMember();
			List<Member> chatter = clutch.getVoiceState().getChannel().getMembers();
			
			for(Member i : chatter) {
				if(!(i == clutch)) {
					i.mute(true).queue();
				}
				
			}
		}
	}
	public static void ClutchHelp(TextChannel textChannel) {
		textChannel.sendMessage("Use command like so: \n$clutch\n$clutch @User").queue();
	}
}
