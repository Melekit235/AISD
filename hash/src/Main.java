import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] terms = {"Математика","Высшая","Матанализ","Умолчание","Линейная алгебра","Алгоритм","Высшая","Java","Низкоуровневое","Assembler"};

        ArrayList<Term1> term1ArrayList = new ArrayList<>(2);
        int f = 0;
        for(int i = 0; i < 2; i++){
            Term1 tempTerm1 = new Term1();
            tempTerm1.name = terms[f];
            f++;
            int m = (int)(Math.random() * 5 + 1); // на каком количестве страниц
            for (int j = 0; j < m; j++){
                tempTerm1.pages.add((int)(Math.random() * 100 + 1));//номер страницы
            }
            term1ArrayList.add(tempTerm1);

            for(int j = 0; j < 2; j++){//ввод поддреминов
                Term2 tempTerm2 = new Term2();
                tempTerm2.name = terms[f];
                f++;
                m = (int)(Math.random() * 5 + 1);
                for (int k = 0; k < m; k++){
                    tempTerm2.pages.add((int)(Math.random() * 100 + 1));
                }
                term1ArrayList.get(i).undterm.add(tempTerm2);

                for (int k = 0; k < 1; k++){//Ввод подтермин подтермина
                    Term3 tempTerm3 = new Term3();
                    tempTerm3.name = terms[f];
                    f++;
                    m = (int)(Math.random() * 5 + 1);
                    for (int z = 0; z < m; z++){
                        tempTerm3.pages.add((int)(Math.random() * 100 + 1));
                    }
                    term1ArrayList.get(i).undterm.get(j).undterm.add(tempTerm3);
                }
            }
        }

        boolean work=true;
        int menu;
        while (work){
            System.out.println("1 - Просмотреть списки");
            System.out.println("2 - Добавить термин/подтермин");
            System.out.println("3 - Изменить данные термину/подтермину");
            System.out.println("4 - Сортировка");
            System.out.println("5 - Поиск термина/подтермина");
            System.out.println("6 - Удаление термина/подтермина");
            System.out.println("0 - Выход");
            System.out.println("Введите код задачи :");
            menu = scanner.nextInt();
            switch (menu){
                case 1:{
                    for (int i = 0; i < term1ArrayList.size(); i++){
                        System.out.println((i+1)+" главный термин");
                        term1ArrayList.get(i).info();
                    }
                    break;
                }
                case 2:{
                    System.out.println("1 - Добавить термин");
                    System.out.println("2 - Добавить подтермин");
                    System.out.println("3 - Добавить подтермин подтермину");
                    int switchMenu = scanner.nextInt();
                    switch (switchMenu) {
                        case 1 -> {
                            Term1 temp = new Term1();

                            System.out.println("Введите термин");
                            temp.name = scanner.next();

                            System.out.println("Введите количество страницы с этим термином");
                            int kolPage = scanner.nextInt();
                            for (int i = 0; i < kolPage; i++) {
                                int numPage = scanner.nextInt();
                                temp.pages.add(numPage);
                            }

                            term1ArrayList.add(temp);
                        }
                        case 2 -> {
                            System.out.println("Какому термину добавить подтермин?");
                            String text = scanner.next();

                            ArrayList<Term1> termArray = new ArrayList<>();
                            for (Term1 term1 : term1ArrayList) {
                                if (term1.name.equals(text)) {
                                    termArray.add(term1);
                                    termArray.get(termArray.size() - 1).info();
                                }
                            }

                            if (termArray.size() < 1)
                                System.out.println("Данный термин не был найден");
                            else {
                                System.out.println("С каким продолжить работу?");
                                for (int i = 0; i < termArray.size(); i++) {
                                    System.out.println((i + 1) + " - " + termArray.get(i).name + " - " + termArray.get(i).pages);
                                }

                                int t = Integer.parseInt(scanner.next());
                                Term2 temp = new Term2();
                                System.out.println("Введите подтермин");
                                temp.name = scanner.next();

                                System.out.println("Введите количество страницы с этим термином");
                                int kolPage = scanner.nextInt();
                                for (int i = 0; i < kolPage; i++) {
                                    int numPage = scanner.nextInt();
                                    temp.pages.add(numPage);
                                }
                                termArray.get(t - 1).undterm.add(temp);
                            }
                        }
                        case 3 -> {
                            ArrayList<Term2> termArray = new ArrayList<>();
                            System.out.println("Какому подтермину добавить подтермин?");
                            String text = scanner.next();
                            for (Term1 term1 : term1ArrayList) {
                                for (int j = 0; j < term1.undterm.size(); j++) {
                                    if (term1.undterm.get(j).name.equals(text)) {
                                        termArray.add(term1.undterm.get(j));
                                        termArray.get(termArray.size() - 1).info();
                                    }
                                }
                            }

                            if (termArray.size() > 0) {
                                System.out.println("С каким продолжить работу?");
                                for (int i = 0; i < termArray.size(); i++) {
                                    System.out.println((i + 1) + " - " + termArray.get(i).name + " - " + termArray.get(i).pages);
                                }

                                int t = Integer.parseInt(scanner.next());
                                Term3 temp = new Term3();
                                System.out.println("Введите подтермин");
                                temp.name = scanner.next();

                                System.out.println("Введите количество страницы с этим термином");
                                int kolPage = scanner.nextInt();
                                for (int i = 0; i < kolPage; i++) {
                                    int numPage = scanner.nextInt();
                                    temp.pages.add(numPage);
                                }
                                termArray.get(t - 1).undterm.add(temp);
                            } else
                                System.out.println("Данный термин не был найден");
                        }
                    }
                    break;
                }
                case 3:{
                    System.out.println("1 - Термин");
                    System.out.println("2 - Подтермин");
                    System.out.println("3 - Подтермин подтермина");
                    int switchMenu1 = scanner.nextInt();
                    System.out.println("Введите значение объекта");
                    String objname = scanner.next();
                    System.out.println("Что желаете изменить?");
                    System.out.println("1 - Значение");
                    System.out.println("2 - Страницы");
                    int switchMenu2 = scanner.nextInt();

                    switch (switchMenu1) {
                        case 1 -> {
                            ArrayList<Term1> res = new ArrayList<>();
                            for (Term1 term1 : term1ArrayList) {
                                if (term1.name.equals(objname)) {
                                    term1.info();
                                    res.add(term1);
                                }
                            }

                            if (res.size() == 0) {
                                System.out.println("Данный термин не был найден");
                            } else {
                                System.out.println("С каким из найденных продолжить работу?");
                                for (int i = 0; i < res.size(); i++) {
                                    System.out.println((i + 1) + " - Значение = " + res.get(i).name + " Страницы : " + res.get(i).pages);
                                }
                                int choice = Integer.parseInt(scanner.next());
                                switch (switchMenu2) {
                                    case 1 -> {
                                        System.out.println("Введите новое значение");
                                        res.get(choice - 1).name = scanner.next();
                                    }
                                    case 2 -> {
                                        /*System.out.println("Введите количество новых страницы с этим термином");
                                        int kolPage = scanner.nextInt();
                                        System.out.println("Введите новые страницы ");
                                        for (int i = 0; i < kolPage; i++) {
                                            if (!res.get(i - 1).pages.contains(i)) {
                                                res.get(i - 1).pages.add(i);
                                            }
                                        }

                                        System.out.println("Введите страницы, которые нужно удалить");
                                        System.out.println("Для окончания введите 0");
                                        int delPAge = scanner.nextInt();
                                        while ((delPAge != 0) && (res.get(choice - 1).pages.size() > 0)) {
                                            if (res.get(choice - 1).pages.contains(delPAge)) {
                                                res.get(choice - 1).pages.remove(res.get(choice - 1).pages.indexOf(delPAge));
                                            }
                                            delPAge = scanner.nextInt();
                                        }*/


                                        System.out.println("Введите новые страницы ");
                                        System.out.println("Для окончания введите 0");
                                        int u = scanner.nextInt();
                                        while (u != 0) {
                                            if (!res.get(choice - 1).pages.contains(u)) {
                                                res.get(choice - 1).pages.add(u);
                                            }
                                            ;
                                            u = scanner.nextInt();
                                        }
                                        System.out.println("Введите страницы, которые нужно удалить");
                                        System.out.println("Для окончания введите 0");
                                        u = scanner.nextInt();
                                        while ((u != 0) && (res.get(choice - 1).pages.size() > 0)) {
                                            if (res.get(choice - 1).pages.contains(u)) {
                                                res.get(choice - 1).pages.remove(res.get(choice - 1).pages.indexOf(u));
                                            }
                                            ;
                                            u = scanner.nextInt();
                                        }



                                    }
                                }
                            }
                        }
                        case 2 -> {
                            ArrayList<Term2> termArray = new ArrayList<>();
                            int count = 0;
                            for (Term1 term1 : term1ArrayList) {
                                for (int j = 0; j < term1.undterm.size(); j++) {
                                    if (term1.undterm.get(j).name.equals(objname)) {
                                        term1.undterm.get(j).info();
                                        count++;
                                        termArray.add(term1.undterm.get(j));
                                    }
                                }
                            }
                            if (count > 1) {
                                System.out.println("С каким продолжаем работу?");
                                for (int i = 0; i < termArray.size(); i++) {
                                    System.out.println((i + 1) + " - " + termArray.get(i).name + " " + termArray.get(i).pages);
                                }
                                int choice = Integer.parseInt(scanner.next());

                                switch (switchMenu2) {
                                    case 1 -> {
                                        System.out.println("Введите новое значение");
                                        termArray.get(choice - 1).name = scanner.next();
                                    }
                                    case 2 -> {
                                        System.out.println("Введите новые страницы ");
                                        System.out.println("Для окончания введите 0");
                                        int u = scanner.nextInt();
                                        while (u != 0) {
                                            if (!termArray.get(choice - 1).pages.contains(u)) {
                                                termArray.get(choice - 1).pages.add(u);
                                            }
                                            ;
                                            u = scanner.nextInt();
                                        }
                                        System.out.println("Введите страницы, которые нужно удалить");
                                        System.out.println("Для окончания введите 0");
                                        u = scanner.nextInt();
                                        while ((u != 0) && (termArray.get(choice - 1).pages.size() > 0)) {
                                            if (termArray.get(choice - 1).pages.contains(u)) {
                                                termArray.get(choice - 1).pages.remove(termArray.get(choice - 1).pages.indexOf(u));
                                            }
                                            ;
                                            u = scanner.nextInt();
                                        }
                                    }
                                }
                            } else {
                                switch (switchMenu2) {
                                    case 1 -> {
                                        System.out.println("Введите новое значение");
                                        termArray.get(0).name = scanner.next();
                                    }
                                    case 2 -> {
                                        System.out.println("Введите новые страницы ");
                                        System.out.println("Для окончания введите 0");
                                        int u = scanner.nextInt();
                                        while (u != 0) {
                                            if (!termArray.get(0).pages.contains(u)) {
                                                termArray.get(0).pages.add(u);
                                            }
                                            ;
                                            u = scanner.nextInt();
                                        }
                                        System.out.println("Введите страницы, которые нужно удалить");
                                        System.out.println("Для окончания введите 0");
                                        u = scanner.nextInt();
                                        while ((u != 0) && (termArray.get(0).pages.size() > 0)) {
                                            if (termArray.get(0).pages.contains(u)) {
                                                termArray.get(0).pages.remove(termArray.get(0).pages.indexOf(u));
                                            }
                                            ;
                                            u = scanner.nextInt();
                                        }
                                        }
                                    }
                                }
                                if (termArray.size() < 1) System.out.println("Данный термин не был найден");
                            }
                        case 3 -> {
                            ArrayList<Term3> termArray = new ArrayList<>();
                            for (Term1 term1 : term1ArrayList) {
                                for (int j = 0; j < term1.undterm.size(); j++) {
                                    for (int z = 0; z < term1.undterm.get(j).undterm.size(); z++) {
                                        if (term1.undterm.get(j).undterm.get(z).name.equals(objname)) {
                                            termArray.add(term1.undterm.get(j).undterm.get(z));
                                            term1.undterm.get(j).undterm.get(z).info();
                                        }
                                    }
                                }
                            }

                            if (termArray.size() > 0) {
                                System.out.println("С каким продолжить работу?");
                                for (int i = 0; i < termArray.size(); i++) {
                                    System.out.println((i + 1) + " - " + termArray.get(i).name + " - " + termArray.get(i).pages);
                                }
                                int choice = Integer.parseInt(scanner.next());
                                switch (switchMenu2) {
                                    case 1 -> {
                                        System.out.println("Введите новое значение");
                                        termArray.get(choice - 1).name = scanner.next();
                                    }
                                    case 2 -> {
                                        System.out.println("Введите новые страницы ");
                                        System.out.println("Для окончания введите 0");
                                        int u = scanner.nextInt();
                                        while (u != 0) {
                                            if (!termArray.get(choice - 1).pages.contains(u)) {
                                                termArray.get(choice - 1).pages.add(u);
                                            }
                                            u = scanner.nextInt();
                                        }

                                        System.out.println("Введите страницы, которые нужно удалить");
                                        System.out.println("Для окончания введите 0");
                                        u = scanner.nextInt();
                                        while ((u != 0) && (termArray.get(choice - 1).pages.size() > 0)) {
                                            if (termArray.get(choice - 1).pages.contains(u)) {
                                                termArray.get(choice - 1).pages.remove(termArray.get(choice - 1).pages.indexOf(u));
                                            }
                                            u = scanner.nextInt();
                                        }
                                    }
                                }
                            } else {
                                System.out.println("Проверьте ввод");
                            }
                        }
                    }
                    break;
                }
                case 4:{
                    System.out.println("1 - Термин");
                    System.out.println("2 - Подтермин");
                    System.out.println("3 - Подтермин подтермина");
                    int switchMenu1 = scanner.nextInt();
                    System.out.println("По какому принципу сортируем?");
                    System.out.println("1 - Алфавит");
                    System.out.println("2 - Первая страница");
                    int switchMenu2 = scanner.nextInt();

                    Comparator<Term1> comparatorStr = new Comparator<Term1>() {
                        @Override
                        public int compare(Term1 o1, Term1 o2) {
                            int j = 0;
                            while (((o1.name.charAt(j) == o2.name.charAt(j))) || (o1.name.length() > j) && (o2.name.length() > j)) {
                                if (o1.name.charAt(j) > o2.name.charAt(j)) {
                                    return 1;
                                } else {
                                    if (o1.name.charAt(j) < o2.name.charAt(j)) {
                                        return -1;
                                    }
                                }
                                j++;
                            }
                            return 0;
                        }
                    };

                    Comparator<Term1> comparatorAge = new Comparator<Term1>() {
                        @Override
                        public int compare(Term1 o1, Term1 o2) {
                            return Integer.compare(o1.pages.get(0), o2.pages.get(0));
                        }
                    };

                    Comparator<Term2> comparatorStr2 = new Comparator<Term2>() {
                        @Override
                        public int compare(Term2 o1, Term2 o2) {
                            int j = 0;
                            while (((o1.name.charAt(j) == o2.name.charAt(j))) || (o1.name.length() > j) && (o2.name.length() > j)) {
                                if (o1.name.charAt(j) > o2.name.charAt(j)) {
                                    return 1;
                                } else {
                                    if (o1.name.charAt(j) < o2.name.charAt(j)) {
                                        return -1;
                                    }
                                }
                                j++;
                            }
                            return 0;
                        }
                    };

                    Comparator<Term2> comparatorAge2 = new Comparator<Term2>() {
                        @Override
                        public int compare(Term2 o1, Term2 o2) {
                            return Integer.compare(o1.pages.get(0), o2.pages.get(0));
                        }
                    };

                    Comparator<Term3> comparatorStr3 = new Comparator<Term3>() {
                        @Override
                        public int compare(Term3 o1, Term3 o2) {
                            int j = 0;
                            while (((o1.name.charAt(j) == o2.name.charAt(j))) || (o1.name.length() > j) && (o2.name.length() > j)) {
                                if (o1.name.charAt(j) > o2.name.charAt(j)) {
                                    return 1;
                                } else {
                                    if (o1.name.charAt(j) < o2.name.charAt(j)) {
                                        return -1;
                                    }
                                }
                                j++;
                            }
                            return 0;
                        }
                    };

                    Comparator<Term3> comparatorAge3 = new Comparator<Term3>() {
                        @Override
                        public int compare(Term3 o1, Term3 o2) {
                            return Integer.compare(o1.pages.get(0), o2.pages.get(0));
                        }
                    };

                    switch (switchMenu1) {
                        case 1:{
                            switch (switchMenu2) {
                                case 1 -> {
                                    Collections.sort(term1ArrayList, comparatorStr);
                                }
                                case 2 -> {
                                    for (Term1 term1 : term1ArrayList) {
                                        Collections.sort(term1.pages);
                                    }
                                    Collections.sort(term1ArrayList, comparatorAge);
                                }
                            }
                            break;
                        }
                        case 2:{

                            System.out.println("Введите термин для определения подтерминов");
                            String text = scanner.next();
                            int d = -1;
                            for (int i = 0; i < term1ArrayList.size(); i++) {
                                if (term1ArrayList.get(i).name.equals(text)){
                                    d = i;
                                }
                            }
                            if (d!= -1) {
                                switch (switchMenu2) {
                                    case 1 -> {
                                        Collections.sort(term1ArrayList.get(d).undterm, comparatorStr2);
                                    }
                                    case 2 -> {
                                        for (int i = 0; i < term1ArrayList.size(); i++) {
                                            Collections.sort(term1ArrayList.get(i).undterm.get(i).pages);
                                        }
                                        Collections.sort(term1ArrayList.get(d).undterm, comparatorAge2);
                                    }
                                }
                                break;
                            }
                            System.out.println("Термин не был найден");
                        }
                        case 3:{
                            System.out.println("Введите термин для определения подтерминов");
                            String text = scanner.next();
                            int d = -1;
                            int v = -1;
                            for (int i = 0; i < term1ArrayList.size(); i++) {
                                for (int j = 0; j < term1ArrayList.get(i).undterm.size(); j++) {
                                    if (term1ArrayList.get(i).undterm.get(j).name.equals(text)) {
                                        d = i;
                                        v = j;
                                    }
                                }
                            }
                            if ((d!= -1)&&(v!= -1)) {
                                switch (switchMenu2) {
                                    case 1 -> {
                                        Collections.sort(term1ArrayList.get(d).undterm.get(v).undterm, comparatorStr3);
                                    }
                                    case 2 -> {
                                        for (Term1 term1 : term1ArrayList) {
                                            Collections.sort(term1.undterm.get(v).pages);
                                        }
                                        Collections.sort(term1ArrayList.get(d).undterm.get(v).undterm, comparatorAge3);
                                    }
                                }
                                break;
                            }
                            System.out.println("Термин не был найден");
                        }
                    }
                    break;
                }
                case 5:{
                    /*System.out.println("Введите 1 - Поиск по термину, 2 - Поиск по подтермину");
                    int switchMenu = scanner.nextInt();
                    System.out.println("Введите данные");
                    String text = scanner.next();

                    boolean find = false;
                    switch (switchMenu) {
                        case 1 -> {
                            /*for (int i = 0; i < term1ArrayList.size(); i++) {
                                for (int j = 0; j < term1ArrayList.get(i).undterm.size(); j++) {
                                    if (term1ArrayList.get(i).name.equals(text)) {
                                        term1ArrayList.get(i).info();
                                        find = true;
                                    }

                                    if (term1ArrayList.get(i).undterm.get(j).name.equals(text)) {
                                        term1ArrayList.get(i).undterm.get(j).info();
                                        find = true;
                                    }
                                }
                            }

                            if (find)
                                System.out.println("Данного термина нет");
                            break;
                        }
                        case 2 -> {
                            for (Term1 term1 : term1ArrayList) {
                                for (int j = 0; j < term1.undterm.size(); j++) {
                                    if (term1.undterm.get(j).name.equals(text)) {
                                        term1.info();
                                        find = true;
                                    }
                                    for (int z = 0; z < term1.undterm.get(j).undterm.size(); z++) {
                                        if (term1.undterm.get(j).undterm.get(z).name.equals(text)) {
                                            term1.undterm.get(j).info();
                                            find = true;
                                        }
                                    }
                                }
                            }
                            if (!find) {
                                System.out.println("Данного термина нет");
                            }
                        }
                    }
                    break;*/


                    System.out.println("Введите 1 - Поиск по термину, 2 - Поиск по подтермину");
                    int p = scanner.nextInt();
                    System.out.println("Введите данные");
                    String text = scanner.next();

                    boolean f1 = false;
                    switch (p) {
                        case 1: {
                            for (int i = 0; i < term1ArrayList.size(); i++) {
                                for (int j = 0; j < term1ArrayList.get(i).undterm.size(); j++) {
                                    if (term1ArrayList.get(i).name.equals(text)) {
                                        term1ArrayList.get(i).info();
                                        f1 = true;
                                    }

                                    if (term1ArrayList.get(i).undterm.get(j).name.equals(text)) {
                                        term1ArrayList.get(i).undterm.get(j).info();
                                        f1 = true;
                                    }
                                }
                            }

                            if (!f1) {
                                System.out.println("Данного термина нет");
                            }
                            break;
                        }
                        case 2: {
                            for (int i = 0; i < term1ArrayList.size(); i++) {
                                for (int j = 0; j < term1ArrayList.get(i).undterm.size(); j++) {
                                    if (term1ArrayList.get(i).undterm.get(j).name.equals(text)) {
                                        term1ArrayList.get(i).info();
                                        f1 = true;
                                    }
                                    for (int z = 0; z < term1ArrayList.get(i).undterm.get(j).undterm.size(); z++){
                                        if (term1ArrayList.get(i).undterm.get(j).undterm.get(z).name.equals(text)) {
                                            term1ArrayList.get(i).undterm.get(j).info();
                                            f1 = true;
                                        }
                                    }
                                }


                            }
                            if (!f1){
                                System.out.println("Данного термина нет");
                            }
                            break;
                        }
                    }
                    break;
                }
                case 6:{
                    System.out.println("Что хотите удалить?");
                    System.out.println("1 - Термин");
                    System.out.println("2 - Подтермин");
                    System.out.println("3 - Подтермин подтермина");
                    int switchMenu = scanner.nextInt();
                    System.out.println("Введите запрос для удаления");
                    String text = scanner.next();

                    switch (switchMenu) {
                        case 1 -> {
                            ArrayList<Term1> termArray = new ArrayList<>();
                            for (Term1 term1 : term1ArrayList) {
                                if (term1.name.equals(text)) {
                                    termArray.add(term1);
                                    termArray.get(termArray.size() - 1).info();
                                }
                            }
                            if (termArray.size() > 0) {
                                System.out.println("С каким продолжить работу?");
                                for (int i = 0; i < termArray.size(); i++) {
                                    System.out.println((i + 1) + " - " + termArray.get(i).name + " - " + termArray.get(i).pages);
                                }
                                int t = Integer.parseInt(scanner.next());
                                term1ArrayList.remove(termArray.get(t - 1));
                            } else System.out.println("Проверьте данные");
                        }
                        case 2 -> {
                            ArrayList<Term2> termArray = new ArrayList<>();
                            for (Term1 term1 : term1ArrayList) {
                                for (int j = 0; j < term1.undterm.size(); j++) {
                                    if (term1.undterm.get(j).name.equals(text)) {
                                        termArray.add(term1.undterm.get(j));
                                        termArray.get(termArray.size() - 1).info();
                                    }
                                }
                            }
                            if (termArray.size() > 0) {
                                System.out.println("С каким продолжить работу?");
                                for (int i = 0; i < termArray.size(); i++) {
                                    System.out.println((i + 1) + " - " + termArray.get(i).name + " - " + termArray.get(i).pages);
                                }
                                int choice = Integer.parseInt(scanner.next());
                                for (Term1 term1 : term1ArrayList) {
                                    if (term1.undterm.contains(termArray.get(choice - 1))) {
                                        term1.undterm.remove(termArray.get(choice - 1));
                                        break;
                                    }
                                }
                            } else System.out.println("Проверьте данные");
                            break;
                        }
                        case 3 -> {
                            ArrayList<Term3> termArray = new ArrayList<>();
                            for (Term1 term1 : term1ArrayList) {
                                for (int j = 0; j < term1.undterm.size(); j++) {
                                    for (int z = 0; z < term1.undterm.get(j).undterm.size(); z++) {
                                        if (term1.undterm.get(j).undterm.get(z).name.equals(text)) {
                                            termArray.add(term1.undterm.get(j).undterm.get(z));
                                            termArray.get(termArray.size() - 1).info();
                                        }
                                    }
                                }
                            }
                            if (termArray.size() > 0) {
                                System.out.println("С каким продолжить работу?");
                                for (int i = 0; i < termArray.size(); i++) {
                                    System.out.println((i + 1) + " - " + termArray.get(i).name + " - " + termArray.get(i).pages);
                                }
                                int choice = Integer.parseInt(scanner.next());
                                for (Term1 term1 : term1ArrayList) {
                                    for (int j = 0; j < term1.undterm.size(); j++) {
                                        if (term1.undterm.get(j).undterm.contains(termArray.get(choice - 1))) {
                                            term1.undterm.get(j).undterm.remove(termArray.get(choice - 1));
                                            break;
                                        }
                                    }
                                }
                            } else System.out.println("Проверьте данные");
                        }
                    }
                    break;
                }
                case 0:{
                    work=false;
                    break;
                }
            }
        }
    }
}