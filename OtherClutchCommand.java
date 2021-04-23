package Commands;

import java.util.List;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class OtherClutchCommand extends ListenerAdapter{
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		String[] message = e.getMessage().getContentRaw().split(" ");
		
		if(message[0].equalsIgnoreCase("$clutch") && message.length > 1) {
			Member clutcher = e.getMessage().getMentionedMembers().get(0);
			Member friend = e.getGuild().getMemberById(e.getAuthor().getId());
			
			List<Member> toMute = friend.getVoiceState().getChannel().getMembers();
			
			for(Member i : toMute) {
				if(!(i == clutcher)) {
					i.mute(true).queue();
				}
				
			}
		}
		
	}
}
