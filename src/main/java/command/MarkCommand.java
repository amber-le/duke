package command;

import task.Task;

import java.util.List;

public class MarkCommand extends CrabyMessage {
    public void handleMarkCommand(String input, List<Task> tasks) {
        try {
            int tmp = input.indexOf("k");
            String checkMark = input.substring(tmp + 1).trim();
            int checkNum = (Integer.parseInt(checkMark)) - 1;
            if (checkNum >= tasks.size() || checkNum < 0) {
                System.out.print("   Oops, something wrong! Your list only have 1 to ");
                System.out.println(tasks.size() + " tasks.");
                System.out.println("   Please try again!\n" + line);
                return;
            }
            tasks.get(checkNum).setDone(true);
            System.out.println("   Nice! I've marked this task as DONE ツ:");
            System.out.println("   ╰┈➤ " + tasks.get(checkNum) + "\n" + line);
        } catch (NumberFormatException e) {
            System.out.println("   Oops!!! Looks like you used the wrong format.");
            System.out.println("   Try with: mark [integer] e.g: mark 1\n" + line);

        }
    }
}
