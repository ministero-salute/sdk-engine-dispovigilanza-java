//
// Questo file � stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andr� persa durante la ricompilazione dello schema di origine. 
// Generato il: 2022.05.05 alle 12:24:26 PM CEST 
//


/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.dispovig.tracciato.bean.output;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvRecurse;

import it.mds.sdk.libreriaregole.dtos.RecordDtoGenerico;
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
 *         &lt;element ref="{}codRapportoWeb"/>
 *         &lt;element ref="{}luogoEpisodio"/>
 *         &lt;element ref="{}dispositivo"/>
 *         &lt;element ref="{}evento"/>
 *         &lt;element ref="{}compilatore"/>
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
    "codRapportoWeb",
    "luogoEpisodio",
    "dispositivo",
    "evento",
    "compilatore"
})
@XmlRootElement(name = "rapporto")
public class Rapporto extends RecordDtoGenerico {

    @XmlElement(required = true)
    @CsvBindByPosition(position = 0)
    protected String codRapportoWeb;
    @XmlElement(required = true)    
    @CsvRecurse
    public LuogoEpisodio luogoEpisodio = new LuogoEpisodio();
    @XmlElement(required = true)
    @CsvRecurse
    protected Dispositivo dispositivo;
    @XmlElement(required = true)
    @CsvRecurse
    protected Evento evento;
    @XmlElement(required = true)
    @CsvRecurse
    protected Compilatore compilatore;

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("Rapporto{id=").append(codRapportoWeb).append("} - ").append(this.luogoEpisodio.numRapporto);
//        builder.append("Rapporto{id=").append(codRapportoWeb).append(", numRapporto=")
//                .append(luogoEpisodio.numRapporto).append(", price=").append(codRapportoWeb).append("}");

        return builder.toString();
    }
    
    /**
     * Recupera il valore della propriet� codRapportoWeb.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodRapportoWeb() {
        return codRapportoWeb;
    }

    /**
     * Imposta il valore della propriet� codRapportoWeb.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodRapportoWeb(String value) {
        this.codRapportoWeb = value;
    }

    /**
     * Recupera il valore della propriet� luogoEpisodio.
     * 
     * @return
     *     possible object is
     *     {@link LuogoEpisodio }
     *     
     */
    public LuogoEpisodio getLuogoEpisodio() {
        return luogoEpisodio;
    }

    /**
     * Imposta il valore della propriet� luogoEpisodio.
     * 
     * @param value
     *     allowed object is
     *     {@link LuogoEpisodio }
     *     
     */
    public void setLuogoEpisodio(LuogoEpisodio value) {
        this.luogoEpisodio = value;
    }

    /**
     * Recupera il valore della propriet� dispositivo.
     * 
     * @return
     *     possible object is
     *     {@link Dispositivo }
     *     
     */
    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    /**
     * Imposta il valore della propriet� dispositivo.
     * 
     * @param value
     *     allowed object is
     *     {@link Dispositivo }
     *     
     */
    public void setDispositivo(Dispositivo value) {
        this.dispositivo = value;
    }

    /**
     * Recupera il valore della propriet� evento.
     * 
     * @return
     *     possible object is
     *     {@link Evento }
     *     
     */
    public Evento getEvento() {
        return evento;
    }

    /**
     * Imposta il valore della propriet� evento.
     * 
     * @param value
     *     allowed object is
     *     {@link Evento }
     *     
     */
    public void setEvento(Evento value) {
        this.evento = value;
    }

    /**
     * Recupera il valore della propriet� compilatore.
     * 
     * @return
     *     possible object is
     *     {@link Compilatore }
     *     
     */
    public Compilatore getCompilatore() {
        return compilatore;
    }

    /**
     * Imposta il valore della propriet� compilatore.
     * 
     * @param value
     *     allowed object is
     *     {@link Compilatore }
     *     
     */
    public void setCompilatore(Compilatore value) {
        this.compilatore = value;
    }

}
