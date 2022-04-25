package by.epam.note.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import by.epam.note.logic.NotebookLogicTest;

import org.junit.Assert;
import org.junit.Test;

public class NotebookUtilTest {

	@Test
	// ввести 1
	public void checkGetInt() {
		int expected = 1;
		int real = NotebookUtil.getInt();

		Assert.assertEquals(expected, real);
	}

	@Test
	public void checkWriteToFile() throws IOException {

		File testFile = new File("testFile.txt");
		String note = "Запись в файл";
		NotebookUtil.writeToFile(testFile, note);

		File expFile = new File("expFile.txt");

		try (FileWriter fileWriter = new FileWriter(expFile, true)) {
			fileWriter.write(note);
			fileWriter.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		boolean real = NotebookLogicTest.compare("testFile.txt", "expFile.txt");
		boolean expected = true;
		Assert.assertEquals(expected, real);
	}

	@Test
	// ввести год - 2022, месяц - 4, день - 16
	public void checkGetDate() {
		LocalDate realDate = NotebookUtil.getDate();
		LocalDate expectedDate = LocalDate.of(2022, 4, 16);

		Assert.assertEquals(expectedDate, realDate);
	}

	@Test
	// ввести "текст для проверки"
	public void checkGetContent() {
		String expected = "текст для проверки";
		String real = NotebookUtil.getContent();

		Assert.assertEquals(expected, real);
	}

}
