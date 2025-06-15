public class Bolsista extends Aluno{
    private float bolsa;

    public void renovarbolsa(){
        System.out.println("renovando bolsa de" + this.getNome() + "...");
    }
    @Override
    public void pagarmensalidade(){
        System.out.println(this.getNome() + " tem bolsa no valor de: " + this.getBolsa() + ", pagamento facilitado!");
    }
    public float getBolsa() {
        return bolsa;
    }
    public void setBolsa(float bolsa) {
        this.bolsa = bolsa;
    }
}
