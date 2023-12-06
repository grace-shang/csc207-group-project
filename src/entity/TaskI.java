package entity;

public interface TaskI {

    String getName();

    boolean getComplete();

    void setName(String name);

    void setComplete(boolean complete);

    void setTaskId(long taskId);
    long getTaskId();
}
