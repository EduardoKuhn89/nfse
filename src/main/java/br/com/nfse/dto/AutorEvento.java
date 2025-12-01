package br.com.nfse.dto;

/**
 *
 * @author eduardo
 */
public class AutorEvento {

    public static AutorEvento cnpj(String cnpj) {
        AutorEvento autor = new AutorEvento();
        autor.setCnpjAutor(cnpj);
        return autor;
    }

    public static AutorEvento cpf(String cpf) {
        AutorEvento autor = new AutorEvento();
        autor.setCpfAutor(cpf);
        return autor;
    }

    private String cnpjAutor;
    private String cpfAutor;

    public String getCnpjAutor() {
        return cnpjAutor;
    }

    public void setCnpjAutor(String cnpjAutor) {
        this.cnpjAutor = cnpjAutor;
    }

    public String getCpfAutor() {
        return cpfAutor;
    }

    public void setCpfAutor(String cpfAutor) {
        this.cpfAutor = cpfAutor;
    }

    @Override
    public String toString() {
        return "AutorEvento{" + "cnpjAutor=" + cnpjAutor + ", cpfAutor=" + cpfAutor + '}';
    }

}
