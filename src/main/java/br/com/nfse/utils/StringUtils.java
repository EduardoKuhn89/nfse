package br.com.nfse.utils;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringUtils {

    public static final String UTF8_BOM = "\uFEFF";

    private static final String[] CARACTS = {"0", "1", "b", "2", "4", "5", "6", "7", "8",
        "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
        "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
        "x", "y", "z", "!", "@", "&", "#"};

    private static final String[] BYTES_UN = {"Kb", "Mb", "Gb", "Tb", "Pb", "EB", "ZB", "YB"};

    private final char[] DIGITS_HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static String removerTags(String texto) {
        String noHTMLString = texto.replaceAll("\\<.*?\\>", "");
        return noHTMLString;
    }

    public static String manterLetras(String str) {
        if (str == null) {
            return null;
        }
        return str.replaceAll("[^a-zA-Z]", "");
    }

    public static String manterNumeros(String str) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                s.append(str.charAt(i));
            }
        }
        return s.toString();
    }

    public static String pegaPrimeirosNumeros(String str) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                s.append(str.charAt(i));
            } else {
                break;
            }
        }
        return s.toString();
    }

    public static String zerosParaDireita(String val, int q) {
        if (val == null) {
            val = "";
        }

        for (int i = 0; i < q; i++) {
            val = val + "0";
        }

        return val;
    }

    public static String zerosParaEsquerda(String val, int q) {
        if (val == null) {
            val = "";
        }

        if (val.length() >= q) {
            return val;
        }

        for (int i = 0; i < q; i++) {
            val = "0" + val;
            if (val.length() >= q) {
                return val;
            }
        }

        return val;
    }

    public static List<String> separaCol(String linha, String separador) {
        List<String> lista = new ArrayList<>();
        String campo = "";
        for (int i = 1; i <= linha.length(); i++) {
            if (copy(linha, i, 1).equals(separador)) {
                if (i > 1) {
                    lista.add(campo);
                }
                campo = "";
            } else {
                campo = campo + copy(linha, i, 1);
            }
        }
        if (!"".equals(campo) && !campo.equals(separador)) {
            lista.add(campo);
        }

        return lista;
    }

    /**
     * Incluir uma determinada quantidade de vezes um determinado caracter no
     * inicio do texto.
     *
     * @param texto Texto que terá o caracter inserido no início.
     * @param c Caracter que será incluído.
     * @param q Quantidade de vezes que o caracter será incluído.
     * @return Texto com os caracteres incluídos.
     */
    public static String incluirCaracterInicio(String texto, char c, int q) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < q; i++) {
            s.append(c);
        }
        s.append(texto);
        return s.toString();
    }

    /**
     * Capitalizar a primeira letra de todas as palavras do texto.
     *
     * @param texto Texto que terá as palavras capitalizadas.
     * @return Texto com a formatação aplicada.
     */
    public static String capitalizarIniciais(String texto) {
        String[] split = texto.split(" ");
        StringBuilder s = new StringBuilder();
        for (String split1 : split) {
            s.append(String.valueOf(split1.charAt(0)).toUpperCase());
            s.append(split1.substring(1, split1.length()));
            s.append(" ");
        }
        return s.toString().trim();
    }

    /**
     * Contar a quantidade de vezes que uma palavra ocorre em um texto. Não usar
     * caracteres especiais na palavra a ser pesquisada.
     *
     * @param texto Texto.
     * @param palavra Palavra que será contada no texto.
     * @param ignoreCase Considerar maiúsculas ou minúsculas.
     * @return Quantidade de vezes que a palavra ocorre.
     */
    public static int contarQuantidadePalavra(String texto, String palavra, boolean ignoreCase) {
        if (ignoreCase) {
            texto = texto.toLowerCase();
            palavra = palavra.toLowerCase();
        }
        Pattern padrao = Pattern.compile(palavra);
        Matcher pesquisa = padrao.matcher(texto);
        int r = 0;
        while (pesquisa.find()) {
            r++;
        }
        return r;
    }

    public static String concat(String[] str, String caract, int index) {
        if (str == null || str.length == 0) {
            return "";
        }

        String result = "";
        for (int i = 0; i <= index; i++) {
            result += str[i] + (i < index ? caract : "");
        }

        return result;
    }

    /**
     * Obter a cadeia de caracteres que forma o alfabeto brasileiro em
     * minúsculas.
     *
     * @return Array de caracteres.
     */
    public static char[] obterAlfabetoMinusculas() {
        return new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'x', 'y', 'w', 'z'};
    }

    /**
     * Obter a cadeia de caracteres que forma o alfabeto brasileiro em
     * minúsculas.
     *
     * @return Array de caracteres.
     */
    public static char[] obterAlfabetoMaiusculas() {
        return new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'X', 'Y', 'W', 'Z'};
    }

    public static String limparQuebras(String str) {
        return str.replaceAll("\\r\\n|\\r|\\n|\\t", " ");
    }

    //Converte uma letra com acento para sem acento
    public static String rmAccent(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    public static String copy(String str, int inicio, int quantidadeCaracteres) {
        if (str == null || str.isEmpty()) {
            str = "";
        }
        if (quantidadeCaracteres > str.length()) {
            quantidadeCaracteres = str.length();
        }

        if (inicio > str.length()) {
            inicio = 1;
        }

        if (inicio <= 0) {
            inicio = 1;
        }

        if ((inicio - 1) + quantidadeCaracteres > str.length()) {
            quantidadeCaracteres = str.length() - (inicio - 1);
        }

        if (quantidadeCaracteres < 0) {
            quantidadeCaracteres = 0;
        }

        return String.valueOf(str.toCharArray(), inicio - 1, quantidadeCaracteres);
    }

    public static String subStr(String str, int start, int end) {
        if (str == null || start > str.length() - 1 || end > str.length() - 1) {
            return "";
        }

        try {
            return str.substring(start, end);
        } catch (Exception e) {
            return "";
        }
    }

    public static String addString(String str, String value, int position) {
        if (str == null || str.isEmpty()) {
            return "";
        }

        if (value.isEmpty()) {
            return str;
        }

        StringBuilder sb = new StringBuilder(str);
        sb.insert(position, value);
        return sb.toString();
    }

    public static boolean isUpperCase(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }

        str = str.trim();
        return Character.isUpperCase(str.charAt(str.length() - 1));
    }

    public static String toSingularText(String str) {
        if (str == null || str.trim().length() <= 1) {
            return str;
        }

        str = str.trim();
        String result = "";
        for (String s : str.split(" ")) {
            result += toSingular(s) + " ";
        }

        return result.trim();
    }

    public static String toSingular(String str) {
        if (str == null || str.trim().length() <= 1) {
            return str;
        }

        str = str.trim();
        boolean isUpper = isUpperCase(str);

        String aux = str.toUpperCase();
        String fim = null;
        String troca = "";

        if (aux.endsWith("ORES")) {
            fim = "ORES";
            troca = "OR";
        } else if (aux.endsWith("ÁVEIS")) {
            fim = "ÁVEIS";
            troca = "ÁVEL";
        } else if (aux.endsWith("AVEIS")) {
            fim = "AVEIS";
            troca = "AVEL";
        } else if (aux.endsWith("ÕES")) {
            fim = "ÕES";
            troca = "ÃO";
        } else if (aux.endsWith("OES")) {
            fim = "OES";
            troca = "AO";
        } else if (aux.endsWith("AS") || aux.endsWith("OS") || aux.endsWith("ES")) {
            fim = "S";
            troca = "";
        }

        if (fim != null && !troca.isEmpty()) {
            str = str.substring(0, aux.lastIndexOf(fim)) + (!isUpper ? troca.toLowerCase() : troca);
        } else if (fim != null) {
            str = str.substring(0, str.length() - 1);
        }

        return str;
    }

    public static String toPluralText(String str) {
        if (str == null || str.trim().length() <= 1) {
            return str;
        }

        str = str.trim();
        String result = "";
        for (String s : str.split(" ")) {
            result += toPlural(s) + " ";
        }

        return result.trim();
    }

    public static String toPlural(String str) {
        if (str == null || str.trim().length() <= 1) {
            return str;
        }

        str = str.trim();
        boolean isUpper = isUpperCase(str);

        String aux = str.toUpperCase();
        String fim = null;
        String troca = "";
        if (aux.endsWith("OR")) {
            fim = "OR";
            troca = "ORES";
        } else if (aux.endsWith("ÁVEL")) {
            fim = "ÁVEL";
            troca = "ÁVEIS";
        } else if (aux.endsWith("AVEL")) {
            fim = "AVEL";
            troca = "AVEIS";
        } else if (aux.endsWith("ÃO")) {
            fim = "ÃO";
            troca = "ÕES";
        } else if (aux.endsWith("AO")) {
            fim = "AO";
            troca = "OES";
        } else if (!aux.endsWith("S")) {
            str = str + "" + (isUpper ? "S" : "s");
        }

        if (fim != null && !troca.isEmpty()) {
            str = str.substring(0, aux.lastIndexOf(fim)) + (!isUpper ? troca.toLowerCase() : troca);
        } else if (fim != null) {
            str = str.substring(0, str.length() - 1);
        }

        return str;
    }

    public static String getInfoTag(String xml, String tag) {
        if (xml == null || tag == null) {
            return null;
        }

        if (xml.contains("<" + tag + ">") && xml.contains("</" + tag + ">")) {
            return xml.substring(xml.indexOf("<" + tag + ">") + ("<" + tag + ">").length(), xml.indexOf("</" + tag + ">"));
        }

        return null;
    }

    public static String rmInitialWords(String value, char caracter) {
        if (value == null || value.isEmpty()) {
            return value;
        }

        int i = 0;
        while (i < value.length() && value.charAt(i) == caracter) {
            i++;
        }

        return value.substring(i);
    }

    public static String ajustaBase64(String value, int lineLength) {
        if (value == null || value.isEmpty()) {
            return value;
        }

        String linhas[] = value.replace("<img src=\"", "\n<img src=\"").replace("\r", "").split("\n");

        StringBuilder result = new StringBuilder();
        for (String linha : linhas) {
            if (linha.contains("src=\"data:image/")) {
                int startIndex = linha.indexOf("src=\"data:image/") + 16;
                int endIndex = linha.indexOf("\"", startIndex);
                if (endIndex == -1) {
                    endIndex = linha.length();
                }
                String imgSrc = linha.substring(startIndex, endIndex);

                StringBuilder bloco = new StringBuilder();
                for (int i = 0; i < imgSrc.length(); i += lineLength) {
                    int end = Math.min(i + lineLength, imgSrc.length());
                    bloco.append(imgSrc, i, end);
                    if (end != imgSrc.length()) {
                        bloco.append("\n");
                    }
                }

                result.append(linha, 0, startIndex).append(bloco).append(linha.substring(endIndex)).append("\n");
            } else {
                result.append(linha).append("\n");
            }
        }

        return result.toString();
    }

    public String base64ToHex(String base64) {
        byte[] decoded = Base64.getDecoder().decode(base64);
        return toHex(decoded);
    }

    private String toHex(byte[] data) {
        final StringBuffer sb = new StringBuffer(data.length * 2);
        for (int i = 0; i < data.length; i++) {
            sb.append(DIGITS_HEX[(data[i] >>> 4) & 0x0F]);
            sb.append(DIGITS_HEX[data[i] & 0x0F]);
        }
        return sb.toString();
    }



    public static String toCamelCaseRgx(String input) {
        Pattern pattern = Pattern.compile("_(\\w)");
        Matcher matcher = pattern.matcher(input);

        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(result, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(result);

        result.replace(0, 1, result.substring(0, 1).toLowerCase());

        return result.toString();
    }

    public static String formataCep(String cep) {
        return Pattern.compile("(\\d{5})(\\d{3})").matcher(cep).replaceAll("$1-$2");
    }

    public static String formataCpfCnpj(String cpfCnpj) {
        if (cpfCnpj == null || cpfCnpj.trim().isEmpty()) {
            return "";
        }

        switch (cpfCnpj.length()) {
            case 14: //CNPJ
                return Pattern.compile("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})").matcher(cpfCnpj).replaceAll("$1.$2.$3/$4-$5");
            case 12: //CEI
                return Pattern.compile("(\\d{2})(\\d{3})(\\d{3})(\\d{3})(\\d{1})").matcher(cpfCnpj).replaceAll("$1.$2.$3.$4-$5");
            case 11: //CPF
                return Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})").matcher(cpfCnpj).replaceAll("$1.$2.$3-$4");
            default:
                return cpfCnpj;
        }
    }

    public static String formataNit(String inscrNit) {
        if (inscrNit == null || inscrNit.trim().isEmpty()) {
            return "";
        }

        return Pattern.compile("(\\d{3})(\\d{5})(\\d{2})(\\d{1})").matcher(inscrNit).replaceAll("$1.$2.$3-$4");
    }

    public static String camuflarCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return "";
        }

        cpf = Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})").matcher(cpf).replaceAll("$1.$2.$3-$4");

        if (cpf.length() >= 11) {
            cpf = "***." + cpf.substring(4, 11) + "-**";
        }

        return cpf;
    }

    public static boolean isCnpj(String cnpj) {
        cnpj = cnpj.replace(".", "");
        cnpj = cnpj.replace("-", "");
        cnpj = cnpj.replace("/", "");

        try {
            Long.valueOf(cnpj);
        } catch (NumberFormatException e) {
            return false;
        }

        // considera-se erro CNPJ's formados por uma sequencia de numeros iguais
        if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111")
                || cnpj.equals("22222222222222") || cnpj.equals("33333333333333")
                || cnpj.equals("44444444444444") || cnpj.equals("55555555555555")
                || cnpj.equals("66666666666666") || cnpj.equals("77777777777777")
                || cnpj.equals("88888888888888") || cnpj.equals("99999999999999") || (cnpj.length() != 14)) {
            return (false);
        }
        char dig13, dig14;
        int sm, i, r, num, peso; // "try" - protege o código para eventuais
        // erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
                // converte o i-ésimo caractere do CNPJ em um número: // por
                // exemplo, transforma o caractere '0' no inteiro 0 // (48 eh a
                // posição de '0' na tabela ASCII)
                num = (int) (cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig13 = '0';
            } else {
                dig13 = (char) ((11 - r) + 48);
            }

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }
            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig14 = '0';
            } else {
                dig14 = (char) ((11 - r) + 48);
            }
            // Verifica se os dígitos calculados conferem com os dígitos
            // informados.
            if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public static boolean isCpf(String cpf) {
        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");

        try {
            Long.valueOf(cpf);
        } catch (NumberFormatException e) {
            return false;
        }

        if (cpf.length() != 11) {
            return false;
        }

        int d1, d2;
        int digito1, digito2, resto;
        int digitoCPF;
        String nDigResult;

        d1 = d2 = 0;
        digito1 = digito2 = resto = 0;

        for (int nCount = 1; nCount < cpf.length() - 1; nCount++) {
            digitoCPF = Integer.parseInt(cpf.substring(nCount - 1, nCount));

            // multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4
            // e assim por diante.
            d1 = d1 + (11 - nCount) * digitoCPF;

            // para o segundo digito repita o procedimento incluindo o primeiro
            // digito calculado no passo anterior.
            d2 = d2 + (12 - nCount) * digitoCPF;
        }

        // Primeiro resto da divisão por 11.
        resto = (d1 % 11);

        // Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11
        // menos o resultado anterior.
        if (resto < 2) {
            digito1 = 0;
        } else {
            digito1 = 11 - resto;
        }

        d2 += 2 * digito1;

        // Segundo resto da divisão por 11.
        resto = (d2 % 11);

        // Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11
        // menos o resultado anterior.
        if (resto < 2) {
            digito2 = 0;
        } else {
            digito2 = 11 - resto;
        }

        // Digito verificador do CPF que está sendo validado.
        String nDigVerific = cpf.substring(cpf.length() - 2, cpf.length());

        // Concatenando o primeiro resto com o segundo.
        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

        // comparar o digito verificador do cpf com o primeiro resto + o segundo
        // resto.
        return nDigVerific.equals(nDigResult);
    }

    public static boolean isCei(String value) {
        if (value == null) {
            return false;
        }

        //remove tudo que não for dígito
        String cei = value.replaceAll("[^\\d]", "");

        if (cei.length() != 12) {
            return false;
        }

        //rejeita CEIs inválidos conhecidos (todos os dígitos iguais)
        if (cei.matches("^(\\d)\\1{11}$")) {
            return false;
        }

        try {
            int n1 = Character.getNumericValue(cei.charAt(0));
            int n2 = Character.getNumericValue(cei.charAt(1));
            int n3 = Character.getNumericValue(cei.charAt(2));
            int n4 = Character.getNumericValue(cei.charAt(3));
            int n5 = Character.getNumericValue(cei.charAt(4));
            int n6 = Character.getNumericValue(cei.charAt(5));
            int n7 = Character.getNumericValue(cei.charAt(6));
            int n8 = Character.getNumericValue(cei.charAt(7));
            int n9 = Character.getNumericValue(cei.charAt(8));
            int n10 = Character.getNumericValue(cei.charAt(9));
            int n11 = Character.getNumericValue(cei.charAt(10));

            int d1 = n1 * 7 + n2 * 4 + n3 * 1 + n4 * 8 + n5 * 5 + n6 * 2 + n7 * 1 + n8 * 6 + n9 * 3 + n10 * 7 + n11 * 4;

            String wtemp = String.valueOf(d1);
            int somaDigitos = Character.getNumericValue(wtemp.charAt(wtemp.length() - 1));
            if (wtemp.length() >= 2) {
                somaDigitos += Character.getNumericValue(wtemp.charAt(wtemp.length() - 2));
            }

            d1 = (10 - (somaDigitos % 10)) % 10;

            int digitoInformado = Character.getNumericValue(cei.charAt(11));
            return d1 == digitoInformado;
        } catch (Exception e) {
            return false;
        }
    }

    public static String formatBytesSize(long value) {
        double size = (double) value;
        int i = -1;
        do {
            size = size / 1024;
            i++;
        } while (size > 1024);

        return String.format("%.2f", size) + " " + BYTES_UN[i];
    }

    public static String toTitledCase(String str) {
        String[] words = str.split("\\s");
        StringBuilder sb = new StringBuilder();

        for (String word : words) {
            sb.append(word.substring(0, 1).toUpperCase()).append(word.substring(1).toLowerCase()).append(" ");
        }

        return sb.toString();
    }

    public static String quotedStr(String value) {
        if (value == null) {
            return null;
        }
        return "'" + value.replace("'", "''") + "'";
    }

    public static String rmTPBNulls(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("-", "").replace(".", "").replace("/", "").replace("\\", "").replace(" ", "").trim();
    }

    public static String gerarSenha(int tamanho) {
        StringBuilder senha = new StringBuilder();
        for (int i = 0; i < tamanho; i++) {
            int posicao = (int) (Math.random() * CARACTS.length);
            String rdm = "" + Math.random();
            if (rdm.contains(".") && rdm.length() > 3) {
                rdm = rdm.substring(rdm.indexOf(".") + 1, rdm.indexOf(".") + 2);
            }
            senha.append((Double.parseDouble(rdm) % 2) == 0 ? CARACTS[posicao] : ((String) CARACTS[posicao]).toUpperCase());
        }
        return senha.toString();
    }

    public static String removeEndWith(String value, char caracter) {
        if (value == null || value.isEmpty()) {
            return value;
        }

        String result = "";
        boolean agoraPode = false;
        for (int i = value.length() - 1; i >= 0; i--) {
            if (agoraPode || value.charAt(i) != caracter) {
                result = value.charAt(i) + result;
                agoraPode = true;
            }
        }

        return result;
    }

    public static String removeUTF8BOM(String s) {
        if (s != null && s.startsWith(UTF8_BOM)) {
            s = s.substring(1);
        }
        return s;
    }

    public static boolean emailOnlyNumber(String email) {
        if (email == null || !email.contains("@")) {
            return true;
        }

        return email.split("@")[0].matches("[0-9]+");
    }

    public static boolean isOnlyNumber(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        return str.matches("[0-9]+");
    }

    public static String join(Object[] object, String separator) {
        return org.apache.commons.lang3.StringUtils.join(object, separator);
    }

    public static String join(Collection collection, String separator) {
        return org.apache.commons.lang3.StringUtils.join(collection, separator);
    }

    public static <T> String joinQuotedStr(Collection<T> collection, String separator) {
        return collection.stream().map(value -> quotedStr(value.toString())).collect(Collectors.joining(","));
    }

    public static List<Integer> splitToInt(String values, String spliter) {
        List<?> list = Arrays.asList(values.split(spliter));
        return list.stream().mapToInt(n -> Integer.valueOf((String) n)).boxed().collect(Collectors.toList());
    }

    public static String replaceLast(String str, String de, String para) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        int start = str.lastIndexOf(de);
        if (start < 0) {
            return str;
        }

        StringBuilder builder = new StringBuilder();
        builder.append(str.substring(0, start));
        builder.append(para);
        builder.append(str.substring(start + de.length()));
        return builder.toString();
        //return str.replaceFirst("(?s)" + de + "(?!.*?" + de + ")", para);
    }

    public static String replaceValuesInQuotes(String value, String replacement) {
        if (value == null || value.isEmpty()) {
            return value;
        }

        if (replacement == null) {
            replacement = "\"$1.$2\"";
        }

        Pattern pattern = Pattern.compile("\"([^\"]*),([^\"]*)\"");
        Matcher matcher = pattern.matcher(value);
        return matcher.replaceAll(replacement);
    }

    public static String ajusteCsv(String value, String de, String para) {
        if (value == null || value.isEmpty()) {
            return value;
        }

        Pattern pattern = Pattern.compile("" + de + "(?=(?:[^\"]*\"[^\"]*\")*(?![^\"]*\"))");
        Matcher matcher = pattern.matcher(value);
        return matcher.replaceAll(para);
    }

    public static String extractDocName(String fileName, int qtdeCount) {
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }

        if (!fileName.contains(".")) {
            return fileName;
        }

        fileName = fileName.substring(0, fileName.lastIndexOf("."));

        if (!fileName.contains("-")) {
            return fileName;
        }

        int count = 1;
        String retorno = "";
        for (int i = 0; i < fileName.length(); i++) {
            if (fileName.charAt(i) == '-') {
                count++;
            }

            if (count <= 4) {
                continue;
            }

            retorno += fileName.charAt(i);
        }

        if (retorno.startsWith("-")) {
            retorno = retorno.substring(1, retorno.length());
        }

        return retorno;
    }

    public static String getLastStrings(String value, int qtdeCaracteres) {
        if (value == null || value.isEmpty()) {
            return value;
        }

        int startPosition = Math.max(value.length() - qtdeCaracteres, 0);
        return value.substring(startPosition);
    }

    public static String getStringBetween(String value, String caracter1, String caracter2) {
        Pattern pattern = Pattern.compile(caracter1 + "(.*?)" + caracter2);
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    public static List<String> getStringsBetween(String value, String caracter1, String caracter2) {
        List<String> resultados = new ArrayList<>();
        Pattern pattern = Pattern.compile(Pattern.quote(caracter1) + "(.*?)" + Pattern.quote(caracter2));
        Matcher matcher = pattern.matcher(value);

        while (matcher.find()) {
            resultados.add(matcher.group(1).trim());
        }

        return resultados;
    }

}
