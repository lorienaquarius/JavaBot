package general;
import java.util.List;

import javax.security.auth.login.LoginException;

import Commands.*;
import ContinuousChecks.LeaguePatchCheck;
import Events.*;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

public class Main {
	public static List<Member> curMembers;
	
	public static void main(String[] args) throws LoginException {
		
		//JsutGang server
		JDA jda = JDABuilder.createDefault("Token Goes Here").build();
		//Test Server
//		JDA jda = new JDABuilder(AccountType.BOT).setToken("Token Goes here").build();

  	CheckerThreadHandler checkers = new CheckerThreadHandler(jda);
		ListenerThreadHandler listeners = new ListenerThreadHandler(jda);
//
		Thread t1 = new Thread(listeners);
		Thread t2 = new Thread(checkers);
//
		t1.start();
		t2.start();

	
	}

}
