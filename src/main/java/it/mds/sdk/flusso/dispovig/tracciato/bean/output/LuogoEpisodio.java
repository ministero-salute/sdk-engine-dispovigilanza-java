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
 *         &lt;element ref="{}numRapporto"/>
 *         &lt;element ref="{}rapportoRelativoA"/>
 *         &lt;element ref="{}codiceStruttura"/>
 *         &lt;element ref="{}nomeStruttura"/>
 *         &lt;element ref="{}annoValiditaStruttTerrit"/>
 *         &lt;element ref="{}repartoStruttura"/>
 *         &lt;element ref="{}telefono"/>
 *         &lt;element ref="{}fax"/>
 *         &lt;element ref="{}email"/>
 *         &lt;element ref="{}dataEpisodio"/>
 *         &lt;element ref="{}codAziendaOspLocale"/>
 *         &lt;element ref="{}aziendaOspLocale"/>
 *         &lt;element ref="{}nomeOperatore"/>
 *         &lt;element ref="{}cognomeOperatore"/>
 *         &lt;element ref="{}qualificaOperatore"/>
 *         &lt;element ref="{}nomeResponsabile"/>
 *         &lt;element ref="{}cognomeResponsabile"/>
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
    "numRapporto",
    "rapportoRelativoA",
    "codiceStruttura",
    "nomeStruttura",
    "annoValiditaStruttTerrit",
    "repartoStruttura",
    "telefono",
    "fax",
    "email",
    "dataEpisodio",
    "codAziendaOspLocale",
    "aziendaOspLocale",
    "nomeOperatore",
    "cognomeOperatore",
    "qualificaOperatore",
    "nomeResponsabile",
    "cognomeResponsabile"
})
@XmlRootElement(name = "luogoEpisodio")
public class LuogoEpisodio {

    @XmlElement(required = true)
    @CsvBindByPosition(position = 1)
    public String numRapporto;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 2)
    protected String rapportoRelativoA;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 3)
    protected String codiceStruttura;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 4)
    protected String nomeStruttura;
    @CsvBindByPosition(position = 5)
    protected short annoValiditaStruttTerrit;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 6)
    protected String repartoStruttura;
    @CsvBindByPosition(position = 7)
    protected int telefono;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 8)
    protected String fax;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 9)
    protected String email;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 10)
    protected String dataEpisodio;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 11)
    protected String codAziendaOspLocale;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 12)
    protected String aziendaOspLocale;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 13)
    protected String nomeOperatore;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 14)
    protected String cognomeOperatore;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 15)
    protected String qualificaOperatore;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 16)
    protected String nomeResponsabile;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 17)
    protected String cognomeResponsabile;

    /**
     * Recupera il valore della propriet� numRapporto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumRapporto() {
        return numRapporto;
    }

    /**
     * Imposta il valore della propriet� numRapporto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumRapporto(String value) {
        this.numRapporto = value;
    }

    /**
     * Recupera il valore della propriet� rapportoRelativoA.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRapportoRelativoA() {
        return rapportoRelativoA;
    }

    /**
     * Imposta il valore della propriet� rapportoRelativoA.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRapportoRelativoA(String value) {
        this.rapportoRelativoA = value;
    }

    /**
     * Recupera il valore della propriet� codiceStruttura.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceStruttura() {
        return codiceStruttura;
    }

    /**
     * Imposta il valore della propriet� codiceStruttura.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceStruttura(String value) {
        this.codiceStruttura = value;
    }

    /**
     * Recupera il valore della propriet� nomeStruttura.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeStruttura() {
        return nomeStruttura;
    }

    /**
     * Imposta il valore della propriet� nomeStruttura.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeStruttura(String value) {
        this.nomeStruttura = value;
    }

    /**
     * Recupera il valore della propriet� annoValiditaStruttTerrit.
     * 
     */
    public short getAnnoValiditaStruttTerrit() {
        return annoValiditaStruttTerrit;
    }

    /**
     * Imposta il valore della propriet� annoValiditaStruttTerrit.
     * 
     */
    public void setAnnoValiditaStruttTerrit(short value) {
        this.annoValiditaStruttTerrit = value;
    }

    /**
     * Recupera il valore della propriet� repartoStruttura.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepartoStruttura() {
        return repartoStruttura;
    }

    /**
     * Imposta il valore della propriet� repartoStruttura.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepartoStruttura(String value) {
        this.repartoStruttura = value;
    }

    /**
     * Recupera il valore della propriet� telefono.
     * 
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * Imposta il valore della propriet� telefono.
     * 
     */
    public void setTelefono(int value) {
        this.telefono = value;
    }

    /**
     * Recupera il valore della propriet� fax.
     * 
     * @return
     *     possible object is
     *     {@link Fax }
     *     
     */
   

    /**
     * Recupera il valore della propriet� email.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
     * Imposta il valore della propriet� email.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Recupera il valore della propriet� dataEpisodio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataEpisodio() {
        return dataEpisodio;
    }

    /**
     * Imposta il valore della propriet� dataEpisodio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataEpisodio(String value) {
        this.dataEpisodio = value;
    }

    /**
     * Recupera il valore della propriet� codAziendaOspLocale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodAziendaOspLocale() {
        return codAziendaOspLocale;
    }

    /**
     * Imposta il valore della propriet� codAziendaOspLocale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodAziendaOspLocale(String value) {
        this.codAziendaOspLocale = value;
    }

    /**
     * Recupera il valore della propriet� aziendaOspLocale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAziendaOspLocale() {
        return aziendaOspLocale;
    }

    /**
     * Imposta il valore della propriet� aziendaOspLocale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAziendaOspLocale(String value) {
        this.aziendaOspLocale = value;
    }

    /**
     * Recupera il valore della propriet� nomeOperatore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeOperatore() {
        return nomeOperatore;
    }

    /**
     * Imposta il valore della propriet� nomeOperatore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeOperatore(String value) {
        this.nomeOperatore = value;
    }

    /**
     * Recupera il valore della propriet� cognomeOperatore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCognomeOperatore() {
        return cognomeOperatore;
    }

    /**
     * Imposta il valore della propriet� cognomeOperatore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCognomeOperatore(String value) {
        this.cognomeOperatore = value;
    }

    /**
     * Recupera il valore della propriet� qualificaOperatore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQualificaOperatore() {
        return qualificaOperatore;
    }

    /**
     * Imposta il valore della propriet� qualificaOperatore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQualificaOperatore(String value) {
        this.qualificaOperatore = value;
    }

    /**
     * Recupera il valore della propriet� nomeResponsabile.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeResponsabile() {
        return nomeResponsabile;
    }

    /**
     * Imposta il valore della propriet� nomeResponsabile.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeResponsabile(String value) {
        this.nomeResponsabile = value;
    }

    /**
     * Recupera il valore della propriet� cognomeResponsabile.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCognomeResponsabile() {
        return cognomeResponsabile;
    }

    /**
     * Imposta il valore della propriet� cognomeResponsabile.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCognomeResponsabile(String value) {
        this.cognomeResponsabile = value;
    }

}
