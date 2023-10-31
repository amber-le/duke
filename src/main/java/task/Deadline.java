package task;

import utils.DateTimeUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a deadline task.
 */
public class Deadline extends Task {
    protected LocalDateTime time;

    /**
     * This method is to handle the date and time.
     * It will use for the sort function.
     *
     * @return The LocalDateTime object
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * This class represents a deadline task that user input have /by
     *
     * @param description
     * @param timeString
     */
    public Deadline(String description, String timeString) {
        super(description);
        timeString = timeString.trim();
        LocalDateTime dateTime = DateTimeUtils.parseNextDay(timeString);
        if (dateTime != null) {
            this.time = dateTime;
            return;
        }

        String[] isTime = timeString.split(" ");
        if (isTime.length > 1) {
            this.time = handleDateTime(timeString);
        } else {
            this.time = handleDateTime(timeString + " 0000");
        }
    }

    /**
     * This method is to handle the date and time.
     *
     * @return The LocalDateTime object to the format requirement.
     */
    @Override
    public String toString() {
        String timeString = this.time.format(DateTimeFormatter.ofPattern(TIME_OUTPUT_FORMAT));
        return "[D]" + super.toString() + " (before: " + timeString.trim() + ")";
    }

    /**
     * This method is to save the data to the local file
     */
    @Override
    public String toStorageString() {
        String type = "D";
        String status = isDone ? "1" : "0";
        String description = this.description;
        String time = this.time.format(DateTimeFormatter.ofPattern("yyyy/M/d HHmm"));
        return type + " || " + status + " || " + description + " || " + time;
    }

    /**
     * This method will clone the task.
     * It will use for the undo function.
     *
     * @return The LocalDateTime object to the format requirement.
     */
    @Override
    public Task clone() {
        Deadline clone = new Deadline(this.description, this.time.format(DateTimeFormatter.ofPattern("yyyy/M/d HHmm")));
        clone.setDone(this.isDone);
        return clone;
    }
}