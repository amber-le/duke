package io;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskStorage {
    String filePath;

    public TaskStorage(String filePath) {
        this.filePath = filePath;
    }

    public void save(List<Task> tasks) {
        try {
            StringBuilder content = new StringBuilder();
            for (Task task : tasks) {
                content.append(task.toString());
                content.append(System.lineSeparator());
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath));
            writer.write(content.toString());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line;
            while ((line = reader.readLine()) != null) {
                // parse task
                char checkTask = line.charAt(1);
                boolean isDone = false;
                if(line.charAt(5) == '✓'){
                    isDone = true;
                }
                String content = line.substring(9);
                switch (checkTask){
                    case 'D':
                        String[] formatDeadline = content.split(" \\(by:");
                        Deadline deadline = new Deadline(formatDeadline[0], formatDeadline[1]);
                        deadline.setDone(isDone);
                        tasks.add(deadline);
                        break;
                    case 'E':
                        String[] formatEvent = content.split(" \\(from:");
                        String time = formatEvent[1];
                        time = time.substring(0,time.length()-1);
                        Event event = new Event(formatEvent[0], time);
                        event.setDone(isDone);
                        tasks.add(event);
                        break;
                    default:
                        Todo todo = new Todo(content);
                        todo.setDone(isDone);
                        tasks.add(todo);
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
