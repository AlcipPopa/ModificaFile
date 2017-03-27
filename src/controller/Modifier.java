package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.Date;

public class Modifier {
	// final private String pathScrittura="C:\\Users\\Lenovo\\Desktop\\";
	final private String pathScrittura = "\\\\xpsharepointlb1\\d$\\TWS_DOC\\Jobstreams\\";
	private String jobstream, workstation, aux, nomeFile;
	BufferedReader br;

	/**
	 * @param jobstream
	 * @param workstation
	 * @param log
	 * 			contiene le info dei contatti 
	 */
	public Modifier(String jobstream, String workstation, String log) {
		jobstream = jobstream.toUpperCase();
		this.jobstream = jobstream;
		workstation = workstation.toUpperCase();
		this.workstation = workstation;
		nomeFile = workstation.concat(".").concat(jobstream).concat(".txt");
		br = new BufferedReader(new StringReader(log));
	}

	public void modificaFile() {
		try {
			// con PrintWriter, se trovo il file, lo modifico; altrimenti lo creo
			PrintWriter fw = new PrintWriter(pathScrittura.concat(nomeFile));
			BufferedWriter bwr = new BufferedWriter(fw);

			bwr.write("***");
			bwr.newLine();
			bwr.write("*** " + pathScrittura.concat(nomeFile));
			bwr.newLine();
			bwr.write("***");
			bwr.newLine();
			bwr.newLine();
			bwr.write("Note qui sotto");
			bwr.newLine();
			bwr.write("------------------");
			bwr.newLine();

			while ((aux = br.readLine()) != null) {
				bwr.write(aux);
			}

			bwr.newLine();
			bwr.newLine();
			
			// aggiunge alla fine del log i dati di chi modifica
			String data = new Date().toString();
			bwr.write("-- modificato da ".concat(System.getProperty("user.name")).concat(" il: ").concat(data).concat(" --"));
			
			bwr.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getJobstream() {
		return jobstream;
	}

	public String getWorkstation() {
		return workstation;
	}
}
