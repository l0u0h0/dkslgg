// User API (Axios)
import { auth, common } from './api';
// Swal
import Swal from 'sweetalert2';

// 회원가입 API
const register = async (data) => {
  try {
    const response = await common.post(
      '/member/register',
      JSON.stringify(data)
    );

    return response;
  } catch (error) {
    Swal.fire('Error', error.response.data, 'error');
  }
};

// 로그인 API
const signIn = async (data) => {
  try {
    const response = await common.post('/member/login', JSON.stringify(data));

    if (response.status == 200) {
      sessionStorage.setItem('accessToken', response.data.accessToken);
      localStorage.setItem('refreshToken', response.data.refreshToken);
    }

    return response;
  } catch (error) {
    Swal.fire('Error', error.response.data, 'error');
  }
};

// 로그아웃 API
const signout = async () => {
  try {
    const response = await auth.post('/member/logout');

    if (response.data) {
      sessionStorage.clear();
      localStorage.clear();
    }

    return response;
  } catch (error) {
    Swal.fire('Error', error.response.data, 'error');
  }
};

// 현재 로그인 된 유저 정보 API
const getMember = async () => {
  try {
    const response = await auth.get('/member');
    return response;
  } catch (error) {
    console.log(error.response);
    sessionStorage.removeItem('accessToken');
  }
};

// Access 만료 시 재요청 토큰
const reAccessToken = async (refreshToken) => {
  try {
    const response = await common.get('/member/reissue', {
      headers: {
        Authorization: `Bearer ${refreshToken}`,
      },
    });
    if (response.status != 200) throw new Error('토큰 재발급 실패');
    return response;
  } catch (error) {
    console.log(error.response);
    localStorage.removeItem('refreshToken');
  }
};

export { register, signIn, signout, getMember, reAccessToken };
