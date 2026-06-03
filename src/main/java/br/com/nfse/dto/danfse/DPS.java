package br.com.nfse.dto.danfse;

import javax.xml.bind.annotation.*;

/**
 * Elemento {@code <DPS>} embutido na NFS-e. Contém prestador, tomador, serviço
 * e valores/tributos.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DPS {

    @XmlAttribute(name = "versao")
    private String versao;

    @XmlElement(name = "infDPS", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private InfDPS infDPS;

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public InfDPS getInfDPS() {
        return infDPS;
    }

    public void setInfDPS(InfDPS infDPS) {
        this.infDPS = infDPS;
    }

    // =========================================================================
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class InfDPS {

        @XmlAttribute(name = "Id")
        private String id;

        @XmlElement(name = "tpAmb", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String tpAmb;

        @XmlElement(name = "dhEmi", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String dhEmi;

        @XmlElement(name = "verAplic", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String verAplic;

        @XmlElement(name = "serie", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String serie;

        @XmlElement(name = "nDPS", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String nDPS;

        @XmlElement(name = "dCompet", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String dCompet;

        @XmlElement(name = "tpEmit", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String tpEmit;

        @XmlElement(name = "cLocEmi", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String cLocEmi;

        @XmlElement(name = "prest", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private Prestador prest;

        @XmlElement(name = "toma", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private Tomador toma;

        @XmlElement(name = "serv", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private Servico serv;

        @XmlElement(name = "valores", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private ValoresDPS valores;

        @XmlElement(name = "IBSCBS", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private IbsCbsDPS IBSCBS;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTpAmb() {
            return tpAmb;
        }

        public void setTpAmb(String tpAmb) {
            this.tpAmb = tpAmb;
        }

        public String getDhEmi() {
            return dhEmi;
        }

        public void setDhEmi(String dhEmi) {
            this.dhEmi = dhEmi;
        }

        public String getVerAplic() {
            return verAplic;
        }

        public void setVerAplic(String verAplic) {
            this.verAplic = verAplic;
        }

        public String getSerie() {
            return serie;
        }

        public void setSerie(String serie) {
            this.serie = serie;
        }

        public String getnDPS() {
            return nDPS;
        }

        public void setnDPS(String nDPS) {
            this.nDPS = nDPS;
        }

        public String getdCompet() {
            return dCompet;
        }

        public void setdCompet(String dCompet) {
            this.dCompet = dCompet;
        }

        public String getTpEmit() {
            return tpEmit;
        }

        public void setTpEmit(String tpEmit) {
            this.tpEmit = tpEmit;
        }

        public String getcLocEmi() {
            return cLocEmi;
        }

        public void setcLocEmi(String cLocEmi) {
            this.cLocEmi = cLocEmi;
        }

        public Prestador getPrest() {
            return prest;
        }

        public void setPrest(Prestador prest) {
            this.prest = prest;
        }

        public Tomador getToma() {
            return toma;
        }

        public void setToma(Tomador toma) {
            this.toma = toma;
        }

        public Servico getServ() {
            return serv;
        }

        public void setServ(Servico serv) {
            this.serv = serv;
        }

        public ValoresDPS getValores() {
            return valores;
        }

        public void setValores(ValoresDPS valores) {
            this.valores = valores;
        }

        public IbsCbsDPS getIBSCBS() {
            return IBSCBS;
        }

        public void setIBSCBS(IbsCbsDPS IBSCBS) {
            this.IBSCBS = IBSCBS;
        }

        public String getXTpAmb() {
            if (tpAmb == null) {
                return "";
            }

            switch (tpAmb) {
                case "1":
                    return "Produção";
                default:
                    return "Homologação";
            }
        }
    }

    // =========================================================================
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Prestador {

        @XmlElement(name = "CNPJ", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String cnpj;

        @XmlElement(name = "CPF", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String cpf;

        @XmlElement(name = "IM", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String im;

        @XmlElement(name = "xNome", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String xNome;

        @XmlElement(name = "fone", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String fone;

        @XmlElement(name = "email", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String email;

        @XmlElement(name = "end", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private Endereco end;

        @XmlElement(name = "regTrib", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private RegTrib regTrib;

        public String getCnpj() {
            return cnpj;
        }

        public void setCnpj(String cnpj) {
            this.cnpj = cnpj;
        }

        public String getCpf() {
            return cpf;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
        }

        public String getIm() {
            return im;
        }

        public void setIm(String im) {
            this.im = im;
        }

        public String getxNome() {
            return xNome;
        }

        public void setxNome(String xNome) {
            this.xNome = xNome;
        }

        public String getFone() {
            return fone;
        }

        public void setFone(String fone) {
            this.fone = fone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Endereco getEnd() {
            return end;
        }

        public void setEnd(Endereco end) {
            this.end = end;
        }

        public RegTrib getRegTrib() {
            return regTrib;
        }

        public void setRegTrib(RegTrib regTrib) {
            this.regTrib = regTrib;
        }

        public String getInscricaoFederal() {
            return cnpj != null ? cnpj : cpf;
        }
    }

    // =========================================================================
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Tomador {

        @XmlElement(name = "CNPJ", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String cnpj;

        @XmlElement(name = "CPF", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String cpf;

        @XmlElement(name = "NIF", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String nif;

        @XmlElement(name = "IM", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String im;

        @XmlElement(name = "xNome", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String xNome;

        @XmlElement(name = "fone", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String fone;

        @XmlElement(name = "email", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String email;

        @XmlElement(name = "end", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private Endereco end;

        public String getCnpj() {
            return cnpj;
        }

        public void setCnpj(String cnpj) {
            this.cnpj = cnpj;
        }

        public String getCpf() {
            return cpf;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
        }

        public String getNif() {
            return nif;
        }

        public void setNif(String nif) {
            this.nif = nif;
        }

        public String getIm() {
            return im;
        }

        public void setIm(String im) {
            this.im = im;
        }

        public String getxNome() {
            return xNome;
        }

        public void setxNome(String xNome) {
            this.xNome = xNome;
        }

        public String getFone() {
            return fone;
        }

        public void setFone(String fone) {
            this.fone = fone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Endereco getEnd() {
            return end;
        }

        public void setEnd(Endereco end) {
            this.end = end;
        }

        public String getInscricaoFederal() {
            if (cnpj != null) {
                return cnpj;
            }
            if (cpf != null) {
                return cpf;
            }
            return nif;
        }
    }

    // =========================================================================
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Endereco {

        @XmlElement(name = "endNac", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private EnderNac endNac;

        @XmlElement(name = "xLgr", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String xLgr;

        @XmlElement(name = "nro", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String nro;

        @XmlElement(name = "xCpl", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String xCpl;

        @XmlElement(name = "xBairro", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String xBairro;

        public EnderNac getEndNac() {
            return endNac;
        }

        public void setEndNac(EnderNac endNac) {
            this.endNac = endNac;
        }

        public String getxLgr() {
            return xLgr;
        }

        public void setxLgr(String xLgr) {
            this.xLgr = xLgr;
        }

        public String getNro() {
            return nro;
        }

        public void setNro(String nro) {
            this.nro = nro;
        }

        public String getxCpl() {
            return xCpl;
        }

        public void setxCpl(String xCpl) {
            this.xCpl = xCpl;
        }

        public String getxBairro() {
            return xBairro;
        }

        public void setxBairro(String xBairro) {
            this.xBairro = xBairro;
        }

        public String enderecoCompleto() {
            StringBuilder sb = new StringBuilder();
            if (xLgr != null) {
                sb.append(xLgr);
            }
            if (nro != null) {
                sb.append(", ").append(nro);
            }
            if (xCpl != null && !xCpl.isEmpty()) {
                sb.append(", ").append(xCpl);
            }
            if (xBairro != null) {
                sb.append(", ").append(xBairro);
            }
            return sb.toString();
        }
    }

    // =========================================================================
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class RegTrib {

        /**
         * Opção pelo Simples Nacional: 1=Não optante, 2=Optante ME/EPP,
         * 3=Optante SIMEI.
         */
        @XmlElement(name = "opSimpNac", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String opSimpNac;

        /**
         * Regime especial de tributação: 0=Nenhum ...
         */
        @XmlElement(name = "regEspTrib", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String regEspTrib;

        @XmlElement(name = "regApTribSN", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String regApTribSN;

        public String getOpSimpNac() {
            return opSimpNac;
        }

        public void setOpSimpNac(String opSimpNac) {
            this.opSimpNac = opSimpNac;
        }

        public String getRegEspTrib() {
            return regEspTrib;
        }

        public void setRegEspTrib(String regEspTrib) {
            this.regEspTrib = regEspTrib;
        }

        public String getRegApTribSN() {
            return regApTribSN;
        }

        public void setRegApTribSN(String regApTribSN) {
            this.regApTribSN = regApTribSN;
        }

        public String descricaoSimplsNac() {
            if (opSimpNac == null) {
                return "-";
            }

            switch (opSimpNac) {
                case "1":
                    return "Não optante";
                case "2":
                    return "Optante ME/EPP";
                case "3":
                    return "Optante SIMEI";
                default:
                    return opSimpNac;
            }
        }
    }

    // =========================================================================
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Servico {

        @XmlElement(name = "locPrest", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private LocPrest locPrest;

        @XmlElement(name = "cServ", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private CServ cServ;

        @XmlElement(name = "infoCompl", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private InfoCompl infoCompl;

        public LocPrest getLocPrest() {
            return locPrest;
        }

        public void setLocPrest(LocPrest locPrest) {
            this.locPrest = locPrest;
        }

        public CServ getcServ() {
            return cServ;
        }

        public void setcServ(CServ cServ) {
            this.cServ = cServ;
        }

        public InfoCompl getInfoCompl() {
            return infoCompl;
        }

        public void setInfoCompl(InfoCompl infoCompl) {
            this.infoCompl = infoCompl;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class LocPrest {

        @XmlElement(name = "cLocPrestacao", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String cLocPrestacao;
        @XmlElement(name = "xLocPrestacao", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String xLocPrestacao;
        @XmlElement(name = "cPais", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String cPais;
        @XmlElement(name = "xPais", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String xPais;

        public String getcLocPrestacao() {
            return cLocPrestacao;
        }

        public void setcLocPrestacao(String c) {
            this.cLocPrestacao = c;
        }

        public String getxLocPrestacao() {
            return xLocPrestacao;
        }

        public void setxLocPrestacao(String x) {
            this.xLocPrestacao = x;
        }

        public String getcPais() {
            return cPais;
        }

        public void setcPais(String c) {
            this.cPais = c;
        }

        public String getxPais() {
            return xPais;
        }

        public void setxPais(String x) {
            this.xPais = x;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class CServ {

        @XmlElement(name = "cTribNac", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String cTribNac;
        @XmlElement(name = "cTribMun", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String cTribMun;
        @XmlElement(name = "xDescServ", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String xDescServ;
        @XmlElement(name = "cNBS", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String cNBS;
        @XmlElement(name = "cIntContrib", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String cIntContrib;

        public String getcTribNac() {
            return cTribNac;
        }

        public void setcTribNac(String c) {
            this.cTribNac = c;
        }

        public String getcTribMun() {
            return cTribMun;
        }

        public void setcTribMun(String c) {
            this.cTribMun = c;
        }

        public String getxDescServ() {
            return xDescServ;
        }

        public void setxDescServ(String x) {
            this.xDescServ = x;
        }

        public String getcNBS() {
            return cNBS;
        }

        public void setcNBS(String c) {
            this.cNBS = c;
        }

        public String getcIntContrib() {
            return cIntContrib;
        }

        public void setcIntContrib(String c) {
            this.cIntContrib = c;
        }

        /*
         Formata código de tributação nacional como "NN.NN.NN".
         */
        public String cTribNacFormatado() {
            if (cTribNac == null || cTribNac.length() < 6) {
                return cTribNac;
            }
            return cTribNac.substring(0, 2) + "." + cTribNac.substring(2, 4) + "." + cTribNac.substring(4);
        }

        /*
         Aplica a máscara padrão NBS como X.XXXX.XX.XX
         */
        public String getcNBSFormatado() {
            if (cNBS == null || cNBS.length() < 9) {
                return cNBS;
            }
            return cNBS.substring(0, 1) + "." + cNBS.substring(1, 5) + "." + cNBS.substring(5, 7) + "." + cNBS.substring(7, 9);
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class InfoCompl {

        @XmlElement(name = "xInfComp", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String xInfComp;

        public String getxInfComp() {
            return xInfComp;
        }

        public void setxInfComp(String x) {
            this.xInfComp = x;
        }
    }

    // =========================================================================
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ValoresDPS {

        @XmlElement(name = "vServPrest", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private VServPrest vServPrest;

        @XmlElement(name = "trib", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private Tributos trib;

        public VServPrest getvServPrest() {
            return vServPrest;
        }

        public void setvServPrest(VServPrest v) {
            this.vServPrest = v;
        }

        public Tributos getTrib() {
            return trib;
        }

        public void setTrib(Tributos trib) {
            this.trib = trib;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class IbsCbsDPS {

        @XmlElement(name = "finNFSe", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String finNFSe;
        @XmlElement(name = "cIndOp", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String cIndOp;
        @XmlElement(name = "indDest", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String indDest;

        @XmlElement(name = "valores", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private IbsCbsValores valores;

        public String getFinNFSe() {
            return finNFSe;
        }

        public void setFinNFSe(String finNFSe) {
            this.finNFSe = finNFSe;
        }

        public String getcIndOp() {
            return cIndOp;
        }

        public void setcIndOp(String cIndOp) {
            this.cIndOp = cIndOp;
        }

        public String getIndDest() {
            return indDest;
        }

        public void setIndDest(String indDest) {
            this.indDest = indDest;
        }

        public IbsCbsValores getValores() {
            return valores;
        }

        public void setValores(IbsCbsValores valores) {
            this.valores = valores;
        }

        public String getxFinNFSe() {
            if (finNFSe == null) {
                return "-";
            }

            switch (finNFSe) {
                case "0":
                    return "NFS-e regular";
                case "1":
                    return "NFS-e de crédito";
                case "2":
                    return "NFS-e de débito";
                default:
                    return finNFSe;
            }
        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class IbsCbsValores {

        @XmlElement(name = "trib", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private IbsCbsTrib trib;

        public IbsCbsTrib getTrib() {
            return trib;
        }

        public void setTrib(IbsCbsTrib trib) {
            this.trib = trib;
        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class IbsCbsTrib {

        @XmlElement(name = "gIBSCBS", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private IbsCbsGIBSCBS gIBSCBS;

        public IbsCbsGIBSCBS getgIBSCBS() {
            return gIBSCBS;
        }

        public void setgIBSCBS(IbsCbsGIBSCBS gIBSCBS) {
            this.gIBSCBS = gIBSCBS;
        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class IbsCbsGIBSCBS {

        @XmlElement(name = "CST", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String CST;

        @XmlElement(name = "cClassTrib", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String cClassTrib;

        public String getCST() {
            return CST;
        }

        public void setCST(String CST) {
            this.CST = CST;
        }

        public String getcClassTrib() {
            return cClassTrib;
        }

        public void setcClassTrib(String cClassTrib) {
            this.cClassTrib = cClassTrib;
        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class VServPrest {

        @XmlElement(name = "vServ", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String vServ;
        @XmlElement(name = "vDescCondicionado", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String vDescCondicionado;
        @XmlElement(name = "vDescIncondicionado", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String vDescIncondicionado;

        public String getvServ() {
            return vServ;
        }

        public void setvServ(String v) {
            this.vServ = v;
        }

        public String getvDescCondicionado() {
            return vDescCondicionado;
        }

        public void setvDescCondicionado(String v) {
            this.vDescCondicionado = v;
        }

        public String getvDescIncondicionado() {
            return vDescIncondicionado;
        }

        public void setvDescIncondicionado(String v) {
            this.vDescIncondicionado = v;
        }
    }

    // =========================================================================
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Tributos {

        @XmlElement(name = "tribMun", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private TribMun tribMun;

        @XmlElement(name = "tribFed", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private TribFed tribFed;

        @XmlElement(name = "totTrib", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private TotTrib totTrib;

        public TribMun getTribMun() {
            return tribMun;
        }

        public void setTribMun(TribMun t) {
            this.tribMun = t;
        }

        public TribFed getTribFed() {
            return tribFed;
        }

        public void setTribFed(TribFed t) {
            this.tribFed = t;
        }

        public TotTrib getTotTrib() {
            return totTrib;
        }

        public void setTotTrib(TotTrib t) {
            this.totTrib = t;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class TribMun {

        /**
         * Tributação ISSQN: 1=Operação Tributável, 2=Imune, 3=Isenta...
         */
        @XmlElement(name = "tribISSQN", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String tribISSQN;
        /**
         * Tipo retenção ISSQN: 1=Não Retido, 2=Retido pelo Prestador...
         */
        @XmlElement(name = "tpRetISSQN", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String tpRetISSQN;
        @XmlElement(name = "vBC", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String vBC;
        @XmlElement(name = "pAliq", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String pAliq;
        @XmlElement(name = "vISSQN", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String vISSQN;
        @XmlElement(name = "vBM", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String vBM;
        @XmlElement(name = "BM", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String bm;
        @XmlElement(name = "tpBM", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String tpBM;
        @XmlElement(name = "cMunFG", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String cMunFG;
        @XmlElement(name = "tpImmune", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String tpImmune;
        @XmlElement(name = "exigSuspensa", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String exigSuspensa;
        @XmlElement(name = "nProcesso", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String nProcesso;

        public String getTribISSQN() {
            return tribISSQN;
        }

        public void setTribISSQN(String t) {
            this.tribISSQN = t;
        }

        public String getTpRetISSQN() {
            return tpRetISSQN;
        }

        public String getTpRetISSQN(String defaultValue) {
            if (tpRetISSQN == null) {
                return defaultValue;
            }
            return tpRetISSQN;
        }

        public void setTpRetISSQN(String t) {
            this.tpRetISSQN = t;
        }

        public String getvBC() {
            return vBC;
        }

        public void setvBC(String v) {
            this.vBC = v;
        }

        public String getpAliq() {
            return pAliq;
        }

        public void setpAliq(String p) {
            this.pAliq = p;
        }

        public String getvISSQN() {
            return vISSQN;
        }

        public void setvISSQN(String v) {
            this.vISSQN = v;
        }

        public String getvBM() {
            return vBM;
        }

        public void setvBM(String v) {
            this.vBM = v;
        }

        public String getBm() {
            return bm;
        }

        public void setBm(String b) {
            this.bm = b;
        }

        public String getTpBM() {
            return tpBM;
        }

        public void setTpBM(String t) {
            this.tpBM = t;
        }

        public String getcMunFG() {
            return cMunFG;
        }

        public void setcMunFG(String c) {
            this.cMunFG = c;
        }

        public String getTpImmune() {
            return tpImmune;
        }

        public void setTpImmune(String t) {
            this.tpImmune = t;
        }

        public String getExigSuspensa() {
            return exigSuspensa;
        }

        public void setExigSuspensa(String e) {
            this.exigSuspensa = e;
        }

        public String getnProcesso() {
            return nProcesso;
        }

        public void setnProcesso(String n) {
            this.nProcesso = n;
        }

        public String descTribISSQN() {
            if (tribISSQN == null) {
                return "-";
            }

            switch (tribISSQN) {
                case "1":
                    return "Operação Tributável";
                case "2":
                    return "Imune";
                case "3":
                    return "Isenta";
                case "4":
                    return "Exportação";
                case "5":
                    return "Suspensa";
                case "6":
                    return "Não Incidência";
                default:
                    return tribISSQN;
            }
        }

        public String descTpRetISSQN() {
            if (tpRetISSQN == null) {
                return "-";
            }

            switch (tpRetISSQN) {
                case "1":
                    return "Não Retido";
                case "2":
                    return "Retido pelo Prestador";
                case "3":
                    return "Retido pelo Tomador";
                default:
                    return tpRetISSQN;
            }
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class TribFed {

        @XmlElement(name = "piscofins", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private PisCofins pisCofins;

        @XmlElement(name = "vRetIRRF", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String vRetIRRF;

        @XmlElement(name = "vRetCSLL", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String vRetCSLL;

        @XmlElement(name = "vRetPrev", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String vRetPrev;

        public PisCofins getPisCofins() {
            return pisCofins;
        }

        public void setPisCofins(PisCofins p) {
            this.pisCofins = p;
        }

        public String getvRetIRRF() {
            return vRetIRRF;
        }

        public void setvRetIRRF(String v) {
            this.vRetIRRF = v;
        }

        public String getvRetCSLL() {
            return vRetCSLL;
        }

        public void setvRetCSLL(String v) {
            this.vRetCSLL = v;
        }

        public String getvRetPrev() {
            return vRetPrev;
        }

        public void setvRetPrev(String v) {
            this.vRetPrev = v;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class PisCofins {

        @XmlElement(name = "CST", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String cst;
        @XmlElement(name = "tpRetPisCofins", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String tpRetPisCofins;
        @XmlElement(name = "vPis", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String vPis;
        @XmlElement(name = "vRetPis", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String vRetPis;
        @XmlElement(name = "vCofins", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String vCofins;
        @XmlElement(name = "vRetCofins", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String vRetCofins;

        public String getCst() {
            return cst;
        }

        public void setCst(String c) {
            this.cst = c;
        }

        public String getTpRetPisCofins() {
            return tpRetPisCofins;
        }

        public void setTpRetPisCofins(String t) {
            this.tpRetPisCofins = t;
        }

        public String getvRetPis() {
            return vRetPis;
        }

        public void setvRetPis(String v) {
            this.vRetPis = v;
        }

        public String getvPis() {
            return vPis;
        }

        public void setvPis(String vPis) {
            this.vPis = vPis;
        }

        public String getvCofins() {
            return vCofins;
        }

        public void setvCofins(String vCofins) {
            this.vCofins = vCofins;
        }

        public String getvRetCofins() {
            return vRetCofins;
        }

        public void setvRetCofins(String v) {
            this.vRetCofins = v;
        }

        public String descTpRet() {
            if (tpRetPisCofins == null) {
                return "-";
            }

            switch (tpRetPisCofins) {
                case "0":
                    return "PIS/COFINS/CSLL Não Retidos";
                case "1":
                    return "PIS/COFINS Retido";
                case "2":
                    return "PIS/COFINS Não Retido";
                case "3":
                    return "PIS/COFINS/CSLL Retidos";
                case "4":
                    return "PIS/COFINS Retidos, CSLL Não Retido";
                case "5":
                    return "PIS Retido, COFINS/CSLL Não Retido";
                case "6":
                    return "COFINS Retido, PIS/CSLL Não Retido";
                case "7":
                    return "PIS Não Retido, COFINS/CSLL Retidos";
                case "8":
                    return "PIS/COFINS Não Retidos, CSLL Retido";
                case "9":
                    return "COFINS Não Retido, PIS/CSLL Retidos";
                default:
                    return tpRetPisCofins;
            }
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class TotTrib {

        @XmlElement(name = "vTotTrib", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private VTotTrib vTotTrib;

        public VTotTrib getvTotTrib() {
            return vTotTrib;
        }

        public void setvTotTrib(VTotTrib v) {
            this.vTotTrib = v;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class VTotTrib {

        @XmlElement(name = "vTotTribFed", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String vTotTribFed;
        @XmlElement(name = "vTotTribEst", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String vTotTribEst;
        @XmlElement(name = "vTotTribMun", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String vTotTribMun;

        public String getvTotTribFed() {
            return vTotTribFed;
        }

        public void setvTotTribFed(String v) {
            this.vTotTribFed = v;
        }

        public String getvTotTribEst() {
            return vTotTribEst;
        }

        public void setvTotTribEst(String v) {
            this.vTotTribEst = v;
        }

        public String getvTotTribMun() {
            return vTotTribMun;
        }

        public void setvTotTribMun(String v) {
            this.vTotTribMun = v;
        }
    }
}
