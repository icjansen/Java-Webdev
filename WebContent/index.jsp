<jsp:useBean id = "userBean" class = "beans.UserBean" scope="session" />
<!-- toont de header bovenaan de pagina -->
<%@ include file="WEB-INF/jsps/header.jspf"%>
<!-- toont de gebruikersnaam die in sessie bekend is -->
<%= "Welkom " + userBean.getUserName() %>
<!-- toont de footer onderaan de pagina -->
<%@ include file="WEB-INF/jsps/footer.jspf"%>