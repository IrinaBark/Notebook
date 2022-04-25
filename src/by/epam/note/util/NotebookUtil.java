package by.epam.note.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class NotebookUtil {

	public static int getInt() {
		int menu;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		while (!sc.hasNextInt()) {
			sc.nextLine();
			System.out.println("Введите, пожалуйста, номер опции (цифра) >");
		}
		menu = sc.nextInt();
		return menu;
	}

	public static void writeToFile(File file, String note) {
		try (FileWriter fileWriter = new FileWriter(file, true)) {
			fileWriter.write(note);
			fileWriter.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static LocalDate getDate() {
		int year;
		int month;
		int day;
        
		int minYear = 2000;
		int maxYear = 3000;
		int firstMonth = 1;
		int lastMonth = 12;
		int firstDay = 1;
		int lastDay = 31;
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		System.out.println("Введите год (число) >");

		while (!sc.hasNextInt()) {
			sc.nextLine();
			System.out.println("Неверный формат ввода! Введите год (число)  >");
		}
		while ((year = sc.nextInt()) < minYear || year > maxYear) {
			sc.nextLine();
			System.out.println("Год введен некорректно! Введите год (число)");
		}
		System.out.println("Введите месяц (число) >");
		while (!sc.hasNextInt()) {
			System.out.println("Неверный формат ввода!");
		}
		while ((month = sc.nextInt()) < firstMonth || month > lastMonth) {
			sc.nextLine();
			System.out.println("Неверный формат ввода!");
		}

		System.out.println("Введите день (число) >");
		while (!sc.hasNextInt()) {
			System.out.println("Неверный формат ввода!");
		}
		while ((day = sc.nextInt()) < firstDay || day > lastDay) {
			sc.nextLine();
			System.out.println("Неверный формат ввода!");
		}

		LocalDate date = LocalDate.of(year, month, day);
		return date;
	}

	public static String getContent() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Введите текст для поиска >");
		String content = sc.nextLine();
		return content;
	}
    
	public static void printFile(File file) {
		try (FileReader reader = new FileReader(file)) {
			int c;
			System.out.println("Полученный текст записи: ");
			while ((c = reader.read()) != -1) {
				if ((char) c == '#') {
					System.out.println();
					continue;
				}
				System.out.print((char) c);
			}

		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
	public static void deleteFileContent (File file) {
		try (FileWriter fileWriter = new FileWriter(file, false)) {
			fileWriter.write("");
			fileWriter.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
}
