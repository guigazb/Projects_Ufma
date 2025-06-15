//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Data data = new Data();
        data.SetData(29,5,2023);
        data.Printdata();
        data.SetData(29,"Maio",2023);
        data.Printdata();
        data.SetData(149,2023); // se for 2024 esse é o dia 28 de maio
        data.Printdata();
    }
}