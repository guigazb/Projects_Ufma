public abstract class Pessoa {
    protected String nome;
    protected int idade;
    protected String saoq;

    public void fazeraniversario() {
        this.idade++;
    }

    public int getIdade() {
        return idade;
    }

    public String getNome() {
        return nome;
    }

    public String getSaoq() {
        return saoq;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSaoq(String saoq) {
        this.saoq = saoq;
    }

}