<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@tag import="org.springframework.util.StringUtils"%>
<%@attribute name="searchOutput" required="true" type="com.irs.springmvcexporterwebapp.negocio.servicios.support.SearchOutput"%>
<%@attribute name="pagedLink" required="true" type="java.lang.String"%>
<%@attribute name="pdfLink" required="false" type="java.lang.String"%>
<%@attribute name="xlsLink" required="false" type="java.lang.String"%>
<%@attribute name="exportLink" required="false" type="java.lang.String"%>
<div class="row">
    <div class="col-md-6">
        <div class="pagination pagination-sm" style="margin: 0px !important;">
            <%--
            <c:if test="${not empty pdfLink}">
                <li><a class="btn btn-xs" href="${pdfLink}" target="_blank" title="<fmt:message key='icon.export.pdf'/>"><span class="fa fa-file-pdf-o fa-lg alert-danger" aria-hidden="true"></span></a></li>
            </c:if>
            <c:if test="${not empty xlsLink}">
                <li><a class="btn btn-xs" href="${xlsLink}" target="_blank" title="<fmt:message key='icon.export.xls'/>"><span class="fa fa-file-excel-o fa-lg alert-success" aria-hidden="true"></span></a></li>
            </c:if>
            --%>
            <span class="btn-group" role="group">
                <button type="button" class="btn btn-sm btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <fmt:message key="button.exportar"/>
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="${exportLink}?format=pdf" class="btn-sm" target="_blank"><span class="fa fa-file-pdf-o fa-lg alert-danger" aria-hidden="true"></span> PDF</a></li>
                    <li><a href="${exportLink}?format=xsltfo" class="btn-sm" target="_blank"><span class="fa fa-file-pdf-o fa-lg alert-danger" aria-hidden="true"></span> XLST FO</a></li>
                    <li><a href="${exportLink}?format=xls" class="btn-sm" target="_blank"><span class="fa fa-file-excel-o fa-lg alert-success" aria-hidden="true"></span> XLS</a></li>
                    <li><a href="${exportLink}?format=csv" class="btn-sm" target="_blank"><span class="fa fa-file-text-o fa-lg" aria-hidden="true"></span> CSV</a></li>
                    <li><a href="${exportLink}?format=json" class="btn-sm" target="_blank"><span class="fa fa-file-o fa-lg" aria-hidden="true"></span> JSON</a></li>
                    <li><a href="${exportLink}?format=xml" class="btn-sm" target="_blank"><span class="fa fa-file-code-o fa-lg" aria-hidden="true"></span> XML</a></li>
                </ul>
            </span>
            <span class="pagination-app-text">
                <fmt:message key="pagination.sumary">
                    <fmt:param>${searchOutput.pageNumber}</fmt:param>
                    <fmt:param>${searchOutput.totalPages}</fmt:param>
                    <fmt:param>${searchOutput.total}</fmt:param>
                </fmt:message>
            </span>
        </div>  
    </div>		
    <div class="col-md-6">
        <c:if test="${searchOutput.totalPages > 1}">
            <ul class="pagination pagination-sm pull-right" style="margin: 0px !important;">
                <c:if test="${searchOutput.hasFirstPage}">
                    <li><a href="<%= StringUtils.replace(pagedLink, "~", String.valueOf(1)) %>"><span class="glyphicon glyphicon-step-backward" aria-hidden="true"></span></a></li>
                    <li><a href="<%= StringUtils.replace(pagedLink, "~", String.valueOf(searchOutput.getPreviousPage())) %>"><span class="glyphicon glyphicon-backward" aria-hidden="true"></span></a></li>
		</c:if>
                <c:if test="${!searchOutput.hasFirstPage}">
                    <li class="disabled"><a href="#"><span class="glyphicon glyphicon-step-backward" aria-hidden="true"></span></a></li>
                    <li class="disabled"><a href="#"><span class="glyphicon glyphicon-backward" aria-hidden="true"></span></a></li>
		</c:if>
		<c:if test="${searchOutput.firstLinkedPage > 1}">
                    <li class="disabled"><a href="#"><span>...</span></a></li>
		</c:if>
		<c:forEach begin="${searchOutput.firstLinkedPage}" end="${searchOutput.lastLinkedPage}" var="i">
                    <c:choose>
                        <c:when test="${searchOutput.pageNumber == i}">
                            <li class="active"><a href="#">${i} <span class="sr-only">(current)</span></a></li>
			</c:when>
			<c:otherwise>
                            <li><a href="<%= StringUtils.replace(pagedLink, "~", String.valueOf(jspContext.getAttribute("i"))) %>">${i}</a></li>
			</c:otherwise>
                    </c:choose>
		</c:forEach>
		<c:if test="${searchOutput.lastLinkedPage < searchOutput.totalPages}">
                    <li class="disabled"><a href="#"><span>...</span></a></li>
		</c:if>
		<c:if test="${searchOutput.hasLastPage}">
                    <li><a href="<%= StringUtils.replace(pagedLink, "~", String.valueOf(searchOutput.getNextPage())) %>"><span class="glyphicon glyphicon-forward" aria-hidden="true"></span></a></li>
                    <li><a href="<%= StringUtils.replace(pagedLink, "~", String.valueOf(searchOutput.getTotalPages())) %>"><span class="glyphicon glyphicon-step-forward" aria-hidden="true"></span></a></li>
		</c:if>
                <c:if test="${!searchOutput.hasLastPage}">
                    <li class="disabled"><a href="#"><span class="glyphicon glyphicon-forward" aria-hidden="true"></span></a></li>
                    <li class="disabled"><a href="#"><span class="glyphicon glyphicon-step-forward" aria-hidden="true"></span></a></li>
                </c:if>
            </ul>
	</c:if>
    </div>
</div>