package br.com.nfse;

import br.com.nfse.dto.danfse.*;
import br.com.nfse.dto.danfse.DPS.*;
import br.com.nfse.dto.danfse.IbsCbsNFSe.*;
import br.com.nfse.utils.MunicipioUtils;
import br.com.nfse.utils.NumberUtils;
import br.com.nfse.utils.QrCodeUtils;
import br.com.nfse.utils.XmlUtils;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.*;

import java.io.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import net.sf.jasperreports.engine.util.JRLoader;

/*
  Gera o DANFSe (Documento Auxiliar da NFS-e) em PDF a partir de um XML de
  NFS-e.
  
  Exemplo de uso:
  byte[] pdf = DanfseGenerator.builder()
      .xml(xmlString)
      .jrxmlPath("/templates/danfse.jrxml")  // opcional – usa classpath se omitido            
      .imgPrefeitura(logoPrefeitura)         // opcional
      .generate();
  }
 
  @author eduardo
 */
public class DanfseGenerator {

    private static final DateTimeFormatter FMT_DATETIME = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static final DateTimeFormatter FMT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final NumberFormat FMT_MOEDA = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String xml;
        private String jrxmlPath;
        private String jrxmlClasspath;

        private String xPaisPrestacao = "Brasil";
        private String xPaisIncidenciaIbsCbs = "Brasil";

        private boolean cancelada = false;

        private byte[] imgPrefeitura;

        private Builder() {
        }

        public Builder xml(String xml) {
            this.xml = Objects.requireNonNull(xml, "XML é obrigatório");
            return this;
        }

        public Builder jrxmlPath(String jrxmlPath) {
            this.jrxmlPath = jrxmlPath;
            return this;
        }

        public Builder jrxmlClasspath(String jrxmlClasspath) {
            this.jrxmlClasspath = jrxmlClasspath;
            return this;
        }

        public Builder imgPrefeitura(byte[] imgPrefeitura) {
            this.imgPrefeitura = imgPrefeitura;
            return this;
        }

        public Builder xPaisPrestacao(String xPaisPrestacao) {
            this.xPaisPrestacao = xPaisPrestacao;
            return this;
        }

        public Builder xPaisIncidenciaIbsCbs(String xPaisIncidenciaIbsCbs) {
            this.xPaisIncidenciaIbsCbs = xPaisIncidenciaIbsCbs;
            return this;
        }

        public Builder cancelada(boolean cancelada) {
            this.cancelada = cancelada;
            return this;
        }

        /**
         * Gera o DANFSe e retorna o conteúdo PDF como array de bytes.
         *
         * @return
         * @throws Exception em caso de falha no parse do XML, compilação do
         * JRXML ou exportação do PDF
         */
        public byte[] generate() throws Exception {
            if (xml == null || xml.isEmpty()) {
                throw new IllegalStateException("xml é obrigatório");
            }

            NFSeXml nfse = XmlUtils.xmlToObject(xml, NFSeXml.class);
            Map<String, Object> params = this.buildValues(nfse);

            JasperReport report = this.loadReport();
            JasperPrint print = JasperFillManager.fillReport(report, params, new JREmptyDataSource());
            return exportToPdf(print, nfse);
        }

