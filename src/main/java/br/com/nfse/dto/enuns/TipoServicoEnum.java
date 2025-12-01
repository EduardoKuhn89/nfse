package br.com.nfse.dto.enuns;

/**
 *
 * @author eduardo
 */
public enum TipoServicoEnum {
    SEFIN("Secretaria de Finanças Nacional (SEFIN)"),
    ADN("Ambiente de Dados Nacional (ADN)");

    private final String label;

    TipoServicoEnum(String label) {
        this.label = label;
    }

    public String getLabeç() {
        return label;
    }
}
