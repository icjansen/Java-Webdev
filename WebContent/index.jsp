<jsp:useBean id = "userBean" class = "beans.UserBean" scope="session" />
<%@ include file="WEB-INF/jsps/header.jspf"%>
<%= userBean.getUserName() %>
<%@ include file="WEB-INF/jsps/footer.jspf"%>