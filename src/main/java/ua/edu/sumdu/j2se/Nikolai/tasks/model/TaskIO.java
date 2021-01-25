package ua.edu.sumdu.j2se.Nikolai.tasks.model;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class TaskIO {
    private static final Logger log = Logger.getLogger(TaskIO.class);

    public static void LoadTaskList (AbstractTaskList tasks) {
        readBinary(tasks, new File(StringsVariables.filename));
    }

    public static void SaveTaskList (AbstractTaskList tasks) {
        writeBinary(tasks, new File(StringsVariables.filename));
    }

    public static void writeBinary( AbstractTaskList tasks, File file) {

        try(FileOutputStream out = new FileOutputStream(file)){
            write(tasks, out);
        }
        catch (IOException e)
        {
            log.fatal(e.getMessage() ,e);
        }
    }

    public static void readBinary( AbstractTaskList tasks, File file) {

        try(FileInputStream in = new FileInputStream(file)){
            read(tasks, in);
        }
        catch (IOException e)
        {
            log.fatal(e.getMessage(), e);
        }
    }

    public static void write( AbstractTaskList tasks, OutputStream out) throws IOException {
        try (DataOutputStream outputStream = new DataOutputStream(out)) {
            outputStream.writeInt(tasks.size());
            for (Task task : tasks){
                outputStream.writeInt(task.getTitle().length());
                outputStream.writeUTF(task.getTitle());
                outputStream.writeInt(task.isActive() ? 1 : 0);
                outputStream.writeInt(task.getRepeatInterval());
                if (task.getRepeatInterval() > 0) {
                    outputStream.writeLong(task.getStartTime().toEpochSecond(ZoneOffset.UTC));
                    outputStream.writeLong(task.getEndTime().toEpochSecond(ZoneOffset.UTC));
                }
                else {
                    outputStream.writeLong(task.getTime().toEpochSecond(ZoneOffset.UTC));
                }
            }
        }
    }

    public static void read( AbstractTaskList tasks, InputStream in) throws IOException {
        try (DataInputStream inputStream = new DataInputStream(in)) {
            int size = inputStream.readInt();
            for (int i = 0; i < size; i++){
                inputStream.readInt();
                String name = inputStream.readUTF();
                boolean active = inputStream.readInt() == 1;
                int interval = inputStream.readInt();
                if (interval > 0) {
                    long sTime = inputStream.readLong();
                    long eTime = inputStream.readLong();
                    LocalDateTime startTime =
                            LocalDateTime.ofInstant(Instant.ofEpochSecond(sTime),
                                    ZoneId.of( StringsVariables.zone ));
                    LocalDateTime endTime =
                            LocalDateTime.ofInstant(Instant.ofEpochSecond(eTime),
                                    ZoneId.of( StringsVariables.zone ));
                    tasks.add(new Task(name, startTime, endTime, interval, active));
                }
                else {
                    long time = inputStream.readLong();
                    LocalDateTime finalTime =
                            LocalDateTime.ofInstant(Instant.ofEpochSecond(time),
                                    ZoneId.of( StringsVariables.zone ));
                    tasks.add(new Task(name, finalTime, active));
                }
            }
        }
    }

    public static void write(AbstractTaskList tasks, Writer out) {
        try (BufferedWriter st = new BufferedWriter(out)) {
            Gson gson = new Gson();
            String taskJson = gson.toJson(tasks);
            st.write(taskJson);
        } catch (IOException e) {
            log.fatal(e.getMessage(),e);
        }
    }

    public static void read(AbstractTaskList tasks, Reader in) {
        try (BufferedReader st = new BufferedReader(in)) {
            Gson gson = new Gson();
            String json = st.readLine();
            AbstractTaskList listOfTasks = gson.fromJson(
                    json,
                    TaskListFactory.createTaskList(tasks.type).getClass()
            );
            for (Task task: listOfTasks) {
                tasks.add(task);
            }
        } catch (IOException e) {
            log.fatal(e.getMessage(), e);
        }
    }

    public static void writeText(AbstractTaskList tasks, File file){
        try (FileWriter st = new FileWriter(file)) {
            write(tasks, st);
        } catch (IOException e) {
            log.fatal(e.getMessage(),e);
        }
    }

    public static void readText(AbstractTaskList tasks, File file){
        try (FileReader fileReader = new FileReader(file)) {
            read(tasks, fileReader);
        } catch (IOException e) {
            log.fatal(e.getMessage(),e);
        }
    }
}
