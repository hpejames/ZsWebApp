<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% String contextPath = request.getContextPath(); %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>永兴高大乐队</title>
    <link href="<%=contextPath%>/css/video-js.css" rel="stylesheet">
    <style>
        body{background-color: #191919}
        .m{ width: 740px; height: 400px; }
    </style>
</head>

<body>
<div class="m">
    <video id="my-video" class="video-js" controls preload="auto" width="315" height="550"
           poster="m.png" data-setup="{}">
        <source src="<%=contextPath%>/vedio/gaoda.mp4" type="video/mp4">
        <p class="vjs-no-js">
            To view this video please enable JavaScript, and consider upgrading to a web browser that
            <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a>
        </p>
    </video>
    <script src="<%=contextPath%>/js/video.min.js"></script>
    <script type="text/javascript">
        var myPlayer = videojs('my-video');
        videojs("my-video").ready(function(){
            var myPlayer = this;
            myPlayer.play();
        });
    </script>
</div>

</body>
</html>
