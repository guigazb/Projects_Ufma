public class Stack<item> {

    private item[] arrayPilha;

    private int topo;

    public Stack(int max){
		/*instanciando um vetor genérico (cria um vetor do tipo Objetc
                e faz o cast (conversão) para o tipo T*/
        arrayPilha = (item[]) new Object[max];
        topo = -1;
    }


    public boolean stkPush(item elemento){
        if(this.estaCheia() == false) {
            topo++;
            arrayPilha[topo] = elemento;
            return true;
        }
        return false;
    }

    public item stkPop(){
        if(this.estaVazia() == false){
            item aux = arrayPilha[topo];
            topo--;
            return aux;
        }
        return null;
    }

    public boolean estaVazia(){
        return topo == -1;
    }

    public boolean estaCheia(){
        return topo == arrayPilha.length-1;
    }

}