import React, { useState } from 'react';
import './style.css';
import InputBox from 'components/InputBox';

//      component: 인증 페이지      //
export default function Authentication() {

  const SignInCard = () => {
    const [email, setEmail] = useState<string>('');
    const [emailError, setEmailError] = useState<boolean>(false);
    const [password, setPassword] = useState<string>('');
    const [passwordError, setPasswordError] = useState<boolean>(true);
    const [passwordErrorMessage, setPasswordErrorMessage] = useState<string>('비밀번호는 8자 이상 입력해주세요.');
    const [passwordType, setpasswordType] = useState<'text' | 'password'>('password');
    const [passwordIcon, setPasswordIcon] = useState<'eye-off-icon' | 'eye-on-icon'>('eye-off-icon');
  
    const onPasswordIconClickHandler = () => {
      if (passwordType === 'text') {
        setpasswordType('password');
        setPasswordIcon('eye-on-icon');
      }
      if (passwordType === 'password') {
        setpasswordType('text');
        setPasswordIcon('eye-off-icon');
      }
    }
  }

  //      render: 인증 페이지 렌더링      //
  return (
    <div id='auth-wrapper'>
      <div className='auth-container'>
        <div className='auth-jumbotron-box'>
          <div className='auth-jumbotron-contents'>
            <div className='jumbotron-icon'></div>
            <div className='auth-jumbotron-text-box'>
              <div className='auth-jumbotron-text'>{'환영합니다.'}</div>
              <div className='auth-jumbotron-text'>{'HOONS BOARD 입니다.'}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
