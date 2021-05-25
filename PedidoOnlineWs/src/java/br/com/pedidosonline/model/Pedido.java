package br.com.pedidosonline.model;

import java.math.BigDecimal;
import java.util.Date;

public class Pedido {

    private Integer idpedido;
    private Date dataCriacao;
    private BigDecimal subTotal;
    private BigDecimal desconto;
    private BigDecimal total;
    private Integer qtdItens;
    private String clienteMesaComanda;

    public Pedido() {
    }

    public Pedido(Integer idpedido, Date dataCriacao, BigDecimal subTotal, BigDecimal desconto, BigDecimal total, Integer qtdItens, String clienteMesaComanda) {
        this.idpedido = idpedido;
        this.dataCriacao = dataCriacao;
        this.subTotal = subTotal;
        this.desconto = desconto;
        this.total = total;
        this.qtdItens = qtdItens;
        this.clienteMesaComanda = clienteMesaComanda;
    }

    

    public Integer getId() {
        return this.idpedido;
    }
    public void setId(Integer id) {
        this.idpedido = id;
    }
    public Date getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    public BigDecimal getSubTotal() {
        return subTotal;
    }
    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }
    public BigDecimal getDesconto() {
        return desconto;
    }
    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }
    public BigDecimal getTotal() {
        return total;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    public Integer getQtdItens() {
        return qtdItens;
    }
    public void setQtdItens(Integer qtdItens) {
        this.qtdItens = qtdItens;
    }
    public String getClienteMesaComanda() {
        return clienteMesaComanda;
    }
    public void setClienteMesaComanda(String clienteMesaComanda) {
        this.clienteMesaComanda = clienteMesaComanda;
    }

    @Override
    public String toString(){
        return "Pedido{" + "idpedido=" + idpedido + ", dataCriacao=" + dataCriacao + ", subTotal=" + subTotal + ", desconto=" + desconto + ", total=" + total + ", qtdItens=" + qtdItens + ", clienteMesaComanda=" + clienteMesaComanda + '}';
    }

}