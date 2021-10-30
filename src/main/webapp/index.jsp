<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="ru_RU" scope="session" />
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>

    <script src="js/global/load_mark.js" ></script>
    <script src="js/global/load_model.js" ></script>
    <script src="js/index/filter.js" ></script>

    <title>car_trade</title>
</head>
<body>
<div class="container">
    <div class="row">
        <ul class="nav">
            <c:if test="${user != null}">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/adv.do?id=0">Добавить обьявление</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/logout.do">
                        <c:out value="${user.name}"/> | Выйти</a>
                </li>
            </c:if>
            <c:if test="${user == null}">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp">Войти</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/reg.jsp">Зарегистрироваться</a>
                </li>
            </c:if>
        </ul>
    </div>

    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <form id="formFilter">
                    <div style="position: relative;" class="form-row align-items-center">
                        <div style="width: 25%;" class="col-auto my-1" >
                            <label for="markSelection">Марка</label>
                            <select class="form-control" name="mark" id="markSelection" onchange="loadModel();">
                                <option selected disabled value="">Выберите марку</option>
                            </select>
                        </div>

                        <div style="width: 25%;" class="col-auto my-1">
                            <label for="modelSelection">Модель</label>
                            <select class="form-control" name="model" id="modelSelection">
                                <option selected disabled value="">Выберите модель</option>
                            </select>
                        </div>

                        <div style="width: 30%;" class="col-auto my-1">
                            <label for="range">Цена</label>
                            <div id="range">
                                <input style="width: 80%" type="number" name="min" min="0" max="9999999999" placeholder="от" id="min" >
                                <br>
                                <input style="width: 80%" type="number" name="max" min="0" max="9999999999" placeholder="до" id="max">
                            </div>
                        </div>
                        <button style="position: absolute; right: 0; bottom: 0;" type="reset" id="btn" onclick="filter()"
                                class="btn btn-primary">Применить</button>
                    </div>
                </form>
            </div>
            <div class="card-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Автор</th>
                        <th scope="col">Описание</th>
                        <th scope="col">Марка</th>
                        <th scope="col">Модель<br>Тип Кузова</th>
                        <th scope="col">Цена</th>
                        <th scope="col">Фото</th>
                        <th scope="col">Дата публикации</th>
                        <th scope="col">Статус</th>
                    </tr>
                    </thead>
                    <tbody id="advBody">
                    <c:forEach items="${advertisements}" var="adv">
                        <tr>
                            <td>
                                <c:if test="${user != null and user.id == adv.user.id}">
                                    <a href='<c:url value="/adv.do?id=${adv.id}"/>'>
                                        <i class="fa fa-edit mr-3"></i>
                                    </a>
                                </c:if>
                                <c:out value="${adv.user.name}"/>
                            </td>
                            <td>
                                <c:out value="${adv.description}"/>
                            </td>
                            <td>
                                <c:out value="${adv.car.mark.name}"/>
                            </td>
                            <td>
                                <c:out value="${adv.car.model.name}"/><br>
                                <c:out value="${adv.car.model.body.name}"/>
                            </td>
                            <td>
                                <c:out value="${adv.price}"/>
                            </td>
                            <td>
                                <c:forEach items="${adv.photos}" var="photo">
                                <label>
                                    <img src="<c:url value='/download.do?name=${photo.name}.jpg'/>"
                                         width="150px" height="150px"/>
                                </label>
                                </c:forEach>
                            </td>
                            <td>
                                <fmt:formatDate value="${adv.created}" dateStyle="SHORT" />
                            </td>
                            <td>
                                <c:if test="${adv.sold}">
                                    Продано
                                </c:if>
                                <c:if test="${!adv.sold}">
                                    Актуально
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>