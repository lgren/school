<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css"/>
</head>
<body>
<div class="container">
    <div class="row">
        <input type="button" onclick="startProcess()" class="btn btn-default" value="触发请假流程"/>
    </div>
<c:if test="${task.isSuccess() && task.getData() != null}">
    <div class="row" style="margin-bottom: 20px">
        <div class="row">
            <div class="col-xs-3" style="height: 150px">
                <h3>任务信息</h3>
                <span>任务ID:</span><span>${task.getData().get("task").get("id")}</span><br>
                <span>任务属于:</span><span>${task.getData().get("task").get("assignee")}</span><br>
                <span>任务创建时间:</span><span><fmt:formatDate value="${task.getData().get('task').get('createTime')}" pattern="yyyy-MM-dd HH:mm:ss"/></span><br>
            </div>
            <c:if test="${task.getData().get('variables') != null && task.getData().get('variables').size() > 0}">
                <div class="col-xs-3">
                    <h3>参数信息</h3>
                    <c:forEach var="key" items="${task.getData().get('variables').keySet()}">
                        <span>${key}:</span><span>${task.getData().get('variables').get(key)}</span><br>
                    </c:forEach>
                </div>
            </c:if>
        </div>
        <form action="taskComplete.do" method="put" class="row form-inline">
            <div class="form-group">
                <c:choose>
                    <c:when test="${task.getData().get('task').get('assignee').equals('张三')}">
                        <div class="form-group">
                            <label for="description${task.getData().get("task").get("id")}"
                                   class="control-label">请假原因:</label>
                            <input id="description${task.getData().get("task").get("id")}" name="description"
                                   type="text" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="beginTime${task.getData().get("task").get("id")}"
                                   class="control-label">开始时间:</label>
                            <input id="beginTime${task.getData().get("task").get("id")}" name="beginTime" type="date"
                                   class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="endTime${task.getData().get("task").get("id")}"
                                   class="control-label">结束时间:</label>
                            <input id="endTime${task.getData().get("task").get("id")}" name="endTime" type="date"
                                   class="form-control"/>
                        </div>
                    </c:when>
                    <c:when test="${task.getData().get('task').get('assignee').equals('李四')}">
                        <div class="form-group">
                            <label for="errorMsg${task.getData().get("task").get("id")}"
                                   class="control-label">给予意见:</label>
                            <input id="errorMsg${task.getData().get("task").get("id")}" name="errorMsg" type="text"
                                   class="form-control"/>
                        </div>
                    </c:when>
                </c:choose>
                <input type="button" onclick="complete(this,${task.getData().get("task").get("id")})" value="提交"
                       class="btn btn-default"/>
            </div>
        </form>
    </div>
</c:if>
<c:if test="${taskList.isSuccess() && taskList.getData() != null && taskList.getData().size() > 0}">
<c:forEach var="taskVar" items="${taskList.getData()}">
<div class="row" style="margin-bottom: 20px">
    <div class="row">
        <div class="col-xs-3" style="height: 150px">
            <h3>任务信息</h3>
            <span>任务ID:</span><span>${taskVar.get("task").get("id")}</span><br>
            <span>任务属于:</span><span>${taskVar.get("task").get("assignee")}</span><br>
            <span>任务创建时间:</span><span>${taskVar.get('task').get('createTime')}</span><br>
        </div>
        <c:if test="${taskVar.get('variables') != null && taskVar.get('variables').size() > 0}">
            <div>
                <h3>参数信息</h3>
                <c:forEach var="key" items="${taskVar.get('variables').keySet()}">
                    <span>${key}:</span><span>${taskVar.get('variables').get(key)}</span><br>
                </c:forEach>
            </div>
        </c:if>
    </div>
        <form action="taskComplete.do" method="put" class="row form-inline">
            <div class="form-group">
                <c:choose>
                    <c:when test="${taskVar.get('task').get('assignee').equals('张三')}">
                        <div class="form-group">
                            <label for="description${taskVar.get("task").get("id")}" class="control-label">请假原因:</label>
                            <input id="description${taskVar.get("task").get("id")}" name="description" type="text"
                                   class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="beginTime${taskVar.get("task").get("id")}" class="control-label">开始时间:</label>
                            <input id="beginTime${taskVar.get("task").get("id")}" name="beginTime" type="date"
                                   class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="endTime${taskVar.get("task").get("id")}" class="control-label">结束时间:</label>
                            <input id="endTime${taskVar.get("task").get("id")}" name="endTime" type="date"
                                   class="form-control"/>
                        </div>
                    </c:when>
                    <c:when test="${taskVar.get('task').get('assignee').equals('李四')}">
                        <div class="form-group">
                            <label for="errorMsg${taskVar.get("task").get("id")}" class="control-label">给予意见:</label>
                            <input id="errorMsg${taskVar.get("task").get("id")}" name="errorMsg" type="text"
                                   class="form-control"/>
                        </div>
                    </c:when>
                </c:choose>
                <input type="button" onclick="complete(this,${taskVar.get("task").get("id")})" value="提交"
                       class="btn btn-default"/>
            </div>
        </form>
    </div>
    </c:forEach>
    </c:if>
</div>
    <script src="/webjars/jquery/3.1.0/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        function startProcess() {
            $.ajax({
                url: "/activitiCore.do/startProcess?param=leave",
                type: "get",
                success: function (data) {
                    console.log(data);
                    window.location.reload();
                }
            })
        }
        function complete(node, taskId) {
            var $node = $(node).parent().parent();
            $.ajax({
                url: "/taskComplete.do",
                type: "put",
                data: {
                    "taskId": taskId,
                    "endTime": $node.find("#endTime" + taskId).val(),
                    "errorMsg": $node.find("#errorMsg" + taskId).val(),
                    "beginTime": $node.find("#beginTime" + taskId).val(),
                    "description": $node.find("#description" + taskId).val()
                },
                success: function (data) {
                    console.log(data);
                    window.location.reload();
                }
            })
        }
    </script>
</body>
</html>
