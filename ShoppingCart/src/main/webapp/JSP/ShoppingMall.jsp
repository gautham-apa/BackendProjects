<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Shopping Mall</title>
    </head>
    <body>
        <h1>Shopping Mall</h1>
        <form>
            <table>
                <tr>
                    <td>
                        <button type="submit" name="category" value="books">Books</button>
                    </td>
                    <td>
                        <button type="submit" name="category" value="music">Music</button>
                    </td>
                    <td>
                        <button type="submit" name="category" value="computers">
                            Computers
                        </button>
                    </td>
                    <td>
                        <button type="submit" name="category" value="cart">Cart</button
                        ><br />
                    </td>
                </tr>
            </table>
        </form>
        <br/>
        <c:if test = "${action != null}">
            <form method="post">
                <c:forEach items="${pageProductList}" var="item">
                    <input type="checkbox" name="item" id="${item.id}" value="${item.id}">
                    <label for="${item.name}">${item.name}</label><br/>
                </c:forEach>
                <c:choose>
                    <c:when test = "${pageProductList.size() == 0}">
                        <p>Cart is empty!</p>
                    </c:when>
                    <c:when test = "${action == 'add'}">
                        <br/><button type="submit">Add Items</button>
                        <input type="hidden" id="action" name="action" value="add">
                    </c:when>
                    <c:when test = "${action == 'remove'}">
                        <br/><button type="submit">Remove Items</button>
                        <input type="hidden" id="action" name="action" value="remove">
                    </c:when>
                </c:choose>
            </form>
        </c:if>
    </body>
</html>
