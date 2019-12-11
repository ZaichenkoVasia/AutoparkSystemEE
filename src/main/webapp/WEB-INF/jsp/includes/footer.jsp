<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="lang" value="${empty lang ? 'uk_UA' : sessionScope.lang}" scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/messages"/>
</div>
</div>
</main>

<footer id="footer-site">
    <div>
        <span><fmt:message key="created.by"/></span>
    </div>
</footer>
</body>
</html>

