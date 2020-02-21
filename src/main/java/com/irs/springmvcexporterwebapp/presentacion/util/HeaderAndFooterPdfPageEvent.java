package com.irs.springmvcexporterwebapp.presentacion.util;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;

public class HeaderAndFooterPdfPageEvent extends PdfPageEventHelper {

    private static final Logger LOG = LoggerFactory.getLogger(HeaderAndFooterPdfPageEvent.class);
    
    public static final Font FONT_6_NORMAL = FontFactory.getFont(BaseFont.HELVETICA, BaseFont.CP1252, 6, Font.NORMAL);
    public static final Font FONT_8_NORMAL = FontFactory.getFont(BaseFont.HELVETICA, BaseFont.CP1252, 8, Font.NORMAL);
     
    private static final float CM_17 = centimeterToPoint(17.0f);
    private static final float CM_380 = centimeterToPoint(3.80f);
    private static final float CM_05 = centimeterToPoint(0.5f);
    
    private Map<String, String> params;
    
    /** Objeto de mensajes i18n. */
    private MessageSourceAccessor messageSource;
        
    /** Tabla del encabezado. */
    private PdfPTable tableHeader;
	
    /** Tabla del pie. */
    //private PdfPTable tableFooter;
    
    private PdfPTable tableDireccion;
        
    public HeaderAndFooterPdfPageEvent(MessageSourceAccessor messageSource, Map<String, String> params) throws IOException, DocumentException {
        super();
        this.messageSource = messageSource;
        this.params = params;
        this.tableHeader = createTableHeader();
 	//this.tableFooter = createTableFooter();
        this.tableDireccion = createTableDireccion();
    }
    
