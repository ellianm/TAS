package br.edu.materdei.tas.compra.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "compra")
public class CompraEntity {
    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;
    
    @Column(length = 6, nullable = false)
    private String codigo;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dtCompra;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private FornecedorEntity fornecedor;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ItemCompraEntity> itens;

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

    public Date getDtcompra() {
        return dtCompra;
    }

    public void setDtcompra(Date dtcompra) {
        this.dtCompra = dtcompra;
    }

    public FornecedorEntity getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(FornecedorEntity fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Set<ItemCompraEntity> getItens() {
        return itens;
    }

    public void setItens(Set<ItemCompraEntity> itens) {
        this.itens = itens;
    }
}
