package com.cocay.sicecd.security.pdf;
 import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.itextpdf.kernel.pdf.EncryptionConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.ReaderProperties;
import com.itextpdf.kernel.pdf.WriterProperties;
public class SeguridadPDF {


public byte[] obtenHash(String usuario,String propietario){
    String hashed = usuario + propietario;
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    byte[] encodedhash = digest.digest(hashed.getBytes(StandardCharsets.UTF_8));
    return encodedhash;
}


public void renombra(String src){
    File oldfile = new File(src);
    File newfile = new File(src.substring(0, src.length() - 4) + "_ax.pdf");
    if (oldfile.renameTo(newfile)) {
        System.out.println("Rename succesful");
    } else {
        System.out.println("Rename failed");
    }
    oldfile = null;
    newfile = null;
}


public void cifraPdf(String src,String usuario,String propietario){
    renombra(src);
    byte[] encodedhash = obtenHash(usuario, propietario);
    PdfReader reader = new PdfReader(src.substring(0, src.length() - 4) + "_ax.pdf");
    WriterProperties props = new WriterProperties().setStandardEncryption(encodedhash, encodedhash, EncryptionConstants.ALLOW_PRINTING, EncryptionConstants.ENCRYPTION_AES_128 | EncryptionConstants.DO_NOT_ENCRYPT_METADATA);
    FileOutputStream fileOS = new FileOutputStream(src);
    PdfWriter writer = new PdfWriter(fileOS, props);
    PdfDocument pdfDoc = new PdfDocument(reader, writer);
    /*
    	reader.setCloseStream(true);
    	pdfDoc.setCloseReader(true);
    	pdfDoc.setCloseWriter(true);
    	pdfDoc.setFlushUnusedObjects(true);
    	//*/
    pdfDoc.close();
    // fileOS.flush();
    writer.close();
    fileOS.close();
    reader.close();
    fileOS = null;
    writer = null;
    reader = null;
    pdfDoc = null;
    File oldFile = new File(src.substring(0, src.length() - 4) + "_ax.pdf");
    if (!oldFile.delete()) {
        oldFile.deleteOnExit();
    }
}


public void descifraPdf(String src,String usuario,String propietario){
    renombra(src);
    byte[] encodedhash = obtenHash(usuario, propietario);
    PdfReader reader = new PdfReader(src.substring(0, src.length() - 4) + "_ax.pdf", new ReaderProperties().setPassword(encodedhash));
    PdfDocument pdfDoc = new PdfDocument(reader, new PdfWriter(src));
    System.out.println(new String(reader.computeUserPassword()));
    pdfDoc.close();
    pdfDoc = null;
    reader.close();
    reader = null;
    new File(src.substring(0, src.length() - 4) + "_ax.pdf").delete();
}


}