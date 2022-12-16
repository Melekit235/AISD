import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.*;

import static java.lang.Math.*;

public class Graph extends JFrame{
    static void hat(){
        System.out.println("Введите код задачи");
        System.out.println("1 - Все пути между вершинами");
        System.out.println("2 - Вывести матрицу смежности");
        System.out.println("0 - Конец программы");
    }
    static int ortcentr(vertex[][] matrix){
        int[] cenorg = new int[matrix.length];
        for(int j = 0; j < matrix.length; j++){
            for(int z = 0; z < matrix.length; z++){
                if(j != z){
                    ArrayList<theway> paths = new ArrayList<>();
                    int length = 0;
                    String path = "";
                    HashSet<Integer> vis = new HashSet<>();
                    paths = paths(length,z ,j,matrix,path,vis,paths);

                    if (paths.size() != 0) {
                        Comparator<theway> comp1 = new Comparator<theway>() {
                            @Override
                            public int compare(theway o1, theway o2) {
                                return Integer.compare(o1.length,o2.length);
                            }
                        };
                        Collections.sort(paths,comp1);
                        if(cenorg[j] < paths.get(0).length) {
                            cenorg[j] = paths.get(0).length;
                        }
                    }else{
                        cenorg[j] = 100000;
                    }
                }
            }
        }
        int j = 0;
        for(int z = 0; z < cenorg.length; z++){
            if (cenorg[j] > cenorg[z]){
                j = z;
            }
        }
        return (j + 1);
    }
    static ArrayList<theway> paths(int length,int start, int end, vertex[][]matrix, String path, HashSet<Integer> visited,ArrayList<theway>paths){
        if (start == end){
            paths.add(new theway(path,length));
            return paths;

        }
        for(int i = 0; i < matrix.length; i++){
            if(matrix[start][i].ispath == 1){
                if (!visited.contains(i)){
                    path = path + " -> "+( i + 1);
                    length += matrix[start][i].wt;
                    visited.add(i);
                    paths = paths(length,i,end,matrix,path,visited,paths);
                    path = path.substring(0,path.length()-5);
                    visited.remove(i);
                    length -= matrix[start][i].wt;
                }

            }
        }
        return paths;

    }


