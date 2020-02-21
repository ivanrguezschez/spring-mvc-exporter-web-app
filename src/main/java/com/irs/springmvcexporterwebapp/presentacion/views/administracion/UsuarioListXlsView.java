package com.irs.springmvcexporterwebapp.presentacion.views.administracion;

import com.irs.springmvcexporterwebapp.negocio.servicios.support.SearchOutput;
import com.irs.springmvcexporterwebapp.negocio.vo.RolVO;
import com.irs.springmvcexporterwebapp.negocio.vo.UsuarioVO;
import com.irs.springmvcexporterwebapp.presentacion.ConstPresentacion;
import com.irs.springmvcexporterwebapp.presentacion.commands.administracion.UsuarioSearchCommand;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.LocalizedResourceHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

@Component("usuarioListXlsView")
public class UsuarioListXlsView extends AbstractXlsxView {

    /**
     * Objeto logger para trazas.
     */
    private static final Logger LOG = LoggerFactory.getLogger(UsuarioListXlsView.class);

    private static final String EXTENSION = ".xlsx";

    private static final String HEADER_CONTENT_DISPOSITION = "Content-Disposition";

    private static final String ATTACHMENT_FILENAME_FORMAT = "attachment;filename=%1$s";

    /**
     * Url a la plantilla excel del listado de usuarios.
     */
    private static final String EXCEL_TEMPLATE_URL
            = "classpath:xls/plantilla-usuarioListXlsView";

    private String urlTemplate;

    /**
     * Constructor por defecto.
     */
    public UsuarioListXlsView() {
        super();
        this.urlTemplate = EXCEL_TEMPLATE_URL;
    }

    @Override
    protected Workbook createWorkbook(Map<String, Object> model,
            HttpServletRequest request) {
        XSSFWorkbook workbook;
        if (this.urlTemplate != null) {
            workbook = getTemplateSource(this.urlTemplate, request);
        } else {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Crea el documento Excel desde cero");
            }
            workbook = new XSSFWorkbook();
        }

        return workbook;
    }

    /**
     * Crea un objeto workbook desde un documento XLS existente o un documento
     * XLS vacio si la plantilla no existe.
     *
     * @param url La url a la plantilla excel sin parte de ruta ni extensión.
     * @param request El objeto HTTP request
     * @return Un objeto HSSFWorkbook
     */
    private XSSFWorkbook getTemplateSource(String url, HttpServletRequest request) {
        LocalizedResourceHelper helper = new LocalizedResourceHelper(getApplicationContext());
        Locale userLocale = RequestContextUtils.getLocale(request);
        Resource inputFile = helper.findLocalizedResource(url, EXTENSION, userLocale);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Crea el documento Excel desde la plantilla '{}'", inputFile);
        }

        try {
            return new XSSFWorkbook(inputFile.getInputStream());
        } catch (IOException e) {
            if (LOG.isWarnEnabled()) {
                LOG.warn("Error cargando el documento plantilla de Excel {}: {}", this.urlTemplate, e.getMessage());
            }
            if (LOG.isDebugEnabled()) {
                LOG.debug("Crea el documento Excel desde cero");
            }

            return new XSSFWorkbook();
        }
    }

    /**
     * Metodo que prepara el objeto response para asignar un nombre al archivo
     * generado.
     *
     * @param request Objeto request.
     * @param response Objeto response.
     */
    @Override
    protected void prepareResponse(HttpServletRequest request,
            HttpServletResponse response) {
        super.prepareResponse(request, response);
        response.setHeader(HEADER_CONTENT_DISPOSITION,
                String.format(ATTACHMENT_FILENAME_FORMAT, getMessage("xls.fileName.usuarioList")));
    }

    /**
     * Método que construye el documento excel del listado de usuarios.
     *
     * @param model Objeto con los datos del modelo.
     * @param wb Objeto hoja de trabajo excel.
     * @param request Objeto http request.
     * @param response Objeto http response.
     * @throws Exception si se produce cualquier error.
     */
    @Override
    protected void buildExcelDocument(Map<String, Object> model,
            Workbook wb, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Creando el excel de listado de usuarios");
        }

        final UsuarioSearchCommand command = (UsuarioSearchCommand) model.get(ConstPresentacion.COMMAND_USUARIO_SEARCH_KEY);
        List<RolVO> roles = (List<RolVO>) model.get(ConstPresentacion.ROLES);

        RolVO rol = (RolVO) CollectionUtils.find(roles, new Predicate() {
            public boolean evaluate(Object object) {
                if (command.getRol() != null) {
                    return ((RolVO) object).getId().intValue() == command.getRol();
                }

                return false;
            }
        });

        SearchOutput<UsuarioVO> searchOutput = (SearchOutput<UsuarioVO>) model.get(ConstPresentacion.USUARIO_LIST_KEY);

        Sheet sheet = wb.getSheetAt(0);

        if (StringUtils.isNotEmpty(command.getNif())) {
            sheet.getRow(3).getCell(1).setCellValue(command.getNif());
        }
        if (StringUtils.isNotEmpty(command.getNombre())) {
            sheet.getRow(4).getCell(1).setCellValue(command.getNombre());
        }
        if (StringUtils.isNotEmpty(command.getApellido1())) {
            sheet.getRow(5).getCell(1).setCellValue(command.getApellido1());
        }
        if (StringUtils.isNotEmpty(command.getApellido2())) {
            sheet.getRow(6).getCell(1).setCellValue(command.getApellido2());
        }
        if (rol != null) {
            sheet.getRow(7).getCell(1).setCellValue(rol.getRol());
        }

        Row row = null;
        Cell cell = null;
        int rowNum = 11;
        List<UsuarioVO> usuarios = (List<UsuarioVO>) searchOutput.getSearchResult().getResult();
        
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(wb.createDataFormat().getFormat(getMessage("pattern.date")));
                
        if (CollectionUtils.isNotEmpty(usuarios)) {
            for (UsuarioVO u : usuarios) {
                row = sheet.createRow(rowNum);
                row.createCell(0).setCellValue(u.getNif());
                row.createCell(1).setCellValue(u.getNombre());
                row.createCell(2).setCellValue(u.getApellido1());
                row.createCell(3).setCellValue(u.getApellido2());
                row.createCell(4).setCellValue(u.getRol().getRol());
                //row.createCell(5).setCellValue(u.getFechaAlta());
                
                cell = row.createCell(5);
 		cell.setCellStyle(cellStyle);
            	cell.setCellValue(u.getFechaAlta());
 	                
                rowNum++;
            }
            adjustWidths(sheet, 6);
        }

    }

    private String getMessage(String key) {
        return getMessage(key, null);
    }

    private String getMessage(String key, Object[] args) {
        return getMessageSourceAccessor().getMessage(key, args);
    }

    private void adjustWidths(Sheet sheet, int numColumns) {
        for (int i = 0; i < numColumns; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}
