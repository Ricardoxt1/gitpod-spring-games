package application.model;

import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.*;

@Entity
@Table(name = "questoes")
public class Questoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String enunciado;

    @OneToMany(mappedBy = "questoes")
    private Set<Alternativas> alternativas = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public Set<Alternativas> getAlternativas() {
        return alternativas;
    }

    public void getAlternativas(Set<Alternativas> alternativas) {
        this.alternativas = alternativas;
    }

}
