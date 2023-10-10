package command;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * AddTaskCommand class is a class that handle the add command.
 * It implements the CommandInterface.
 * It has a method to handle the add command.
 */
public class AddTaskCommand extends CrabyMessage implements CommandInterface{
    private static String removeKeyWords(String input) {
        input = input
                .replace("Deadline", "")
                .replace("Todo", "")
                .replace("Event", "")
                .replace("deadline", "")
                .replace("todo", "")
                .replace("event", "")
                .replace("add", "")
                .replace("Add", "");
        return input;
    }

    private static void printAddMessage(String input, List<Task> tasks) {
        System.out.println(blank + "✎ added:");
        System.out.println(blank + "╰┈➤ " + input + " - to your list");
        System.out.println(blank + "Now you have " + tasks.size() + " tasks in your list \uD83D\uDDCE.");
        System.out.println(line);
    }

    @Override
    /**
     * This method is to handle the add command.
     * It will add the task to the list.
     * @param input The input from the user.
     * @param tasks The list of tasks.
     */
    public short handleCommand(String input, List<Task> tasks) {
        input = removeKeyWords(input);
        if (input.contains("/by")) {
            String[] formatDeadline = input.split("/by");
            if (formatDeadline.length > 1) {
                try {
                    tasks.add(new Deadline(formatDeadline[0].trim(), formatDeadline[1]));
                    printAddMessage(input, tasks);
                }catch (DateTimeParseException e) {
                    System.out.println(e);
                    System.out.println(blank + "The date or the timer format is incorrect.");
                    System.out.println(blank + "╰┈➤ Please enter in format dd/mm/yyyy hhmm");
                    System.out.println(line);
                }
            } else {
                System.out.println(blank + "Oops!!! Looks like you used the wrong format.");
                System.out.println(blank + "Please give more information after use /by");
                System.out.println(line);
            }
            return 0;
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
                System.out.println(blank + "Oops!!! Looks like you used the wrong format.");
                System.out.println(blank + "Please give more information after use /from");
                System.out.println(line);
            }
            return 0;
        }
        tasks.add(new Todo(input));
        printAddMessage(input, tasks);
        return 0;
    }
}
