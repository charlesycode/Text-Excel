package textExcel;

import java.io.FileNotFoundException;
import java.util.Scanner;

// Update this file with your own code.

public class TextExcel
{

	public static void main(String[] args)
	{
	    // Add your command loop here
		Scanner console = new Scanner(System.in);
		Spreadsheet sprdsht = new Spreadsheet();
		System.out.println("Say something");
		String command = console.nextLine();
		System.out.println(sprdsht.processCommand(command));
		while (!command.equalsIgnoreCase("quit")) {
			command = console.nextLine();
			System.out.println(sprdsht.processCommand(command));
		}
		console.close();
	}
}
