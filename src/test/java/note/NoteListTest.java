package note;

import ui.Ui;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NoteListTest {

    @Test
    public void getSize() {
        Ui ui = new Ui();
        NoteList notes = new NoteList(ui);
        int testSize = 5;
        int testScene = 2;
        for (int i = 0; i < testSize; i++) {
            Note testNote = new Note("Test", "DEFAULT " + i, testScene);
            notes.createNote(testNote, testScene);
        }
        assertEquals(5,notes.getSize());
    }

    @Test
    public void searchNotesUsingSceneIndex() {
        NoteList notes = new NoteList(new Ui());
        Note testOne = new Note("test one","TEST ONE",1);
        Note testTwo = new Note("test two","TEST TWO",2);
        Note testThree = new Note("test three","TEST THREE",1);
        notes.createNote(testOne,1);
        notes.createNote(testTwo,2);
        notes.createNote(testThree,1);
        ArrayList<Note> actualResult;
        ArrayList<Note> expectedResult = new ArrayList<>();
        actualResult = notes.searchNotesUsingSceneIndex(1,notes);
        expectedResult.add(testOne);
        expectedResult.add(testThree);
        assertEquals(actualResult,expectedResult);
    }

    @Test
    public void searchNoteUsingTitle() {
        NoteList notes = new NoteList(new Ui());
        Note testAlpha = new Note("test alpha","TEST ALPHA",1);
        Note testBeta = new Note("test beta","TEST BETA",2);
        Note testGamma = new Note("test gamma","TEST GAMMA",3);
        Note testAlphaTwo = new Note("test alpha two","TEST ALPHA TWO",2);
        notes.createNote(testAlpha,1);
        notes.createNote(testBeta,2);
        notes.createNote(testGamma,3);
        notes.createNote(testAlphaTwo,2);
        ArrayList<Note> actualResult;
        ArrayList<Note> expectedResult = new ArrayList<>();
        actualResult = notes.searchNoteUsingTitle("ALPHA",notes);
        expectedResult.add(testAlpha);
        expectedResult.add(testAlphaTwo);
        assertEquals(actualResult,expectedResult);
    }

    @Test
    public void stringSpliter() {
        NoteList notes = new NoteList(new Ui());
        String testString = "THIS IS A TEST OF DUKE";
        String[] expectedResult = {"THIS","IS","A","TEST","OF","DUKE"};
        String[] underTest = notes.stringSpliter(testString);
        assertTrue(expectedResult.equals(underTest));
        /*assertEquals("THIS",underTest[0]);
        assertEquals("IS",underTest[1]);
        assertEquals("A",underTest[2]);
        assertEquals("TEST",underTest[3]);
        assertEquals("OF",underTest[4]);
        assertEquals("DUKE",underTest[5]);*/
    }

    @Test
    public void getIndexNote() {
        NoteList notes = new NoteList(new Ui());
        Note testOne = new Note("test 1","TEST 1",1);
        Note testTwo = new Note("test 2","TEST 2",2);
        notes.createNote(testOne,1);
        notes.createNote(testTwo,2);
        /*assertEquals(testOne,notes.getIndexNote(1));
        assertEquals(testTwo, notes.getIndexNote(2));*/
        assertTrue(testOne.equals(notes.getIndexNote(1)));
        assertTrue(testTwo.equals(notes.getIndexNote(2)));
    }
}