<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="ru_RU" scope="session" />
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

    <script src="js/global/load_mark.js" ></script>
    <script src="js/global/load_model.js" ></script>
    <script src="js/adv/validate.js" ></script>
    <script src="js/adv/load_photo.js" ></script>
    <script src="js/adv/add_photo.js" ></script>
    <script src="js/adv/after_load_page.js" ></script>

    <title>car trade</title>
</head>

<body>
<div class="container">
    <div class="row">
        <ul class="nav">
            <c:if test="${user != null}">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/logout.do">
                        <c:out value="${user.name}"/> | Выйти</a>
                </li>
            </c:if>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/index.do">На главную</a>
            </li>
        </ul>
    </div>
</div>

<div class="container pt-3">
    <c:if test="${user != null}">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <c:if test="${adv == null}">
                    Новое обьявление.
                </c:if>
                <c:if test="${adv != null}">
                    Редактирование обьявления.
                </c:if>
            </div>

            <div class="card-body">
                <form<c:if test="${adv == null}">
                            action="http://localhost:8080/car_trade/adv.do?id=0"
                        </c:if>
                        <c:if test="${adv != null}">
                            action="http://localhost:8080/car_trade/adv.do?id=${adv.id}"
                        </c:if>
                        method="post">
                    <input type='hidden' id="advId" name='advId'
                    <c:if test="${adv == null}">value="0"</c:if>
                    <c:if test="${adv != null}">value=<c:out value="${adv.id}"/>
                    </c:if>>
                    <div class="form-group">
                        <label for="description">Описание</label>
                        <textarea class="form-control" name="description" id="description"
                        rows="2"><c:if test="${adv != null}"><c:out value="${adv.description}"/></c:if></textarea>
                    </div>

                    <div style="position: relative;" class="form-row align-items-center">
                        <div style="width: 35%;" class="col-auto my-1">
                            <label for="markSelection">Марка</label>
                            <select class="form-control" name="mark" id="markSelection" onchange="loadModel();">
                                <c:if test="${adv != null}">
                                    <option value="${adv.car.mark.id}"><c:out value="${adv.car.mark.name}"/></option>
                                </c:if>
                            </select>
                        </div>

                        <div style="width: 35%;" class="col-auto my-1">
                            <label for="modelSelection">Модель</label>
                            <select class="form-control" name="model" id="modelSelection">
                                <c:if test="${adv != null}">
                                    <option value="${adv.car.model.id}"><c:out value="${adv.car.model.name}"/></option>
                                </c:if>
                            </select>
                        </div>

                        <div style="width: 30%;" class="col-auto my-1">
                            <label for="priceId">Цена</label>
                            <input style="width: 100%"
                            <c:if test="${adv != null}">value="${adv.price}"</c:if>
                            <c:if test="${adv == null}"> value="0"</c:if>
                            type="number" name="price" min="0" max="9999999999" id="priceId" >
                        </div>
                    </div>
                    <br>

                    <div class="form-group">
                        <input type='hidden' name='advPhoto' id='advPhoto' type="text" value=''>
                        <ul class="nav" id="photoList">
                            <c:if test="${adv != null}">
                                <c:forEach items="${adv.photos}" var="photo">
                                    <li value="<c:out value="${photo.name}"/>">
                                        <label style="width: 180px;">
                                            <img src="<c:url value='/download.do?name=${photo.name}.jpg'/>"
                                                 width="150px" height="150px"/>
                                        </label>
                                    </li>
                                </c:forEach>
                            </c:if>
                        </ul>
                    </div>

                    <div class="form-group form-check">
                        <br>
                        <label class="form-check-label" for="checkbox1">Продано</label>
                        <input type="checkbox" class="ui-corner-right" name="status" id="checkbox1"
                        <c:if test="${adv != null && adv.sold}">
                               checked="checked"
                        </c:if>
                        >
                    </div>
                    <br>
                    <button type="submit" class="btn btn-primary" onclick="return validate();">Сохранить</button>
                </form>
            </div>

            <hr align="left" width="300" size="4"/>
            <div class="card-body">
                <form id="formPhotoAction" action="" method="post">
                    <label for="image_file">Добавление фото</label>
                    <div class="checkbox">
                        <input type="file" name="image_file" id="image_file">
                    </div>
                    <br>
                    <button type="button" class="btn btn-primary" onclick="addPhoto()">Добавить</button>
                    <br>
                </form>
            </div>
        </div>
    </div>
    </c:if>
</div>
</body>
</html>