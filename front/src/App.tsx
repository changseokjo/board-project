import React, { useState } from 'react';
import './App.css';
import { commentListMock } from 'mocks';
import CommentListItem from 'components/CommentListItem';
import Footer from 'layouts/Footer';

function App() {
  return (
    <Footer />
  );
}

export default App;

// ! 네비게이션 설계
// ! 메인 화면 : '/' - Main
// ! 로그인 화면 + 회원가입 화면 : '/auth' - Autentication
// ! 검색 화면 : '/search/:word' - Search
// ! 게시물 상세 보기 화면 : '/boadr/detail/:boardNumber' - BoardDetail
// ! 게시물 작성 화면 : '/board/write' - BoardWrite
// ! 게시물 수정 화면 : '/board/update/:boardNumber' - BoardUpdate
// ! 유저 게시물 화면 : '/user/:email' - User
