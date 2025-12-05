
import br.com.nfse.CertificateManager;
import br.com.nfse.ConfigManager;
import br.com.nfse.Nfse;
import br.com.nfse.dto.AutorEvento;
import br.com.nfse.dto.ConvenioResult;
import br.com.nfse.dto.EventoResult;
import br.com.nfse.dto.NFSeResult;
import br.com.nfse.dto.enuns.AmbienteEnum;
import br.com.nfse.utils.DateUtils;
import br.com.nfse.utils.FileUtils;
import br.com.nfse.utils.StringUtils;
import br.com.nfse.xsd.*;
import java.io.File;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * @author eduardo
 */
public class Test {

    private static final String CERT_PATH = "C:\\ZTEMP\\target.pfx";
    private static final String CERT_PASSWORD = "*****";
    private static final String SCHEMAS_PATH = "C:\\GitHub\\nfse\\schemas\\1.01\\PL_NFSE_NT04_RTCv101";
    private static final String CH_NFSE_TESTE = "43172021293234012000161000000000000525080000000000";

    public enum TipoTeste {
        ENVIO("E", "Envio de DPS"),
        CONVENIO("C", "Consulta Convênio"),
        DANFSE("D", "Geração DANFSe"),
        CANCELAMENTO("X", "Cancelamento NFSe"),
        EVENTO("Y", "Envio de Evento"),
        TODOS("ALL", "Executar Todos os Testes");

        private final String codigo;
        private final String descricao;

        TipoTeste(String codigo, String descricao) {
            this.codigo = codigo;
            this.descricao = descricao;
        }

        public static Optional<TipoTeste> fromCodigo(String codigo) {
            return Arrays.stream(values())
                    .filter(t -> t.codigo.equalsIgnoreCase(codigo))
                    .findFirst();
        }

        public String getCodigo() {
            return codigo;
        }

        public String getDescricao() {
            return descricao;
        }
    }

    public static void main(String[] args) {
        try {
            String testCode = args.length > 0 ? args[0] : "ALL";

            ConfigManager config = buildConfig();
            TestRunner runner = new TestRunner(config);
            Optional<TipoTeste> tipoTeste = TipoTeste.fromCodigo(testCode);

            // Se presente, executa o 'runner::executar'
            tipoTeste.ifPresent(runner::executar);

            // Se ausente, executa o bloco 'else'
            if (!tipoTeste.isPresent()) {
                System.err.println(" Teste não reconhecido: " + testCode);
                mostrarOpcoesDisponiveis();
            }
        } catch (Exception e) {
            System.err.println(" Erro na execução dos testes:");
            e.printStackTrace();
        }
    }

    private static void mostrarOpcoesDisponiveis() {
        System.out.println("\n Testes disponíveis:");
        Arrays.stream(TipoTeste.values())
                .forEach(t -> System.out.printf("   %s - %s%n", t.getCodigo(), t.getDescricao()));
    }

    private static ConfigManager buildConfig() throws Exception {
        CertificateManager certificado = CertificateManager.builder()
                .fromFile(new File(CERT_PATH))
                .password(CERT_PASSWORD)
                .zoneId(ZoneId.of("America/Sao_Paulo"))
                .build();

        return ConfigManager.builder()
                .certificado(certificado)
                .ambiente(AmbienteEnum.HOMOLOGACAO)
                .pathSchemas(SCHEMAS_PATH)
                .build();
    }

    static class TestRunner {

        private final ConfigManager config;
        private final Map<TipoTeste, TestCase> testes;

        public TestRunner(ConfigManager config) {
            this.config = config;
            this.testes = inicializarTestes();
        }

        private Map<TipoTeste, TestCase> inicializarTestes() {
            Map<TipoTeste, TestCase> map = new HashMap<>();

            map.put(TipoTeste.ENVIO, new TestCase(
                    "Envio DPS",
                    this::testEnvio
            ));

            map.put(TipoTeste.CONVENIO, new TestCase(
                    "Consulta Convênio",
                    this::testConvenio
            ));

            map.put(TipoTeste.DANFSE, new TestCase(
                    "Geração DANFSe",
                    this::testDanfse
            ));

            map.put(TipoTeste.CANCELAMENTO, new TestCase(
                    "Cancelamento NFSe",
                    this::testCancelamento
            ));

            map.put(TipoTeste.EVENTO, new TestCase(
                    "Envio Evento",
                    this::testEvento
            ));

            return map;
        }

        public void executar(TipoTeste tipo) {
            if (tipo == TipoTeste.TODOS) {
                executarTodos();
                return;
            }

            TestCase testCase = testes.get(tipo);
            if (testCase != null) {
                testCase.executar();
            } else {
                System.err.println(" Teste não implementado: " + tipo.getDescricao());
            }
        }