        // =====================================================================
        // buildParams – todos os nomes correspondem ao danfse.jrxml
        // =====================================================================
        private Map<String, Object> buildValues(NFSeXml nfse) {
            Map<String, Object> result = new HashMap<>();

            InfNFSe inf = nfse.getInfNFSe();
            if (inf == null) {
                putDefaults(result);
                return result;
            }

            DPS dps = inf.getDps();
            InfDPS infDps = dps != null ? dps.getInfDPS() : null;
            Emit emit = inf.getEmit();

            // -----------------------------------------------------------------
            // Cabeçalho / identificação da NFS-e
            // -----------------------------------------------------------------            
            result.put("chNFSe", inf.getChNFSe());
            result.put("nNFSe", valueOrHyphen(inf.getnNFSe()));

            if (infDps != null) {
                result.put("dCompet", formatDate(infDps.getdCompet()));
                result.put("dhEmi", formatDateTime(infDps.getDhEmi()));
                result.put("nDPS", valueOrHyphen(infDps.getnDPS()));
                result.put("serie", valueOrHyphen(infDps.getSerie()));
            } else {
                result.put("dCompet", formatDate(inf.getDhProc()));
                result.put("dhEmi", formatDateTime(inf.getDhProc()));
                result.put("nDPS", valueOrHyphen(inf.getnNFSe()));
                result.put("serie", "-");
            }

            if (inf.getxTribMun() != null && !inf.getxTribMun().isEmpty()) {
                result.put("xTributacao", inf.getxTribMun());
            } else {
                result.put("xTributacao", inf.getxTribNac());
            }

            // -----------------------------------------------------------------
            // Emitente / Prestador
            // -----------------------------------------------------------------
            if (emit != null) {
                result.put("emitCNPJ", formatCnpjCpf(emit.getInscricaoFederal()));
                result.put("emitxNome", valueOrHyphen(emit.getxNome()));
                result.put("emitfone", formatFone(emit.getFone()));
                EnderNac en = emit.getEnderNac();
                if (en != null) {
                    result.put("emitxLgr", valueOrHyphen(en.getxLgr()));
                    result.put("emitnro", valueOrHyphen(en.getNro()));
                    result.put("emitxBairro", valueOrHyphen(en.getxBairro()));
                    result.put("emitxMun", valueOrHyphen(inf.getxLocEmi()));
                    result.put("emitUF", valueOrHyphen(en.getUf()));
                    result.put("emitCEP", formatCep(en.getCep()));
                }
                result.put("emitIM", valueOrHyphen(emit.getIm()));
            }

            if (infDps != null) {
                Prestador prest = infDps.getPrest();
                if (prest != null) {
                    result.putIfAbsent("emitCNPJ", formatCnpjCpf(prest.getInscricaoFederal()));
                    result.put("emitemail", valueOrHyphen(prest.getEmail()));
                    result.putIfAbsent("emitfone", formatFone(prest.getFone()));
                    result.putIfAbsent("emitIM", valueOrHyphen(prest.getIm()));
                    RegTrib rt = prest.getRegTrib();
                    if (rt != null) {
                        result.put("opSimpNac", valueOrHyphen(rt.getOpSimpNac()));
                        result.put("regApTribSN", valueOrHyphen(rt.getRegApTribSN()));
                    }
                }
            }

            // Endereço do emitente pode também vir da DPS.prest.end
            if (infDps != null && infDps.getPrest() != null && infDps.getPrest().getEnd() != null) {
                DPS.Endereco endPrest = infDps.getPrest().getEnd();
                result.putIfAbsent("emitxLgr", valueOrHyphen(endPrest.getxLgr()));
                result.putIfAbsent("emitnro", valueOrHyphen(endPrest.getNro()));
                result.putIfAbsent("emitxBairro", valueOrHyphen(endPrest.getxBairro()));
                if (endPrest.getEndNac() != null) {
                    result.putIfAbsent("emitUF", valueOrHyphen(endPrest.getEndNac().getUf()));
                    result.putIfAbsent("emitCEP", formatCep(endPrest.getEndNac().getCep()));
                }
            }

            // -----------------------------------------------------------------
            // Tomador
            // -----------------------------------------------------------------
            if (infDps != null) {
                Tomador toma = infDps.getToma();
                if (toma != null) {
                    String docToma = toma.getInscricaoFederal();
                    if (docToma != null && docToma.length() == 11) {
                        result.put("tomaCPF", formatCnpjCpf(docToma));
                        result.put("tomaCNPJ", "");
                    } else {
                        result.put("tomaCNPJ", formatCnpj(docToma));
                        result.put("tomaCPF", "");
                    }
                    result.put("tomaIM", valueOrHyphen(toma.getIm()));
                    result.put("tomaxNome", valueOrHyphen(toma.getxNome()));
                    result.put("tomafone", formatFone(toma.getFone()));
                    result.put("tomaemail", valueOrHyphen(toma.getEmail()));

                    DPS.Endereco endToma = toma.getEnd();
                    if (endToma != null) {
                        result.put("tomaxLgr", valueOrHyphen(endToma.getxLgr()));
                        result.put("tomanro", valueOrHyphen(endToma.getNro()));
                        result.put("tomaxBairro", valueOrHyphen(endToma.getxBairro()));
                        result.put("endToma", endToma.enderecoCompleto());
                        if (endToma.getEndNac() != null) {
                            result.put("tomacMun", valueOrHyphen(endToma.getEndNac().getcMun()));
                            result.put("tomaCEP", formatCep(endToma.getEndNac().getCep()));

                            MunicipioUtils.get(endToma.getEndNac().getcMun())
                                    .ifPresent(mun -> {
                                        result.put("tomaxMun", mun.getNomeCidade());
                                        result.put("tomaUf", mun.getUF());
                                    });
                        }
                    }
                }
            }

            if (inf.getValores() != null) {
                result.put("vCalcDR", formatCurrency(inf.getValores().getvCalcDR()));
                result.put("vBC", formatCurrency(inf.getValores().getvBC()));
                result.put("pAliqAplic", formatNumber(inf.getValores().getpAliqAplic()));
                result.put("vISSQN", formatCurrency(inf.getValores().getvISSQN()));
                result.put("vTotalRet", formatCurrency(inf.getValores().getvTotalRet()));
                result.put("vLiq", formatCurrency(inf.getValores().getvLiq()));
            }

            // -----------------------------------------------------------------
            // Serviço
            // -----------------------------------------------------------------
            if (infDps != null) {
                Servico serv = infDps.getServ();
                if (serv != null) {
                    CServ cs = serv.getcServ();
                    if (cs != null) {
                        result.put("cTribNac", cs.cTribNacFormatado());
                        //result.put("xTribNac", valueOrHyphen(cs.getxDescServ()));
                        result.put("cTribMun", valueOrHyphen(cs.getcTribMun()));
                        result.put("xDescServ", valueOrHyphen(cs.getxDescServ()));
                        result.put("cNBS", valueOrHyphen(cs.getcNBSFormatado()));
                        //result.put("xNBS", valueOrHyphen(cs.getxDescServ())); // descrição NBS não existe no XML

                        if (cs.getcTribMun() != null) {
                            result.put("cTributacao", valueOrHyphen(cs.getcTribMun()));
                        } else {
                            result.put("cTributacao", cs.cTribNacFormatado());
                        }
                    }
                    LocPrest lp = serv.getLocPrest();
                    if (lp != null) {
                        MunicipioUtils.get(lp.getcLocPrestacao())
                                .ifPresent(mun -> {
                                    result.put("xLocPrestacao", mun.getNomeCidade());
                                    result.put("ufLocPrestacao", mun.getUF());
                                    result.put("xPaisPrestacao", valueOrHyphen(xPaisPrestacao));

                                    result.put("xLocPrestacaoCpl", valueOrHyphen(inf.getxLocPrestacao()) + " / " + mun.getUF() + " / " + valueOrHyphen(xPaisPrestacao));
                                });
                    }
                    InfoCompl ic = serv.getInfoCompl();
                    result.putIfAbsent("xInfComp", ic != null ? valueOrHyphen(ic.getxInfComp()) : "");
                }
            }

            // -----------------------------------------------------------------
            // Tributação municipal (ISSQN)
            // -----------------------------------------------------------------
            String cIndOp = "-";
            if (infDps != null && infDps.getValores() != null) {
                ValoresDPS vd = infDps.getValores();

                if (infDps.getIBSCBS() != null) {
                    cIndOp = valueOrHyphen(infDps.getIBSCBS().getcIndOp());
                    result.put("xFinNFSe", valueOrHyphen(infDps.getIBSCBS().getxFinNFSe()));
                    if (infDps.getIBSCBS().getValores() != null && infDps.getIBSCBS().getValores().getTrib() != null) {
                        result.put("CST", valueOrHyphen(infDps.getIBSCBS().getValores().getTrib().getgIBSCBS().getCST()));
                        result.put("cClassTrib", valueOrHyphen(infDps.getIBSCBS().getValores().getTrib().getgIBSCBS().getcClassTrib()));
                    }
                }

                VServPrest vs = vd.getvServPrest();
                if (vs != null) {
                    result.put("vServ", formatCurrency(vs.getvServ()));
                    result.put("vDescCond", formatCurrency(vs.getvDescCondicionado()));
                    result.put("vDescIncond", formatCurrency(vs.getvDescIncondicionado()));
                }

                Tributos trib = vd.getTrib();
                if (trib != null) {
                    TribMun tm = trib.getTribMun();
                    if (tm != null) {
                        result.put("tribISSQN", valueOrHyphen(tm.getTribISSQN()));
                        result.put("tpRetISSQN", valueOrHyphen(tm.getTpRetISSQN()));
                        result.put("pAliq", formatNumber(tm.getpAliq()));

                        if (tm.getvISSQN() != null) {
                            result.put("vBC", formatNumber(tm.getvBC()));
                            result.put("vISSQN", formatNumber(tm.getvISSQN()));
                            result.put("vCalcBM", formatNumber(tm.getvBM()));
                            result.put("tpBM", valueOrHyphen(tm.getTpBM()));
                            result.put("tpImunidade", valueOrHyphen(tm.getTpImmune()));
                            result.put("tpSusp", valueOrHyphen(tm.getExigSuspensa()));
                            result.put("nProcesso", valueOrHyphen(tm.getnProcesso()));
                            result.put("regEspTrib", infDps.getPrest() != null
                                    && infDps.getPrest().getRegTrib() != null
                                    ? valueOrHyphen(infDps.getPrest().getRegTrib().getRegEspTrib()) : "-");
                            result.put("cPaisResult", "-");
                            result.put("vCalcDR", "-");
                        }
                    }

                    TribFed tf = trib.getTribFed();
                    if (tf != null) {
                        result.put("vRetIRRF", formatNumber(tf.getvRetIRRF()));
                        result.put("vRetCP", formatNumber(tf.getvRetPrev()));
                        result.put("vRetCSLL", formatNumber(tf.getvRetCSLL()));
                        result.put("xRetCP", "-"); // descrição contrib.sociais – preenchida abaixo
                        PisCofins pc = tf.getPisCofins();
                        if (pc != null) {
                            result.put("tpRetPisCofins", valueOrHyphen(pc.getTpRetPisCofins()));
                            if (pc.getvRetPis() != null) {
                                result.put("vPis", formatCurrency(pc.getvRetPis()));
                                result.put("_rawVPis", pc.getvRetPis());
                            } else {
                                result.put("vPis", formatCurrency(pc.getvPis()));
                                result.put("_rawVPis", pc.getvPis());
                            }

                            if (pc.getvRetCofins() != null) {
                                result.put("vCofins", formatCurrency(pc.getvRetCofins()));
                                result.put("_rawVCofins", pc.getvRetCofins());
                            } else {
                                result.put("vCofins", formatCurrency(pc.getvCofins()));
                                result.put("_rawVCofins", pc.getvCofins());
                            }

                            result.put("xRetCP", pc.descTpRet());
                        }
                    }

                    TotTrib tt = trib.getTotTrib();
                    if (tt != null && tt.getvTotTrib() != null) {
                        VTotTrib vtt = tt.getvTotTrib();
                        result.put("vTotTribFed", formatCurrency(vtt.getvTotTribFed()));
                        result.put("vTotTribEst", formatCurrency(vtt.getvTotTribEst()));
                        result.put("vTotTribMun", formatCurrency(vtt.getvTotTribMun()));

                        // vTotalRet = soma das retenções federais
                        BigDecimal irrf = bigDecimalOf(result, "vRetIRRF");
                        BigDecimal csll = bigDecimalOf(result, "vRetCSLL");
                        BigDecimal retCP = bigDecimalOf(result, "vRetCP");
                        result.put("vTotalRet", formatCurrency(irrf.add(csll).add(retCP)));
                    }
                }
            }

            // -----------------------------------------------------------------
            // IBS / CBS
            // -----------------------------------------------------------------
            IbsCbsNFSe ibsCbs = inf.getIbsCbs();
            if (ibsCbs != null) {
                Optional<MunicipioUtils.Municipio> ibsCbsIncidencia = MunicipioUtils.get(ibsCbs.getcLocalidadeIncid());
                if (ibsCbsIncidencia.isPresent()) {
                    result.put("xLocalIncidenciaIbsCbsCpl", cIndOp + " / " + valueOrHyphen(ibsCbs.getcLocalidadeIncid()) + " / " + valueOrHyphen(ibsCbsIncidencia.get().getNomeCidade()) + " / " + valueOrHyphen(ibsCbsIncidencia.get().getUF()) + " / " + valueOrHyphen(xPaisIncidenciaIbsCbs));
                }

                ValoresIbsCbs vi = ibsCbs.getValores();
                if (vi != null) {
                    result.put("ibsVBC", formatNumber(vi.getvBC()));

                    AliqUF uf = vi.getUf();
                    if (uf != null) {
                        result.put("ibsAliqUF", formatNumber(uf.getpIBSUF()));
                        result.put("ibsRedAliqUF", formatNumber(uf.getpRedAliqUF()));
                        result.put("ibsAliqEfetUF", formatNumber(uf.getpAliqEfetUF()));
                    }
                    AliqMun mun = vi.getMun();
                    if (mun != null) {
                        result.put("ibsAliqMun", formatNumber(mun.getpIBSMun()));
                        result.put("ibsRedAliqMun", formatNumber(mun.getpRedAliqMun()));
                        result.put("ibsAliqEfetMun", formatNumber(mun.getpAliqEfetMun()));
                    }
                    AliqFed fed = vi.getFed();
                    if (fed != null) {
                        result.put("cbsAliq", formatNumber(fed.getpCBS()));
                        result.put("cbsRedAliq", formatNumber(fed.getpRedAliqCBS()));
                        result.put("cbsAliqEfet", formatNumber(fed.getpAliqEfetCBS()));
                    }
                }

                TotCIBS tot = ibsCbs.getTotCIBS();
                if (tot != null) {
                    result.put("vTotNF", formatCurrency(tot.getvTotNF()));

                    GIBS gibs = tot.getgIBS();
                    if (gibs != null) {
                        result.put("ibsTot", formatCurrency(gibs.getvIBSTot()));
                        result.put("_rawIbsTot", gibs.getvIBSTot());
                        result.put("ibsUFTot", gibs.getgIBSUFTot() != null ? formatNumber(gibs.getgIBSUFTot().getvIBSUF()) : "-");
                        result.put("ibsMunTot", gibs.getgIBSMunTot() != null ? formatNumber(gibs.getgIBSMunTot().getvIBSMun()) : "-");
                    }
                    GCBS gcbs = tot.getgCBS();
                    if (gcbs != null) {
                        result.put("cbsTot", formatCurrency(gcbs.getvCBS()));
                        result.put("_rawCbsTot", gcbs.getvCBS());
                    }
                }
            }

            result.put("cIndOp", cIndOp);
            MunicipioUtils.get(inf.getcLocIncid())
                    .ifPresent(mun -> {
                        result.put("cLocIncid", valueOrHyphen(inf.getcLocIncid()));
                        result.put("xLocIncid", valueOrHyphen(mun.getNomeCidade() + " - " + mun.getUF()));
                    });

            // -----------------------------------------------------------------
            // Valor líquido + IBS/CBS
            // -----------------------------------------------------------------
            String vLiqRaw = inf.getValores() != null ? inf.getValores().getvLiq() : null;
            result.put("vLiq", formatCurrency(vLiqRaw));

            // vPisCofinsTotal: soma PIS + COFINS já formatada (evita BigDecimal no JRXML)
            BigDecimal bdPis = bigDecimalOfStr((String) result.getOrDefault("_rawVPis", null));
            BigDecimal bdCofins = bigDecimalOfStr((String) result.getOrDefault("_rawVCofins", null));
            result.put("vPisCofinsTotal", formatCurrency(bdPis.add(bdCofins)));

            BigDecimal bdLiq = bigDecimalOfStr(vLiqRaw);
            BigDecimal bdIbs = bigDecimalOfStr((String) result.getOrDefault("_rawIbsTot", null));
            BigDecimal bdCbs = bigDecimalOfStr((String) result.getOrDefault("_rawCbsTot", null));

            result.put("vTotIBSCBS", formatCurrency(bdIbs.add(bdCbs)));
            result.put("vLiqIBSCBS", formatCurrency(bdLiq.add(bdIbs).add(bdCbs)));

            result.put("imgQrCode", QrCodeUtils.toBase64("https://www.nfse.gov.br/ConsultaPublica/?tpc=1&chave=" + inf.getChNFSe()));

            if (imgPrefeitura != null) {
                result.put("imgPrefeitura", Base64.getEncoder().encodeToString(imgPrefeitura));
            }

            result.put("ambGer", inf.getAmbGer());
            result.put("xAmbGer", inf.getXAmbGer());

            if (dps != null) {
                result.put("tpAmb", dps.getInfDPS().getTpAmb());
                result.put("xTpAmb", dps.getInfDPS().getXTpAmb());
            }

            result.put("cancelada", cancelada);

            putDefaults(result);
            return result;
        }

