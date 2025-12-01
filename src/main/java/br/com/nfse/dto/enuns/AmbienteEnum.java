package br.com.nfse.dto.enuns;

/**
 *
 * @author eduardo
 */
public enum AmbienteEnum {

    PRODUCAO("1"),
    HOMOLOGACAO("2");

    private final String value;

    AmbienteEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
