package command;

import io.HelpMessage;
import static io.NaturalDatesMessage.printNaturalDatesList;
import task.Task;

import java.util.List;

/**
 * This class will print out the help message.
 */
public class HelpCommand extends HelpMessage implements CommandInterface {

    /**
     * @inheritDoc Sends the help message to the user.
     */
    @Override
    public void handleCommand(String input, List<Task> tasks) {
        assert input != null;
        assert tasks != null;
        if (input.equalsIgnoreCase("help")){
            printHelpMessage();
            printHelpMessage1();
        }else {
            printNaturalDatesList();
        }
    }
}
