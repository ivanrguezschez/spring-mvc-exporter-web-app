package com.irs.springmvcexporterwebapp.presentacion.views.administracion;

import com.irs.springmvcexporterwebapp.negocio.servicios.support.SearchOutput;
import com.irs.springmvcexporterwebapp.negocio.vo.RolVO;
import com.irs.springmvcexporterwebapp.negocio.vo.UsuarioVO;
import com.irs.springmvcexporterwebapp.presentacion.ConstPresentacion;
import com.irs.springmvcexporterwebapp.presentacion.commands.administracion.UsuarioSearchCommand;
import com.irs.springmvcexporterwebapp.presentacion.util.HeaderAndFooterPdfPageEvent;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

@Component("usuarioListPdfView")
public class UsuarioListPdfView extends AbstractPdfView {

    private static final Logger LOG = LoggerFactory.getLogger(UsuarioListPdfView.class);

    public static final Font FONT_8_NORMAL = FontFactory.getFont(BaseFont.HELVETICA, BaseFont.CP1252, 8, Font.NORMAL);
    public static final Font FONT_8_BOLD = FontFactory.getFont(BaseFont.HELVETICA, BaseFont.CP1252, 8, Font.BOLD);
    public static final Font FONT_10_NORMAL = FontFactory.getFont(BaseFont.HELVETICA, BaseFont.CP1252, 10, Font.NORMAL);
    public static final Font FONT_10_BOLD = FontFactory.getFont(BaseFont.HELVETICA, BaseFont.CP1252, 10, Font.BOLD);
    public static final Font FONT_12_BOLD = FontFactory.getFont(BaseFont.HELVETICA, BaseFont.CP1252, 12, Font.BOLD);
        
    public static final DateFormat DATE_FORMAT_DEFAULT = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected Document newDocument() {
        float margin1Cm = centimeterToPoint(1.0f);
        float margin2Cm = centimeterToPoint(2.0f);
        
        return new Document(PageSize.A4, margin2Cm, margin2Cm, margin1Cm, margin2Cm);
    }
    
