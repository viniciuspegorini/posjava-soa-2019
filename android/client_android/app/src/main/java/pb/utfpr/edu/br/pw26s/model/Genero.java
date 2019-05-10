package pb.utfpr.edu.br.pw26s.model;

public class Genero {

    private Integer id;

    private String nome;

    public Genero(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return this.getId() + " - " + this.getNome();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genero categoria = (Genero) o;

        return id.equals(categoria.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
