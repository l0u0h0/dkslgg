// Axios
import { common, auth } from './api.js';
// Swal
import Swal from 'sweetalert2';

const getGroupList = async () => {
  try {
    const response = await common.get('/team');
    if (response.status != 200) new Error('서버 오류');
    return response.data;
  } catch (error) {
    console.log(error);
  }
};

const setNewGroup = async (formData) => {
  try {
    const response = await auth.post('/team/create', formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });

    if (response.status != 200) return null;

    return response;
  } catch (error) {
    Swal.fire('Error', error.response.data, 'error');
  }
};

const searchGroup = async (word) => {
  try {
    const response = await common.get(`/team/search?word=${word}`);

    return response;
  } catch (error) {
    Swal.fire('Error', error.response.data, 'error');
  }
};

const groupDetail = async (name, hasToken) => {
  console.log('service in : ', name, hasToken);
  try {
    if (hasToken) {
      const response = await auth.get(`/team/${name}`);
      return response;
    } else {
      const response = await common.get(`/team/${name}`);
      return response;
    }
  } catch (error) {
    Swal.fire('Error', error.response.data, 'error');
  }
};

const joinGroup = async (data) => {
  try {
    const response = await auth.post('/team/join', JSON.stringify(data));
    return response.data;
  } catch (error) {
    Swal.fire('Error', error.response.data, 'error');
  }
};

const groupLeave = async (name) => {
  try {
    const response = await auth.post('/team/leave', JSON.stringify(name));
    return response.data;
  } catch (error) {
    Swal.fire('Error', error.response.data, 'error');
  }
}

const getSummonerGroup = async (name) => {
  try {
    const response = await common.get(`/summoner/team/${name}`);
    return response;
  } catch (error) {
    console.error(error.response.data);
    return 'NoData';
  }
}

export { getGroupList, setNewGroup, searchGroup, groupDetail, groupLeave, getSummonerGroup, joinGroup };