    /**
     * Método que construye el documento pdf del listado de usuarios.
     *
     * @param model Objeto con los datos del modelo.
     * @param document Objeto documento de pdf.
     * @param writer Objeto writer de pdf.
     * @param request Objeto http request.
     * @param response Objeto http response.
     * @throws Exception si se produce cualquier error.
     */
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document,
            PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Creando el pdf de listado de usuarios");
        }
     
        LOG.debug("leftMargin {}, rightMargin {}, topMargin {}, bottomMargin {}", document.leftMargin(), document.rightMargin(), document.topMargin(), document.bottomMargin());
            
        Map<String, String> params = new HashMap<String, String>();
        params.put("organismo.nivel.1.nombre", "DENOMINACI\u00D3N\nORGANISMO\nDE PRIMER NIVEL");
        params.put("organismo.nivel.1.via.nombre", "AAAAA AAAAAAAAAA, 999");        
        params.put("organismo.nivel.1.via.cp", "99999");
        params.put("organismo.nivel.1.via.provincia", "AAAAAA");
        params.put("organismo.nivel.1.telefono", "99 999 99 99");
        params.put("organismo.nivel.1.fax", "99 999 99 99");
        params.put("organismo.nivel.2.nombre", "DENOMINACI\u00D3N ORGANISMO DE SEGUNDO NIVEL");
        params.put("organismo.nivel.3.nombre", "DENOMINACI\u00D3N ORGANISMO DE TERCER NIVEL");
        
        HeaderAndFooterPdfPageEvent events = new HeaderAndFooterPdfPageEvent(getMessageSourceAccessor(), params);
        writer.setPageEvent(events);
	events.onOpenDocument(writer, document);
                        
        float space05cm = centimeterToPoint(0.5f);
        float space02cm = centimeterToPoint(0.2f);
                
        document.add(createParagraph(getMessage("pdf.title.usuarioList"), FONT_12_BOLD, space05cm));
        
        document.add(createParagraph(getMessage("pdf.title.search"), FONT_10_BOLD, space02cm));
        document.add(createTableSearch(model));
        
        document.add(createParagraph(getMessage("pdf.title.result"), FONT_10_BOLD, space02cm));
        document.add(createTableResult(model));
    }
    
    private PdfPTable createTableSearch(Map<String, Object> model) throws DocumentException {
        final UsuarioSearchCommand command = (UsuarioSearchCommand) model.get(ConstPresentacion.COMMAND_USUARIO_SEARCH_KEY);
        List<RolVO> roles = (List<RolVO>)  model.get(ConstPresentacion.ROLES);
            
	RolVO rol = (RolVO) CollectionUtils.find(roles, new Predicate() {
            public boolean evaluate(Object object) {
                if (command.getRol() != null) {
                    return ((RolVO) object).getId().intValue() == command.getRol();
                }
                
                return false;
            }
        });
	
        /*
        float[] TABLE_SEARCH_WDITHS = new float[] {0.6f, 2f};
        
        PdfPTable table = new PdfPTable(TABLE_SEARCH_WDITHS.length);
	table.setWidths(TABLE_SEARCH_WDITHS);
	table.setWidthPercentage(100);
        */        
        float[] TABLE_SEARCH_WDITHS = new float[] {centimeterToPoint(3.0f), centimeterToPoint(14.0f)};
        PdfPTable table = new PdfPTable(TABLE_SEARCH_WDITHS.length);
	table.setWidths(TABLE_SEARCH_WDITHS);
	table.setTotalWidth(centimeterToPoint(17.0f));
        table.setLockedWidth(true);
        
	table.addCell(createCellLabel(getMessage("pdf.label.nif") + ":"));
	table.addCell(createCellValue(command.getNif()));
				
	table.addCell(createCellLabel(getMessage("pdf.label.nombre") + ":"));
	table.addCell(createCellValue(command.getNombre()));
		
	table.addCell(createCellLabel(getMessage("pdf.label.apellido1") + ":"));
	table.addCell(createCellValue(command.getApellido1()));
		
	table.addCell(createCellLabel(getMessage("pdf.label.apellido2") + ":"));
	table.addCell(createCellValue(command.getApellido2()));
		
	table.addCell(createCellLabel(getMessage("pdf.label.rol") + ":"));
	table.addCell(createCellValue(rol));
				
	return table;
    }
    
    private PdfPTable createTableResult(Map<String, Object> model) throws DocumentException {
        SearchOutput<UsuarioVO> searchOutput = (SearchOutput<UsuarioVO>) 
                model.get(ConstPresentacion.USUARIO_LIST_KEY);
        /*
        float[] TABLE_RESULT_WDITHS = new float[] {0.5f, 1f, 1f, 1f, 1f, 0.5f};
        
        PdfPTable table = new PdfPTable(TABLE_RESULT_WDITHS.length);
	table.setWidths(TABLE_RESULT_WDITHS);
	table.setWidthPercentage(100);
        */
        float[] TABLE_RESULT_WDITHS = new float[] {
            centimeterToPoint(2.0f),
            centimeterToPoint(3.0f),
            centimeterToPoint(3.0f),
            centimeterToPoint(3.0f),
            centimeterToPoint(4.0f),
            centimeterToPoint(2.0f)
        };
        
        PdfPTable table = new PdfPTable(TABLE_RESULT_WDITHS.length);
	table.setWidths(TABLE_RESULT_WDITHS);
	table.setTotalWidth(centimeterToPoint(17.0f));
        table.setLockedWidth(true);
        table.setHeaderRows(1);
                	
	table.addCell(createCellHeader(getMessage("pdf.table.head.label.nif")));
	table.addCell(createCellHeader(getMessage("pdf.table.head.label.nombre")));
	table.addCell(createCellHeader(getMessage("pdf.table.head.label.apellido1")));
	table.addCell(createCellHeader(getMessage("pdf.table.head.label.apellido2")));
	table.addCell(createCellHeader(getMessage("pdf.table.head.label.rol")));
	table.addCell(createCellHeader(getMessage("pdf.table.head.label.fechaAlta")));
			
	List<UsuarioVO> usuarios = (List<UsuarioVO>) searchOutput.getSearchResult().getResult();
	if (CollectionUtils.isNotEmpty(usuarios)) {
            for (UsuarioVO u : usuarios) {
		table.addCell(createCellBody(u.getNif()));
		table.addCell(createCellBody(u.getNombre()));
		table.addCell(createCellBody(u.getApellido1()));
		table.addCell(createCellBody(u.getApellido2()));
		table.addCell(createCellBody(u.getRol().getRol()));
		table.addCell(createCellBody(DATE_FORMAT_DEFAULT.format(u.getFechaAlta())));
            }
        }	

        return table;
    }
        
    private String getMessage(String key) {
        return getMessage(key, null);
    }
	
    private String getMessage(String key, Object[] args) {
	return getMessageSourceAccessor().getMessage(key, args);
    }
    
    private Paragraph createParagraph(String text, Font font, float spacingAfter) {
	Paragraph paragraph = new Paragraph(text, font);
	paragraph.setSpacingAfter(spacingAfter);
        
	return paragraph;
    }
    
    private PdfPCell createCellHeader(String header) {
        PdfPCell cell = new PdfPCell(new Phrase(header, FONT_8_BOLD));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        return cell;
    }
    
    private PdfPCell createCellBody(String value) {
        PdfPCell cell = new PdfPCell(new Phrase(value, FONT_8_NORMAL));
        
        return cell;
    }
    
    private PdfPCell createCellLabel(String label) {
        PdfPCell cell = new PdfPCell(new Phrase(label, FONT_10_NORMAL));
        cell.setBorder(PdfPCell.NO_BORDER);
        
        return cell;
    }
    
    private PdfPCell createCellValue(String value) {
        PdfPCell cell = new PdfPCell(new Phrase(StringUtils.isNotEmpty(value) ? value : "-", FONT_10_BOLD));
        cell.setBorder(PdfPCell.NO_BORDER);
        
        return cell;
    }
    
    private PdfPCell createCellValue(RolVO rol) {
        PdfPCell cell = new PdfPCell(new Phrase(rol != null ? rol.getRol() : "-", FONT_10_BOLD));
        cell.setBorder(PdfPCell.NO_BORDER);
        
        return cell;
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
    
    private float centimeterToPoint(float cm) {
        return Math.round((cm * 72.0 / 2.54) * 100 / 100);
    }
}
