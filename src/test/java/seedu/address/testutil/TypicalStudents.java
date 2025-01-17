package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPNAME_G1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPNAME_G2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REPONAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REPONAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENTNUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENTNUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USERNAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USERNAME_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.student.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline").withEmail("alice@example.com")
            .withStudentNumber("A0000000B").withUserName("paulice").withRepoName("ip").withTags("friends").build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier").withStudentNumber("A0000000B")
            .withEmail("johnd@example.com").withTags("owesMoney", "friends").withUserName("benben")
            .withGroupName(VALID_GROUPNAME_G1).withRepoName("ip").build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz").withUserName("cark")
            .withGroupName(VALID_GROUPNAME_G1).withRepoName("ip").withStudentNumber("A0000000B")
            .withEmail("heinz@example.com").build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier").withEmail("cornelia@example.com")
            .withGroupName(VALID_GROUPNAME_G1).withStudentNumber("A0000000B").withUserName("danmei").withRepoName("ip")
            .withTags("friends").build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer").withUserName("em")
            .withGroupName(VALID_GROUPNAME_G2).withRepoName("ip").withStudentNumber("A0000000B")
            .withEmail("werner@example.com").build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz").withUserName("fku")
            .withGroupName(VALID_GROUPNAME_G2).withRepoName("ip").withStudentNumber("A0000000B")
            .withEmail("lydia@example.com").build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best").withUserName("user")
            .withRepoName("ipppp").withStudentNumber("A0000000B").withEmail("anna@example.com").build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier")
            .withEmail("stefan@example.com").withStudentNumber("B0584738B").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller")
            .withEmail("hans@example.com").withStudentNumber("B0584738B").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY).withEmail(VALID_EMAIL_AMY)
            .withStudentNumber(VALID_STUDENTNUMBER_AMY).withUserName(VALID_USERNAME_AMY)
            .withRepoName(VALID_REPONAME_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB).withEmail(VALID_EMAIL_BOB)
            .withStudentNumber(VALID_STUDENTNUMBER_BOB).withUserName(VALID_USERNAME_BOB)
            .withRepoName(VALID_REPONAME_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical students.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }
        return ab;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
