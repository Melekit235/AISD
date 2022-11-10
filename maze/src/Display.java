import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public abstract class Display {

    private static boolean create = false;
    public static JFrame window;
    public static Canvas content;

    private static BufferedImage buffer;
    private static int[] bufferData;
    private static Graphics bufferGraphics;
    private static int clearColor;
    private static BufferStrategy bufferStrategy;

    public static void create(int width, int height,int _clearColor,int numBuffer){
        if(create)
            return;

        window = new JFrame("lab");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content= new Canvas();

        Dimension size = new Dimension(width,height);
        content.setPreferredSize(size);


        window.setResizable(false);
        window.getContentPane().add(content);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        buffer = new BufferedImage(width,height, BufferedImage.TYPE_INT_ARGB);
        bufferData = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData();
        bufferGraphics = buffer.getGraphics();
        ((Graphics2D)bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        clearColor = _clearColor;

        content.createBufferStrategy(numBuffer);
        bufferStrategy = content.getBufferStrategy();

        create=true;
    }

    public static void clear(){
        Arrays.fill(bufferData,clearColor);
    }

    public static void swapBuffers(){
        Graphics g = bufferStrategy.getDrawGraphics();
        g.drawImage(buffer,0,0,null);
        bufferStrategy.show();
    }

    public static Graphics2D getGraphics(){
        return (Graphics2D) bufferGraphics;
    }

    public  static void destroy(){
        if(!create)
            return;

        window.dispose();
    }

    public static void addInputListener(Input inputListener){
        window.add(inputListener);
    }

}