    public PdfPTable getTableHeader() {
	return tableHeader;
    }
		
//    public PdfPTable getTableFooter() {
//	return tableFooter;
//    }

    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        LOG.debug("leftMargin {}, rightMargin {}, topMargin {}, bottomMargin {}", document.leftMargin(), document.rightMargin(), document.topMargin(), document.bottomMargin());
	//document.setMargins(document.leftMargin(), document.rightMargin(), document.topMargin(), document.bottomMargin());
	try {
            document.add(tableHeader);	
	} catch (Exception e) {
            LOG.warn(e.getMessage(), e);
	}
    }
		
    @Override
    public void onStartPage(PdfWriter writer, Document document) {
	try {
            document.add(tableHeader);			
	} catch (Exception e) {
            LOG.warn(e.getMessage(), e);
	}
    }
	
    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        /*
	PdfPCell cell = tableFooter.getRow(0).getCells()[0];
	cell.setPhrase(null);
        cell.addElement(new Phrase("Página " + writer.getPageNumber(), FONT_6_NORMAL));
		
	//tableFooter.setTotalWidth(document.right() - document.left());
	//tableFooter.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin(), writer.getDirectContent());
        tableFooter.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin() - centimeterToPoint(0.5f), writer.getDirectContent());
        */
        
        try {
            PdfPTable tableFooter = new PdfPTable(2);
            tableFooter.setWidths(new float[] {
                centimeterToPoint(13.20f), 
                centimeterToPoint(3.80f) 
            });
            tableFooter.setTotalWidth(CM_17);
            tableFooter.setLockedWidth(true);

            PdfPCell cell = new PdfPCell(new Phrase(getMessage("pdf.footer.pagina", new Object[] {writer.getPageNumber()}), FONT_6_NORMAL));
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            tableFooter.addCell(cell);

            cell = new PdfPCell(this.tableDireccion);
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setBorderWidthLeft(0.3f);
            cell.setPaddingLeft(centimeterToPoint(0.20f));
            tableFooter.addCell(cell);
            
            //tableFooter.setTotalWidth(document.right() - document.left());
            //tableFooter.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin(), writer.getDirectContent());
            tableFooter.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin() - CM_05, writer.getDirectContent());
        
        } catch (Exception e) {
            LOG.warn(e.getMessage(), e);
        }
    }

    private PdfPTable createTableHeader() throws IOException, DocumentException {
        /*
        PdfPTable tableHeader = new PdfPTable(2);
	tableHeader.setWidths(new float[] {1f, 0.6f});
	tableHeader.setWidthPercentage(100);
	tableHeader.setSpacingAfter(20);
	//tableHeader.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        tableHeader.addCell(createTableOrganismoNivel1(getMessage("image.escudo.pdf"), 
                StringEscapeUtils.unescapeJava(params.get("organismo.nivel.1.nombre")),
                50));
        
        tableHeader.addCell(createTableSubsecretaria(
        		getMessage("pdf.header.organo.segundo.nivel"),
                        getMessage("pdf.header.organo.tercer.nivel"))); 				
		
	return tableHeader;
        */
        PdfPTable tableHeader = new PdfPTable(3);
	tableHeader.setWidths(new float[] {
            centimeterToPoint(2.0f), 
            centimeterToPoint(11.20f), 
            centimeterToPoint(3.8f)});
	tableHeader.setSpacingAfter(centimeterToPoint(0.5f));
	//tableHeader.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        tableHeader.setTotalWidth(centimeterToPoint(17.0f));
        tableHeader.setLockedWidth(true);
                        
        ByteArrayOutputStream output = new ByteArrayOutputStream();
	IOUtils.copy(getClass().getClassLoader().getResourceAsStream(getMessage("image.escudo.pdf")), output);
                        
	Image image = Image.getInstance(output.toByteArray());
	image.scalePercent(75);
                        
 	// Añado el logotipo
        PdfPCell cell = new PdfPCell(new Phrase(new Chunk(image, 0, 0)));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setRowspan(2);
        tableHeader.addCell(cell);
                
        cell = new PdfPCell(new Phrase(StringEscapeUtils.unescapeJava(params.get("organismo.nivel.1.nombre")), FONT_8_NORMAL));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPaddingTop(centimeterToPoint(0.4f));
        cell.setRowspan(2);
        tableHeader.addCell(cell);
        
        cell = new PdfPCell(new Phrase(params.get("organismo.nivel.2.nombre"), FONT_6_NORMAL));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(new Color(240, 240, 240));
        cell.setPadding(centimeterToPoint(0.2f));
        tableHeader.addCell(cell);
        
        cell = new PdfPCell(new Phrase(params.get("organismo.nivel.3.nombre"), FONT_6_NORMAL));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPadding(centimeterToPoint(0.2f));
        tableHeader.addCell(cell);
		
	return tableHeader;
    }

    /*
    private PdfPTable createTableFooter() throws IOException, DocumentException {
	PdfPTable t = new PdfPTable(2);
	t.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
	t.addCell("");
		
	PdfPCell cell = new PdfPCell();
	cell.setBorder(PdfPCell.NO_BORDER);
	cell.addElement(createTableDireccion());
	t.addCell(cell);
	
	return t;
    }
    */
	
    /*
    private PdfPTable createTablePagina(PdfWriter writer) {
	PdfPTable t = new PdfPTable(1);
	t.setWidthPercentage(40);
	t.setHorizontalAlignment(Element.ALIGN_LEFT);
	t.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

	t.addCell(new Phrase(getMessage("pdf.footer.pagina", new Object[] { writer.getPageNumber() }), FONT_6_NORMAL));
		
	return t;
    }
    */
    
    private String getMessage(String code) {
	return messageSource.getMessage(code);
    }
	
    private String getMessage(String code, Object[] args) {
	return messageSource.getMessage(code, args);
    }

    private String getMessage(String code, Object[] args, Locale locale) {
	return messageSource.getMessage(code, args, locale);
    }
    
    /*
    private PdfPTable createTableOrganismoNivel1(String imageName, 
            String organismoNivel1Nombre, int scalePercent) throws IOException, DocumentException {
	PdfPTable table = new PdfPTable(2);
 	table.setHorizontalAlignment(Element.ALIGN_LEFT);
 	table.setTotalWidth(200);
 	table.setLockedWidth(true);
 	table.setWidths(new float[] {0.4f, 1.6f});
 		
 	PdfPCell cell = null;
 		
        ByteArrayOutputStream output = new ByteArrayOutputStream();
	IOUtils.copy(getClass().getClassLoader().getResourceAsStream(imageName), output);
			
	Image image = Image.getInstance(output.toByteArray());
	image.scalePercent(scalePercent);
                
 	// Añado el logotipo
 	cell = new PdfPCell(new Phrase(new Chunk(image, 0, 0)));
 	//cell.setBorder(PdfPCell.NO_BORDER);
 	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
 	table.addCell(cell);
 	   	
 	// Añado el nombre del organismo de primer nivel
        cell = new PdfPCell(new Phrase(organismoNivel1Nombre, FONT_8_NORMAL));
        //cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        
        return table;
    }
    */
    
    /*
    private PdfPTable createTableOrganismoNivel2(String organismoNivel2Label, String organismoNivel2Value) {
	PdfPTable table = new PdfPTable(1);
	table.setWidthPercentage(30);

	PdfPCell cell = null;
		
	cell = new PdfPCell(new Phrase(organismoNivel2Label, FONT_6_NORMAL));
	//cell.setBorder(PdfPCell.NO_BORDER);
	cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	cell.setBackgroundColor(new Color(240, 240, 240));
	cell.setPaddingTop(5);
	cell.setPaddingBottom(5);
	table.addCell(cell);
					
	cell = new PdfPCell(new Phrase(organismoNivel2Value, FONT_6_NORMAL));
	//cell.setBorder(PdfPCell.NO_BORDER);
	cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	table.addCell(cell);

	return table;
    }
    */
    
    private PdfPTable createTableDireccion() throws IOException, DocumentException {
        /*
	PdfPTable tableDireccion = new PdfPTable(1);
	tableDireccion.setWidthPercentage(40);
	tableDireccion.setHorizontalAlignment(Element.ALIGN_RIGHT);
	tableDireccion.getDefaultCell().setPadding(0);
	tableDireccion.getDefaultCell().setPaddingLeft(2);
	//tableDireccion.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
	//tableDireccion.getDefaultCell().setBorderWidthLeft(0.1f);
		
        tableDireccion.addCell(new Phrase(params.get("organismo.nivel.1.via.nombre"), FONT_6_NORMAL));
	tableDireccion.addCell(new Phrase(params.get("organismo.nivel.1.via.cp") + " " + params.get("organismo.nivel.1.via.provincia"), FONT_6_NORMAL));
	tableDireccion.addCell(new Phrase(getMessage("pdf.footer.telefono",  new Object[] { params.get("organismo.nivel.1.telefono") }), FONT_6_NORMAL));
        tableDireccion.addCell(new Phrase(getMessage("pdf.footer.fax",  new Object[] { params.get("organismo.nivel.1.fax") }), FONT_6_NORMAL));

        return tableDireccion;
        */
       
        PdfPTable tableDireccion = new PdfPTable(1);
	//tableDireccion.getDefaultCell().setPaddingLeft(0);
        //tableDireccion.getDefaultCell().setPaddingTop(1);
        //tableDireccion.getDefaultCell().setPaddingBottom(1);
	//tableDireccion.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        
	tableDireccion.setWidths(new float[] { CM_380 });
        tableDireccion.setTotalWidth(CM_380);
        tableDireccion.setLockedWidth(true);
        /*
        tableDireccion.addCell(new Phrase(params.get("organismo.nivel.1.via.nombre"), FONT_6_NORMAL));
	tableDireccion.addCell(new Phrase(params.get("organismo.nivel.1.via.cp") + " " + params.get("organismo.nivel.1.via.provincia"), FONT_6_NORMAL));
	tableDireccion.addCell(new Phrase(getMessage("pdf.footer.telefono",  new Object[] { params.get("organismo.nivel.1.telefono") }), FONT_6_NORMAL));
        tableDireccion.addCell(new Phrase(getMessage("pdf.footer.fax",  new Object[] { params.get("organismo.nivel.1.fax") }), FONT_6_NORMAL));
        */
        
        PdfPCell cell = new PdfPCell(new Phrase(params.get("organismo.nivel.1.via.nombre"), FONT_6_NORMAL));        
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPadding(0);
        cell.setPaddingBottom(1);
        tableDireccion.addCell(cell);
        
        cell = new PdfPCell(new Phrase(params.get("organismo.nivel.1.via.cp") + " " + params.get("organismo.nivel.1.via.provincia"), FONT_6_NORMAL));        
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPadding(0);
        cell.setPaddingBottom(1);
        tableDireccion.addCell(cell);
        
        cell = new PdfPCell(new Phrase(getMessage("pdf.footer.telefono",  new Object[] { params.get("organismo.nivel.1.telefono") }), FONT_6_NORMAL));        
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPadding(0);
        cell.setPaddingBottom(1);
        tableDireccion.addCell(cell);
        
        cell = new PdfPCell(new Phrase(getMessage("pdf.footer.fax",  new Object[] { params.get("organismo.nivel.1.fax") }), FONT_6_NORMAL));        
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPadding(0);
        cell.setPaddingBottom(1.5f);
        tableDireccion.addCell(cell);
                
        return tableDireccion;
    }
    
    private float pointToInch(float point) {
        return (float) (point / 72.0);
    }
    
    private float pointToCentimeter(float point) {
        return Math.round((point * 2.54 / 72.0) * 100 / 100);
    }
    
    private float pointToMilimeter(float point) {
        //return (float) (point * 2.54 / 72.0 * 100);
        return Math.round((point * 2.54 / 72.0 * 100) * 100 / 100);
    }
    
    private static float centimeterToPoint(float cm) {
        return Math.round((cm * 72.0 / 2.54) * 100 / 100);
    }
}
