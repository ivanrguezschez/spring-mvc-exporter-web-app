<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<%@include file="/WEB-INF/jsp/common/common.jsp" %>
<div class="cover">
    <div class="panel panel-danger">
        <div class="panel-heading"><fmt:message key="panel.header.error"/></div>
        <div class="panel-body">
            <div class="row">
                <div class="col-md-12">
                    <p><fmt:message key="error.message.general"/></p>
                </div>
            </div>
            <div class="text-right">
                <a href="<c:url value='/home.htm'/>" class="btn btn-sm btn-danger"><span class="glyphicon glyphicon-home"></span> <fmt:message key="button.home"/></a>
            </div>
        </div>
    </div>
</div>