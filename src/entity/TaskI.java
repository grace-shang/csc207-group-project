package entity;

public interface TaskI {

    String getName();

    String getProjectName();

    boolean getComplete();

    void setName(String name);

    void setComplete(boolean complete);
}
