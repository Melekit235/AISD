import java.awt.*;
import java.awt.event.KeyEvent;

public class Game implements Runnable {

    public static final int[][] flour1 ={
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {9,0,0,1,0,0,0,1,0,0,0,0,1,0,0,0,0,0,1},
            {1,0,1,1,0,1,0,1,0,1,1,0,1,0,1,1,1,0,1},
            {1,0,0,0,0,1,0,0,0,0,1,0,1,0,1,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,0,1,0,1,0,1,0,1,1,1},
            {1,0,0,0,0,0,0,0,1,0,1,0,0,0,1,0,0,0,1},
            {1,0,1,1,1,1,1,0,1,1,1,1,1,1,1,0,1,0,1},
            {1,0,1,0,0,0,1,0,0,0,0,0,0,0,1,0,1,0,1},
            {1,0,1,1,1,0,0,0,1,1,1,1,1,0,1,1,1,0,1},
            {1,0,0,0,1,1,1,1,1,0,1,0,0,0,0,0,0,0,1},
            {1,0,1,0,0,0,0,1,0,0,0,0,1,1,1,1,1,0,1},
            {1,0,1,1,1,1,0,1,1,1,1,1,1,0,0,0,1,0,1},
            {1,0,0,0,1,0,0,1,0,0,0,1,0,0,1,0,1,0,1},
            {1,1,1,0,1,0,1,1,0,1,0,1,0,1,1,0,1,1,1},
            {1,0,0,0,1,0,0,0,0,1,0,1,0,0,1,0,0,0,1},
            {1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,0,1},
            {1,0,1,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,1},
            {1,0,1,0,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1},
            {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };
    public static final int[][] flour2 ={

            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1},
            {1,0,1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,0,1},
            {1,0,0,0,0,0,1,0,0,0,1,0,0,0,1,6,1,0,1},
            {1,0,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,0,1},
            {1,0,1,0,1,0,0,0,1,0,1,0,1,0,0,0,1,0,1},
            {1,1,1,0,1,0,1,0,1,1,1,0,1,1,1,1,1,0,1},
            {1,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,0,1,0,1,1,1,1,1,0,1,0,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,1},
            {1,0,1,1,1,0,1,1,1,1,1,1,1,0,1,0,1,1,1},
            {1,0,0,0,1,0,1,0,0,0,0,0,0,0,1,0,0,0,1},
            {1,0,1,1,1,0,1,0,1,1,1,1,1,0,1,1,1,0,1},
            {1,0,1,0,1,0,1,0,1,0,0,0,1,0,0,0,1,0,1},
            {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
            {1,0,1,0,1,1,1,0,1,0,1,0,1,0,1,0,1,0,1},
            {1,5,1,0,0,0,0,0,1,0,1,0,0,0,1,0,1,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    };
    public static final int[][] flour3 ={
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,1},
            {1,0,1,1,1,1,1,0,1,0,1,1,1,1,1,0,1,1,1},
            {1,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,1,0,1},
            {1,0,1,0,1,1,1,1,1,0,1,0,0,0,0,0,1,0,1},
            {1,0,1,0,0,0,1,0,0,0,1,0,1,1,1,1,1,0,1},
            {1,0,1,1,1,0,1,0,1,1,1,0,1,0,0,0,1,0,1},
            {1,0,1,0,0,0,1,0,0,0,1,0,1,0,1,0,1,0,1},
            {1,0,1,0,1,1,1,0,1,1,1,0,0,0,1,0,0,0,1},
            {1,0,1,0,1,0,0,0,0,0,1,1,1,0,1,0,1,0,1},
            {1,0,1,0,1,1,1,1,1,0,1,0,1,0,1,0,1,0,1},
            {1,0,1,0,0,0,1,6,1,0,0,0,1,0,1,1,1,0,1},
            {1,0,1,1,1,0,1,0,1,0,1,0,0,0,0,0,0,0,1},
            {1,0,0,0,1,0,1,0,1,0,1,0,1,0,1,1,1,1,1},
            {1,1,1,0,1,0,0,0,1,0,1,0,1,0,1,0,0,0,1},
            {1,0,1,0,1,1,1,1,1,0,1,0,0,0,0,0,1,0,1},
            {1,0,1,0,0,0,0,0,1,0,0,0,1,1,1,0,1,0,1},
            {1,0,0,0,1,1,0,0,1,1,1,0,0,1,0,0,0,0,1},
            {1,0,1,0,1,0,0,0,0,0,0,0,1,1,0,1,0,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    };


    public static final int WIDTH=1000;
    public static final int HEIGHT=950;
    public static final int CLEAR_COLOR=0xff000000;
    public static final int NUM_BUFFERS=3;

    public static final float UPDATE_RATE = 60.0f;
    public static final float UPDATE_INTERVAL =Time.SECOND/UPDATE_RATE;
    public static final long IDLE_TIME = 1;
    public static final int SCALE=50;

    public static int NUM_FLOUR=3;

    private static final String H ="Вас похитили пришельцы, и затащили на свой корабль. Когда вас завели";
    private static final String He = "внутрь вы заметили что на этаже c выходом зеленые стены, однако";
    private static final String Hel ="это заметили и завязали вам глаза. По ощущения вы поняли что вас ";
    private static final String Hell  = "подняли на 3 этаж. Теперь ваша задача сбежать с этого коробля.";
    private static final String Hello  = "Нажмите пробел, что бы начать";

    private boolean running;
    private boolean start=false;
    private Thread gameThread;

    private final Graphics2D graphics;

    float x =910;
    float y =810;
    float radius = 10;

    private final Input input;

    public Game(){
        running=false;
        Display.create(WIDTH,HEIGHT,CLEAR_COLOR,NUM_BUFFERS);
        input = new Input();
        Display.addInputListener(input);
        graphics = Display.getGraphics();
    }

    public synchronized void start(){
        if (running)
            return;

        running=true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public synchronized void stop(){
        if(!running)
            return;
        start=false;
        while (!start) {
            Display.swapBuffers();
            graphics.setColor(Color.white);
            graphics.setFont(new Font("ARIAL",Font.PLAIN,SCALE));
            graphics.drawString("Вы сбежали с коробля", 200, 450);

            Display.swapBuffers();
        }

        running=false;

        try {
            gameThread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        cleanUp();


    }

    private void update(int[][] flour){
        if(input.getKey(KeyEvent.VK_UP)){
            y-=4;
            int i = (int) (x/50);
            int j = (int) (y/50);
            if (flour[i][j]==1){
                y+=4;
            }
        }
        if(input.getKey(KeyEvent.VK_DOWN)){
            y+=4;
            int i = (int) (x/50);
            int j = (int) ((y+17)/50);
            if (flour[i][j]==1){
                y-=4;
            }
        }
        if(input.getKey(KeyEvent.VK_LEFT)){
            x-=4;
            int i = (int) ((x)/50);
            int j = (int) (y/50);
            if (flour[i][j]==1){
                x+=4;
            }
        }
        if(input.getKey(KeyEvent.VK_RIGHT)){
            x+=4;
            int i = (int) ((x+15)/50);
            int j = (int) (y/50);
            if (flour[i][j]==1){
                x-=4;
            }
        }

        int i = (int) ((x+15)/50);
        int j = (int) (y/50);
        if (flour[i][j]==5){
            NUM_FLOUR++;
            switch (NUM_FLOUR) {
                case 2 -> {
                    x = 4 * SCALE;
                    y = 15 * SCALE;
                }
                case 3 -> {
                    x = 12 * SCALE;
                    y = 7 * SCALE;
                }
            }
        }
        if (flour[i][j]==6){
            NUM_FLOUR--;
            switch (NUM_FLOUR) {
                case 1 -> {
                    x = 18 * SCALE;
                    y = 16 * SCALE;
                }
                case 2 -> {
                    x = 17 * SCALE;
                    y = SCALE;
                }
            }
        }
        if (flour[i][j]==9){
            stop();
        }
    }

    private void drawFlour(int[][] flour){
        switch (NUM_FLOUR) {
            case 1 -> graphics.setColor(Color.green);
            case 2 -> graphics.setColor(Color.YELLOW);
            case 3 -> graphics.setColor(Color.red);
        }

        for (int i=0;i<20;i++){
            for (int j = 0; j<19;j++){
                switch (flour[i][j]) {
                    case 1 -> graphics.fillRect(i * SCALE, j * SCALE, SCALE, SCALE);
                    case 5 -> {
                        graphics.fillRect(i * SCALE + 20, j * SCALE+3, 10, SCALE - 10);
                        graphics.fillRect(i * SCALE + 10, j * SCALE + 10, 10, 10);
                        graphics.fillRect(i * SCALE + 30, j * SCALE + 10, 10, 10);
                    }
                    case 6 -> {
                        graphics.fillRect(i * SCALE + 20, j * SCALE + 5, 10, SCALE - 10);
                        graphics.fillRect(i * SCALE + 10, j * SCALE + 23, 10, 10);
                        graphics.fillRect(i * SCALE + 30, j * SCALE + 23, 10, 10);
                    }
                }
            }
        }
        graphics.setColor(Color.white);
    }

    private void render(){
        Display.clear();
        while (!start) {

            graphics.setColor(Color.white);
            graphics.setFont(new Font("ARIAL",Font.PLAIN,SCALE/2));
            graphics.drawString(H, 50, 50);
            graphics.drawString(He, 50, 100);
            graphics.drawString(Hel, 50, 150);
            graphics.drawString(Hell, 50, 200);
            graphics.drawString(Hello, 50, 250);

            graphics.fillRect(50 + 20, 300, 10, SCALE - 10);
            graphics.fillRect(50 + 10, 300 + 10, 10, 10);
            graphics.fillRect(50 + 30, 300 + 10, 10, 10);
            graphics.drawString(" - Подьем на этаж выше ", 100, 325);

            graphics.fillRect(50 + 20, 350 + 5, 10, SCALE - 10);
            graphics.fillRect(50 + 10, 350 + 23, 10, 10);
            graphics.fillRect(50 + 30, 350 + 23, 10, 10);
            graphics.drawString(" - Спуск на этаж ниже ", 100, 385);

            if(input.getKey(KeyEvent.VK_SPACE))
                start=true;

            Display.swapBuffers();
        }
        graphics.fillOval((int)x,(int)y,(int)radius*2,(int)radius*2);
        switch (NUM_FLOUR) {
            case 1 -> drawFlour(flour1);
            case 2 -> drawFlour(flour2);
            case 3 -> drawFlour(flour3);
        }
        Display.swapBuffers();
    }

    private void cleanUp(){
        Display.destroy();
    }

    public void run() {
        float delta = 0;

        long lastTime = Time.get();

        while (running){
            long now = Time.get();
            long elapsedTime = now - lastTime;
            lastTime=now;

            boolean render = false;
            delta += (elapsedTime / UPDATE_INTERVAL);
            while (delta>1){
                switch (NUM_FLOUR) {
                    case 1 -> update(flour1);
                    case 2 -> update(flour2);
                    case 3 -> update(flour3);
                }
                render= true;
                delta--;
            }
            if(render){
                render();
            } else{
                try {
                    Thread.sleep(IDLE_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
