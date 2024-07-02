/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.dispovig.tracciato;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import it.mds.sdk.flusso.dispovig.parser.regole.RecordDtoDispovig;
import it.mds.sdk.flusso.dispovig.parser.regole.conf.ConfigurazioneFlussoDispovig;
import it.mds.sdk.flusso.dispovig.tracciato.bean.output.FlsDispovig;
import it.mds.sdk.flusso.dispovig.tracciato.bean.output.Rapporto;
import it.mds.sdk.libreriaregole.tracciato.TracciatoSplitter;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("tracciatoSplitter")
public class TracciatoSplitterImpl implements TracciatoSplitter<RecordDtoDispovig> {
    @Override
    @Deprecated
    public List<Path> dividiTracciato(Path tracciato) {
        return null;
    }

    private static final String ERR_MSG = "Errore nella generazione del tracciato xml di output";
	public static final String XML_FILENAME_TEMPLATE = "SDK_DISPO_VIG_%s_%s.xml";
    
    public static byte[] getBeansAsByteArray(final List<RecordDtoDispovig> beans) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        OutputStreamWriter streamWriter = new OutputStreamWriter(stream);
        CSVWriter writer = new CSVWriter(streamWriter);

        StatefulBeanToCsv<RecordDtoDispovig> beanToCsv = new StatefulBeanToCsvBuilder<RecordDtoDispovig>(writer).build();
        
		try {
			beanToCsv.write(beans);
			streamWriter.flush();
		} catch (CsvDataTypeMismatchException e) {
			log.error(ERR_MSG);
		} catch (CsvRequiredFieldEmptyException e) {
			log.error(ERR_MSG);
		} catch (IOException e) {
			log.error(ERR_MSG);
		}finally {
			try {
				writer.close();
			} catch (IOException e) {
				log.error(ERR_MSG);
			}
			try {
				streamWriter.close();
			} catch (IOException e) {
				log.error(ERR_MSG);
			}
			try {
				stream.close();
			} catch (IOException e) {
				log.error(ERR_MSG);
			}
			
			
		}
       
        
        return stream.toByteArray();
    }
    
    @Override
    public List<Path> dividiTracciato(List<RecordDtoDispovig> records,String idRun) {
    	
    	/* EFFETTURATO un doppio passaggio. Riportare gli accettati in csv e caricare 
    	 * i bean di jaxb in automatico (con opencsv)
    	 */
    	
    	FlsDispovig flsDispovig = creaFlsDispovig(records);
    	
    	ConfigurazioneFlussoDispovig conf = new ConfigurazioneFlussoDispovig();
        String dateFormat = new SimpleDateFormat("yyyyMMdd").format(new Date());

		String fileName= String.format(XML_FILENAME_TEMPLATE, dateFormat, idRun);
		Path xml = Path.of(conf.getXmlOutput().getPercorso(),fileName);
		try {
			jakarta.xml.bind.JAXBContext jaxbContext = JAXBContextFactory
					.createContext(new Class[] { FlsDispovig.class }, null);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, StandardCharsets.UTF_8.name());
			jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
			jaxbMarshaller.marshal(flsDispovig,
				xml.toFile());
		} catch (JAXBException e) {
			log.error("Error:",e);
		} 

        return List.of(xml);
    }
    
    
	public FlsDispovig creaFlsDispovig(List<RecordDtoDispovig> records) {
		
		/* Doppio passaggio. Riportare gli accettati in csv e caricare 
    	 * i bean di jaxb in automatico (con opencsv)
    	 */
		FlsDispovig flsDispovig = new FlsDispovig(); 
    	if (records != null) {
	    	
    		InputStream is = new ByteArrayInputStream(getBeansAsByteArray(records));
	        InputStreamReader isr = new InputStreamReader(is);
	        CSVReader rr = new CSVReader(isr);
			
			try {
				
		        List<Rapporto> dirList = new CsvToBeanBuilder<Rapporto>(rr)
		                .withType(Rapporto.class)
		                .withSeparator('~')
		                .withSkipLines(1)   //Salta la prima riga del file CSV
		                .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
		                .build()
		                .parse();
		        	
		        flsDispovig = new FlsDispovig();
		        flsDispovig.addAllContent(dirList);
		        
			} catch (IllegalStateException e) {
				log.error("Error:",e);
			}finally {
				try {
					rr.close();
				} catch (IOException e) {
					log.error(ERR_MSG);
				} finally {
					try {
						isr.close();
					} catch (IOException e) {
						log.error(ERR_MSG);
					} finally {
						try {
							is.close();
						} catch (IOException e) {
							log.error(ERR_MSG);
						}
					}
				}
			}
    	}
		return flsDispovig;
	}

    public FlsDispovig creaDispovig(List<RecordDtoDispovig> records, FlsDispovig flsDispovig) {

        if (flsDispovig == null)
        	flsDispovig = creaFlsDispovig(records);

        	return flsDispovig;
    }



}
