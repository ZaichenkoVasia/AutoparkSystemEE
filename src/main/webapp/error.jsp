<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/includes/header.jsp"/>
<c:set var="lang" value="${empty lang ? 'uk_UA' : sessionScope.lang}" scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/messages"/>
<div class="col-center">
    <h3><fmt:message key="something.wrong"/></h3>
    <img src="img/error.jpg" alt="Error"/>
</div>
