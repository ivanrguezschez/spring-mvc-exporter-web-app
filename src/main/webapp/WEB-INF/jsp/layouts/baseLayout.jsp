<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
         <title><fmt:message key="title.springmvcexporterwebapp"/></title>
        <link href="${cssPath}bootstrap.min.css" rel="stylesheet">
        <link href="${cssPath}font-awesome.min.css" rel="stylesheet">
        <link href="${cssPath}bootstrap-datepicker.min.css" rel="stylesheet">
        <link href="${cssPath}springmvcexporterwebapp.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="${jsPath}jquery-1.11.3.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="${jsPath}bootstrap.min.js"></script>
        <script src="${jsPath}bootstrap-datepicker.min.js"></script>
        <script src="${jsPath}bootstrap-datepicker.es.min.js"></script>
        <script type="text/javascript" src="${jsPath}springmvcexporterwebapp.js"></script>
    </head>
    <body>
        <div class="container">
            <tiles:insertAttribute name="header"/>
            <tiles:insertAttribute name="menu"/>
            <tiles:insertAttribute name="body"/>
        </div>
        <tiles:insertAttribute name="footer"/>
        <jsp:include page="/WEB-INF/jsp/common/loader.jsp"/>
    </body>
</html>