        private void putDefaults(Map<String, Object> result) {
            String[] stringParams = {
                "chNFSe", "nNFSe", "dCompet", "dhEmi", "nDPS", "serie", "xFinNFSe",
                "emitCNPJ", "emitxNome", "emitxMun", "emitCEP", "opSimpNac", "regApTribSN",
                "tomaCPF", "tomaIM", "tomaxNome", "tomafone", "tomaemail", "endToma",
                "tomaxMun", "tomaCEP", "xTribNac", "xLocPrestacao", "xDescServ",
                "tribISSQN", "xLocIncid", "vServ", "tpRetISSQN", "vDescCond", "vDescIncond",
                "vLiq", "vTotTribFed", "vTotTribEst", "vTotTribMun",
                "emitemail", "emitfone", "emitUF", "emitxLgr", "emitnro", "emitxBairro",
                "tomaxLgr", "tomanro", "tomaxBairro", "emitIM", "tomaCNPJ",
                "cTributacao", "cTribNac", "cTribMun", "cPaisPrestacao", "cPaisResult",
                "pAliq", "nProcesso", "tpImunidade", "tpSusp", "regEspTrib",
                "tpBM", "vCalcDR", "vCalcBM", "vISSQN", "vBC", "vRetISSQN",
                "vRetCP", "vRetCSLL", "vRetIRRF", "tpRetPisCofins", "vPis", "vCofins",
                "vTotalRet", "cNBS", "xNBS", "xInfComp",
                "tomaUf", "ufLocPrestacao", "xRetCP",
                "ibsVBC", "ibsAliqUF", "ibsRedAliqUF", "ibsAliqEfetUF", "xLocalIncidenciaIbsCbsCpl",
                "ibsAliqMun", "ibsRedAliqMun", "ibsAliqEfetMun",
                "cbsAliq", "cbsRedAliq", "cbsAliqEfet",
                "ibsTot", "ibsUFTot", "ibsMunTot", "cbsTot", "vTotIBSCBS", "vTotNF", "vLiqIBSCBS",
                "CST", "cClassTrib",
                "xTributacao", "xLocalPrestacao", "xUfPrestacao", "xPaisPrestacao",
                "ambGer", "xAmbGer", "tpAmb", "xTpAmb"
            };

            for (String key : stringParams) {
                result.putIfAbsent(key, "-");
            }
        }

