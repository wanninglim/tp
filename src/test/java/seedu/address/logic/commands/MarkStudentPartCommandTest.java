package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for MarkStudentPartCommand.
 */
public class MarkStudentPartCommandTest {

    private static final Integer[] MARK_PARTICIPATION_PRESENT_EXPECTED = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    private static final Integer[] MARK_PARTICIPATION_ABSENT_EXPECTED = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    private static final int FIRST_WEEK = 1;

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_markStudentParticipatedUnfilteredList_success() {
        String type = "participated";

        Student studentToMark = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());

        // reset student participation status
        if (model.getStudentParticipation(studentToMark, FIRST_WEEK - 1) == "participated") {
            model.markStudentParticipation(studentToMark, FIRST_WEEK - 1);
        }

        Student markedStudent = new StudentBuilder(studentToMark)
                .withParticipation(MARK_PARTICIPATION_PRESENT_EXPECTED).build();

        MarkStudentPartCommand markStudentPartCommand = new MarkStudentPartCommand(
                Collections.singletonList(INDEX_FIRST_STUDENT), FIRST_WEEK - 1);

        String expectedMessage = String.format(MarkStudentPartCommand.MESSAGE_MARK_STUDENT_SUCCESS,
                markedStudent.getName(), type, FIRST_WEEK);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), markedStudent);

        assertCommandSuccess(markStudentPartCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_markStudentNotParticipatedUnfilteredList_success() {
        String type = "not participated";

        Student studentToMark = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());

        // reset student participation status
        if (model.getStudentParticipation(studentToMark, FIRST_WEEK - 1) == "not participated") {
            model.markStudentParticipation(studentToMark, FIRST_WEEK - 1);
        }

        Student markedStudent = new StudentBuilder(studentToMark)
                .withParticipation(MARK_PARTICIPATION_ABSENT_EXPECTED).build();

        MarkStudentPartCommand markStudentPartCommand = new MarkStudentPartCommand(
                Collections.singletonList(INDEX_FIRST_STUDENT), FIRST_WEEK - 1);

        String expectedMessage = String.format(MarkStudentPartCommand.MESSAGE_MARK_STUDENT_SUCCESS,
                markedStudent.getName(), type, FIRST_WEEK);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), markedStudent);

        assertCommandSuccess(markStudentPartCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_markStudentParticipatedFilteredList_success() {
        String type = "participated";

        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student studentInFilteredList = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());

        // reset student participation status
        if (model.getStudentParticipation(studentInFilteredList, FIRST_WEEK - 1) == "participated") {
            model.markStudentParticipation(studentInFilteredList, FIRST_WEEK - 1);
        }

        Student markedStudent = new StudentBuilder(studentInFilteredList)
                .withParticipation(MARK_PARTICIPATION_PRESENT_EXPECTED).build();

        MarkStudentPartCommand markStudentPartCommand = new MarkStudentPartCommand(
                Collections.singletonList(INDEX_FIRST_STUDENT), FIRST_WEEK - 1);

        String expectedMessage = String.format(MarkStudentPartCommand.MESSAGE_MARK_STUDENT_SUCCESS,
                markedStudent.getName(), type, FIRST_WEEK);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), markedStudent);

        assertCommandSuccess(markStudentPartCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_markStudentNotParticipatedFilteredList_success() {
        String type = "not participated";

        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student studentInFilteredList = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());

        // reset student participation status
        if (model.getStudentParticipation(studentInFilteredList, FIRST_WEEK - 1) == "not participated") {
            model.markStudentParticipation(studentInFilteredList, FIRST_WEEK - 1);
        }

        Student markedStudent = new StudentBuilder(studentInFilteredList)
                .withParticipation(MARK_PARTICIPATION_ABSENT_EXPECTED).build();

        MarkStudentPartCommand markStudentPartCommand = new MarkStudentPartCommand(
                Collections.singletonList(INDEX_FIRST_STUDENT), FIRST_WEEK - 1);

        String expectedMessage = String.format(MarkStudentPartCommand.MESSAGE_MARK_STUDENT_SUCCESS,
                markedStudent.getName(), type, FIRST_WEEK);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), markedStudent);

        assertCommandSuccess(markStudentPartCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        MarkStudentPartCommand markStudentPartCommand = new MarkStudentPartCommand(
                Collections.singletonList(outOfBoundIndex), FIRST_WEEK);

        assertCommandFailure(markStudentPartCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * Mark participation of filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidStudentIndexFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStudentList().size());

        MarkStudentPartCommand markStudentPartCommand = new MarkStudentPartCommand(
                Collections.singletonList(outOfBoundIndex), FIRST_WEEK);

        assertCommandFailure(markStudentPartCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final MarkStudentPartCommand standardCommand = new MarkStudentPartCommand(
                Collections.singletonList(INDEX_FIRST_STUDENT), FIRST_WEEK);

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearStudentsCommand()));
    }
}
