package seedu.address.testutil;

import java.util.HashSet;

import seedu.address.model.task.DeadlineTask;
import seedu.address.model.task.Description;
import seedu.address.model.task.TaskDate;

/**
 * A utility class to help with building Task objects.
 */
public class DeadlineTaskBuilder extends TaskBuilder {

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public DeadlineTaskBuilder() {
        super();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public DeadlineTaskBuilder(DeadlineTask taskToCopy) {
        super();
        super.taskName = taskToCopy.getName();
        super.taskDate = new TaskDate(taskToCopy.getDeadline().toString());
        super.tags = new HashSet<>(taskToCopy.getTags());
        super.isDone = taskToCopy.checkIsDone();
        super.description = new Description(taskToCopy.getDescription());
    }

    @Override
    public DeadlineTask build() {
        return new DeadlineTask(taskName, tags, false, taskDate, description, priority);
    }

}