        // =====================================================================
        // Carregamento do template
        // =====================================================================
        private JasperReport loadReport() throws JRException, IOException {
            // 1. Caminho explícito informado pelo caller
            if (jrxmlPath != null && !jrxmlPath.isEmpty()) {
                File f = new File(jrxmlPath);
                if (f.getName().endsWith(".jasper")) {
                    try (InputStream in = new FileInputStream(f)) {
                        return (JasperReport) JRLoader.loadObject(in);
                    }
                }
                return JasperCompileManager.compileReport(jrxmlPath);
            }

            // 2. Sem caminho explícito: tenta o .jasper pré-compilado no classpath
            try (InputStream jasper = DanfseGenerator.class.getResourceAsStream("/danfse.jasper")) {
                if (jasper != null) {
                    return (JasperReport) JRLoader.loadObject(jasper);
                }
            }

            // 3. Fallback: compila o .jrxml em tempo de execução            
            String cp = jrxmlClasspath != null ? jrxmlClasspath : "/danfse.jrxml";
            try (InputStream jrxml = DanfseGenerator.class.getResourceAsStream(cp)) {
                if (jrxml != null) {
                    return JasperCompileManager.compileReport(jrxml);
                }
            }

            throw new IllegalStateException(
                    "Template não encontrado. Coloque 'danfse.jasper' (preferido) ou 'danfse.jrxml' "
                    + "em src/main/resources, ou informe o caminho via jrxmlPath().");
        }

