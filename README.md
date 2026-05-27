# NFS-e Nacional — Cliente Java

Cliente Java para integração com a **NFS-e Nacional**, oferecendo estruturas, serviços e utilitários para comunicação com a API oficial do projeto NFS-e Nacional.

## ✨ Funcionalidades

- 🔗 Comunicação com os endpoints oficiais da NFS-e Nacional  
- 📄 Emissão, consulta e cancelamento de NFS-e  
- 🔐 Suporte a autenticação via certificado digital A1  
- 🧩 Serialização e desserialização automática dos modelos JSON
- 🔍 Consulta/distribuição das NFS-e em lote ou individual 
- 📜 Geração da DANFSe

## ✨ Documentação técnica
- 🔗 https://www.gov.br/nfse/pt-br/biblioteca/documentacao-tecnica 

## ✨ Repositório

    <dependency>
        <groupId>io.github.eduardokuhn89</groupId>
        <artifactId>nfse-client</artifactId>
        <version>1.2.2</version>
    </dependency>

## ✨ Exemplo de uso

    CertificateManager certificado = CertificateManager.builder()
          .fromFile(new File(CERT_PATH))
          .password(CERT_PASSWORD)
          .zoneId(ZoneId.of("America/Sao_Paulo"))
          .build();

    ConfigManager config = ConfigManager.builder()
          .certificado(certificado)
          .ambiente(AmbienteEnum.HOMOLOGACAO)
          .pathSchemas(SCHEMAS_PATH)
          .build();          

    Nfse.builder()
          .config(config)
          .assinar(true)
          .validar(true)
          .enviarDps(dps);

    DFeResult result = NfseDistrib.builder()
          .config(config)
          .consultarPorUltimoNSU(0L);          

    String xml = Nfse.builder()
          .config(config)
          .chNFSe(CH_NFSE_TESTE)
          .consultaXml()
          .getNfseXml();          

    byte[] pdf = DanfseGenerator.builder()
          .xml(xml)
          //.cancelada(true)
          //.imgPrefeitura(logoPrefeitura)      
          .generate();          
