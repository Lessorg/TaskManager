package ua.edu.sumdu.j2se.Nikolai.tasks.model;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import ua.edu.sumdu.j2se.Nikolai.tasks.model.observer.LinkedListObserver;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class LinkedTaskList extends AbstractTaskList {
    private static final Logger log = Logger.getLogger(LinkedTaskList.class);

    private Node head;
    private int size;
    private LinkedListObserver saveChecker;

    public void setSaveChecker(LinkedListObserver saveChecker) {
        this.saveChecker = saveChecker;
    }

    public LinkedTaskList(){
        this.size = 0;
        this.head = null;
        saveChecker= null;
        type = ListTypes.LINKED;
    }

    private static class Node {
        public Task task;
        public Node nextNode;

        Node(Task date){
            this.task = date;
            nextNode = null;
        }
    }

    @Override
    public @NotNull Iterator<Task> iterator() {

        return new Iterator<Task>() {
            Node currentNode = head;
            Node previousNode = null;

            @Override
            public boolean hasNext() {
                return head != null && currentNode != null;
            }

            @Override
            public Task next() {
                previousNode = currentNode;
                currentNode = currentNode.nextNode;
                return previousNode.task;
            }

            @Override
            public void remove() {
                try {
                    if(previousNode == null) {
                        Exception e = new IllegalStateException(StringsVariables.illegalEx);
                        log.fatal(e.getMessage(), e);
                    }
                    LinkedTaskList.this.remove(previousNode.task);
                }
                catch (IllegalStateException | UnsupportedOperationException e){
                    log.fatal(e.getMessage(), e);
                }
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedTaskList tasks = (LinkedTaskList) o;
        if(size == tasks.size){
            if (size == 0)
                return true;
            Node currentNode = head;
            for (Task task:tasks) {
                if(task.equals(currentNode.task)){
                    currentNode = currentNode.nextNode;
                }
                else {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        for (Object obj : this) {
            hashCode = 31 * hashCode + (obj == null ? 0 : obj.hashCode());
        }
        return hashCode;
    }

    @Override
    public LinkedTaskList clone() {
        LinkedTaskList ResultList;
        try {
            ResultList = (LinkedTaskList)super.clone();
        }
        catch( CloneNotSupportedException e ) {
            log.fatal(e.getMessage() , new CloneNotSupportedException());
            ResultList = null;
        }
        return ResultList;
    }

    @Override
    public void add(Task task){
        Node newNode = new Node(task);

        if (head == null){
            head = newNode;
        }
        else{
            Node currentNode = head;

            while(currentNode.nextNode != null){
                currentNode = currentNode.nextNode;
            }

            currentNode.nextNode = newNode;
        }
        size++;
        if (saveChecker != null)
        saveChecker.update();
    }

    @Override
    public boolean remove(int id){

        if (head != null){
            Node currentNode = head;
            Node previousNode = head;

            for (int i = 0; i < size; i++) {
                if (currentNode.task.getId() == id) {
                    if (previousNode == currentNode) {
                        head = currentNode.nextNode;
                    } else {
                        previousNode.nextNode = currentNode.nextNode;
                    }
                    size--;

                    saveChecker.update();
                    return true;
                }
                previousNode = currentNode;
                currentNode = currentNode.nextNode;
            }
        }
        return false;
    }

    @Override
    public boolean remove(Task task){

        if (head != null){
            Node currentNode = head;
            Node previousNode = head;

            for (int i = 0; i < size; i++) {
                if (task.equals(getTask(i))) {
                    if (previousNode == currentNode) {
                        head = currentNode.nextNode;
                    } else {
                        previousNode.nextNode = currentNode.nextNode;
                    }
                    size--;

                    saveChecker.update();
                    return true;
                }
                previousNode = currentNode;
                currentNode = currentNode.nextNode;
            }
        }
        return false;
    }

    @Override
    public int size(){
        return this.size;
    }

    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException{
        if (size > index && index >= 0){

            Node currentNode = head;
            int currentIndex = 0;

            while(currentIndex != index){
                currentNode = currentNode.nextNode;
                currentIndex++;
            }
            return currentNode.task;
        }
        else{
            Exception e = new IndexOutOfBoundsException("Index out of range: index = " + index);
            log.fatal(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            result.append(getTask(i).toString());
        }
        return result.toString();
    }

    @Override
    public Stream<Task> getStream() {
        List<Task> list = new LinkedList<>();
        for (Node i = head; i != null; i = i.nextNode){
            list.add(i.task);
        }
        return list.stream();
    }
}
