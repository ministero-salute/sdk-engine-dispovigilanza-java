//
// Questo file � stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andr� persa durante la ricompilazione dello schema di origine. 
// Generato il: 2022.04.12 alle 10:57:37 AM CEST 
//


/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.dispovig.tracciato.bean.output;

import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvRecurse;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element ref="{http://flussi.mds.it/flsDispovig}Rapporto" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "content"
})
@XmlRootElement(name = "flsDispovig")
public class FlsDispovig {

//  @XmlMixed
    @XmlElementRef(name = "Rapporto", namespace = "http://flussi.mds.it/flsDispovig", type = Rapporto.class, required = false)
    @CsvRecurse
    protected List<Rapporto> content;

    /**
     * Gets the value of the content property.
     * 
     * <p>
     * This rapportor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Rapporto }
     * {@link String }
     * 
     * 
     */
    public List<Rapporto> getContent() {
        if (content == null) {
            content = new ArrayList<Rapporto>();
        }
        return this.content;
    }

    public void addContent(Rapporto rapporto) {
        if (content == null) {
            content = new ArrayList<Rapporto>();
        }
        
        if (rapporto != null)
        	content.add(rapporto);
       
    }
    
    public void addAllContent(List<Rapporto> rapporto) {
        if (content == null) {
            content = new ArrayList<Rapporto>();
        }
        
        if (rapporto != null)
        	content.addAll(rapporto);
       
    }
}
