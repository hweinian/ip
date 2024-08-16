import java.util.ArrayList;
import java.util.Scanner;

public class Gravitas {
    private static int numOfTask = 0;

    public static void printTask(Task t) {
        System.out.println("  [" + t.getEventType() + "][" + t.getStatusIcon() + "] " + t.description);
        System.out.println("Now you have " + Gravitas.numOfTask + " tasks in the list.");
    }
    public static void printList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        Task currentTask;
        for (int i = 0; i < tasks.size(); i++) {
            currentTask = tasks.get(i);
            System.out.println((i + 1) + ". [" + currentTask.getEventType() + "][" + currentTask.getStatusIcon() + "] " + currentTask.description);
        }
    }

    public static void main(String[] args) {
        int count = 0;
        String name = "Gravitas";
        String greet = "____________________________________________________________\nHello! I'm " + name + "\nWhat can I do for you?\n____________________________________________________________\n";
        String header = "____________________________________________________________";
        String footer = "____________________________________________________________\n";
        String bye = "Bye. Hope to see you again soon! \n____________________________________________________________";
        String mark = "Nice! I've marked this task as done:";
        String unMark = "OK, I've marked this task as not done yet:";
        String added = "Got it. I've added this task:";
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println(greet);

        while (true) {
            String msg = sc.nextLine();
            String[] msgFrag = msg.split(" ", 2);
            System.out.println(header);
            if (msg.equals("bye")) {
                sc.close(); //close scanner
                System.out.println(bye);
                break;
            } else if (msg.equals("list")) {
                Gravitas.printList(tasks);
            } else if (msgFrag[0].equals("mark")) {
                int index = Integer.parseInt((msgFrag[1]));
                Task t = tasks.get(index - 1);
                t.markTask();
                System.out.println(mark);
                System.out.println("\t[" + t.getStatusIcon() + "] " + t.description);
            } else if (msgFrag[0].equals("unmark")) {
                int index = Integer.parseInt((msgFrag[1]));
                Task t = tasks.get(index - 1);
                t.unMarkTask();
                System.out.println(unMark);
                System.out.println("\t[" + t.getStatusIcon() + "] " + t.description);
            } else if (msgFrag[0].equals("deadline")) {
                String[] deadline = msg.split("/by ", 2);
                String[] editedMsg = deadline[0].split(" ", 2);
                String formattedMsg = editedMsg[1] + "(by: " + deadline[1] + ")";
                Deadline d = new Deadline(formattedMsg);
                tasks.add(d);
                Gravitas.numOfTask += 1;
                System.out.println(added);
                Gravitas.printTask(d);
            } else if (msgFrag[0].equals("event")) {
                String[] deadline = msg.split("/from ", 2);
                String[] editedMsg = deadline[0].split(" ", 2);
                String[] formattedDeadline = deadline[1].split("/to", 2);
                String formattedMsg = editedMsg[1] + "(from: " + formattedDeadline[0] + "to:" + formattedDeadline[1] + ")";
                Event e = new Event(formattedMsg);
                tasks.add(e);
                Gravitas.numOfTask += 1;
                System.out.println(added);
                Gravitas.printTask(e);
            } else if (msgFrag[0].equals("todo")){
                Todo t = new Todo(msgFrag[1]);
                tasks.add(t);
                Gravitas.numOfTask += 1;
                System.out.println(added);
                Gravitas.printTask(t);
            }
            System.out.println(footer);
        }
    }
}
