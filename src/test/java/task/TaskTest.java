package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void getStatusIcon() {

    }

    @Test
    void setDone(boolean b) {
         Task task = new Task("test description");
         task.setIsDone(true);
         assertEquals(true, task.getIsDone());
         task.setIsDone(false);
         assertEquals(false, task.getIsDone());
    }

    @Test
    void getIsDone() {
        Task task = new Task("test description");
        assertEquals(false, task.getIsDone());
    }

    @Test
    void handleDateTime() {
        Event event = new Event("test description", "2023/10/20 2359");
        assertEquals("2023-10-20T23:59", event.handleDateTime("2023/10/20 2359").toString());
    }

    @Test
    void testToString() {
        Task task = new Task("test description");
        assertEquals("[҉҉҉] Test description", task.toString());
    }

    @Test
    void toStorageString() {
        Task task = new Task("test description");
        assertEquals("[҉҉҉] Test description", task.toStorageString());

    }
}