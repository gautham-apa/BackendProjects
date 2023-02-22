<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
  <script type="text/javascript">
    alert("${successMessage}");
    location = "store?category=cart";
  </script>
</html>
