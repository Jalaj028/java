//we can create frame by creating its object or by extending it, bot methods works well


//here we are creating frame by creating object of the frame class
import java.awt.*;
import java.awt.event.*;

public class Development {
    Development()
    {
        Frame fm = new Frame();

        fm.setBackground(Color.blue);
        Button btn = new Button("Jalaj");
        fm.add(btn);
        btn.setSize(100, 100);
        fm.setLayout(null);
        Label lb = new Label("Welcome to java first project");
        lb.setBounds(80, 80, 200, 200);
        fm.add(lb);
        fm.setSize(400, 400);
        fm.setVisible(true);

        fm.setTitle("title");
    }

    public static void main(String[] args) {
        Development dev = new Development();
    }
}
