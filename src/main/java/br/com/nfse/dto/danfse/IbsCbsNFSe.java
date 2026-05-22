package br.com.nfse.dto.danfse;

import javax.xml.bind.annotation.*;

/**
 * Bloco {@code <IBSCBS>} no nível da {@code <NFSe>}. Contém alíquotas, base de
 * cálculo e totais de IBS e CBS.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class IbsCbsNFSe {

    @XmlElement(name = "cLocalidadeIncid", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String cLocalidadeIncid;

    @XmlElement(name = "xLocalidadeIncid", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private String xLocalidadeIncid;

    @XmlElement(name = "valores", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private ValoresIbsCbs valores;

    @XmlElement(name = "totCIBS", namespace = "http://www.sped.fazenda.gov.br/nfse")
    private TotCIBS totCIBS;

    public String getcLocalidadeIncid() {
        return cLocalidadeIncid;
    }

    public void setcLocalidadeIncid(String cLocalidadeIncid) {
        this.cLocalidadeIncid = cLocalidadeIncid;
    }

    public String getxLocalidadeIncid() {
        return xLocalidadeIncid;
    }

    public void setxLocalidadeIncid(String xLocalidadeIncid) {
        this.xLocalidadeIncid = xLocalidadeIncid;
    }

    public ValoresIbsCbs getValores() {
        return valores;
    }

    public void setValores(ValoresIbsCbs valores) {
        this.valores = valores;
    }

    public TotCIBS getTotCIBS() {
        return totCIBS;
    }

    public void setTotCIBS(TotCIBS totCIBS) {
        this.totCIBS = totCIBS;
    }

    // -- sub-classes ----------------------------------------------------------
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ValoresIbsCbs {

        /**
         * Base de cálculo IBS/CBS.
         */
        @XmlElement(name = "vBC", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String vBC;

        @XmlElement(name = "uf", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private AliqUF uf;

        @XmlElement(name = "mun", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private AliqMun mun;

        @XmlElement(name = "fed", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private AliqFed fed;

        public String getvBC() {
            return vBC;
        }

        public void setvBC(String vBC) {
            this.vBC = vBC;
        }

        public AliqUF getUf() {
            return uf;
        }

        public void setUf(AliqUF uf) {
            this.uf = uf;
        }

        public AliqMun getMun() {
            return mun;
        }

        public void setMun(AliqMun mun) {
            this.mun = mun;
        }

        public AliqFed getFed() {
            return fed;
        }

        public void setFed(AliqFed fed) {
            this.fed = fed;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AliqUF {

        /**
         * Alíquota IBS Estadual (%).
         */
        @XmlElement(name = "pIBSUF", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String pIBSUF;
        /**
         * Percentual de redução da alíquota estadual (%).
         */
        @XmlElement(name = "pRedAliqUF", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String pRedAliqUF;
        /**
         * Alíquota efetiva IBS Estadual (%).
         */
        @XmlElement(name = "pAliqEfetUF", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String pAliqEfetUF;

        public String getpIBSUF() {
            return pIBSUF;
        }

        public void setpIBSUF(String pIBSUF) {
            this.pIBSUF = pIBSUF;
        }

        public String getpRedAliqUF() {
            return pRedAliqUF;
        }

        public void setpRedAliqUF(String pRedAliqUF) {
            this.pRedAliqUF = pRedAliqUF;
        }

        public String getpAliqEfetUF() {
            return pAliqEfetUF;
        }

        public void setpAliqEfetUF(String pAliqEfetUF) {
            this.pAliqEfetUF = pAliqEfetUF;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AliqMun {

        /**
         * Alíquota IBS Municipal (%).
         */
        @XmlElement(name = "pIBSMun", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String pIBSMun;
        /**
         * Percentual de redução da alíquota municipal (%).
         */
        @XmlElement(name = "pRedAliqMun", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String pRedAliqMun;
        /**
         * Alíquota efetiva IBS Municipal (%).
         */
        @XmlElement(name = "pAliqEfetMun", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String pAliqEfetMun;

        public String getpIBSMun() {
            return pIBSMun;
        }

        public void setpIBSMun(String pIBSMun) {
            this.pIBSMun = pIBSMun;
        }

        public String getpRedAliqMun() {
            return pRedAliqMun;
        }

        public void setpRedAliqMun(String pRedAliqMun) {
            this.pRedAliqMun = pRedAliqMun;
        }

        public String getpAliqEfetMun() {
            return pAliqEfetMun;
        }

        public void setpAliqEfetMun(String pAliqEfetMun) {
            this.pAliqEfetMun = pAliqEfetMun;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AliqFed {

        /**
         * Alíquota CBS (%).
         */
        @XmlElement(name = "pCBS", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String pCBS;
        /**
         * Percentual de redução da alíquota CBS (%).
         */
        @XmlElement(name = "pRedAliqCBS", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String pRedAliqCBS;
        /**
         * Alíquota efetiva CBS (%).
         */
        @XmlElement(name = "pAliqEfetCBS", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String pAliqEfetCBS;

        public String getpCBS() {
            return pCBS;
        }

        public void setpCBS(String pCBS) {
            this.pCBS = pCBS;
        }

        public String getpRedAliqCBS() {
            return pRedAliqCBS;
        }

        public void setpRedAliqCBS(String pRedAliqCBS) {
            this.pRedAliqCBS = pRedAliqCBS;
        }

        public String getpAliqEfetCBS() {
            return pAliqEfetCBS;
        }

        public void setpAliqEfetCBS(String pAliqEfetCBS) {
            this.pAliqEfetCBS = pAliqEfetCBS;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class TotCIBS {

        /**
         * Valor total da NF (IBS+CBS inclusos).
         */
        @XmlElement(name = "vTotNF", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String vTotNF;

        @XmlElement(name = "gIBS", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private GIBS gIBS;

        @XmlElement(name = "gCBS", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private GCBS gCBS;

        public String getvTotNF() {
            return vTotNF;
        }

        public void setvTotNF(String vTotNF) {
            this.vTotNF = vTotNF;
        }

        public GIBS getgIBS() {
            return gIBS;
        }

        public void setgIBS(GIBS gIBS) {
            this.gIBS = gIBS;
        }

        public GCBS getgCBS() {
            return gCBS;
        }

        public void setgCBS(GCBS gCBS) {
            this.gCBS = gCBS;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class GIBS {

        /**
         * Valor total do IBS.
         */
        @XmlElement(name = "vIBSTot", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String vIBSTot;

        @XmlElement(name = "gIBSUFTot", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private GIBSUFTot gIBSUFTot;

        @XmlElement(name = "gIBSMunTot", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private GIBSMunTot gIBSMunTot;

        public String getvIBSTot() {
            return vIBSTot;
        }

        public void setvIBSTot(String vIBSTot) {
            this.vIBSTot = vIBSTot;
        }

        public GIBSUFTot getgIBSUFTot() {
            return gIBSUFTot;
        }

        public void setgIBSUFTot(GIBSUFTot gIBSUFTot) {
            this.gIBSUFTot = gIBSUFTot;
        }

        public GIBSMunTot getgIBSMunTot() {
            return gIBSMunTot;
        }

        public void setgIBSMunTot(GIBSMunTot gIBSMunTot) {
            this.gIBSMunTot = gIBSMunTot;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class GIBSUFTot {

        /**
         * Valor IBS Estadual.
         */
        @XmlElement(name = "vIBSUF", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String vIBSUF;

        public String getvIBSUF() {
            return vIBSUF;
        }

        public void setvIBSUF(String vIBSUF) {
            this.vIBSUF = vIBSUF;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class GIBSMunTot {

        /**
         * Valor IBS Municipal.
         */
        @XmlElement(name = "vIBSMun", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String vIBSMun;

        public String getvIBSMun() {
            return vIBSMun;
        }

        public void setvIBSMun(String vIBSMun) {
            this.vIBSMun = vIBSMun;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class GCBS {

        /**
         * Valor CBS.
         */
        @XmlElement(name = "vCBS", namespace = "http://www.sped.fazenda.gov.br/nfse")
        private String vCBS;

        public String getvCBS() {
            return vCBS;
        }

        public void setvCBS(String vCBS) {
            this.vCBS = vCBS;
        }
    }
}
