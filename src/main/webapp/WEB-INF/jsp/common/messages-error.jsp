<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<%@include file="/WEB-INF/jsp/common/common.jsp" %>
<c:if test="${!empty globalError}">
    <div class="alert alert-danger">
        <c:out value="${globalError}"/><br/>
        <spring:hasBindErrors name="${param.beanName}">
            <spring:bind path="${param.beanName}.*">
                <c:forEach var="error" items="${status.errorMessages}">
                    <c:out value="${error}"/><br/>
                </c:forEach>
            </spring:bind>
        </spring:hasBindErrors>
    </div>
</c:if>
<c:if test="${empty globalError}">
    <spring:hasBindErrors name="${param.beanName}">
        <div class="alert alert-danger">
            <spring:bind path="${param.beanName}.*">
                <c:forEach var="error" items="${status.errorMessages}">
                    <c:out value="${error}"/><br/>
                </c:forEach>
            </spring:bind>
        </div>
    </spring:hasBindErrors>	        
</c:if>