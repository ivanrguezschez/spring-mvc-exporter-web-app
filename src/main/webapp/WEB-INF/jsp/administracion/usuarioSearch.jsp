<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<script type="text/javascript">
    $(document).ready(function () {
        $('#nif').focus();
        $('#buttonSearch').click(function () {
            $('form').submit();
        });
        $('.date').datepicker({
            autoclose: true,
            todayHighlight: true,
            language: "es"
        });

    });
</script>
<div class="row row-heading-app">
    <div class="col-md-12">
        <h4 class="text-primary text-uppercase"><fmt:message key="header.administracion"/> <small><fmt:message key="header.usuarios"/></small></h4>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/common/messages-error.jsp" flush="true">
    <jsp:param name="beanName" value="usuarioSearchCommand"/>
</jsp:include>
<jsp:include page="/WEB-INF/jsp/common/messages-check.jsp"/>
            
<div class="panel panel-info panel-table">
    <div class="panel-heading">
        <a class="state" data-toggle="collapse" href="#collapseSearchCriteria"></a>
        <fmt:message key="panel.header.search"/>
        <div class="pull-right">
            <button id="buttonSearch" class="btn btn-xs btn-default"><span class="glyphicon glyphicon-search"></span> <fmt:message key="button.buscar"/></button>
        </div>
    </div>	
    <div id="collapseSearchCriteria" class="panel-collapse collapse in">
        <div class="panel-body">
            <form:form role="form" action="${ctxAdministracion}/usuarioSearch.htm" commandName="usuarioSearchCommand">	
                <div class="row">
                    <div class="col-md-2">
                        <div class="form-group form-group-sm">
                            <label for="nif" class="control-label"><fmt:message key="label.nif"/></label>
                            <form:input path="nif" maxlength="9" size="8" class="form-control input-sm" />
                        </div>
                    </div>
                    <div class="col-md-2">					  			
                        <div class="form-group form-group-sm">
                            <label for="nombre" class="control-label"><fmt:message key="label.nombre"/></label>
                            <form:input path="nombre" maxlength="50" size="50" class="form-control input-sm"/>
                        </div>	
                    </div>
                    <div class="col-md-3">
                        <div class="form-group form-group-sm">
                            <label for="apellido1" class="control-label"><fmt:message key="label.apellido1"/></label>
                            <form:input path="apellido1" maxlength="50" size="50" class="form-control input-sm"/>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group form-group-sm">
                            <label for="apellido2" class="control-label"><fmt:message key="label.apellido2"/></label>
                            <form:input path="apellido2" maxlength="50" size="50" class="form-control input-sm"/>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="form-group form-group-sm">
                            <label for="rol" class="control-label"><fmt:message key="label.rol"/></label>
                            <form:select path="rol" class="form-control input-sm">
                                <form:option value="" label="${maskSelect}"/>
                                <form:options items="${roles}" itemValue="idRol" itemLabel="rol"/>
                            </form:select>
                        </div>	
                    </div>
                </div>      
            </form:form>
        </div>
    </div>
</div>
<c:if test="${usuarioList != null}">
    <div class="panel panel-info">
        <div class="panel-heading">
            <a class="state" data-toggle="collapse" href="#collapseSearchResult"></a>
            <fmt:message key="panel.header.result"/>
        </div>
        <div id="collapseSearchResult" class="panel-collapse collapse in">
            <div class="panel-body">
                <c:if test="${not empty usuarioList.searchResult.result}">
                    <table class="table table-bordered table-striped table-hover table-condensed table-app">
                        <thead class="bg-primary">
                            <tr>
                                <th width="60px"><fmt:message key="table.head.label.nif"/></th>
                                <th><fmt:message key="table.head.label.nombre"/></th>
                                <th><fmt:message key="table.head.label.apellido1"/></th>
                                <th><fmt:message key="table.head.label.apellido2"/></th>
                                <th width="100px"><fmt:message key="table.head.label.rol"/></th>
                                <th width="90px"><fmt:message key="table.head.label.fechaAlta"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${usuarioList.searchResult.result}" var="usuario" varStatus="status">
                                <tr>
                                    <td><c:out value="${usuario.nif}"/></td>   	
                                    <td><c:out value="${usuario.nombre}"/></td>
                                    <td><c:out value="${usuario.apellido1}"/></td>
                                    <td><c:out value="${usuario.apellido2}"/></td>
                                    <td><c:out value="${usuario.rol.rol}"/></td>
                                    <td><fmt:formatDate value="${usuario.fechaAlta}" pattern="${patternDate}"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <c:url var="pagedLink" value="usuarioSearch.htm">
			<c:param name="p" value="~"/>
                    </c:url>
                    <springmvcexporterwebapp:pagination searchOutput="${usuarioList}" pagedLink="${pagedLink}" exportLink="usuarioListExporter.htm" />
                </c:if>
                <c:if test="${empty usuarioList.searchResult.result}">  
                    <div class="alert alert-warning"><fmt:message key="info.message.notfound.usuario"/></div>	   
                </c:if>
            </div>
        </div>		
    </div>
</c:if>