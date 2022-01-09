package application;

import java.io.Console;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.json.JSONObject;

import discordtokengrabber.Grabber;
import simpleutils.*;

public class Cli {



	public static void start() throws Exception {
		System.out.println("");
		System.out.println("Welcome to Discord Token Info shell!");
		System.out.println("Version: " + Main.AppVer);
		
		
		System.out.println(" ____    ______  ______     \n"
				+ "/\\  _`\\ /\\__  _\\/\\__  _\\    \n"
				+ "\\ \\ \\/\\ \\/_/\\ \\/\\/_/\\ \\/    \n"
				+ " \\ \\ \\ \\ \\ \\ \\ \\   \\ \\ \\    \n"
				+ "  \\ \\ \\_\\ \\ \\ \\ \\   \\_\\ \\__ \n"
				+ "   \\ \\____/  \\ \\_\\  /\\_____\\\n"
				+ "    \\/___/    \\/_/  \\/_____/\n"
				+ "");
		System.out.println("Type \"help\" if you need help");

		
		if(!Main.seto) {
			System.out.println("");
			System.out.println(""
					+ "â•”â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•—\n"
					+ "â•‘ This is a development build. Be careful, it could kill your pets. â•‘\n"
					+ "â•šâ•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�\n");
		}
		
		waitforuserinput();
	}

