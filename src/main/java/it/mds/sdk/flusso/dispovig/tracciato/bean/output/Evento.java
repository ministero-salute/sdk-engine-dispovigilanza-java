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
 *         &lt;element ref="{}offeso"/>
 *         &lt;element ref="{}etaPaziente"/>
 *         &lt;element ref="{}inizPaziente"/>
 *         &lt;element ref="{}dataImpianto"/>
 *         &lt;element ref="{}isDispUtilizzato"/>
 *         &lt;element ref="{}utilizzo"/>
 *         &lt;element ref="{}descUtilizzo"/>
 *         &lt;element ref="{}altroUtilizzoDispo"/>
 *         &lt;element ref="{}classeIncidente"/>
 *         &lt;element ref="{}descClasseIncidente"/>
 *         &lt;element ref="{}descrizioneIncidente"/>
 *         &lt;element ref="{}conseguenza"/>
 *         &lt;element ref="{}descConseguenza"/>
 *         &lt;element ref="{}altraConseguenza"/>
 *         &lt;element ref="{}numPezzi"/>
 *         &lt;element ref="{}disponibilitaDispositivo"/>
 *         &lt;element ref="{}luogoDisponibilitaDispositivo"/>
 *         &lt;element ref="{}azioniIntraprese"/>
 *         &lt;element ref="{}textAzioniIntraprese"/>
 *         &lt;element ref="{}altreAzioniIntraprese"/>
 *         &lt;element ref="{}altreInformazioni"/>
 *         &lt;element ref="{}dataRapporto"/>
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
    "offeso",
    "etaPaziente",
    "inizPaziente",
    "dataImpianto",
    "isDispUtilizzato",
    "utilizzo",
    "descUtilizzo",
    "altroUtilizzoDispo",
    "classeIncidente",
    "descClasseIncidente",
    "descrizioneIncidente",
    "conseguenza",
    "descConseguenza",
    "altraConseguenza",
    "numPezzi",
    "disponibilitaDispositivo",
    "luogoDisponibilitaDispositivo",
    "azioniIntraprese",
    "textAzioniIntraprese",
    "altreAzioniIntraprese",
    "altreInformazioni",
    "dataRapporto"
})
@XmlRootElement(name = "evento")
public class Evento {

