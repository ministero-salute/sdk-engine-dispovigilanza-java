//
// Questo file � stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andr� persa durante la ricompilazione dello schema di origine. 
// Generato il: 2022.05.05 alle 12:24:26 PM CEST 
//


/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.dispovig.tracciato.bean.output;

import com.opencsv.bean.CsvBindByPosition;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}tipoDispositivo"/>
 *         &lt;element ref="{}codiceDispositivo"/>
 *         &lt;element ref="{}nomeDispositivo"/>
 *         &lt;element ref="{}modelloDispositivo"/>
 *         &lt;element ref="{}codiceFabbrDispositivo"/>
 *         &lt;element ref="{}numeroLotto"/>
 *         &lt;element ref="{}dataScadenza"/>
 *         &lt;element ref="{}CND"/>
 *         &lt;element ref="{}desCND"/>
 *         &lt;element ref="{}GMDN"/>
 *         &lt;element ref="{}desGMDN"/>
 *         &lt;element ref="{}EDMA"/>
 *         &lt;element ref="{}desEDMA"/>
 *         &lt;element ref="{}tipoIVD"/>
 *         &lt;element ref="{}descTipoIVD"/>
 *         &lt;element ref="{}valutazionePrestazioni"/>
 *         &lt;element ref="{}utilizzo"/>
 *         &lt;element ref="{}versioneSoftware"/>
 *         &lt;element ref="{}denominazioneFabbricante"/>
 *         &lt;element ref="{}nazioneFabbricante"/>
 *         &lt;element ref="{}descNazioneFabbricante"/>
 *         &lt;element ref="{}regioneFabbricante"/>
 *         &lt;element ref="{}descRegioneFabbricante"/>
 *         &lt;element ref="{}provinciaFabbricante"/>
 *         &lt;element ref="{}descProvinciaFabbricante"/>
 *         &lt;element ref="{}comuneFabbricante"/>
 *         &lt;element ref="{}descComuneFabbricante"/>
 *         &lt;element ref="{}localitaFabbricante"/>
 *         &lt;element ref="{}indirizzoFabbricante"/>
 *         &lt;element ref="{}denominazioneMandatario"/>
 *         &lt;element ref="{}nazioneMandatario"/>
 *         &lt;element ref="{}descNazioneMandatario"/>
 *         &lt;element ref="{}regioneMandatario"/>
 *         &lt;element ref="{}descRegioneMandatario"/>
 *         &lt;element ref="{}provinciaMandatario"/>
 *         &lt;element ref="{}descProvinciaMandatario"/>
 *         &lt;element ref="{}comuneMandatario"/>
 *         &lt;element ref="{}descComuneMandatario"/>
 *         &lt;element ref="{}localitaMandatario"/>
 *         &lt;element ref="{}indirizzoMandatario"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "tipoDispositivo",
    "codiceDispositivo",
    "nomeDispositivo",
    "modelloDispositivo",
    "codiceFabbrDispositivo",
    "numeroLotto",
    "dataScadenza",
    "cnd",
    "desCND",
    "gmdn",
    "desGMDN",
    "edma",
    "desEDMA",
    "tipoIVD",
    "descTipoIVD",
    "valutazionePrestazioni",
    "utilizzo",
    "versioneSoftware",
    "denominazioneFabbricante",
    "nazioneFabbricante",
    "descNazioneFabbricante",
    "regioneFabbricante",
    "descRegioneFabbricante",
    "provinciaFabbricante",
    "descProvinciaFabbricante",
    "comuneFabbricante",
    "descComuneFabbricante",
    "localitaFabbricante",
    "indirizzoFabbricante",
    "denominazioneMandatario",
    "nazioneMandatario",
    "descNazioneMandatario",
    "regioneMandatario",
    "descRegioneMandatario",
    "provinciaMandatario",
    "descProvinciaMandatario",
    "comuneMandatario",
    "descComuneMandatario",
    "localitaMandatario",
    "indirizzoMandatario"
})
@XmlRootElement(name = "dispositivo")
public class Dispositivo {

