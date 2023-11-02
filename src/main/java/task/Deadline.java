package task;

import utils.DateTimeUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a deadline task.
 * It extends from the Task class.
 */
public class Deadline extends Task {
    protected LocalDateTime time;

    /**
     * Returns the LocalDateTime object.
     * This method is to handle the date and time.
     * It will use for the sort function.
     *
     * @return The LocalDateTime object.
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
     * @inheritDoc
     */
    @Override
    public String toString() {
        String timeString = this.time.format(DateTimeFormatter.ofPattern(TIME_OUTPUT_FORMAT));
        return "[D]" + super.toString() + " (before: " + timeString.trim() + ")";
    }

    /**
     * @inheritDoc
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
     * @inheritDoc
     */
    @Override
    public Task clone() {
        Deadline clone = new Deadline(this.description, this.time.format(DateTimeFormatter.ofPattern("yyyy/M/d HHmm")));
        clone.setDone(this.isDone);
        return clone;
    }
}