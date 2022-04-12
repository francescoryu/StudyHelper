import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, InterruptedException {
        new Login(new Menu());
        new Countdown();
        new TodoList();


    }
}
