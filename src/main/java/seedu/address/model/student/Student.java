package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.commons.RepoName;
import seedu.address.model.group.GroupName;
import seedu.address.model.tag.Tag;

/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final Email email;
    private final StudentNumber studentNumber;

    // Data fields
    private final Attendance attendance;
    private final Participation participation;
    private final Set<Tag> tags = new HashSet<>();
    private final UserName userName;
    private final RepoName repoName;
    private final GroupName groupName;

    /**
     * Every field must be present and not null.
     * Constructor for a new Person object
     */
    public Student(Name name, Email email, StudentNumber studentNumber, UserName userName, RepoName repoName,
                   Set<Tag> tags) {
        requireAllNonNull(name, email, studentNumber, tags);
        this.name = name;
        this.email = email;
        this.studentNumber = studentNumber;
        this.tags.addAll(tags);
        this.attendance = new Attendance();
        this.participation = new Participation();
        if (userName != null) {
            this.userName = userName;
        } else {
            this.userName = new UserName();
        }
        if (repoName != null) {
            this.repoName = repoName;
        } else {
            this.repoName = new RepoName();
        }
        this.groupName = new GroupName();
    }

    /**
     * Constructor for a re-stored Person object
     */

    public Student(Name name, Email email, StudentNumber studentNumber, UserName userName, RepoName repoName,
                   Set<Tag> tags, Attendance attendance, Participation participation, GroupName groupName) {
        requireAllNonNull(name, email, studentNumber, tags, attendance);
        this.name = name;
        this.email = email;
        this.studentNumber = studentNumber;
        this.tags.addAll(tags);
        this.attendance = attendance;
        this.participation = participation;
        if (userName != null) {
            this.userName = userName;
        } else {
            this.userName = new UserName();
        }
        if (repoName != null) {
            this.repoName = repoName;
        } else {
            this.repoName = new RepoName();
        }
        this.groupName = groupName;
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public StudentNumber getStudentNumber() {
        return studentNumber;
    }

    public UserName getUserName() {
        return userName;
    }

    public RepoName getRepoName() {
        return repoName;
    }

    public String getStudentLink() {
        if (!userName.isNull() && !repoName.isNull()) {
            return new GithubLink(userName, repoName).toString();
        } else {
            return "-";
        }
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public Participation getParticipation() {
        return participation;
    }

    public GroupName getGroupName() {
        return groupName;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public int checkPresent(int week) {
        return attendance.checkPresent(week);
    }

    public void toggleAttendance(int week) {
        attendance.toggleAttendance(week);
    }

    public int checkParticipated(int week) {
        return participation.checkParticipated(week);
    }

    public void toggleParticipation(int week) {
        participation.toggleParticipation(week);
    }

    /**
     * Returns true if both students have the same name.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getName().equals(getName());
    }

    public boolean hasGroupName() {
        return !groupName.isNull();
    }

    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getName().equals(getName())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getStudentNumber().equals((getStudentNumber()))
                && otherStudent.getStudentLink().equals((getStudentLink()))
                && otherStudent.getGroupName().equals(getGroupName())
                && otherStudent.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, email, tags, attendance, participation, studentNumber, userName, repoName, groupName);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Email: ")
                .append(getEmail())
                .append("; Student Number: ")
                .append(getStudentNumber())
                .append("; Github Link: ")
                .append(getStudentLink())
                .append("; Group Name: ")
                .append(getGroupName());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Represents a Student's github ip link in tApp.
     * Guarantees: immutable;
     */
    public static class GithubLink {

        public static final String GITHUB_ADDRESS = "https://github.com/";

        public final String fullLink;
        public final UserName userName;
        public final RepoName repoName;

        /**
         * Constructs a {@code GithubName}.
         *
         * @param userName A valid user name.
         * @param repoName A valid repo name
         */
        public GithubLink(UserName userName, RepoName repoName) {
            this.userName = userName;
            this.repoName = repoName;
            if (userName != null && repoName != null) {
                this.fullLink = GITHUB_ADDRESS + userName + "/" + repoName;
            } else {
                this.fullLink = "-";
            }
        }

        @Override
        public String toString() {
            return fullLink;
        }

        @Override
        public boolean equals(Object other) {
            return other == this // short circuit if same object
                    || (other instanceof GithubLink// instanceof handles nulls
                    && fullLink.equals(((GithubLink) other).fullLink)); // state check
        }

        @Override
        public int hashCode() {
            return fullLink.hashCode();
        }
    }
}
