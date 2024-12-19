package readinglog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
	private static final String version = "v1.0.0";

	public static void main(String[] args) {
		if(args.length == 0) {
			
		} else if (args[0].equals("version")) {
			System.out.println(version);
			System.exit(0);
		} else {
			System.err.println("No command.");
			System.exit(1);
		}
		Property property = new Property();
		String ostype = System.getProperty("os.name").toLowerCase();
		Path path = Paths.get(
				""
						+ property.getProperty("LOG_PATH")
						+ "/"
						+ property.getProperty("LOG_NAME"));
		File file = new File(path.toString());
		System.err.println(ostype);
		System.err.println("Path:" + path.toAbsolutePath());
		System.err.println("File exist:" + file.exists());
		if (!file.exists()) {
			System.err.println("Not Found.");
			System.exit(0);
		}

		String joined = property.getProperty("JOINED_TEXT");
		String left = property.getProperty("LEFT_TEXT");

		while (true) {
			int count = 0;
			try {
				Thread.sleep(3 * 60 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String line;
				while ((line = br.readLine()) != null) {
					int index = 0;
					while ((index = line.indexOf(joined, index)) != -1) {
						count++;
						index += joined.length();
					}
					while ((index = line.indexOf(left, index)) != -1) {
						count--;
						index += left.length();
					}
					
				}
				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.println(count + "人オンライン");
			
			if (count == 0) {
				break;
			}

		}
			System.out.println("シャットダウンします。");
			try {
				if (ostype.contains("win")) {
					System.out.println("Windows Shutdown...");
					String[] shutdownCmd = {"shutdown", "/s", "/t", "0"};
					Runtime.getRuntime().exec(shutdownCmd);
				} else if(ostype.contains("mac") 
						|| ostype.contains("nix")
						|| ostype.contains("nux")
						|| ostype.contains("aix")
						) {
					System.out.println("Other Shutdown...");
					String[] shutdownCom = {"sudo", "shutdown" ,"-h" ,"now"};
					Runtime.getRuntime().exec(shutdownCom);
				} else {
					System.err.println("Unknown OS.");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

	}

}