        // =====================================================================
        // Exportação PDF
        // =====================================================================
        private byte[] exportToPdf(JasperPrint jasperPrint, NFSeXml nfse) throws JRException {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
            SimplePdfExporterConfiguration cfg = new SimplePdfExporterConfiguration();
            cfg.setMetadataTitle("DANFSe | NF: " + nfse.getInfNFSe().getnNFSe() + " | " + nfse.getInfNFSe().getChNFSe());
            cfg.setMetadataAuthor("Sistema NFS-e");
            exporter.setConfiguration(cfg);
            exporter.exportReport();
            return baos.toByteArray();
        }

        private String valueOrHyphen(String s) {
            return (s == null || s.isEmpty()) ? "-" : s.trim();
        }

        private BigDecimal bigDecimalOf(Map<String, Object> p, String key) {
            return bigDecimalOfStr((String) p.getOrDefault(key, null));
        }

        private BigDecimal bigDecimalOfStr(String s) {
            if (s == null || s.isEmpty() || "-".equals(s.trim())) {
                return BigDecimal.ZERO;
            }
            try {
                if (s.contains(".") && s.contains(",")) {
                    s = s.replace(".", "");
                }
                return new BigDecimal(s.replace(",", ".").trim());
            } catch (Exception e) {
                return BigDecimal.ZERO;
            }
        }

