package ua.edu.sumdu.j2se.Nikolai.tasks;



public class ArrayTaskList {

    private Task taskListArray[];

    public ArrayTaskList(){
        this.taskListArray = new Task[0];
    }

    public void add(Task task){
        Task  tempArray[] = taskListArray.clone();
        taskListArray = new Task[(size() + 1)];
        System.arraycopy(tempArray, 0, taskListArray, 0, tempArray.length);
        taskListArray[size() - 1] = task;
    }

    public boolean remove(Task task){
        for (int i = 0; i < size(); i++){
            if (task.equalsTask(taskListArray[i])){
                for (int j = i; j < size() - 1; j++){
                    taskListArray[j] = taskListArray[j + 1];
                }

                Task  tempArray[] = taskListArray.clone();
                taskListArray = new Task[size() - 1];
                System.arraycopy(tempArray, 0, taskListArray, 0, size());
                return true;
            }

        }
        return false;
    }

    public int size(){
        return taskListArray.length;
    }

    public Task getTask(int index){
        if (size() > index && index >= 0){
            return taskListArray[index];
        }
        else{
            Task temp = new Task();
            return temp;
        }
    }

    public ArrayTaskList incoming(int from, int to){

        ArrayTaskList incomingTasks = new ArrayTaskList();

        for (int i = 0; i < size(); i++){
            if (taskListArray[i].isActive()) {
                if (!taskListArray[i].isRepeated() && taskListArray[i].getTime() > from &&
                        taskListArray[i].getTime() <= to) {

                    incomingTasks.add(taskListArray[i]);

                } else if (taskListArray[i].isRepeated() && taskListArray[i].getRepeatInterval() > 0) {

                    for (int j = taskListArray[i].getStartTime(); j < taskListArray[i].getEndTime();
                         j = j + taskListArray[i].getRepeatInterval()) {

                        if (j > from && j <= to) {
                            incomingTasks.add(taskListArray[i]);
                            break;
                        }
                    }
                }
            }
        }
        return incomingTasks;
    }
}
