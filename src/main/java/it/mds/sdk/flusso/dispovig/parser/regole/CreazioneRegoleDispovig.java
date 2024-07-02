/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.dispovig.parser.regole;

import java.util.ArrayList;
import java.util.List;

import it.mds.sdk.libreriaregole.regole.beans.Campo;
import it.mds.sdk.libreriaregole.regole.beans.Parametri;
import it.mds.sdk.libreriaregole.regole.beans.RegolaGenerica;
import it.mds.sdk.libreriaregole.regole.beans.RegoleFlusso;

public class CreazioneRegoleDispovig {
	
	public static RegoleFlusso creaRegoleDispovig() {
	//Creo la Lista dei campi
			List<Campo> campi = new ArrayList<Campo>();
			
			//lista di regole per ogni campo
			 List<RegolaGenerica> regoleCOIdentificativo = new ArrayList<RegolaGenerica>();
	
			
	//-[]-[]-[]-[tipo_str]-[id_er]-[id_ass]-[vld_id_ass]-[tip_id_ass]-[dat_nasc]-[sesso]-[cod_asl_ass]-[citt]-[stat_est]-[cod_ist_comp]-[tipo_op]-[tipo_canale]-[cod_esen]-[tipo_erog]-[tipo_esen]-[tipo_cont]-[id_cont]-[dat_erog]-[quota_fissa_ass]-[quota_perc_ass]-[dat_pres]-[costo_srv_ric]-[cod_pr]-[tipo_pr]-[tipo_f]-[cod_f]-[qta]-[fatt_conv]-[targatura]-[costo_acq]-[costo_srv
			 Campo centraleOpIdentificativo = creaRegolaCentraleOpIdentificativo(regoleCOIdentificativo, "centraleOperativaIdentificativo");
			
			 		
			//aggioungo il campo alla lista campi da validare
	        campi.add(centraleOpIdentificativo);


		RegoleFlusso regoleFlusso =  RegoleFlusso.builder()
						.withCampi(campi)
						.build();
			
		return regoleFlusso;

}
	
	private static Campo creaRegolaCentraleOpIdentificativo (List<RegolaGenerica>  regoleCampo, String nomeCampo) {

		Parametri parametri = new Parametri();
		parametri.getParametriMap().put("parametroCampoCondizionante","1|4|6");
		parametri.getParametriMap().put("nomeCampoCondizionante","modalitaArrivo");
		
		parametri.getParametriMap().put("parametroCampoCondizionante2","5");
		parametri.getParametriMap().put("nomeCampoCondizionante2","responsabileInvio");
		
//		RegolaObbligatorietaCondizionataMultiCampo regolaObbligatorietaCondizionataMultiCampo = new RegolaObbligatorietaCondizionataMultiCampo("regolaObbligatorietaCondizionataMultiCampo", "1103","Il campo è obbligatorio",parametri);

		//aggiungo regole
//		regoleCampo.add(regolaObbligatorietaCondizionataMultiCampo);
		// creo campo
		return new Campo(regoleCampo, nomeCampo);
				
	}
	
	
	
}