        private String formatNumber(String s) {
            if (s == null || s.isEmpty()) {
                return "-";
            }
            return NumberUtils.format(bigDecimalOfStr(s));
        }

        private String formatCurrency(BigDecimal bd) {
            if (bd == null) {
                return "-";
            }
            return FMT_MOEDA.format(bd);
        }

        private String formatCurrency(String s) {
            if (s == null || s.isEmpty() || "-".equals(s.trim())) {
                return "-";
            }
            try {
                BigDecimal bd = new BigDecimal(s.trim());
                return FMT_MOEDA.format(bd);
            } catch (NumberFormatException e) {
                return "";
            }
        }

        private String formatDateTime(String iso) {
            if (iso == null || iso.isEmpty()) {
                return "-";
            }
            try {
                return ZonedDateTime.parse(iso).format(FMT_DATETIME);
            } catch (Exception e) {
                try {
                    return LocalDateTime.parse(iso.substring(0, 19)).format(FMT_DATETIME);
                } catch (Exception ex) {
                    return iso;
                }
            }
        }

        private String formatDate(String iso) {
            if (iso == null || iso.isEmpty()) {
                return "-";
            }
            try {
                return LocalDate.parse(iso.substring(0, 10)).format(FMT_DATE);
            } catch (Exception e) {
                return iso;
            }
        }

