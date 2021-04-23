package Commands;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class HelpCommand extends ListenerAdapter{
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		String[] message = e.getMessage().getContentRaw().split(" ");
		TextChannel channel = e.getChannel();
		if(message[0].equalsIgnoreCase("$help") && !e.getAuthor().isBot()) {
			try {
				
				switch(message[1].toLowerCase()) {
				case "banish":
					BanishCommand.banishHelp(channel);
					break;
				case "recruit":
					RecruitCommand.recruitHelp(channel);
					break;
				case "restore":
					RestoreCommand.restoreHelp(channel);
					break;
				case "background":
					channel.sendMessage("Current background processes: \n"
							+ "1. League of Legends patch detector \n"
							+ "2. Minecraft update detector \n"
							+ "3. CS:GO update detector").queue();
					break;
				case "wip":
					WIPCommand.WIPHelp(channel);
					break;
				case "clutch":
					ClutchCommand.ClutchHelp(channel);
					break;
				case "info":
					InfoCommand.infoHelp(channel);
				default:
					channel.sendMessage("Available Commands: \n 1. $banish [User] \n 2. $restore [User] \n 3. $recruit [game] \n 4. $WIP [add/get] \n 5. $clutch (User) \n 6. $info \n"
							+ "To see help about a specific command, use $help [command] \n "
							+ "To see background processes, use $help background").queue();

				} 
			} catch(IndexOutOfBoundsException f) {
				channel.sendMessage("Available Commands: \n 1. $banish [User] \n 2. $restore [User] \n 3. $recruit [game] \n 4. $WIP [add/get] \n 5. $clutch (User) \n 6. $info \n"
						+ "To see help about a specific command, use $help [command] \n "
						+ "To see background processes, use $help background").queue();
			}

		}
	}

}
