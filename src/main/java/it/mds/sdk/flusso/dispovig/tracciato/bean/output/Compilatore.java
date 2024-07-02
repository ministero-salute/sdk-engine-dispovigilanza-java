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
 *         &lt;element ref="{}ruolo"/>
 *         &lt;element ref="{}descRuolo"/>
 *         &lt;element ref="{}nome"/>
 *         &lt;element ref="{}cognome"/>
 *         &lt;element ref="{}qualificaCompilatore"/>
 *         &lt;element ref="{}struttSanitaria"/>
 *         &lt;element ref="{}codStruttSanitaria"/>
 *         &lt;element ref="{}telefonoCompilatore"/>
 *         &lt;element ref="{}faxCompilatore"/>
 *         &lt;element ref="{}emailCompilatore"/>
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
    "ruolo",
    "descRuolo",
    "nome",
    "cognome",
    "qualificaCompilatore",
    "struttSanitaria",
    "codStruttSanitaria",
    "telefonoCompilatore",
    "faxCompilatore",
    "emailCompilatore"
})
@XmlRootElement(name = "compilatore")
public class Compilatore {

	@CsvBindByPosition(position = 80)
    protected byte ruolo;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 81)
    protected String descRuolo;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 82)
    protected String nome;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 83)
    protected String cognome;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 84)
    protected String qualificaCompilatore;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 85)
    protected String struttSanitaria;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 86)
    protected String codStruttSanitaria;
    @CsvBindByPosition(position = 87)
    protected int telefonoCompilatore;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 88)
    protected String faxCompilatore;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 89)
    protected String emailCompilatore;

    /**
     * Recupera il valore della propriet� ruolo.
     * 
     */
    public byte getRuolo() {
        return ruolo;
    }

    /**
     * Imposta il valore della propriet� ruolo.
     * 
     */
    public void setRuolo(byte value) {
        this.ruolo = value;
    }

    /**
     * Recupera il valore della propriet� descRuolo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescRuolo() {
        return descRuolo;
    }

    /**
     * Imposta il valore della propriet� descRuolo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescRuolo(String value) {
        this.descRuolo = value;
    }

    /**
     * Recupera il valore della propriet� nome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il valore della propriet� nome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Recupera il valore della propriet� cognome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Imposta il valore della propriet� cognome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCognome(String value) {
        this.cognome = value;
    }

    /**
     * Recupera il valore della propriet� qualificaCompilatore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQualificaCompilatore() {
        return qualificaCompilatore;
    }

    /**
     * Imposta il valore della propriet� qualificaCompilatore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQualificaCompilatore(String value) {
        this.qualificaCompilatore = value;
    }

    /**
     * Recupera il valore della propriet� struttSanitaria.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStruttSanitaria() {
        return struttSanitaria;
    }

    /**
     * Imposta il valore della propriet� struttSanitaria.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStruttSanitaria(String value) {
        this.struttSanitaria = value;
    }

    /**
     * Recupera il valore della propriet� codStruttSanitaria.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodStruttSanitaria() {
        return codStruttSanitaria;
    }

    /**
     * Imposta il valore della propriet� codStruttSanitaria.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodStruttSanitaria(String value) {
        this.codStruttSanitaria = value;
    }

    /**
     * Recupera il valore della propriet� telefonoCompilatore.
     * 
     */
    public int getTelefonoCompilatore() {
        return telefonoCompilatore;
    }

    /**
     * Imposta il valore della propriet� telefonoCompilatore.
     * 
     */
    public void setTelefonoCompilatore(int value) {
        this.telefonoCompilatore = value;
    }

  

    /**
     * Recupera il valore della propriet� emailCompilatore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailCompilatore() {
        return emailCompilatore;
    }

    /**
     * Imposta il valore della propriet� emailCompilatore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailCompilatore(String value) {
        this.emailCompilatore = value;
    }

}
