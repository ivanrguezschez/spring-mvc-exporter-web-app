<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<footer class="footer">
    <div class="container">
        <div class="col-md-6 bg-primary col-left">
            <p><fmt:message key="footer.copyright"/>&nbsp;<script>document.write(new Date().getFullYear());</script>&nbsp;-&nbsp;<fmt:message key="footer.irs"/></p>		
        </div>
        <div class="col-md-6 bg-primary col-right">
            <p class="text-right"><fmt:message key="app.version.prefix"/><fmt:message key="app.version"/></p>
        </div>
    </div>
</footer>