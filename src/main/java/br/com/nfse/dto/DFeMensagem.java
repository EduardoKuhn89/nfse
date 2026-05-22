package br.com.nfse.dto;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/*
  Mensagem de processamento retornada pela API de Distribuição de DF-e.
 */
public class DFeMensagem {

    @SerializedName("Codigo")
    private String codigo;

    @SerializedName("Descricao")
    private String descricao;

    @SerializedName("Complemento")
    private String complemento;

    @SerializedName("Parametros")
    private List<String> parametros;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public List<String> getParametros() {
        return parametros;
    }

    public void setParametros(List<String> parametros) {
        this.parametros = parametros;
    }

    @Override
    public String toString() {
        return "DFeMensagem{"
                + "codigo='" + codigo + '\''
                + ", descricao='" + descricao + '\''
                + ", complemento='" + complemento + '\''
                + '}';
    }
}
