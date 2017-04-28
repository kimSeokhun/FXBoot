<%@ tag language="java" pageEncoding="UTF-8" body-content="empty"%>
<%@ attribute name="number" required="true" description="페이지 번호" %>
<%@ attribute name="size" required="true" description="페이지당 데이터 수" %>
<%@ attribute name="count" required="true" description="foreach count 값" %>

${number * size + count}

