package es.uniovi.asw.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import es.uniovi.asw.util.GeneradorPasswords;

public class GeneradorCartasPDF implements GeneradorCartas {

	@Override
	public String generarCarta(String nombre,String NIF ,String email) {
		
		String contraseña = GeneradorPasswords.getGeneradorPasswords().generarContraseña();
		
		FileOutputStream archivo;
		
		File folder = new File("cartas");
		try {
			if(!folder.exists()){
				folder.mkdir();
			}
			archivo = new FileOutputStream("cartas/"+email+".pdf");

		      Document documento = new Document();
		      PdfWriter.getInstance(documento, archivo);
		      documento.open();
		      documento.add(new Paragraph("Le comunicamos al usuario con DNI "+NIF+", que ha sido añadido al censo electoral "
						+ "y puede entrar en su cuenta con la siguiente"
						+ " información:"));
		      
		      documento.add(new Paragraph("Usuario: "+email));
		      documento.add(new Paragraph("Contraseña: "+contraseña));
		      documento.close();
		      
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (DocumentException e) {
			
			e.printStackTrace();
		}

		return contraseña;
	}

}
