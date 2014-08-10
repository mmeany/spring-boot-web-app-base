<%@ include file="/WEB-INF/tags/include-for-tags.jsp"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jstl/core_rt" %>
<%@ attribute name="surrounding" required="true" %>
<%@ attribute name="showSearch" required="true" %>

    <div class="pagination pagination-right">
        <c:if test="${not empty page and page.totalPages > 1}">
        <ul class="pagination pagination-right">
            <cr:choose>
                <cr:when test="${page.isFirst()}"><li class="disabled"><a href="javascript:mvmNOP();">|&lt;</a></li></cr:when>
                <cr:otherwise><li><a href="<c:url value=""><c:param name="page" value="0"/><c:param name="size" value="${page.size}"/><c:param name="criteria" value=""/></c:url>" data-ajax-load="#ajaxload" title="first">|&lt;</a></li></cr:otherwise>
            </cr:choose>
            <cr:choose>
                <cr:when test="${page.isFirst()}"><li class="disabled"><a href="javascript:mvmNOP();">&lt;</a></li></cr:when>
                <cr:otherwise><li><a href="<c:url value=""><c:param name="page" value="${page.number - 1}"/><c:param name="size" value="${page.size}"/><c:param name="criteria" value=""/></c:url>" data-ajax-load="#ajaxload" title="prev">&lt;</a></li></cr:otherwise>
            </cr:choose>

	        <c:set var="startPage" value="0"/>
	        <c:set var="endPage" value="0"/>
			<c:choose>
			    <c:when test="${page.totalPages <= (1 + 2 * surrounding)}">
			        <c:set var="endPage" value="${page.totalPages - 1}"/>
			    </c:when>
	            <c:otherwise>
	                <c:set var="startPage" value="${page.number - surrounding}"/>
			        <c:choose>
	                    <c:when test="${startPage < 0}">
	                        <c:set var="startPage" value="0"/>
	                        <c:set var="endPage" value="${2 * surrounding}"/>
	                    </c:when>
		                <c:otherwise>
	                        <c:set var="endPage" value="${page.number + surrounding}"/>
	                        <c:if test="${endPage >= page.totalPages}">
		                        <c:set var="endPage" value="${page.totalPages - 1}"/>
		                        <c:set var="startPage" value="${endPage - (2 * surrounding)}"/>
	                        </c:if>
		                </c:otherwise>
			        </c:choose>
	            </c:otherwise>
			</c:choose>
	
	        <c:forEach var="pn" begin="${startPage + 1}" end="${endPage + 1}">
	            <c:choose>
	                <c:when test="${pn == page.number + 1}"><li class="disabled"><a href="javascript:mvmNOP();">${pn}</a></li></c:when>
	                <c:otherwise><li><a href="<c:url value=""><c:param name="page" value="${pn - 1}"/><c:param name="size" value="${page.size}"/><c:param name="criteria" value=""/></c:url>" data-ajax-load="#ajaxload">${pn}</a></li></c:otherwise>
	            </c:choose>
	        </c:forEach>

            <c:choose>
                <c:when test="${page.number == page.totalPages - 1}"><li class="disabled"><a href="javascript:mvmNOP();">&gt;</a></li></c:when>
                <c:otherwise><li><a href="<c:url value=""><c:param name="page" value="${page.number + 1}"/><c:param name="size" value="${page.size}"/><c:param name="criteria" value=""/></c:url>" data-ajax-load="#ajaxload" title="next">&gt;</a></li></c:otherwise>
            </c:choose>
            <cr:choose>
                <cr:when test="${page.isLast()}"><li class="disabled"><a href="javascript:mvmNOP();">&gt;|</a></li></cr:when>
                <cr:otherwise><li><a href="<c:url value=""><c:param name="page" value="${page.totalPages - 1}"/><c:param name="size" value="${page.size}"/><c:param name="criteria" value=""/></c:url>" data-ajax-load="#ajaxload" title="last">&gt;|</a></li></cr:otherwise>
            </cr:choose>
        </ul>
        </c:if>
        <c:if test="${showSearch == 'true'}">
        <form action="<c:url value=""/>" class="form-search pull-left" method="post">
            <input type="text" name="searchFor" class="input-medium search-query" placeholder="" maxlength="40">
            <button type="submit" class="btn">Search</button>
        </form>
        </c:if>
    </div>
