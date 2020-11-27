package br.edu.materdei.tas.agendamento.entity;

import br.edu.materdei.tas.core.entity.ProdutoEntity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "agendamentoitem")
public class AgendamentoItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "dtPrevista")
    private Date dtPrevista;

    @Column(name = "dtEntregue")
    private Date dtEntregue;

    @Column(name = "qtdPrevista")
    private float qtdPrevista;

    @Column(name = "qtdEntregue")
    private float qtdEntregue;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ProdutoEntity produto;

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

    public Date getDtPrevista() {
        return dtPrevista;
    }

    public void setDtPrevista(Date dtPrevista) {
        this.dtPrevista = dtPrevista;
    }

    public Date getDtEntregue() {
        return dtEntregue;
    }

    public void setDtEntregue(Date dtEntregue) {
        this.dtEntregue = dtEntregue;
    }

    public float getQtdPrevista() {
        return qtdPrevista;
    }

    public void setQtdPrevista(float qtdPrevista) {
        this.qtdPrevista = qtdPrevista;
    }

    public float getQtdEntregue() {
        return qtdEntregue;
    }

    public void setQtdEntregue(float qtdEntregue) {
        this.qtdEntregue = qtdEntregue;
    }

    public ProdutoEntity getProduto() {
        return produto;
    }

    public void setProduto(ProdutoEntity produto) {
        this.produto = produto;
    }
}
