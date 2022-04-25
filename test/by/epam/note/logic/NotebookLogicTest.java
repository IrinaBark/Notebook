package by.epam.note.logic;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import by.epam.note.bean.Note;
import by.epam.note.bean.Notebook;
import by.epam.note.util.NotebookUtil;

import org.junit.Assert;
import org.junit.Test;

public class NotebookLogicTest {

	public static boolean compare(String fileName1, String fileName2) throws IOException {
		try (BufferedInputStream fis1 = new BufferedInputStream(new FileInputStream(fileName1));
			 BufferedInputStream fis2 = new BufferedInputStream(new FileInputStream(fileName2))) {
			boolean isSame = true;
			int ch1 = 0;
			while ((ch1 = fis1.read()) != -1) {

				if (ch1 != fis2.read()) {
					isSame = false;
					return isSame;
				}

			}
			if (fis2.read() != -1) {
				isSame = false;
			}
			return isSame;
		}
	}

	public static List <Note> createNotebook() {

		LocalDate date1 = LocalDate.of(2022, 04, 11);
		LocalDate date2 = LocalDate.of(2023, 05, 11);
		LocalDate date3 = LocalDate.of(2022, 04, 20);

		String note1 = "Запись 1";
		String note2 = "Запись 2";
		String note3 = "Запись 3";

		Note nt1 = new Note(note1, date1);
		Note nt2 = new Note(note2, date2);
		Note nt3 = new Note(note3, date3);

		List<Note> notebook = new ArrayList<Note>();
		notebook.add(nt1);
		notebook.add(nt2);
		notebook.add(nt3);

		return notebook;
	}

	@Test
	public void checkByDate() {

		Notebook checkable = new Notebook();
		checkable.setNotebook(createNotebook());

		LocalDate date = LocalDate.of(2022, 04, 11);
		String note = "Запись 1";

		NotebookLogic nb = new NotebookLogic();
		Notebook real = nb.search(date, checkable);

		List<Note> expectedNote = new ArrayList<Note>();
		expectedNote.add(new Note(note, date));
		Notebook expected = new Notebook(expectedNote);

		Assert.assertEquals(expected, real);

	}

	@Test
	public void checkByContent() {
		Notebook checkable = new Notebook();
		checkable.setNotebook(createNotebook());

		LocalDate date = LocalDate.of(2022, 04, 11);
		String note = "Запись 1";

		NotebookLogic nb = new NotebookLogic();
		Notebook real = nb.search(note, checkable);

		List<Note> expectedNote = new ArrayList<Note>();
		expectedNote.add(new Note(note, date));
		Notebook expected = new Notebook(expectedNote);

		Assert.assertEquals(expected, real);
	}

	@Test
	public void checkDeleteLastNote() {
		Notebook checkable = new Notebook();
		checkable.setNotebook(createNotebook());

		NotebookLogic nb = new NotebookLogic();
		nb.delete(checkable);

		List<Note> expectedNote = new ArrayList<Note>();
		String note1 = "Запись 1";
		String note2 = "Запись 2";
		LocalDate date1 = LocalDate.of(2022, 04, 11);
		LocalDate date2 = LocalDate.of(2023, 05, 11);

		expectedNote.add(new Note(note1, date1));
		expectedNote.add(new Note(note2, date2));
		Notebook expected = new Notebook(expectedNote);

		Assert.assertEquals(expected, checkable);
	}

	@Test
	public void checkDeleteByContent() {
		Notebook checkable = new Notebook();
		checkable.setNotebook(createNotebook());

		NotebookLogic nb = new NotebookLogic();
		String content = "3";
		nb.delete(content, checkable);

		List<Note> expectedNote = new ArrayList<Note>();
		String note1 = "Запись 1";
		String note2 = "Запись 2";
		LocalDate date1 = LocalDate.of(2022, 04, 11);
		LocalDate date2 = LocalDate.of(2023, 05, 11);

		expectedNote.add(new Note(note1, date1));
		expectedNote.add(new Note(note2, date2));
		Notebook expected = new Notebook(expectedNote);

		Assert.assertEquals(expected, checkable);
	}

	@Test
	public void checkDeleteByDate() {
		Notebook checkable = new Notebook();
		checkable.setNotebook(createNotebook());

		NotebookLogic nb = new NotebookLogic();
		LocalDate date = LocalDate.of(2022, 04, 20);
		nb.delete(date, checkable);

		List<Note> expectedNote = new ArrayList<Note>();
		String note1 = "Запись 1";
		String note2 = "Запись 2";
		LocalDate date1 = LocalDate.of(2022, 04, 11);
		LocalDate date2 = LocalDate.of(2023, 05, 11);

		expectedNote.add(new Note(note1, date1));
		expectedNote.add(new Note(note2, date2));
		Notebook expected = new Notebook(expectedNote);

		Assert.assertEquals(expected, checkable);
	}

	@Test
	// ввести "Запись 4"
	public void checkCreateNewNote() {
		Notebook notebook = new Notebook(createNotebook());
		NotebookLogic nbl = new NotebookLogic();
		nbl.createNewNote(notebook, "fileTest");

		Note note = new Note("Запись 4#");
		LocalDate date = notebook.getNotebook().get(3).getDate();

		List<Note> expectedNote = new ArrayList<Note>();
		expectedNote.addAll(createNotebook());
		expectedNote.add(note);
		expectedNote.get(3).setDate(date);

		List<Note> realNote = notebook.getNotebook();

		Assert.assertEquals(expectedNote, realNote);
	}

	@Test
	public void checkOverwriteFile() throws IOException {

		File testFile = new File("test.txt");
		Notebook notebook = new Notebook(createNotebook());

		for (Note n : notebook.getNotebook()) {
			NotebookUtil.writeToFile(testFile, n.getNote());
		}
		NotebookLogic nbl = new NotebookLogic();
		nbl.delete(notebook);
		nbl.overwriteFile(testFile, notebook);

		File expected = new File("testExpected.txt");

		for (Note n : notebook.getNotebook()) {
			NotebookUtil.writeToFile(expected, n.getNote());
		}
		boolean expectedResult = true;
		boolean realResult = compare("test.txt", "testExpected.txt");
		
		Assert.assertEquals(expectedResult, realResult);
	}
}
