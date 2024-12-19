package readinglog;

import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Property {
	private static Path path;
	private Properties properties = new Properties();
	private FileInputStream istream;

	public Property() {
		path = Paths.get("" + "settings.properties");
		System.out.println(path.toAbsolutePath());
		
		if(!Files.exists(path)) {
			System.out.println("Make Properties File.");
			makeProperties();
			System.out.println("必要な情報をプロパティファイルに書き込んでください。");
			System.exit(1);
		} else {
			System.out.println("Already exist.");
			try {
				istream = new FileInputStream(path.toString());
			} catch (FileNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		
	}
	public String getProperty(String property) {
		try {
			properties.load(istream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(property);
	}
	
	private static void makeProperties() {
		try {
			Files.createFile(path);
		} catch (IOException e) {
			System.out.println(e);
			return;
		}
		Properties properties = new Properties();
		//ここを変更↓
//		properties.setProperty("Test", "xxxxxxxxxxxxx");
		properties.setProperty("LOG_PATH", "logs");
		properties.setProperty("LOG_NAME", "latest.log");
		properties.setProperty("JOINED_TEXT", "joined the game");
		properties.setProperty("LEFT_TEXT", "left the game");
        //ここを変更↑
        try {
			properties.store(new FileOutputStream(path.toString()), "Comments");
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return;
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return;
		}
	}
	
}
