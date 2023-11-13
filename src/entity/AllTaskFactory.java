package entity;

import java.time.LocalDateTime;

public class AllTaskFactory implements TaskFactory{
    @Override
    public Task create(String taskName) {
        return new Task(taskName);
    }

}