    @XmlElement(required = true)
    @CsvBindByPosition(position = 58)
    protected String offeso;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 59)
    protected String etaPaziente;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 60)
    protected String inizPaziente;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 61)
    protected String dataImpianto;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 62)
    protected String isDispUtilizzato;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 63)
    protected String utilizzo;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 64)
    protected String descUtilizzo;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 65)
    protected String altroUtilizzoDispo;
    @CsvBindByPosition(position = 66)
    protected byte classeIncidente;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 67)
    protected String descClasseIncidente;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 68)
    protected String descrizioneIncidente;
    @CsvBindByPosition(position = 69)
    protected byte conseguenza;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 70)
    protected String descConseguenza;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 71)
    protected String altraConseguenza;
    @CsvBindByPosition(position = 72)
    protected byte numPezzi;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 73)
    protected String disponibilitaDispositivo;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 74)
    protected String luogoDisponibilitaDispositivo;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 75)
    protected String azioniIntraprese;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 76)
    protected String textAzioniIntraprese;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 77)
    protected String altreAzioniIntraprese;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 78)
    protected String altreInformazioni;
    @XmlElement(required = true)
    @CsvBindByPosition(position = 79)
    protected String dataRapporto;

    /**
     * Recupera il valore della propriet� offeso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOffeso() {
        return offeso;
    }

    /**
     * Imposta il valore della propriet� offeso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOffeso(String value) {
        this.offeso = value;
    }

   

   

    /**
     * Recupera il valore della propriet� isDispUtilizzato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsDispUtilizzato() {
        return isDispUtilizzato;
    }

    /**
     * Imposta il valore della propriet� isDispUtilizzato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsDispUtilizzato(String value) {
        this.isDispUtilizzato = value;
    }

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
     * Recupera il valore della propriet� descUtilizzo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescUtilizzo() {
        return descUtilizzo;
    }

    /**
     * Imposta il valore della propriet� descUtilizzo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescUtilizzo(String value) {
        this.descUtilizzo = value;
    }

   
    /**
     * Recupera il valore della propriet� classeIncidente.
     * 
     */
    public byte getClasseIncidente() {
        return classeIncidente;
    }

    /**
     * Imposta il valore della propriet� classeIncidente.
     * 
     */
    public void setClasseIncidente(byte value) {
        this.classeIncidente = value;
    }

    /**
     * Recupera il valore della propriet� descClasseIncidente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescClasseIncidente() {
        return descClasseIncidente;
    }

    /**
     * Imposta il valore della propriet� descClasseIncidente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescClasseIncidente(String value) {
        this.descClasseIncidente = value;
    }

    /**
     * Recupera il valore della propriet� descrizioneIncidente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneIncidente() {
        return descrizioneIncidente;
    }

    /**
     * Imposta il valore della propriet� descrizioneIncidente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneIncidente(String value) {
        this.descrizioneIncidente = value;
    }

    /**
     * Recupera il valore della propriet� conseguenza.
     * 
     */
    public byte getConseguenza() {
        return conseguenza;
    }

    /**
     * Imposta il valore della propriet� conseguenza.
     * 
     */
    public void setConseguenza(byte value) {
        this.conseguenza = value;
    }

    /**
     * Recupera il valore della propriet� descConseguenza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescConseguenza() {
        return descConseguenza;
    }

    /**
     * Imposta il valore della propriet� descConseguenza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescConseguenza(String value) {
        this.descConseguenza = value;
    }

   

    /**
     * Recupera il valore della propriet� numPezzi.
     * 
     */
    public byte getNumPezzi() {
        return numPezzi;
    }

    /**
     * Imposta il valore della propriet� numPezzi.
     * 
     */
    public void setNumPezzi(byte value) {
        this.numPezzi = value;
    }

    /**
     * Recupera il valore della propriet� disponibilitaDispositivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisponibilitaDispositivo() {
        return disponibilitaDispositivo;
    }

    /**
     * Imposta il valore della propriet� disponibilitaDispositivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisponibilitaDispositivo(String value) {
        this.disponibilitaDispositivo = value;
    }

    

   

    /**
     * Recupera il valore della propriet� azioniIntraprese.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAzioniIntraprese() {
        return azioniIntraprese;
    }

    /**
     * Imposta il valore della propriet� azioniIntraprese.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAzioniIntraprese(String value) {
        this.azioniIntraprese = value;
    }

    /**
     * Recupera il valore della propriet� textAzioniIntraprese.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextAzioniIntraprese() {
        return textAzioniIntraprese;
    }

    /**
     * Imposta il valore della propriet� textAzioniIntraprese.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextAzioniIntraprese(String value) {
        this.textAzioniIntraprese = value;
    }

  
    public String getEtaPaziente() {
		return etaPaziente;
	}

	public void setEtaPaziente(String etaPaziente) {
		this.etaPaziente = etaPaziente;
	}

	public String getInizPaziente() {
		return inizPaziente;
	}

	public void setInizPaziente(String inizPaziente) {
		this.inizPaziente = inizPaziente;
	}

	public String getDataImpianto() {
		return dataImpianto;
	}

	public void setDataImpianto(String dataImpianto) {
		this.dataImpianto = dataImpianto;
	}

	public String getAltroUtilizzoDispo() {
		return altroUtilizzoDispo;
	}

	public void setAltroUtilizzoDispo(String altroUtilizzoDispo) {
		this.altroUtilizzoDispo = altroUtilizzoDispo;
	}

	public String getAltraConseguenza() {
		return altraConseguenza;
	}

	public void setAltraConseguenza(String altraConseguenza) {
		this.altraConseguenza = altraConseguenza;
	}

	public String getLuogoDisponibilitaDispositivo() {
		return luogoDisponibilitaDispositivo;
	}

	public void setLuogoDisponibilitaDispositivo(String luogoDisponibilitaDispositivo) {
		this.luogoDisponibilitaDispositivo = luogoDisponibilitaDispositivo;
	}

	public String getAltreAzioniIntraprese() {
		return altreAzioniIntraprese;
	}

	public void setAltreAzioniIntraprese(String altreAzioniIntraprese) {
		this.altreAzioniIntraprese = altreAzioniIntraprese;
	}

	/**
     * Recupera il valore della propriet� altreInformazioni.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAltreInformazioni() {
        return altreInformazioni;
    }

    /**
     * Imposta il valore della propriet� altreInformazioni.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAltreInformazioni(String value) {
        this.altreInformazioni = value;
    }

    /**
     * Recupera il valore della propriet� dataRapporto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataRapporto() {
        return dataRapporto;
    }

    /**
     * Imposta il valore della propriet� dataRapporto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataRapporto(String value) {
        this.dataRapporto = value;
    }

}
