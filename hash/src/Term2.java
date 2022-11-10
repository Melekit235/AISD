import java.util.ArrayList;

public class Term2 {
    String name;
    ArrayList<Integer> pages = new ArrayList<>();
    ArrayList<Term3> undterm = new ArrayList<>(1);

    int findundterm(Term3 x){
        for (int i = 0; i < undterm.size(); i++){
            if (x.name.equals(undterm.get(i).name)){
                return i;
            }
        }
        return -1;
    }
    void info(){
        System.out.println("     Значение = "+name);
        System.out.println("      Встречен на странице : "+ pages);
        System.out.println("      Все его поддтермины :");

        for (int i = 0; i < undterm.size(); i++){
            undterm.get(i).info();
        }
    }
}
