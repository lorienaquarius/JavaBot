package Commands;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class InfoCommand extends ListenerAdapter{
	
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		String[] message = e.getMessage().getContentRaw().split(" ");
		
		if(message[0].equalsIgnoreCase("$Info")) {
			e.getChannel().sendMessage("Hi! I'm JsutBot! I was made by Sage7Rules as a fun utility bot for this server."
					+ " I do all kinds of fun things like check for updates for your favourite games, as well as offer fun and useful commands. Use $help for more information on my available commands.").queue();
		}
	}
	
	public static void infoHelp(TextChannel textChannel) {
		textChannel.sendMessage("Use command like so: \n $info").queue();
	}
}