	public static void waitforuserinput() throws Exception {
		
		
		
		simpleutils.Console console = new simpleutils.Console();


		String command = console.readLine("DTI> ");
		if (command.equals("help")) {
			console.println("Command - Info");
			console.println("=======================");
			console.println("");
			console.println("gettokeninfo TOKEN - Get informations about spectified Token");			
			console.println("getmytokens - Get Tokens in this userprofile");
			console.println("about - Shows informations about this application");
			console.println("quit - Exits the application");
			console.println("exit - Same as quit");
		}
		
		if (command.equals("about")) {
			console.println("Version: " + Main.AppVer);
			console.println("");
			console.println("Discord Token Info Tool is originaly developed by " + "dti_dev");
			console.println("This Fork of DTI is Free Software. ");
		}
		
		if (command.equals("getmytokens")) {
			console.println("Searching for Tokens...");
			List<String> tokens = Grabber.getTokens(false);

			StringBuilder strbul = new StringBuilder();
			if (tokens.size() == 0) {
				console.println("No Tokens found");
			} else {
				for (String str : tokens) {
					strbul.append(str + "\n");
				}
				console.println("Tokens found: ");
				console.println(strbul.toString());
			}
			
			
		}
		
		if (command.startsWith("gettokeninfo ")) {
			String args = command.substring(13);

			console.println("Getting Token info...");
			
			String serverjson = Main.getDiscordTokenInfo("https://discord.com/api/v8/users/@me", args);
			
			if (serverjson == null) {
				console.println("Error: The spectified token is not valid.");
			}else {
				JSONObject obj = new JSONObject(serverjson);
				String jsonstrToken = args;
				String jsonstrUsername = obj.getString("username");
				String jsonstrDiscriminator = obj.getString("discriminator");
				String jsonstrId = obj.getString("id");
				String jsonstrAvatar = obj.getString("avatar");
				Integer jsonstrPublicFlags = obj.getInt("public_flags");
				Integer jsonstrFlags = obj.getInt("flags");
				String jsonstrBio = obj.getString("bio");
				String jsonstrLocale = obj.getString("locale");
				Boolean jsonstrMFAenabled = obj.getBoolean("mfa_enabled");
				String jsonstrEmail = obj.getString("email");
				String jsonstrPhone = obj.getString("phone");
				Boolean jsonstrVerified = obj.getBoolean("verified");
				String jsonstrVerified_formatted;
				String jsonstrMFAenabled_formatted;
				String jsonstrPublicFlags_formatted;
				String jsonstrNSFW = "(not set)";
				String jsonstrNitro = "";
				String jsonstrAccCreationDate = "Unknown";
				
				if (Boolean.TRUE.equals(jsonstrVerified)) {
					jsonstrVerified_formatted = "Yes";
				} else {
					jsonstrVerified_formatted = "No";
				}

				if (Boolean.TRUE.equals(jsonstrMFAenabled)) {
					jsonstrMFAenabled_formatted = "Enabled";
				} else {
					jsonstrMFAenabled_formatted = "Disabled";
				}
				// NSFW
				try {

					if (obj.has("nsfw_allowed")) {

						if (Boolean.TRUE.equals(obj.getBoolean("nsfw_allowed"))) {
							jsonstrNSFW = "Yes";
						} else {
							jsonstrNSFW = "No";
						}

					}

				} catch (Exception ex) {

				}
				// Phone
				try {

					jsonstrPhone = obj.getString("phone");

				} catch (Exception ex) {

				}
				// Avatar
				try {

					jsonstrAvatar = obj.getString("avatar");

				} catch (Exception ex) {

				}
				// E-Mail
				try {

					jsonstrEmail = obj.getString("email");

				} catch (Exception ex) {

				}

				// Discord NITRO
				try {

					if (obj.has("premium_type")) {

						switch (obj.getInt("premium_type")) {
						case 0:
							jsonstrNitro = "Not a Nitro subscriber";
							break;
						case 1:
							jsonstrNitro = "Nitro Classic subscriber";
							break;
						case 2:
							jsonstrNitro = "Nitro subscriber";
							break;
						}

					}

				} catch (Exception ex) {
					jsonstrNitro = "Not a Nitro subscriber";
				}

				switch (jsonstrPublicFlags) {
				case 1:
					jsonstrPublicFlags_formatted = "Discord Employee";
					break;
				case 2:
					jsonstrPublicFlags_formatted = "Partnered Server Owner";
					break;
				case 4:
					jsonstrPublicFlags_formatted = "HypeSquad Events";
					break;
				case 8:
					jsonstrPublicFlags_formatted = "Bug Hunter Level 1";
					break;
				case 64:
					jsonstrPublicFlags_formatted = "House Bravery";
					break;
				case 128:
					jsonstrPublicFlags_formatted = "House Brilliance";
					break;
				case 256:
					jsonstrPublicFlags_formatted = "House Balance";
					break;
				case 512:
					jsonstrPublicFlags_formatted = "Early Supporter";
					break;
				case 16384:
					jsonstrPublicFlags_formatted = "Bug Hunter Level 2";
					break;
				case 131072:
					jsonstrPublicFlags_formatted = "Early Verified Bot Developer";
					break;
				default:
					jsonstrPublicFlags_formatted = "Unknown";
					break;
				}

				ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
				ScriptContext context = engine.getContext();
				StringWriter writer = new StringWriter();
				context.setWriter(writer);

				try {
					engine.eval("function convertIDtoUnix(id) {\r\n" + "		/* Note: id has to be str */\r\n"
							+ "		var bin = (+id).toString(2);\r\n" + "		var unixbin = '';\r\n"
							+ "		var unix = '';\r\n" + "		var m = 64 - bin.length;\r\n"
							+ "		unixbin = bin.substring(0, 42-m);\r\n"
							+ "		unix = parseInt(unixbin, 2) + 1420070400000;\r\n" + "		return unix / 1000;\r\n"
							+ "	}" + "function convert(id) {\r\n"
							+ "		var unix = convertIDtoUnix(id.toString());\r\n" + "        \r\n"
							+ "		// Unixtimestamp\r\n" + "		 var unixtimestamp = unix;\r\n"
							+ "		 // Months array\r\n"
							+ "		 var months_arr = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];\r\n"
							+ "		 // Convert timestamp to milliseconds\r\n"
							+ "		 var date = new Date(unixtimestamp*1000);\r\n" + "		 // Year\r\n"
							+ "		 var year = date.getFullYear();\r\n" + "		 // Month\r\n"
							+ "		 var month = months_arr[date.getMonth()];\r\n" + "		 // Day\r\n"
							+ "		 var day = date.getDate();\r\n" + "		 // Hours\r\n"
							+ "		 var hours = date.getHours();\r\n" + "		 // Minutes\r\n"
							+ "		 var minutes = \"0\" + date.getMinutes();\r\n" + "		 // Seconds\r\n"
							+ "		 var seconds = \"0\" + date.getSeconds();\r\n"
							+ "		 // Display date time in MM-dd-yyyy h:m:s format\r\n"
							+ "		 var convdataTime = day+'-'+month+'-'+year+' '+ hours + ':' + minutes.substr(-2) + ':' + seconds.substr(-2);\r\n"
							+ "		 \r\n" + "		return convdataTime;\r\n" + "		\r\n" + "	}"
							+ "print(convert(" + jsonstrId + "));");
					
						jsonstrAccCreationDate = writer.toString();
				} catch (ScriptException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		
				
				console.println("Username: " + jsonstrUsername + "#" + jsonstrDiscriminator);
				console.println("Locale: " + jsonstrLocale);
				console.println("ID: " + jsonstrId);
				console.println("Token: " + args);
				console.println("Phone: " + jsonstrPhone);
				console.println("E-Mail:" + jsonstrEmail);
				console.println("Verified: " + jsonstrVerified);
				console.println("Account created: "+ jsonstrAccCreationDate.replace("\n", ""));
				console.println("Public flags: " + jsonstrPublicFlags_formatted);
				console.println("Nitro status:" + jsonstrNitro);
				console.println("NSFW allowed: "+ jsonstrNSFW);
				console.println("Multi-factor authentication: " + jsonstrMFAenabled_formatted);
				console.println("Bio: \n       " + jsonstrBio.replace("\n", "\n       "));
	
				
			}
			
			

		}
		if (command.equals("exit") || command.equals("quit")) {

			console.println("Goodbye!");
			System.exit(0);

		}

		waitforuserinput();
	}
}
