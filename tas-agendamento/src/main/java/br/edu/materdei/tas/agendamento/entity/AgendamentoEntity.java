package br.edu.materdei.tas.agendamento.entity;

import javax.persistence.*;
import java.util.List;

@MappedSuperclass
public class AgendamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="codigo")
    private String codigo;

    @Column(name = "observacao",length = 300)
    private String observacao;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ResponsavelEntity responsavel;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AgendamentoItemEntity> itens;

    @Column(name = "situacao")
    private Integer situacao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public ResponsavelEntity getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(ResponsavelEntity responsavel) {
        this.responsavel = responsavel;
    }

    public List<AgendamentoItemEntity> getItens() {
        return itens;
    }

    public void setItens(List<AgendamentoItemEntity> itens) {
        this.itens = itens;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }

    public void updateSituacao() {

    }
}
