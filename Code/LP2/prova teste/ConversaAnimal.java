public class ConversaAnimal {
    public static void main(String[]args){
        Humano homem = new Humano("olá");
        Animal cachorro = new Animal("Au au");
        dialogar(homem);
        dialogar(cachorro);
        cachorro = homem;
        dialogar(cachorro);

    }
    public static void dialogar(Animal A1){
        A1.Comunicar();
        if(A1 instanceof Humano){
            Humano h = (Humano)A1;
            h.Comunicar("boa prova!");
        }
    }
}
