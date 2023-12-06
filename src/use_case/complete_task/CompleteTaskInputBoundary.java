package use_case.complete_task;

import java.io.IOException;

public interface CompleteTaskInputBoundary {
    void execute(CompleteTaskInputData completeTaskInputData) throws IOException;
}
