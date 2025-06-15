public class Data {
    public int dia;
    public int mes;
    public int ano;

    private final int[] diasmes = {31,28,31,30,31,30,31,31,30,31,30,31};
    public Data(){
        this.dia = 0;
        this.mes = 0;
        this.ano = 0;
    }

    public void SetData(int dia, int mes, int ano){
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public void SetData(int dia, String mes,int ano){
        this.dia = dia;
        this.ano = ano;
        switch(mes){
            case "Janeiro":
                this.mes = 1;
                break;
            case "Fevereiro":
                this.mes = 2;
                break;
            case "Março":
                this.mes = 3;
                break;
            case "Abril":
                this.mes = 4;
                break;
            case "Maio":
                this.mes = 5;
                break;
            case "Junho":
                this.mes = 6;
                break;
            case "Julho":
                this.mes = 7;
                break;
            case "Agosto":
                this.mes = 8;
                break;
            case "Setembro":
                this.mes = 9;
                break;
            case "Outubro":
                this.mes = 10;
                break;
            case "Novembro":
                this.mes = 11;
                break;
            case "Dezembro":
                this.mes = 12;
                break;
            default:
                System.out.println("mes invalido...");
                break;

        }
    }
    public void SetData(int totdias,int ano){
        this.ano = ano;
        if((ano%4 == 0 && ano%100 != 0) || (ano%400 == 0)){
            diasmes[1] = 29;
        }
        for(int i = 0; i < diasmes.length; i++){
            if(totdias > diasmes[i]){
                totdias -= diasmes[i];
            }else{
                this.dia = totdias;
                this.mes = i +1;
                break;
            }
        }
    }

    public void Printdata(){
        System.out.println("o dia é: " + this.dia);
        System.out.println("o MÊS é: " + this.mes);
        System.out.println("o ano é: " + this.ano);
    }

}
