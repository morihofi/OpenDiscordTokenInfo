package application;

import java.io.File;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class Seleniumcontroller {

	@FXML
	private RadioButton rbFirefox;

	@FXML
	private ToggleGroup browser;

	@FXML
	private RadioButton rbChrome;

	@FXML
	private Button btnstartbrowser;

	@FXML
	private Button btnexit;

	@FXML
	private Label lblstatus;

	public WebDriver driver;

	@FXML
	void btnexitclick(ActionEvent event) {
		try {

			driver.close();

		} catch (Exception ex) {

		}
		rbChrome.setDisable(false);
		rbFirefox.setDisable(false);

		Stage stage = (Stage) btnexit.getScene().getWindow();
		// do what you have to do
		stage.close();
	}

	private Thread th;

	void InitDriverPaths() {
		String startupdir = Main.startupdir();

		String arch = "";

		if (simpleutils.OperatingSystem.Is64Bit()) {
			arch = "64";
		} else {
			arch = "86";
		}

		switch (simpleutils.OperatingSystem.getOS()) {
		case WINDOWS:
			// do windows stuff

			System.setProperty("webdriver.gecko.driver",
					startupdir + File.separator + "data" + File.separator + "selenium" + File.separator + "firefox"
							+ File.separator + "win" + arch + File.separator + "geckodriver.exe");
			System.setProperty("webdriver.chrome.driver",
					startupdir + File.separator + "data" + File.separator + "selenium" + File.separator + "chrome"
							+ File.separator + "win32" + File.separator + "chromedriver.exe");

			break;

		case LINUX:
			System.setProperty("webdriver.gecko.driver",
					startupdir + File.separator + "data" + File.separator + "selenium" + File.separator + "firefox"
							+ File.separator + "linux" + arch + File.separator + "geckodriver");
			System.setProperty("webdriver.chrome.driver",
					startupdir + File.separator + "data" + File.separator + "selenium" + File.separator + "chrome"
							+ File.separator + "linux64" + File.separator + "chromedriver");

			// Can execute?
			if (new File(System.getProperty("webdriver.gecko.driver")).canExecute()) {
				new File(System.getProperty("webdriver.gecko.driver")).setExecutable(true);
			}
			if (new File(System.getProperty("webdriver.chrome.driver")).canExecute()) {
				new File(System.getProperty("webdriver.chrome.driver")).setExecutable(true);
			}

		}
		/*
		 * String chromedriverver = "unknown"; String geckodriverver = "unknown";
		 * 
		 * 
		 * 
		 * 
		 * Platform.runLater(() -> { rbChrome.setText("Google Chrome (chromedriver " +
		 * chromedriverver + "detected)");
		 * rbChrome.setText("Mozilla Firefox (geckodriver " + geckodriverver +
		 * "detected)"); });
		 * 
		 */

	}

	@FXML
	void btnstartbrowserclick(ActionEvent event) {

		rbChrome.setDisable(true);
		rbFirefox.setDisable(true);

		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {

				InitDriverPaths();

				try {

					Platform.runLater(() -> btnstartbrowser.setDisable(true));

					if (rbFirefox.isSelected()) {
						Platform.runLater(() -> lblstatus.setText("Status: Starting Mozilla Firefox..."));
						try {
							driver = new FirefoxDriver();
						} catch (Exception ex) {
							Platform.runLater(() -> {
								Alert alert = new Alert(AlertType.ERROR);
								alert.setTitle("Error on execute");
								alert.setHeaderText("Error on execute");
								alert.setContentText(ex.getLocalizedMessage());
								alert.showAndWait();
								lblstatus.setText("Status: Please click on exit and open this dialog again!");
							});
						}

					} else {
						Platform.runLater(() -> lblstatus.setText("Status: Starting Google Chrome..."));

						try {
							driver = new ChromeDriver();

						} catch (Exception ex) {
							Platform.runLater(() -> {
								Alert alert = new Alert(AlertType.ERROR);
								alert.setTitle("Error on execute");
								alert.setHeaderText("Error on execute");
								alert.setContentText(ex.getLocalizedMessage());
								alert.showAndWait();
								lblstatus.setText("Status: Please click on exit and open this dialog again!");
								
							});
							
						}
						
						
					}

					
					System.out.println("[Selenium] [" + driver.getClass().getSimpleName() + "] Opening Discord App...");
					driver.get("https://discord.com/app");
					Platform.runLater(() -> lblstatus.setText("Status: Running auto-login..."));
					System.out
							.println("[Selenium] [" + driver.getClass().getSimpleName() + "] Execute login script...");
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("let token = \"" + Main.lastgettokeninfotoken + "\";\r\n" + "\r\n"
							+ "function login(token) {\r\n" + "    setInterval(() => {\r\n"
							+ "      document.body.appendChild(document.createElement `iframe`).contentWindow.localStorage.token = `\"${token}\"`\r\n"
							+ "    }, 50);\r\n" + "    setTimeout(() => {\r\n" + "      location.reload();\r\n"
							+ "    }, 2500);\r\n" + "  }\r\n" + "\r\n" + "login(token);");

					while (true) {

						Thread.sleep(1000);

						System.out.println(
								"[Selenium] [" + driver.getClass().getSimpleName() + "] Checking if logged in...");

						if (driver.getCurrentUrl().equals("https://discord.com/channels/@me")) {
							break;
						}

					}

					Platform.runLater(() -> lblstatus.setText("Status: Login succeed!"));
					Platform.runLater(() -> {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("You're now logged in.");
						alert.setHeaderText("You're now logged in.");
						alert.setContentText(
								"You are now logged in to the account. \nClick on \"Exit\" in this window or close browser if you want to exit");
						alert.showAndWait();
					});

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error on execute");
					alert.setHeaderText("Error on execute");
					alert.setContentText(e.getLocalizedMessage());
					alert.showAndWait();
				}

				return null;
			};

		};
		th = new Thread(task);
		th.setDaemon(true);
		th.start();

	}

}
