package br.com.nfse.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Utilitário para busca de municípios brasileiros pelo código IBGE. 
 *
 * Fonte dos dados:
 * IBGE – Relatório de Divisão Territorial Brasileira 2024 (DTB 2024).  
 * 5.571 municípios, base: 31/12/2024.
 */
public final class MunicipioUtils {

    private static final Logger LOG = Logger.getLogger(MunicipioUtils.class.getName());

    private static final String RESOURCE = "/municipios.properties";

    private static volatile Map<String, Municipio> CACHE = null;

    private static final Object LOCK = new Object();

    private MunicipioUtils() {
    }

    /*
     Busca um município pelo código IBGE completo de 7 dígitos.
    
     Uso:
       MunicipioUtils.get("1100940")
           .ifPresent(mun -> {
               params.put("emitxMun", mun.getNomeCidade());
               params.put("emitUF", mun.getUF());
         });       
     */
    public static Optional<Municipio> get(String codigoIBGE) {
        if (codigoIBGE == null || codigoIBGE.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(getCache().get(codigoIBGE.trim()));
    }

    public static int sizeCache() {
        return getCache().size();
    }

    // =========================================================================
    // Carga do cache
    // =========================================================================
    private static Map<String, Municipio> getCache() {
        Map<String, Municipio> cache = MunicipioUtils.CACHE;
        if (cache == null) {
            synchronized (LOCK) {
                cache = MunicipioUtils.CACHE;
                if (cache == null) {
                    MunicipioUtils.CACHE = cache = carregarMunicipios();
                }
            }
        }

        return cache;
    }

    /*
     * Lê o arquivo "municipios.properties" do classpath e constrói o mapa.
     * Cada linha tem o formato {CODIGO=NOME|SIGLA|ESTADO}.
     */
    private static Map<String, Municipio> carregarMunicipios() {
        Map<String, Municipio> mapa = new HashMap<>(6000, 0.9f);

        InputStream in = MunicipioUtils.class.getResourceAsStream(RESOURCE);
        if (in == null) {
            LOG.severe("Arquivo não encontrado no classpath: " + RESOURCE);
            return mapa;
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {

            String linha;
            int linhaNr = 0;

            while ((linha = br.readLine()) != null) {
                linhaNr++;
                linha = linha.trim();

                // ignorar vazias e comentários
                if (linha.isEmpty() || linha.startsWith("#")) {
                    continue;
                }

                int sepEq = linha.indexOf('=');
                if (sepEq < 0) {
                    LOG.log(Level.WARNING, "Linha {0} ignorada (sem ''=''): {1}", new Object[]{linhaNr, linha});
                    continue;
                }

                String codigo = linha.substring(0, sepEq).trim();
                String resto = linha.substring(sepEq + 1);

                String[] partes = resto.split("\\|", -1);
                if (partes.length < 3) {
                    continue;
                }

                Municipio municipio = new Municipio(
                        codigo,
                        partes[0].trim(),// nomeCidade
                        partes[1].trim(),// UF sigla
                        partes[2].trim() // nomeEstado
                );

                mapa.put(codigo, municipio);
            }

        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Erro ao ler " + RESOURCE, e);
        }

        return mapa;
    }

    // =========================================================================
    // Classe Municipio (record estático interno)
    // =========================================================================
    public static final class Municipio {

        private final String codigo;
        private final String nomeCidade;
        private final String uf;
        private final String nomeEstado;

        public Municipio(String codigo, String nomeCidade, String uf, String nomeEstado) {
            this.codigo = Objects.requireNonNull(codigo, "codigo");
            this.nomeCidade = Objects.requireNonNull(nomeCidade, "nomeCidade");
            this.uf = Objects.requireNonNull(uf, "uf");
            this.nomeEstado = Objects.requireNonNull(nomeEstado, "nomeEstado");
        }

        /*
         * Código IBGE de 7 dígitos. Ex.: "4314902"
         */
        public String getCodigo() {
            return codigo;
        }

        /*
         * Nome oficial do município. Ex.: "Porto Alegre"
         */
        public String getNomeCidade() {
            return nomeCidade;
        }

        /*
         * Sigla da UF. Ex.: "RS"
         */
        public String getUF() {
            return uf;
        }

        /*
         * Nome completo do estado. Ex.: "Rio Grande do Sul"
         */
        public String getNomeEstado() {
            return nomeEstado;
        }

        @Override
        public String toString() {
            return nomeCidade + " - " + uf + " (" + codigo + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Municipio)) {
                return false;
            }
            return codigo.equals(((Municipio) o).codigo);
        }

        @Override
        public int hashCode() {
            return codigo.hashCode();
        }
    }
}
