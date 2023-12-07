package entity;

public interface TaskI {

    /**
     * @return interface of get name from task class
     */
    String getName();

    /**
     * @return interface of get complete from task class
     */
    Boolean getComplete();

    /**
     * @param name interface of set name from task class
     */
    void setName(String name);

    /**
     * @param complete interface of set complete from task class
     */
    void setComplete(boolean complete);

    /**
     * @param taskId interface of set task id from task class
     */
    void setTaskId(long taskId);

    /**
     * @return interface of get taskid from task class
     */
    long getTaskId();
}
