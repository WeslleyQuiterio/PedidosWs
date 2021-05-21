package br.com.pedidosonline.model;

import java.math.BigDecimal;

public class PedidoItem {

    private Integer idpedidoitem;
    private Integer idpedido;
    private Integer idproduto;
    private Double qtd;
    private Double valorUnitario;
    private Double desconto;
    private BigDecimal total;
    private Integer sequencia;

    public PedidoItem() {
    }

   
    public Integer getId() {
        return this.idpedidoitem;
    }
    public void setId(Integer id) {
        this.idpedidoitem = id;
    }
    public Integer getIdPedido() {
        return idpedido;
    }
    public void setIdPedido(Integer idpedido) {
        this.idpedido = idpedido;
    }
    public Integer getIdProduto() {
        return idproduto;
    }
    public void setIdProduto(Integer idproduto) {
        this.idproduto = idproduto;
    }
    public Double getQtd() {
        return qtd;
    }
    public void setQtd(Double qtd) {
        this.qtd = qtd;
    }
    public Double getValorUnitario() {
        return valorUnitario;
    }
    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
    public Double getDesconto() {
        return desconto;
    }
    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }
    public BigDecimal getTotal() {
        return total;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    public Integer getSequencia() {
        return sequencia;
    }
    public void setSequencia(Integer sequencia) {
        this.sequencia = sequencia;
    }

    @Override
    public String toString(){
        return "PedidoItem{" + "idpedidoitem=" + idpedidoitem + ", idpedido=" + idpedido + ", idproduto=" + idproduto + ", qtd=" + qtd + ", valorUnitario=" + valorUnitario + ", desconto=" + desconto + ", total=" + total + ", sequencia=" + sequencia + '}';
    }

}