        static public void main(String[] args) {
        System.out.println("Введите размер матрицы: ");

        Scanner scanner = new Scanner(System.in);
        int i = Integer.parseInt(scanner.next());
        vertex[][] matrix = new vertex[i][i];
        for( i = 0; i < matrix.length; i++){
            for( int j = 0; j < matrix.length; j++){
                if(i != j) {
                    System.out.println("Есть ли связь между вершинами?" + (i + 1) + "->" + (j + 1));
                    System.out.println("1 - Есть, 2 - Нет");
                    int ispath = Integer.parseInt(scanner.next());
                    int wt = -1;
                    if (ispath == 1) {
                        System.out.println("Введите вес ребра");
                        wt = Integer.parseInt(scanner.next());
                    }
                    matrix[i][j] = new vertex(ispath, wt);
                }else{
                    matrix[i][j] = new vertex(2,-1);
                }
            }
        }
        i = -1;
            JFrame w = new JFrame();
            w.setSize(1000,1000);
            w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Board bor = new Board();
        while( i != 0){
            hat();
            i = Integer.parseInt(scanner.next());
            switch (i) {
                case 1 -> {
                    int a, b,l = 0;

                    ArrayList<theway> paths = new ArrayList<>();
                    HashSet<Integer> vis = new HashSet<>();
                    System.out.println("Введите две вершины");
                    a = Integer.parseInt(scanner.next());
                    b = Integer.parseInt(scanner.next());
                    String path1 = Integer.toString(a) ;
                    paths = paths(l,a - 1,b - 1,matrix,path1,vis,paths);
                    Comparator<theway> comp1 = new Comparator<theway>() {
                        @Override
                        public int compare(theway o1, theway o2) {
                            return Integer.compare(o1.length,o2.length);
                        }
                    };
                    Collections.sort(paths,comp1);
                    int ortc = ortcentr(matrix);
                    bor.ortcentr(ortc);
                    bor.path3(paths);
                    bor.matrix3(matrix);
                    bor.path3(paths);
                    bor.matrix3(matrix);
                    w.add(bor);
                    w.repaint();
                    for(int z = 0; z < paths.size(); z++){
                        System.out.println("Путь " + (z + 1) + "= " + paths.get(z).path);
                        System.out.println("Длина его пути = " + paths.get(z).length);
                    }

                }

                case 2 -> {

                    bor.matrix3(matrix);
                    w.add(bor);
                }
                case 0 -> {

                }
            }
            w.setVisible(true);
        }
        scanner.close();

    }
    static class vertex{
        int ispath, wt;
        vertex(int ispath,int wt){
            this.ispath = ispath;
            this.wt = wt;
        }
    }
    static class theway{
        String path;
        int length;
        theway(String path,int length){
            this.length = length;
            this.path = path;
        }
    }
    public static class Board extends JPanel {
        vertex[][]matrixb = new vertex[10][10];
        int ortc;
        void ortcentr(int c){
            this.ortc = c;
        }
        ArrayList<theway> paths = new ArrayList<>();
        void path3(ArrayList<theway> path){
            this.paths = path;
        }
        void matrix3(vertex[][]matrix2){
            this.matrixb = matrix2;
        }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            draw_screen(g);
        }
        private void draw_screen(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            int[][] coord = new int[matrixb.length][2];
            g2d.setPaint( Color.orange );
            for(int i = 0; i < matrixb.length; i++){
                coord[i][0] = 225 + (int) Math.floor(cos(toDegrees((i)*360/matrixb.length))*200);
                coord[i][1] = 225 + (int) Math.floor(sin(toDegrees((i)*360/matrixb.length))*200);
            }
            for(int i = 0; i < coord.length; i++){
                g2d.drawOval(coord[i][0],coord[i][1],50,50);
                g2d.fillOval(coord[i][0],coord[i][1],50,50);
                g2d.setColor(Color.BLACK);
                g2d.drawString(Integer.toString(i + 1),coord[i][0] + 25,coord[i][1] + 25);
                g2d.setColor(Color.orange);
            }
            for(int i = 0; i < matrixb.length; i++){
                for(int j = 0; j < matrixb.length; j++){
                    if (matrixb[i][j].ispath == 1){
                        g2d.drawLine(coord[i][0] + 25,coord[i][1] + 25,coord[j][0] + 25,coord[j][1] + 25);
                        g2d.setColor(Color.RED);
                        g2d.fillOval(coord[j][0] + 20 - (coord[j][0] - coord[i][0])/5,coord[j][1] + 20 - (coord[j][1] - coord[i][1])/5,10,10);
                        g2d.setColor(Color.BLACK);
                        g2d.drawString(Integer.toString(matrixb[i][j].wt),coord[j][0] + 20 - (coord[j][0] - coord[i][0])/5,coord[j][1] + 20 - (coord[j][1] - coord[i][1])/5);
                        g2d.setColor(Color.black);
                    }
                }
            }
            if (paths.size() != 0){
                g2d.drawString("Минимальный путь = " + paths.get(0).path + " ; Его длина = "+paths.get(0).length, 100,500 );
                g2d.drawString("Максимальный путь = " + paths.get(paths.size() - 1).path + " ; Его длина = "+paths.get(paths.size() - 1).length, 100,550 );
                g2d.drawString("Все пути = ",100,575);
                for(int i = 0; i < paths.size(); i++){
                    g2d.drawString("Путь " + (i + 1) + " = " + paths.get(i).path + " ; Длина = "+paths.get(i).length,100,600 + i*25);
                }
                g2d.drawString("Центр орграфа = " + ortc,100,475);
            }

        }
    }


}
