package com.example.demo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.skin.TreeViewSkin;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
public class HelloApplication extends Application {
         int Rad = 15; //Радиус элемента дерева
         int Len = 40;//Расстояние между уровнями по вертикали

static class Tree {
    Tree left;
    Tree right;
    int value;
    boolean thread;
    int x;
    int y;
}
    Tree prev, el, x;
    public Tree tree;

    public Tree delEl ( int value, Tree der, Tree PrevEl){
        prev = PrevEl;
        el = find(value, der);
        if (el == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Удаляемый элемент не найден!");
            alert.setContentText("Повторите попытку удаления");
            alert.showAndWait();
        } else {
            if (el.left == null) {
                if (prev.left == el) {
                    prev.left = el.right;
                } else {
                    prev.right = el.right;
                }
            } else {
                if (el.right == null || el.thread) {
                    if (prev.left == el) {
                        prev.left = el.left;
                    } else {
                        prev.right = el.left;
                    }
                } else {
                    x = el.right;
                    while (x.left != null) {
                        x = x.left;
                    }
                    el.value = x.value;
                    PrevEl = delEl(x.value, el.right, el);
                }
            }
        }
        return PrevEl;
    }

    public String obr(Tree el) {
        if (el == null) {
            return "0 ";
        }
        String res = "";
        res = res + "" + el.value + ' ';//res=""+el.value+' ';
        res = res + obr(el.left);
        res = res + "" + el.value + ' ';
        res = res + obr(el.right);
        res = res + '(' + "" + el.value + ')' + ' ';
        return res;
    }

    public String pram(Tree el) {
        if (el == null) {
            return "0 ";
        }
        String res = "";
        res = res + '(' + "" + el.value + ')' + ' ';
        res = res + pram(el.left);
        res = res+ "" + el.value + ' ';
        res = res + pram(el.right);
        res = res + "" + el.value + ' ';

        return res;
    }

    public Tree add(int el, Tree rez) {
        if (rez == null) {
            rez = new Tree();
            rez.value = el;
            rez.left = null;
            rez.right = null;
            rez.thread = false;
        }

            else{
            if (el < rez.value) {
                rez.left = add(el, rez.left);
            } else {
                rez.right = add(el, rez.right);
            }
        }
        return rez;
    }

    public String SimAndSew(Tree e, GraphicsContext g,HelloController gr) {
        Tree el=e;
        prev = el.left;
        String res = "";
        res = sim(el.left, g);
        if (prev.right == null) {
            prev.thread = true;
            prev.right = el;
            DrawL(prev.x + Rad, prev.y,  (int)gr.canv.getWidth()- 10, prev.y, g);
            DrawL((int)gr.canv.getWidth() - 10, prev.y, el.x, el.y + Rad, g);
        }
        return res;
    }

    public String sim(Tree e, GraphicsContext g) {
        Tree el=e;
        String res = "";
        if (el == null) {
            return "0 ";
        }
        res = "" + el.value + ' ';
        res = res + sim(el.left, g);
        res = res + '(' + "" + el.value + ')' + ' ';
        if (prev.right == null || prev.thread) {
            prev.thread = true;
            prev.right = el;
            DrawL(prev.x + Rad, prev.y, el.x, el.y + Rad, g);
        } else {
            prev.thread = false;
        }
        prev = el;
        if (!(el.thread)) {
            res = res + sim(el.right, g);
            res = res + "" + el.value + ' ';
        }
        else {
            res=res+'0'+' '+""+el.value+' ';
        }
        return res;
    }

    public Tree find(int val, Tree der) {
        if (der == null) {
            return der;
        }
        if (der.value == val) {
            return der;
        } else {
            prev = der;
            if (der.value < val) {
                return find(val, der.right);
            } else {
                return find(val, der.left);
            }
        }
    }
    public void DrawL(int x0, int y0, int x, int y, GraphicsContext g) {
        int x1, x2, y1, y2, i;
        if (x > x0) {
            x1 = x0;
            x2 = x;
            y1 = y0;
            y2 = y;
        } else {
            x1 = x;
            x2 = x0;
            y1 = y;
            y2 = y0;
        }
        i = x1;
        while (i <= x2) {
            g.setStroke(Color.BLACK);
            g.strokeLine(i,y1,i+5,y1);
            i += 10;
        }
        i = y1;
        if (y1 > y2) {
            while (i >= y2) {
                g.setStroke(Color.BLACK);
                g.strokeLine(x2,i,x2,i-5);
                i -= 10;
            }
        } else {
            while (i <= y2) {
                g.setStroke(Color.BLACK);
                g.strokeLine(x2,i,x2,i-5);
                i += 10;
            }
        }
    }

    public void delThread(Tree El) {
        if (El == null) {
            return;
        }
        delThread(El.left);
        if (!El.thread) {
            delThread(El.right);
        }
        if (El.thread) {
            El.thread = false;
            El.right = null;
        }
    }

    public void DrawRec(Tree el, int y, int x1, int x2, GraphicsContext g) {
        g.setFill(Color.WHITE);
        g.setFill(Color.ORANGE);
        g.fillOval((x2+x1) / 2-15,y-7,2*Rad,Rad*2);
        g.setFill(Color.BLACK);
        g.fillText("" + el.value, (x2 + x1) / 2 - Rad + 11, y + 10);
        g.setFill(Color.WHITE);
        el.x = (x2 + x1) / 2;
        el.y = y + Rad;
        if (el.left != null) {
            g.strokeLine((x1 + x2) / 2, y + Rad * 2-7,((x1 + x2) / 2 + x1) / 2, y + Rad * 2 + Len);
            DrawRec(el.left, y + Rad * 2 + Len, x1, (x1 + x2) / 2, g);
        }
        if (el.right != null && !el.thread) {
            g.strokeLine((x1 + x2) / 2, y + Rad * 2-7, ((x1+x2)/2+x2)/2, y+Rad*2+Len);
            DrawRec(el.right, y + Rad * 2 + Len, (x1 + x2) / 2, x2, g);
        }
    }

    public void Draw(Tree el, int y, int x1, int x2, GraphicsContext g) {
        g.setFill(Color.ORANGE);
        g.fillRect((x1+x2) / 2 - 25,y,50,Rad*2);
        g.setFill(Color.BLACK);
        g.fillText("HEAD", (x1 + x2) / 2 - 15, y + 19);
        g.setFill(Color.BLACK);
        g.setStroke(Color.BLACK);
        g.strokeLine((x1 + x2) / 2, y + Rad * 2,(x1 + x2) / 2, y + Rad * 2 + (Len / 2));
        el.x = (x1 + x2) / 2 + 25;
        el.y = y;
        DrawRec(el.left, y + Rad * 2 + (Len / 2), x1, x2, g);
    }


    public HelloController gr;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        gr=loader.getController();
        gr.gr=gr;
        gr.gc=gr.canv.getGraphicsContext2D();
        stage.setScene(scene);
        stage.setTitle("BinaryTree");
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}