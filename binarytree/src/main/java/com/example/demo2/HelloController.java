package com.example.demo2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController  {

    @FXML
    public Button b1;
    @FXML
    public Button b2;
    @FXML
    public TextField t1;
    @FXML
    public  TextField t2;
    @FXML
    public Label l1;
    @FXML
    public Label l2;
    @FXML
    public Label l3;
    @FXML
    public Canvas canv;

    HelloApplication hell = new HelloApplication();
    public GraphicsContext gc;
    public HelloController gr;
    ArrayList arr = new ArrayList();
    public void button1click()
    {

        String s = t1.getText();
        boolean f=false;
        for (int i=0; i<arr.size(); i++)
        {
            if (Integer.parseInt(s)==(int)arr.get(i))
            {                f=true;
            }
        }
        if (!f)
        {arr.add(Integer.parseInt(s));}
        hell.tree=null;
        for (int i=0; i<arr.size(); i++)
        {
            hell.tree=hell.add((int)arr.get(i),hell.tree);
        }
        HelloApplication.Tree x = new HelloApplication.Tree();
        x.left= hell.tree;
        x.right=null;
        x.value=-1;
        hell.tree=x;
        gc.clearRect(0,0,canv.getWidth(),canv.getHeight());
        hell.Draw(hell.tree, 0,0,(int)canv.getWidth(), gc);
        l1.setText("Обратный обход: "+hell.obr(hell.tree.left));
        l3.setText("Прямой обход: " + hell.pram(hell.tree.left));
        l2.setText("Симметричный обход: "+ hell.SimAndSew(hell.tree, gc,gr));

    }

    public void button2click()
    {
        hell.delThread(hell.tree.left);
        hell.delEl(Integer.parseInt(t2.getText()),hell.tree.left,hell.tree);
        for (int i=0; i<arr.size(); i++)
        {
            if ((int)arr.get(i)==Integer.parseInt(t2.getText()))
            {
                arr.remove(i);
            }
        }
        gc.clearRect(0,0,canv.getWidth(),canv.getHeight());
        hell.Draw(hell.tree,100,100,(int)canv.getWidth(),gc);
        l1.setText("Обратный обход: "+hell.obr(hell.tree.left));
        l3.setText("Прямой обход: " + hell.pram(hell.tree.left));
        l2.setText("Симметричный обход: "+ hell.SimAndSew(hell.tree, gc,gr));
    }
}