package command;

import task.Task;

import java.util.List;

public class DeleteCommand extends CrabyMessage implements CommandInterface {
    @Override
    public void handleCommand(String input, List<Task> tasks) {
        try {
            int tmp = input.indexOf("t");
            String checkDelete = input.substring(tmp + 2).trim();
            int checkNum = (Integer.parseInt(checkDelete)) - 1;
            if (checkNum >= tasks.size() || checkNum < 0) {
                System.out.print(blank + "Oops!!! Something wrong! Your list only have 1 ➞ ");
                System.out.println(tasks.size() + " tasks.");
                System.out.println(blank + "Please try again!");
                System.out.println(line);
                return;
            }
            System.out.println(blank + "✂ Removed:");
            System.out.println(blank + "╰┈➤ " + tasks.get(checkNum) + " in your list");
            tasks.remove(checkNum);
            System.out.println(blank + "Now you have: " + tasks.size() + " tasks your the list \uD83D\uDDCE.");
            System.out.println(line);
        } catch (NumberFormatException nfe) {
            System.out.println(blank + "Oops!!! Looks like you used the wrong format.");
            System.out.println(blank + "Try with: delete [integer] e.g: unmark 1");
            System.out.println(line);
        }
    }
}