        private String formatCnpj(String cnpj) {
            if (cnpj == null) {
                return "-";
            }
            String d = cnpj.replaceAll("\\D", "");
            if (d.length() == 14) {
                return d.substring(0, 2) + "." + d.substring(2, 5) + "." + d.substring(5, 8) + "/" + d.substring(8, 12) + "-" + d.substring(12);
            }
            return cnpj;
        }

        private String formatCnpjCpf(String doc) {
            if (doc == null) {
                return "-";
            }
            String d = doc.replaceAll("\\D", "");
            if (d.length() == 14) {
                return formatCnpj(d);
            }
            if (d.length() == 11) {
                return d.substring(0, 3) + "." + d.substring(3, 6) + "." + d.substring(6, 9) + "-" + d.substring(9);
            }
            return doc;
        }

        private String formatFone(String fone) {
            if (fone == null || fone.isEmpty()) {
                return "-";
            }
            String d = fone.replaceAll("\\D", "");
            if (d.length() == 11) {
                return "(" + d.substring(0, 2) + ") " + d.substring(2, 7) + "-" + d.substring(7);
            }
            if (d.length() == 10) {
                return "(" + d.substring(0, 2) + ") " + d.substring(2, 6) + "-" + d.substring(6);
            }
            return fone;
        }

        private String formatCep(String cep) {
            if (cep == null) {
                return "-";
            }
            String d = cep.replaceAll("\\D", "");
            if (d.length() == 8) {
                return d.substring(0, 5) + "-" + d.substring(5);
            }
            return cep;
        }
    }
}
