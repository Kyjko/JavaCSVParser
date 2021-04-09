package basic.framework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Display extends Canvas {
    private JFrame frame;
    private final int W = 1000;
    private final int H = 1000;

    private double maxDataVal = -999999999;

    private int dataStart = 0;
    private int dataEnd = 100;

    private Graphics g;
    private ArrayList<Double> num_data;

    private boolean closed;

    public Display() {
        num_data = new ArrayList<>();

        frame = new JFrame("Figure");
        frame.setSize(W, H);
        frame.setLocationRelativeTo(null);
        frame.setFocusable(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("graph window closed");
                closed = true;
                e.getWindow().dispose();
            }
        });

        frame.setVisible(true);
        frame.add(this);

        this.createBufferStrategy(2);
        BufferStrategy bs = this.getBufferStrategy();
        g =  bs.getDrawGraphics();
    }

    private Consumer<String> stringToNum = s -> {
        try {
            double d = Double.valueOf(s);
            num_data.add(d);
            if(d >= maxDataVal) {
                maxDataVal = d;
            }

        } catch(NumberFormatException ex) {}
    };

    public void graph(HashMap<String, List<String>> data, String key, int dataStart, int dataEnd) {
        this.dataStart = dataStart;
        this.dataEnd = dataEnd;

        if(key != null) {
            Stream<String> datacol = data.get(key).stream();
            datacol.forEach(stringToNum);
        } else {
            System.err.println("Missing field specifier!");
            return;
        }
        this.createBufferStrategy(2);
        BufferStrategy bs = this.getBufferStrategy();
        g = bs.getDrawGraphics();
        while(!closed) {
            g.setColor(Color.darkGray);
            g.fillRect(0, 0, W, H);
            int dataN = dataStart;
            for(Double i : num_data) {
                if(dataN <= dataEnd) {
                    g.setColor(new Color((int)Math.round(i/maxDataVal*255), (int)Math.round((maxDataVal-i)/maxDataVal*255), 0));
                    g.fillRect(dataN * W/(dataEnd - dataStart), H/2 - (int)Math.round(i*0.1), W/(dataEnd - dataStart), (int)Math.round(i*0.1));
                }
                dataN++;
            }

            g.dispose();
            bs.show();
        }
    }

}
