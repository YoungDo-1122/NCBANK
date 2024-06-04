<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<nav class="navbar navbar-expand-md bg-dark navbar-dark fixed-top shadow-lg">
    <a class="navbar-brand" href="${root}/main">
    <img src="${root}/img/NCBank2.png" style="height: 100px"/>
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navMenu">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navMenu">
        <ul class="navbar-nav">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbardrop">
                    계좌
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="${root}/groupAccount/about">계좌1</a>
                    <a class="dropdown-item" href="${root}/groupAccount/create">계좌2</a>
                    <a class="dropdown-item" href="${root}/groupAccount/invite">계좌3</a>
                    <a class="dropdown-item" href="${root}/groupAccount/management">계좌4</a>
                </div>
                </li>
                 <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbardrop">
                    모임통장
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="${root}/groupAccount/about">모임통장이란</a>
                    <a class="dropdown-item" href="${root}/groupAccount/create">모임통장 개설</a>
                    <a class="dropdown-item" href="${root}/groupAccount/invite">모임원 초대하기</a>
                    <a class="dropdown-item" href="${root}/groupAccount/management">모임통장 관리</a>
                </div>
                </li>
                 <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbardrop">
                    환전
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="${root}/groupAccount/about">환전1</a>
                    <a class="dropdown-item" href="${root}/groupAccount/create">환전2</a>
                    <a class="dropdown-item" href="${root}/groupAccount/invite">환전3</a>
                    <a class="dropdown-item" href="${root}/groupAccount/management">환전4</a>
                </div>
               </li>
                 <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbardrop">
                    게시판
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="${root}/groupAccount/about">게시판1</a>
                    <a class="dropdown-item" href="${root}/groupAccount/create">게시판2</a>
                    <a class="dropdown-item" href="${root}/groupAccount/invite">게시판3</a>
                    <a class="dropdown-item" href="${root}/groupAccount/management">게시판4</a>
                </div>
               </li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item"><a href="${root}/user/login" class="nav-link">로그인</a></li>
            <li class="nav-item"><a href="${root}/user/join" class="nav-link">회원가입</a></li>
            <li class="nav-item"><a href="${root}/user/modify" class="nav-link">정보수정</a></li>
            <li class="nav-item"><a href="${root}/user/logout" class="nav-link">로그아웃</a></li>
        </ul>
    </div>
</nav>

<style>
  .nav-item.dropdown .nav-link {
        font-size: 23px;
    }
    .nav-item.dropdown:hover .dropdown-menu {
        display: block;
    }
</style>
