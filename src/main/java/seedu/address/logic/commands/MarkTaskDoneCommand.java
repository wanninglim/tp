package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

public class MarkTaskDoneCommand extends Command {

    public static final String COMMAND_WORD = "doneTask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Mark the task identified by the index number used in the displayed task list as done.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_TASK_DONE_SUCCESS = "Task Completed: %1$s\n";

    private final List<Index> targetIndexList;

    /**
     * Marks a list of tasks with the given indexes as complete.
     * @param targetIndexList The list of indexes of tasks to be marked complete.
     */
    public MarkTaskDoneCommand(List<Index> targetIndexList) {
        requireNonNull(targetIndexList);
        this.targetIndexList = targetIndexList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> savedStateList = new ArrayList<>(model.getFilteredTaskList());
        StringBuilder result = new StringBuilder();

        for (Index targetIndex : targetIndexList) {
            if (targetIndex.getZeroBased() >= savedStateList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
            }

            Task taskToMarkCompleted = savedStateList.get(targetIndex.getZeroBased());
            model.completeTask(taskToMarkCompleted);
            result.append(String.format(MESSAGE_MARK_TASK_DONE_SUCCESS, taskToMarkCompleted));
        }

        return new CommandResult(result.toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkTaskDoneCommand // instanceof handles nulls
                && targetIndexList.equals(((MarkTaskDoneCommand) other).targetIndexList)); // state check
    }

}
