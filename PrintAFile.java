/* A UTILITY CLASS WITH ONE STATIC METHOD FOR PRINTING A FILE */

import java.io.*;

public class PrintAFile {
	public static void printFile(String file) throws IOException {
		try {

			File filename = new File(file);

			FileReader inputFile = new FileReader(filename);

			final int bufferSize = 80;
			char[] buffer = new char[bufferSize];

			int numberRead = inputFile.read(buffer);

			while (numberRead > 0) {
				System.out.println(buffer);
				numberRead = inputFile.read(buffer);
			}

			inputFile.close();

		}

		catch (FileNotFoundException e) {
			System.out.println("Unable to open " + file);
		}

		catch (IOException e) {
			System.out.println("Unable to process " + file);
		}

	}// end method printFile
}// end class PrintAFile
