<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<ul class="nav nav-pills nav-app">
    <li>
        <a href="<c:url value='/home.htm'/>"><span class="glyphicon glyphicon-home"></span></a>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"><fmt:message key="menu.1.label"/> <b class="caret"></b></a>
        <ul class="dropdown-menu dropdown-menu-app">
            <li><a href="${ctxAdministracion}/usuarioSearchLoad.htm"><fmt:message key="submenu.1.1.label"/></a></li>
        </ul>
    </li>
</ul>