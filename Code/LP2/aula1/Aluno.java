public class Aluno extends Pessoa{
    private String curso;
    private int matricula;

    public void pagarmensalidade(){
        System.out.println("O aluno " + this.getNome() +", de matricula: " + this.getMatricula() + " pagou a mensalidade do curso " + this.getCurso() + ".");
        System.out.println("mensalidade paga!");
    }
    public String getCurso() {
        return curso;
    }
    public void setCurso(String curso) {
        this.curso = curso;
    }
    public int getMatricula() {
        return matricula;
    }
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
}

