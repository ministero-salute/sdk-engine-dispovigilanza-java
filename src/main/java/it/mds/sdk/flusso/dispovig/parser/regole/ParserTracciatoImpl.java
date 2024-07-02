/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.dispovig.parser.regole;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;

import it.mds.sdk.flusso.dispovig.parser.regole.conf.ConfigurazioneFlussoDispovig;
import it.mds.sdk.libreriaregole.dtos.RecordDtoGenerico;
import it.mds.sdk.libreriaregole.parser.ParserTracciato;
import it.mds.sdk.rest.exception.ParseCSVException;
import lombok.extern.slf4j.Slf4j;

/**
 * La classe implementa l'interfaccia ParserTracciato e il suo metodo parseTracciato(File tracciato)
 */
@Slf4j
@Component("parserTracciato")
public class ParserTracciatoImpl implements ParserTracciato {
	
	private final ConfigurazioneFlussoDispovig conf = new ConfigurazioneFlussoDispovig();

    /**
     * Il metodo converte un File.csv in una List<RecordDtoGenerico> usando come separatore "~"
     *
     * @param tracciato, File.csv di input
     * @return una lista di RecordDtoDispovig
     */
    @Override
    public List<RecordDtoGenerico> parseTracciato(File tracciato) {
    	FileReader fileReader = null;
    	List<RecordDtoGenerico> dirList = Collections.emptyList();
        try {
            fileReader = new FileReader(tracciato);
            dirList = new CsvToBeanBuilder<RecordDtoGenerico>(fileReader)
                    .withType(RecordDtoDispovig.class)
                    .withSeparator('~')
                    .withSkipLines(1)   //Salta la prima riga del file CSV
                    .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
                    .build()
                    .parse();

        } catch (IOException e) {
            log.error("Error:",e);
        } catch (Exception ex) {
            log.error("Error:",ex);
            throw new ParseCSVException(ex.getMessage());
        }finally {
        	try {
        		if (fileReader != null)
        			fileReader.close();
			} catch (IOException e) {
                log.error("Error:",e);
			}
		}

        return dirList;
    }
    
    public List<RecordDtoGenerico> parseTracciatoBlocco(File tracciato, int inizio, int fine) {
        StopWatch stopWatch = new StopWatch();
        log.info("Inizio lettura file {} da riga {} a riga {}", tracciato.getName(), inizio, fine);
        char separatore = conf.getSeparatore().getSeparatore().charAt(0);
        stopWatch.start();
        try (Reader reader = Files.newBufferedReader(tracciato.toPath())) {
            List<RecordDtoGenerico> res = new ArrayList<>();
            Iterator<RecordDtoDispovig> it = new CsvToBeanBuilder<RecordDtoDispovig>(reader)
                    .withType(RecordDtoDispovig.class)
                    .withSeparator(separatore)
                    .withSkipLines(inizio + 1)   //Salta la prima riga del file CSV
                    .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
                    .build()
                    .iterator();
            int count = inizio;
            int totaleRecordElaborati = 0;
            while (it.hasNext() && count < fine) {
                count++;
                RecordDtoGenerico recordGen = it.next();
                res.add(recordGen);
                totaleRecordElaborati++;
            }
            stopWatch.stop();
            log.info("Fine lettura file {} da riga {} a riga {} con {} record in {} ms", tracciato.getName(), inizio,
                    fine, totaleRecordElaborati, stopWatch.getLastTaskTimeMillis());

            return res;
        } catch (IOException e) {
            throw new ParseCSVException(e.getMessage());
        } catch (Exception ex) {
            log.error("Error:",ex);
            throw new ParseCSVException(ex.getMessage());
        }
    }
    
}
