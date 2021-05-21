package br.com.pedidosonline.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Produto {

    private Integer idproduto;
    private String descricaoCompleta;
    private String descricaoResumida;
    private String unidade;
    private Double fator;
    private Double valorUnitario;
    private String codBarras;

    public Produto() {
    }

    public Produto(Integer idproduto, String descricaoCompleta, String descricaoResumida, String unidade, Double fator, Double valorUnitario, String codBarras) {
        this.idproduto = idproduto;
        this.descricaoCompleta = descricaoCompleta;
        this.descricaoResumida = descricaoResumida;
        this.unidade = unidade;
        this.fator = fator;
        this.valorUnitario = valorUnitario;
        this.codBarras = codBarras;
    }

   

    public Integer getId() {
        return this.idproduto;
    }
    public void setId(Integer id) {
        this.idproduto = id;
    }
    public String getDescricaoCompleta() {
        return descricaoCompleta;
    }
    public void setDescricaoCompleta(String descricaoCompleta) {
        this.descricaoCompleta = descricaoCompleta;
    }
    public String getDescricaoResumida() {
        return descricaoResumida;
    }
    public void setDescricaoResumida(String descricaoResumida) {
        this.descricaoResumida = descricaoResumida;
    }
    public String getUnidade() {
        return unidade;
    }
    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }
    public Double getFator() {
        return fator;
    }
    public void setFator(Double fator) {
        this.fator = fator;
    }
    public Double getValorUnitario() {
        return valorUnitario;
    }
    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
    public String getCodBarras() {
        return codBarras;
    }
    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    @Override
    public String toString(){
        return "Produto{" + "idproduto=" + idproduto + ", descricaoCompleta=" + descricaoCompleta + ", descricaoResumida=" + descricaoResumida + ", unidade=" + unidade + ", fator=" + fator + ", valorUnitario=" + valorUnitario + ", codBarras=" + codBarras + '}';
    }

}