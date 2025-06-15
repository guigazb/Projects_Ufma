public class Main {
    public static void main(String[]args){

        Aluno aluno = new Aluno();
        aluno.setNome("João");
        aluno.setMatricula(12345);
        aluno.setCurso("Engenharia de Software");

        Aluno bolsista = new Bolsista();

        bolsista.setNome("Maria");
        bolsista.setMatricula(67890);
        bolsista.setCurso("Ciência da Computação");
        ((Bolsista) bolsista).setBolsa(50.0f); // Casting para acessar o método específico de Bolsista
        
        System.out.println("Informações do Aluno:");
        System.out.println("Nome: " + aluno.getNome());
        System.out.println("Matrícula: " + aluno.getMatricula());
        System.out.println("Curso: " + aluno.getCurso());

        aluno.pagarmensalidade();

        System.out.println("Informações do Aluno Bolsista:");
        System.out.println("Nome: " + bolsista.getNome());
        System.out.println("Matrícula: " + bolsista.getMatricula());
        System.out.println("Curso: " + bolsista.getCurso());
        ((Bolsista) bolsista).pagarmensalidade();
    }
}