        private void executarTodos() {
            System.out.println(" Executando todos os testes...\n");

            Arrays.stream(TipoTeste.values())
                    .filter(t -> t != TipoTeste.TODOS)
                    .forEach(tipo -> {
                        testes.get(tipo).executar();
                        System.out.println(); // Linha em branco entre testes
                    });

            System.out.println(" Todos os testes foram executados!");
        }

        private void testEnvio() throws Exception {
            TCDPS dps = DpsBuilder.criar(config);

            NFSeResult result = Nfse.builder()
                    .config(config)
                    .assinar(true)
                    .validar(true)
                    .enviarDps(dps);

            System.out.println(" Resultado do envio:");
            System.out.println(result);
        }

        private void testConvenio() throws Exception {
            ConvenioResult result = Nfse.builder()
                    .config(config)
                    .consultaConvenio("4317202");

            System.out.println(" Resultado da consulta:");
            System.out.println(result);
        }

        private void testDanfse() throws Exception {
            byte[] pdf = Nfse.builder()
                    .config(config)
                    .chNFSe("43172021293234012000161000000000000525080000000000")
                    .danfse();

            String caminho = FileUtils.getTempDir() + "danfse_test.pdf";
            FileUtils.saveToFileTemp(pdf, caminho);
            System.out.println(" DANFSe gerado: " + caminho);
        }

        private void testCancelamento() throws Exception {
            EventoResult result = Nfse.builder()
                    .config(config)
                    .chNFSe(CH_NFSE_TESTE)
                    .autor(AutorEvento.cnpj("93234012000161"))
                    .dhEvento(ZonedDateTime.now())
                    .cancelar("2", "Teste de Cancelamento - Serviço não prestado");

            System.out.println(" Resultado do cancelamento:");
            System.out.println(result);
        }

        private void testEvento() throws Exception {
            TE105104 evento = EventoBuilder.criarEvento105104(
                    "1",
                    "1122331",
                    "Descrição Evento"
            );

            EventoResult result = Nfse.builder()
                    .config(config)
                    .chNFSe(CH_NFSE_TESTE)
                    .autor(AutorEvento.cnpj("93234012000161"))
                    .dhEvento(ZonedDateTime.now())
                    .evento(evento, 1);

            System.out.println(" Resultado do evento:");
            System.out.println(result);
        }
    }

    static class TestCase {

        private final String nome;
        private final ThrowingConsumer acao;

        public TestCase(String nome, ThrowingConsumer acao) {
            this.nome = nome;
            this.acao = acao;
        }

        public void executar() {
            System.out.println("====================================================");
            System.out.printf("|   TESTE: %-39s |%n", nome);
            System.out.println("====================================================");

            try {
                long inicio = System.currentTimeMillis();
                acao.accept();
                long duracao = System.currentTimeMillis() - inicio;

                System.out.printf("  -> Teste concluído em %dms%n", duracao);
            } catch (Exception e) {
                System.err.println("  -> Erro no teste: " + e.getMessage());
                e.printStackTrace();
            }
        }

        @FunctionalInterface
        interface ThrowingConsumer {

            void accept() throws Exception;
        }
    }

    static class DpsBuilder {

        public static TCDPS criar(ConfigManager config) {
            TCDPS dps = new TCDPS();
            dps.setVersao("1.01");

            TCInfDPS infDPS = criarInfDPS(config);
            dps.setInfDPS(infDPS);

            return dps;
        }

        private static TCInfDPS criarInfDPS(ConfigManager config) {
            TCInfDPS inf = new TCInfDPS();

            // Configurações básicas
            inf.setTpAmb(config.getAmbiente().getValue());
            inf.setDhEmi(DateUtils.formatWithZone(ZonedDateTime.now()));
            inf.setVerAplic("App-1.00");
            inf.setSerie("1");
            inf.setNDPS("3");
            inf.setDCompet("2025-11-26");
            inf.setTpEmit("1");
            inf.setCLocEmi("4317202");

            // Partes envolvidas
            inf.setPrest(PrestadorBuilder.criar());
            inf.setToma(TomadorBuilder.criar());

            // Serviço e valores
            inf.setServ(ServicoBuilder.criar());
            inf.setValores(ValoresBuilder.criar());

            // Gerar ID
            String idDps = gerarIdDPS(inf);
            inf.setId("DPS" + idDps);

            return inf;
        }

