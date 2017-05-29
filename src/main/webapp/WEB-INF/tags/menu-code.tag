<%@ tag import="org.apache.commons.lang3.StringUtils" %>
<%@ tag import="java.util.List" %>
<%@ tag import="com.flexink.common.utils.MenuUtils" %>
<%@ tag import="com.flexink.domain.menu.Menu" %>
<%@ tag language="java" pageEncoding="UTF-8" body-content="empty" %>
<%@ attribute name="menuGrpCd" required="true" %>

<%-- <%
    List<Menu> commonCodes = MenuUtils.get(menuGrpCd);

%>
 --%>
<%=MenuUtils.get(menuGrpCd)%>