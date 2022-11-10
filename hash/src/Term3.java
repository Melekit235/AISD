import java.util.ArrayList;

public class Term3 {
    String name;
    ArrayList<Integer> pages = new ArrayList<>();
    void info(){
        System.out.println("          Значение = "+name);
        System.out.println("           Встречен на странице : "+pages);
    }
}