    @XmlElement(required = true)
    @CsvBindByPosition(position = 18)
    protected String tipoDispositivo;
    @CsvBindByPosition(position = 19)
    protected int codiceDispositivo;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 20)
    protected String nomeDispositivo;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 21)
    protected String modelloDispositivo;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 22)
    protected String codiceFabbrDispositivo;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 23)
    protected String numeroLotto;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 24)
    protected String dataScadenza;
    @XmlElement(name = "CND", required = true)
    @CsvBindByPosition(position = 25)
    protected String cnd;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 26)
    protected String desCND;
    @XmlElement(name = "GMDN", required = true)
    @CsvBindByPosition(position = 27)
    protected String gmdn;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 28)
    protected String desGMDN;
    @XmlElement(name = "EDMA", required = true)
    @CsvBindByPosition(position = 29)
    protected String edma;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 30)
    protected String desEDMA;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 31)
    protected String tipoIVD;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 32)
    protected String descTipoIVD;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 33)
    protected String valutazionePrestazioni;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 34)
    protected String utilizzo;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 35)
    protected String versioneSoftware;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 36)
    protected String denominazioneFabbricante;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 37)
    protected String nazioneFabbricante;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 38)
    protected String descNazioneFabbricante;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 39)
    protected String regioneFabbricante;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 40)
    protected String descRegioneFabbricante;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 41)
    protected String provinciaFabbricante;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 42)
    protected String descProvinciaFabbricante;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 43)
    protected String comuneFabbricante;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 44)
    protected String descComuneFabbricante;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 45)
    protected String localitaFabbricante;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 46)
    protected String indirizzoFabbricante;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 47)
    protected String denominazioneMandatario;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 48)
    protected String nazioneMandatario;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 49)
    protected String descNazioneMandatario;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 50)
    protected String regioneMandatario;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 51)
    protected String descRegioneMandatario;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 52)
    protected String provinciaMandatario;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 53)
    protected String descProvinciaMandatario;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 54)
    protected String comuneMandatario;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 55)
    protected String descComuneMandatario;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 56)
    protected String localitaMandatario;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 57)
    protected String indirizzoMandatario;

    /**
     * Recupera il valore della propriet� tipoDispositivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDispositivo() {
        return tipoDispositivo;
    }

    /**
     * Imposta il valore della propriet� tipoDispositivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDispositivo(String value) {
        this.tipoDispositivo = value;
    }

    /**
     * Recupera il valore della propriet� codiceDispositivo.
     * 
     */
    public int getCodiceDispositivo() {
        return codiceDispositivo;
    }

    /**
     * Imposta il valore della propriet� codiceDispositivo.
     * 
     */
    public void setCodiceDispositivo(int value) {
        this.codiceDispositivo = value;
    }

    /**
     * Recupera il valore della propriet� nomeDispositivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeDispositivo() {
        return nomeDispositivo;
    }

    /**
     * Imposta il valore della propriet� nomeDispositivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeDispositivo(String value) {
        this.nomeDispositivo = value;
    }

    /**
     * Recupera il valore della propriet� modelloDispositivo.
     * 
     * @return
     *     possible object is
     *     {@link ModelloDispositivo }
     *     
     */
   

    /**
     * Recupera il valore della propriet� codiceFabbrDispositivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFabbrDispositivo() {
        return codiceFabbrDispositivo;
    }

    public String getModelloDispositivo() {
		return modelloDispositivo;
	}

	public void setModelloDispositivo(String modelloDispositivo) {
		this.modelloDispositivo = modelloDispositivo;
	}

	/**
     * Imposta il valore della propriet� codiceFabbrDispositivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFabbrDispositivo(String value) {
        this.codiceFabbrDispositivo = value;
    }

    /**
     * Recupera il valore della propriet� numeroLotto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroLotto() {
        return numeroLotto;
    }

    /**
     * Imposta il valore della propriet� numeroLotto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroLotto(String value) {
        this.numeroLotto = value;
    }

    /**
     * Recupera il valore della propriet� dataScadenza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataScadenza() {
        return dataScadenza;
    }

    /**
     * Imposta il valore della propriet� dataScadenza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataScadenza(String value) {
        this.dataScadenza = value;
    }

    /**
     * Recupera il valore della propriet� cnd.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCND() {
        return cnd;
    }

    /**
     * Imposta il valore della propriet� cnd.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCND(String value) {
        this.cnd = value;
    }

    /**
     * Recupera il valore della propriet� desCND.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesCND() {
        return desCND;
    }

    /**
     * Imposta il valore della propriet� desCND.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesCND(String value) {
        this.desCND = value;
    }

    /**
     * Recupera il valore della propriet� gmdn.
     * 
     * @return
     *     possible object is
     *     {@link GMDN }
     *     
     */
   

    /**
     * Recupera il valore della propriet� desGMDN.
     * 
     * @return
     *     possible object is
     *     {@link DesGMDN }
     *     
     */
   

   

   

    /**
     * Recupera il valore della propriet� tipoIVD.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoIVD() {
        return tipoIVD;
    }

    /**
     * Imposta il valore della propriet� tipoIVD.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoIVD(String value) {
        this.tipoIVD = value;
    }

    /**
     * Recupera il valore della propriet� descTipoIVD.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescTipoIVD() {
        return descTipoIVD;
    }

    /**
     * Imposta il valore della propriet� descTipoIVD.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescTipoIVD(String value) {
        this.descTipoIVD = value;
    }

    /**
     * Recupera il valore della propriet� valutazionePrestazioni.
     * 
     * @return
     *     possible object is
     *     {@link ValutazionePrestazioni }
     *     
     */
   

    /**
     * Recupera il valore della propriet� utilizzo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUtilizzo() {
        return utilizzo;
    }

    /**
     * Imposta il valore della propriet� utilizzo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUtilizzo(String value) {
        this.utilizzo = value;
    }

    /**
     * Recupera il valore della propriet� versioneSoftware.
     * 
     * @return
     *     possible object is
     *     {@link VersioneSoftware }
     *     
     */
    

    /**
     * Recupera il valore della propriet� denominazioneFabbricante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDenominazioneFabbricante() {
        return denominazioneFabbricante;
    }

    /**
     * Imposta il valore della propriet� denominazioneFabbricante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDenominazioneFabbricante(String value) {
        this.denominazioneFabbricante = value;
    }

    /**
     * Recupera il valore della propriet� nazioneFabbricante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNazioneFabbricante() {
        return nazioneFabbricante;
    }

    /**
     * Imposta il valore della propriet� nazioneFabbricante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNazioneFabbricante(String value) {
        this.nazioneFabbricante = value;
    }

    /**
     * Recupera il valore della propriet� descNazioneFabbricante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescNazioneFabbricante() {
        return descNazioneFabbricante;
    }

    /**
     * Imposta il valore della propriet� descNazioneFabbricante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescNazioneFabbricante(String value) {
        this.descNazioneFabbricante = value;
    }

    /**
     * Recupera il valore della propriet� regioneFabbricante.
     * 
     * @return
     *     possible object is
     *     {@link RegioneFabbricante }
     *     
     */
    

    /**
     * Recupera il valore della propriet� descRegioneFabbricante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescRegioneFabbricante() {
        return descRegioneFabbricante;
    }

    /**
     * Imposta il valore della propriet� descRegioneFabbricante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescRegioneFabbricante(String value) {
        this.descRegioneFabbricante = value;
    }

    /**
     * Recupera il valore della propriet� provinciaFabbricante.
     * 
     * @return
     *     possible object is
     *     {@link ProvinciaFabbricante }
     *     
     */
   

    /**
     * Recupera il valore della propriet� descProvinciaFabbricante.
     * 
     * @return
     *     possible object is
     *     {@link DescProvinciaFabbricante }
     *     
     */
    

    /**
     * Recupera il valore della propriet� comuneFabbricante.
     * 
     * @return
     *     possible object is
     *     {@link ComuneFabbricante }
     *     
     */
   

    /**
     * Recupera il valore della propriet� descComuneFabbricante.
     * 
     * @return
     *     possible object is
     *     {@link DescComuneFabbricante }
     *     
     */
   
    /**
     * Recupera il valore della propriet� localitaFabbricante.
     * 
     * @return
     *     possible object is
     *     {@link LocalitaFabbricante }
     *     
     */
    

    /**
     * Recupera il valore della propriet� indirizzoFabbricante.
     * 
     * @return
     *     possible object is
     *     {@link IndirizzoFabbricante }
     *     
     */
    

    /**
     * Recupera il valore della propriet� denominazioneMandatario.
     * 
     * @return
     *     possible object is
     *     {@link DenominazioneMandatario }
     *     
     */
   

    /**
     * Recupera il valore della propriet� nazioneMandatario.
     * 
     * @return
     *     possible object is
     *     {@link NazioneMandatario }
     *     
     */
   

    /**
     * Recupera il valore della propriet� descNazioneMandatario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescNazioneMandatario() {
        return descNazioneMandatario;
    }

    /**
     * Imposta il valore della propriet� descNazioneMandatario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescNazioneMandatario(String value) {
        this.descNazioneMandatario = value;
    }

    /**
     * Recupera il valore della propriet� regioneMandatario.
     * 
     * @return
     *     possible object is
     *     {@link RegioneMandatario }
     *     
     */
   

    /**
     * Recupera il valore della propriet� descRegioneMandatario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescRegioneMandatario() {
        return descRegioneMandatario;
    }

    /**
     * Imposta il valore della propriet� descRegioneMandatario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescRegioneMandatario(String value) {
        this.descRegioneMandatario = value;
    }

	public String getCnd() {
		return cnd;
	}

	public void setCnd(String cnd) {
		this.cnd = cnd;
	}

	

	public String getGmdn() {
		return gmdn;
	}

	public void setGmdn(String gmdn) {
		this.gmdn = gmdn;
	}

	public String getDesGMDN() {
		return desGMDN;
	}

	public void setDesGMDN(String desGMDN) {
		this.desGMDN = desGMDN;
	}

	public String getEdma() {
		return edma;
	}

	public void setEdma(String edma) {
		this.edma = edma;
	}

	public String getDesEDMA() {
		return desEDMA;
	}

	public void setDesEDMA(String desEDMA) {
		this.desEDMA = desEDMA;
	}

	public String getValutazionePrestazioni() {
		return valutazionePrestazioni;
	}

	public void setValutazionePrestazioni(String valutazionePrestazioni) {
		this.valutazionePrestazioni = valutazionePrestazioni;
	}

	public String getVersioneSoftware() {
		return versioneSoftware;
	}

	public void setVersioneSoftware(String versioneSoftware) {
		this.versioneSoftware = versioneSoftware;
	}

	public String getRegioneFabbricante() {
		return regioneFabbricante;
	}

	public void setRegioneFabbricante(String regioneFabbricante) {
		this.regioneFabbricante = regioneFabbricante;
	}

	public String getProvinciaFabbricante() {
		return provinciaFabbricante;
	}

	public void setProvinciaFabbricante(String provinciaFabbricante) {
		this.provinciaFabbricante = provinciaFabbricante;
	}

	public String getDescProvinciaFabbricante() {
		return descProvinciaFabbricante;
	}

	public void setDescProvinciaFabbricante(String descProvinciaFabbricante) {
		this.descProvinciaFabbricante = descProvinciaFabbricante;
	}

	public String getComuneFabbricante() {
		return comuneFabbricante;
	}

	public void setComuneFabbricante(String comuneFabbricante) {
		this.comuneFabbricante = comuneFabbricante;
	}

	public String getDescComuneFabbricante() {
		return descComuneFabbricante;
	}

	public void setDescComuneFabbricante(String descComuneFabbricante) {
		this.descComuneFabbricante = descComuneFabbricante;
	}

	public String getLocalitaFabbricante() {
		return localitaFabbricante;
	}

	public void setLocalitaFabbricante(String localitaFabbricante) {
		this.localitaFabbricante = localitaFabbricante;
	}

	public String getIndirizzoFabbricante() {
		return indirizzoFabbricante;
	}

	public void setIndirizzoFabbricante(String indirizzoFabbricante) {
		this.indirizzoFabbricante = indirizzoFabbricante;
	}

	public String getDenominazioneMandatario() {
		return denominazioneMandatario;
	}

	public void setDenominazioneMandatario(String denominazioneMandatario) {
		this.denominazioneMandatario = denominazioneMandatario;
	}

	public String getNazioneMandatario() {
		return nazioneMandatario;
	}

	public void setNazioneMandatario(String nazioneMandatario) {
		this.nazioneMandatario = nazioneMandatario;
	}

	public String getRegioneMandatario() {
		return regioneMandatario;
	}

	public void setRegioneMandatario(String regioneMandatario) {
		this.regioneMandatario = regioneMandatario;
	}

	public String getProvinciaMandatario() {
		return provinciaMandatario;
	}

	public void setProvinciaMandatario(String provinciaMandatario) {
		this.provinciaMandatario = provinciaMandatario;
	}

	public String getDescProvinciaMandatario() {
		return descProvinciaMandatario;
	}

	public void setDescProvinciaMandatario(String descProvinciaMandatario) {
		this.descProvinciaMandatario = descProvinciaMandatario;
	}

	public String getComuneMandatario() {
		return comuneMandatario;
	}

	public void setComuneMandatario(String comuneMandatario) {
		this.comuneMandatario = comuneMandatario;
	}

	public String getDescComuneMandatario() {
		return descComuneMandatario;
	}

	public void setDescComuneMandatario(String descComuneMandatario) {
		this.descComuneMandatario = descComuneMandatario;
	}

	public String getLocalitaMandatario() {
		return localitaMandatario;
	}

	public void setLocalitaMandatario(String localitaMandatario) {
		this.localitaMandatario = localitaMandatario;
	}

	public String getIndirizzoMandatario() {
		return indirizzoMandatario;
	}

	public void setIndirizzoMandatario(String indirizzoMandatario) {
		this.indirizzoMandatario = indirizzoMandatario;
	}

    /**
     * Recupera il valore della propriet� provinciaMandatario.
     * 
     * @return
     *     possible object is
     *     {@link ProvinciaMandatario }
     *     
     */
   

    /**
     * Recupera il valore della propriet� descProvinciaMandatario.
     * 
     * @return
     *     possible object is
     *     {@link DescProvinciaMandatario }
     *     
     */
   

    /**
     * Recupera il valore della propriet� comuneMandatario.
     * 
     * @return
     *     possible object is
     *     {@link ComuneMandatario }
     *     
     */
   

    /**
     * Recupera il valore della propriet� descComuneMandatario.
     * 
     * @return
     *     possible object is
     *     {@link DescComuneMandatario }
     *     
     */
   

    /**
     * Recupera il valore della propriet� localitaMandatario.
     * 
     * @return
     *     possible object is
     *     {@link LocalitaMandatario }
     *     
     */
   

    /**
     * Recupera il valore della propriet� indirizzoMandatario.
     * 
     * @return
     *     possible object is
     *     {@link IndirizzoMandatario }
     *     
     */
   

}