        private static String gerarIdDPS(TCInfDPS infDPS) {
            TCInfoPrestador prest = infDPS.getPrest();
            String inscr = Optional.ofNullable(prest.getCNPJ())
                    .orElse(prest.getCPF());
            String codMun = prest.getEnd().getEndNac().getCMun();
            String tipoPessoa = inscr.length() == 14 ? "2" : "1";

            return codMun
                    + tipoPessoa
                    + StringUtils.zerosParaEsquerda(inscr, 14)
                    + StringUtils.zerosParaEsquerda(infDPS.getSerie(), 5)
                    + StringUtils.zerosParaEsquerda(infDPS.getNDPS(), 15);
        }
    }

    static class PrestadorBuilder {

        public static TCInfoPrestador criar() {
            TCInfoPrestador prest = new TCInfoPrestador();
            prest.setCNPJ("93234012000161");
            prest.setIM("55555");
            prest.setXNome("NOME EMPRESA LTDA");
            prest.setEnd(criarEndereco());
            prest.setFone("55999995555");
            prest.setEmail("mail@mail.com");
            prest.setRegTrib(criarRegimeTributario());

            return prest;
        }

        private static TCEndereco criarEndereco() {
            TCEndereco end = new TCEndereco();
            end.setXLgr("RUA LATERAL");
            end.setNro("200");
            end.setXCpl("Sala 101");
            end.setXBairro("CENTRO");

            TCEnderNac endNac = new TCEnderNac();
            endNac.setCMun("4317202");
            endNac.setCEP("98780000");
            end.setEndNac(endNac);

            return end;
        }

        private static TCRegTrib criarRegimeTributario() {
            TCRegTrib reg = new TCRegTrib();
            reg.setOpSimpNac("3");
            reg.setRegApTribSN("1");
            reg.setRegEspTrib("0");
            return reg;
        }
    }

    static class TomadorBuilder {

        public static TCInfoPessoa criar() {
            TCInfoPessoa tom = new TCInfoPessoa();
            tom.setCPF("62638224065");
            tom.setXNome("John Wick Fake");
            tom.setEnd(criarEndereco());

            return tom;
        }

        private static TCEndereco criarEndereco() {
            TCEndereco end = new TCEndereco();
            end.setXLgr("Av. Central");
            end.setNro("100");
            end.setXCpl("Bloco Y");
            end.setXBairro("Centro");

            TCEnderNac endNac = new TCEnderNac();
            endNac.setCMun("4317202");
            endNac.setCEP("98780000");
            end.setEndNac(endNac);

            return end;
        }
    }

    static class ServicoBuilder {

        public static TCServ criar() {
            TCServ serv = new TCServ();
            serv.setLocPrest(criarLocalizacao());
            serv.setCServ(criarClassificacao());
            return serv;
        }

        private static TCLocPrest criarLocalizacao() {
            TCLocPrest loc = new TCLocPrest();
            loc.setCLocPrestacao("4317202");
            return loc;
        }

        private static TCCServ criarClassificacao() {
            TCCServ cServ = new TCCServ();
            cServ.setCTribNac("010700");
            cServ.setXDescServ("Manutenção e Suporte do Sistema");
            cServ.setCIntContrib("1495143");
            cServ.setCNBS("000000001");
            return cServ;
        }
    }

    static class ValoresBuilder {

        public static TCInfoValores criar() {
            TCInfoValores val = new TCInfoValores();
            val.setVServPrest(criarValorServico());
            val.setTrib(criarTributacao());
            return val;
        }

        private static TCVServPrest criarValorServico() {
            TCVServPrest vServ = new TCVServPrest();
            vServ.setVServ("675.48");
            return vServ;
        }

        private static TCInfoTributacao criarTributacao() {
            TCInfoTributacao trib = new TCInfoTributacao();
            trib.setTribMun(criarTributacaoMunicipal());
            trib.setTotTrib(criarTotalTributos());
            return trib;
        }

        private static TCTribMunicipal criarTributacaoMunicipal() {
            TCTribMunicipal tribMun = new TCTribMunicipal();
            tribMun.setTribISSQN("1");
            tribMun.setPAliq("2.22");
            tribMun.setTpRetISSQN("1");
            return tribMun;
        }

        private static TCTribTotal criarTotalTributos() {
            TCTribTotal totTrib = new TCTribTotal();

            TCTribTotalMonet vTot = new TCTribTotalMonet();
            vTot.setVTotTribFed("90.85");
            vTot.setVTotTribEst("0.00");
            vTot.setVTotTribMun("13.51");

            totTrib.setVTotTrib(vTot);
            return totTrib;
        }
    }

    static class EventoBuilder {

        public static TE105104 criarEvento105104(String motivo, String processo, String descricao) {
            TE105104 evt = new TE105104();
            evt.setCMotivo(motivo);
            evt.setNProcAdm(processo);
            evt.setXMotivo(descricao);
            return evt;
        }
    }
}
