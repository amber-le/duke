package command;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.List;

public class AddTaskCommand extends CrabyMessage {
    public void addTaskCommand(String input, List<Task> tasks) {
        input = input
                .replace("task.Deadline", "")
                .replace("task.Todo", "")
                .replace("task.Event", "")
                .replace("deadline", "")
                .replace("todo", "")
                .replace("event", "");
        if (input.contains("/by")) {
            String[] formatDeadline = input.split("/by");
            if (formatDeadline.length > 1) {
                tasks.add(new Deadline(formatDeadline[0].trim(), formatDeadline[1]));
                printAddMessage(input, tasks);
            } else {
                System.out.println("   Oops!!! Looks like you used the wrong format.");
                System.out.println("   Please give more information after use /by\n" + line);
            }
            return;
        }
        if (input.contains("/from")) {
            String[] formatEvent = input.split("/from");
            if (formatEvent.length > 1) {
                if (formatEvent[1].contains(("/to"))) {
                    String[] timeEvent = formatEvent[1].split("/to");
                    tasks.add(new Event(formatEvent[0].trim(), timeEvent[0], timeEvent[1]));
                } else {
                    tasks.add(new Event(formatEvent[0].trim(), formatEvent[1]));
                }
                printAddMessage(input, tasks);
            } else {
                System.out.println("   Oops!!! Looks like you used the wrong format.");
                System.out.println("   Please give more information after use /from\n" + line);
            }
            return;
        }
        tasks.add(new Todo(input));
        printAddMessage(input, tasks);
    }

    private static void printAddMessage(String input, List<Task> tasks) {
        System.out.println("   ✎ added: " + input + " - to your list.");
        System.out.println("   You have " + tasks.size() + " tasks in the list \uD83D\uDDCE.\n" + line);
    }
}
